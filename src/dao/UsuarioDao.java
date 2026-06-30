
package dao;

import connection.ConexaoBanco;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


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
}
