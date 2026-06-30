
package connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.io.IOException;

public class ConexaoBanco {
    
    private static Connection conexao = null;
    
    public static Connection conectarBanco(){
        if(conexao == null){
            try{
                Properties prop = new Properties();
                 InputStream input = ConexaoBanco.class.getClassLoader()
                        .getResourceAsStream("db.properties");
                prop.load(input);
                
                String url = prop.getProperty("db.url");
                String usuario = prop.getProperty("db.usuario");
                String senha = prop.getProperty("db.senha");
                
                conexao = DriverManager.getConnection(url,usuario,senha);
            }
            catch(SQLException e){
                System.out.println("Erro na conexão: "+e.getMessage());
            }
            catch(IOException e){
                System.out.println("Erro ao ler o arquivo de configuração: "+e.getMessage());
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
        finally{
            conexao = null;
        }
    }
}
