
package dao;

import connection.ConexaoBanco;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;


public class UsuarioDao {

    public void cadastroUsuario(Usuario user){
        String command = "INSERT INTO usuario (nomeUsuario, senha, nivel, dataCadastro) VALUES (?,?,?,?)";
        
        Connection con = null;
        PreparedStatement testamento = null;
        
        try{
            con = ConexaoBanco.conectarBanco();
            testamento = con.prepareStatement(command);
            
            testamento.setString(1, user.getNomeUsuario());
            testamento.setString(2, user.getSenha());
            testamento.setString(3, user.getNivel());
            testamento.setDate(4, java.sql.Date.valueOf(user.getDataCadastro()));
            
            testamento.executeUpdate();
        }
        catch(SQLIntegrityConstraintViolationException e){
            System.out.println("CPF ou email já cadastrado");
        }
        catch (SQLException e){
            System.out.println("Erro: "+e.getMessage());
        }
        finally{
            ConexaoBanco.desconectarBanco(con, testamento);
        }
        
    }
    
    public List<Usuario> buscarTudo(){
        List<Usuario> lista = new ArrayList<>();
        
        String command = "SELECT codUsuario, nomeUsuario, nivel, dataCadastro FROM usuario";
        Connection con = null;
        PreparedStatement testamento = null;
        
        try{
            con = ConexaoBanco.conectarBanco();
            testamento = con.prepareStatement(command);
            ResultSet rs = testamento.executeQuery();
            
            while(rs.next()){
                Usuario user = new Usuario();
                
                user.setCodUsuario(rs.getInt("codUsuario"));
                user.setNomeUsuario(rs.getString("nomeUsuario"));
                user.setNivel(rs.getString("nivel"));
                LocalDate data = rs.getDate("dataCadastro").toLocalDate();
                if(data != null){
                    user.setDataCadastro(data);
                }
                
                lista.add(user);
            }
        }
        catch(SQLException e){
            System.out.println("Erro: "+e.getMessage());
        }
        finally{
            ConexaoBanco.desconectarBanco(con, testamento);
        }
        
        return lista;
    }
}
