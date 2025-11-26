package controlador;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.Connection;
import modelo.Categoria;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CtrlCategoria {

    //metodo para crear categoria
    public boolean guardar(Categoria obj) {
        boolean respuesta = false;

        String sql = "INSERT INTO tb_categoria (descripcion, estado) VALUES (?, ?)";

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setString(1, obj.getDescripcion());
            consulta.setInt(2, obj.getEstado());

            respuesta = consulta.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar categoría: " + e.getMessage());
        }

        return respuesta;
    }

    // metodo para consultar si existe tal categoria
    public boolean existeCategoria(String cat) {
        boolean respuesta = false;
        String sql = "select descripcion from tb_categoria where descripcion = ?";

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setString(1, cat);

            try (ResultSet rs = consulta.executeQuery()) {
                if (rs.next()) {
                    respuesta = true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar categoría: " + e.getMessage());
        }

        return respuesta;
    }

    //metodo para actualizar categoria
    public boolean actualizar(Categoria obj, int idCategoria) {
        boolean respuesta = false;
        String sql = "update tb_categoria set descripcion = ? where idCategoria = ?";

        try (Connection cn = conexion.Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setString(1, obj.getDescripcion());
            consulta.setInt(2, idCategoria);

            respuesta = consulta.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar categoría: " + e.getMessage());
        }

        return respuesta;
    }

    //metodo para eliminar categoria
    public boolean eliminar(int idCategoria) {
        boolean respuesta = false;
        String sql = "DELETE FROM tb_categoria WHERE idCategoria = ?";

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setInt(1, idCategoria);
            respuesta = consulta.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar categoría: " + e.getMessage());
        }

        return respuesta;
    }

}
