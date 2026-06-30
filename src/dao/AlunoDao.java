
package dao;

import connection.ConexaoBanco;
import model.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

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
            System.out.println("Cadastro realizado");
        }
        catch(SQLIntegrityConstraintViolationException e){
            System.out.println("CPF ou email já cadastrado");
        }
        catch (Exception e){
            System.out.println("Erro: "+e.getMessage());
        }
        finally{
            ConexaoBanco.desconectarBanco(con, testamento);
        }
    }
}
