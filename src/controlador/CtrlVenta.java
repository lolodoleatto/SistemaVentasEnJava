package controlador;

import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.DetalleVenta;
import modelo.Venta;

public class CtrlVenta {

    public static int idDetalleRegistrado;
    java.math.BigDecimal idColVar;

    //metodo para guardar venta
    public boolean guardar(Venta obj) {
        boolean respuesta = false;
        String sql = "INSERT INTO tb_venta (idCliente, valorPagar, fechaVenta, estado) VALUES (?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            consulta.setInt(1, obj.getIdCliente());
            consulta.setDouble(2, obj.getValorPagar());
            consulta.setString(3, obj.getFechaVenta());
            consulta.setInt(4, obj.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                try (ResultSet rs = consulta.getGeneratedKeys()) {
                    while (rs.next()) {
                        idColVar = rs.getBigDecimal(1);
                        idDetalleRegistrado = idColVar.intValue();
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar venta: " + e);
        }

        return respuesta;
    }

    public boolean guardarDetalle(DetalleVenta obj) {
        boolean respuesta = false;
        String sql = "INSERT INTO tb_detalle_venta (idVenta, idProducto, cantidad, precioUnitario, subtotal, descuento, iva, totalPagar, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setInt(1, idDetalleRegistrado);
            consulta.setInt(2, obj.getIdProducto());
            consulta.setInt(3, obj.getCantidad());
            consulta.setDouble(4, obj.getPrecioUnitario());
            consulta.setDouble(5, obj.getSubtotal());
            consulta.setDouble(6, obj.getDescuento());
            consulta.setDouble(7, obj.getIva());
            consulta.setDouble(8, obj.getTotalPagar());
            consulta.setInt(9, obj.getEstado());

            int filasAfectadas = consulta.executeUpdate();
            respuesta = filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar detalle de venta: " + e);
        }

        return respuesta;
    }

    //metodo para actualizar venta
    public boolean actualizar(Venta obj, int idVenta) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            String sql = "UPDATE tb_venta SET idCliente = ?, estado = ? WHERE idVenta = ?";
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setInt(1, obj.getIdCliente());
            consulta.setInt(2, obj.getEstado());
            consulta.setInt(3, idVenta); 

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar venta: " + e.getMessage());
        }
        return respuesta;
    }
}
