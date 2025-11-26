package controlador;

import java.sql.ResultSet;
import conexion.Conexion;
import modelo.Usuario;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class CtrlUsuario {

    //metodo para iniciar sesion
    public boolean loginUser(Usuario obj) {
        boolean respuesta = false;
        String sql = "SELECT usuario FROM tb_usuario WHERE usuario = ? AND password = ?";

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setString(1, obj.getUsuario());
            consulta.setString(2, obj.getContraseña());

            try (ResultSet rs = consulta.executeQuery()) {
                if (rs.next()) {
                    respuesta = true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión");
        }

        return respuesta;
    }

    //metodo para guardar usuario
    public boolean guardar(Usuario obj) {
        boolean respuesta = false;
        String sql = "INSERT INTO tb_usuario (nombre, apellido, usuario, password, telefono, estado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setString(1, obj.getNombre());
            consulta.setString(2, obj.getApellido());
            consulta.setString(3, obj.getUsuario());
            consulta.setString(4, obj.getContraseña());
            consulta.setString(5, obj.getTelefono());
            consulta.setInt(6, obj.getEstado());

            respuesta = consulta.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al agregar usuario: " + e.getMessage());
        }

        return respuesta;
    }

    // metodo para consultar si existe tal usuario
    public boolean existeUsuario(String user) {
        boolean respuesta = false;
        String sql = "SELECT usuario FROM tb_usuario WHERE usuario = ?";

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setString(1, user);

            try (ResultSet rs = consulta.executeQuery()) {
                if (rs.next()) {
                    respuesta = true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar usuario: " + e.getMessage());
        }

        return respuesta;
    }

    //metodo para actualizar usuario
    public boolean actualizar(Usuario obj, int idUsuario) {
        boolean respuesta = false;
        String sql = "UPDATE tb_usuario SET nombre=?, apellido=?, usuario=?, password=?, telefono=?, estado=? WHERE idUsuario = ?";

        try (Connection cn = conexion.Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setString(1, obj.getNombre());
            consulta.setString(2, obj.getApellido());
            consulta.setString(3, obj.getUsuario());
            consulta.setString(4, obj.getContraseña());
            consulta.setString(5, obj.getTelefono());
            consulta.setInt(6, obj.getEstado());
            consulta.setInt(7, idUsuario);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }

        return respuesta;
    }

    //metodo para eliminar usuario
    public boolean eliminar(int idUsuario) {
        boolean respuesta = false;
        String sql = "DELETE FROM tb_usuario WHERE idUsuario = ?";

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setInt(1, idUsuario);

            int filasAfectadas = consulta.executeUpdate();
            if (filasAfectadas > 0) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }

        return respuesta;
    }

}
