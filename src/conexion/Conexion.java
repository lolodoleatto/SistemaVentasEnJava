
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class Conexion {
    
    public static Connection conectar() {
        Properties props = new Properties();
        try {
            // Carga las credenciales desde el archivo
            props.load(new FileInputStream("db.properties"));
            
            Connection cn = DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password")
            );
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexion local: " + e);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de configuración: " + e);
        }
        return null;
    }
}
