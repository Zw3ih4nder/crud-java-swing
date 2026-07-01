
package dao;

import connection.ConexaoBanco;
import model.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class AlunoDao {
    
    public void cadastrarAluno(Aluno al){
        String command = "INSERT INTO aluno (nomeAluno, cpf, email, rg, dataNasc) VALUES (?,?,?,?,?)";
        
        Connection con = null;
        PreparedStatement testamento = null;
        
        try{
            con = ConexaoBanco.conectarBanco();
            testamento = con.prepareStatement(command);
            
            testamento.setString(1, al.getNomeAluno());
            testamento.setString(2, al.getCpf());
            testamento.setString(3, al.getEmail());
            testamento.setString(4, al.getRg());
            testamento.setDate(5, java.sql.Date.valueOf(al.getDataNasc()));
            
            testamento.executeUpdate();
            //System.out.println("Cadastro realizado");
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
    
    public List<Aluno> buscarTudo(){
        List<Aluno> lista = new ArrayList<>();
        String command = "SELECT * FROM aluno";
        Connection con = null;
        PreparedStatement testamento = null;
        
        try{
            con = ConexaoBanco.conectarBanco();
            testamento = con.prepareStatement(command);
            
            ResultSet rs = testamento.executeQuery();
            while(rs.next()){
                Aluno aluno = new Aluno();
                
                aluno.setCodAluno(rs.getInt("codAluno"));
                aluno.setNomeAluno(rs.getString("nomeAluno"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setEmail(rs.getString("email"));
                String rg = rs.getString("rg");
                if (rg != null) {
                    aluno.setRg(rg);
                }
                aluno.setDataNasc(rs.getDate("dataNasc").toLocalDate());
                lista.add(aluno);
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
