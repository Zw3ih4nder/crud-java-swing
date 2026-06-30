
package connection;

import java.sql.Connection;

public class TesteConexaoBanco {
    public static void main(String[] args) {
        Connection conn = ConexaoBanco.conectarBanco();
        if (conn != null) {
            System.out.println("Conectado com sucesso!");
        }
    }
}
