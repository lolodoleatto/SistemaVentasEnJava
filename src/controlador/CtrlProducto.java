package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Producto;

public class CtrlProducto {

    //metodo para agregar producto
    public boolean guardar(Producto obj) {
        boolean respuesta = false;
        String sql = "INSERT INTO tb_producto (descripcion, cantidad, precio, porcentajeIva, idCategoria, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setString(1, obj.getDescripcion());
            consulta.setInt(2, obj.getCantidad());
            consulta.setDouble(3, obj.getPrecio());
            consulta.setDouble(4, obj.getIva());
            consulta.setInt(5, obj.getIdCategoria());
            consulta.setInt(6, obj.getEstado());

            int filasAfectadas = consulta.executeUpdate();
            respuesta = filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }

        return respuesta;
    }

    // metodo para consultar si existe tal producto
    public boolean existeProducto(String prod) {
        boolean respuesta = false;
        String sql = "SELECT descripcion FROM tb_producto WHERE descripcion = ?";

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setString(1, prod);

            try (ResultSet rs = consulta.executeQuery()) {
                if (rs.next()) {
                    respuesta = true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar producto: " + e.getMessage());
        }

        return respuesta;
    }

    //metodo para actualizar producto
    public boolean actualizar(Producto obj, int idProducto) {
        boolean respuesta = false;
        String sql = "UPDATE tb_producto SET descripcion = ?, cantidad = ?, precio = ?, porcentajeIva = ?, idCategoria = ?, estado = ? WHERE idProducto = ?";

        try (Connection cn = conexion.Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setString(1, obj.getDescripcion());
            consulta.setInt(2, obj.getCantidad());
            consulta.setDouble(3, obj.getPrecio());
            consulta.setDouble(4, obj.getIva());
            consulta.setInt(5, obj.getIdCategoria());
            consulta.setInt(6, obj.getEstado());
            consulta.setInt(7, idProducto);

            respuesta = consulta.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }

        return respuesta;
    }

    //metodo para eliminar producto
    public boolean eliminar(int idProducto) {
        boolean respuesta = false;
        String sql = "DELETE FROM tb_producto WHERE idProducto = ?";

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setInt(1, idProducto);
            respuesta = consulta.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }

        return respuesta;
    }

    //metodo para actualizar stock de producto
    public boolean actualizarStock(Producto obj, int idProducto) {
        boolean respuesta = false;
        String sql = "UPDATE tb_producto SET cantidad = ? WHERE idProducto = ?";

        try (Connection cn = conexion.Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setInt(1, obj.getCantidad());
            consulta.setInt(2, idProducto);

            respuesta = consulta.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar stock producto: " + e.getMessage());
        }

        return respuesta;
    }

}
