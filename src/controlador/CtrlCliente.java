package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Cliente;

public class CtrlCliente {

    //metodo para agregar cliente
    public boolean guardar(Cliente obj) {
        boolean respuesta = false;

        String sql = "INSERT INTO tb_cliente (nombre, apellido, dni, direccion, telefono, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setString(1, obj.getNombre());
            consulta.setString(2, obj.getApellido());
            consulta.setString(3, obj.getDni());
            consulta.setString(4, obj.getDireccion());
            consulta.setString(5, obj.getTelefono());
            consulta.setInt(6, obj.getEstado());

            int filasAfectadas = consulta.executeUpdate();
            respuesta = filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }

        return respuesta;
    }

    // metodo para consultar si existe tal cliente
    public boolean existeCliente(String dni) {
        boolean respuesta = false;
        String sql = "SELECT dni FROM tb_cliente WHERE dni = ?";

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setString(1, dni);
            try (ResultSet rs = consulta.executeQuery()) {
                if (rs.next()) {
                    respuesta = true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar cliente: " + e.getMessage());
        }

        return respuesta;
    }

    //metodo para actualizar cliente
    public boolean actualizar(Cliente obj, int idCliente) {
        boolean respuesta = false;

        String sql = "update tb_cliente set nombre = ?, apellido = ?, dni = ?, direccion = ?, telefono = ?, estado = ? where idCliente = ?";

        try (Connection cn = conexion.Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setString(1, obj.getNombre());
            consulta.setString(2, obj.getApellido());
            consulta.setString(3, obj.getDni());
            consulta.setString(4, obj.getDireccion());
            consulta.setString(5, obj.getTelefono());
            consulta.setInt(6, obj.getEstado());
            consulta.setInt(7, idCliente); // idCliente ahora se pasa de forma segura

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }

        return respuesta;
    }

    //metodo para eliminar cliente
    public boolean eliminar(int idCliente) {
        boolean respuesta = false;

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(
                "DELETE FROM tb_cliente WHERE idCliente = ?")) {

            consulta.setInt(1, idCliente);

            int filasAfectadas = consulta.executeUpdate();
            if (filasAfectadas > 0) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }

        return respuesta;
    }
}
