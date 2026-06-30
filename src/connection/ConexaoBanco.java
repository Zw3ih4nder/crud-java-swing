
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ConexaoBanco {
    private static final String url = "jdbc:mysql://localhost:3306/bdacademia";
    private static final String usuario = "root";
    private static final String senha = "";
    
    private static Connection conexao = null;
    
    public static Connection conectarBanco(){
        if(conexao == null){
            try{
                conexao = DriverManager.getConnection(url,usuario,senha);
            }
            catch(SQLException e){
                System.out.println("Erro na conexão: "+e.getMessage());
            }
        }
        return conexao;
    }
    
    public static void desconectarBanco(Connection conn, PreparedStatement stmt) {
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } 
        catch (SQLException e) {
            System.out.println("Erro ao fechar: " + e.getMessage());
        }
    }
}
