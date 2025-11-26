package controlador;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexion.Conexion;
import java.awt.Font;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Reportes {

    //metodo para crear reportes de clientes registrados
    public void reportesClientes() {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/lolod/OneDrive/Documentos/Reporte_clientes.pdf"));
            Image img = Image.getInstance("src/img/iconImage2.png");
            img.scaleToFit(100, 100);
            img.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por\nLorenzo Doleatto\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de CLIENTES\n\n");

            doc.open();
            doc.add(img);
            doc.add(parrafo);

            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Codigo");
            tabla.addCell("Nombre");
            tabla.addCell("Dni");
            tabla.addCell("Telefono");
            tabla.addCell("Direccion");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("select idCliente, concat(nombre, ' ', apellido) as nombres, dni, telefono, direccion from tb_cliente");

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                    } while (rs.next());
                    doc.add(tabla);
                }
            } catch (SQLException e) {
                System.out.println("Error en" + e);
            }
            doc.close();
            JOptionPane.showMessageDialog(null, "Reporte Creado");
        } catch (DocumentException | IOException e) {
            System.out.println("Error en: " + e);
        }

    }

    //metodo para crear reportes de productos registrados
    public void reportesProductos() {
        Document doc = new Document();
        try {

            PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/lolod/OneDrive/Documentos/Reporte_productos.pdf"));
            Image img = Image.getInstance("src/img/iconImage2.png");
            img.scaleToFit(100, 100);
            img.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por\nLorenzo Doleatto\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de PRODUCTOS\n\n");

            doc.open();
            doc.add(img);
            doc.add(parrafo);

            float[] anchoColumnas = {3, 7, 4, 5, 5, 6};

            PdfPTable tabla = new PdfPTable(anchoColumnas);
            tabla.addCell("Cod");
            tabla.addCell("Producto");
            tabla.addCell("Cantidad");
            tabla.addCell("Precio");
            tabla.addCell("Pje. IVA");
            tabla.addCell("Categoria");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select p.idProducto, p.descripcion, p.cantidad, p.precio, p.porcentajeIva, c.descripcion "
                        + "from tb_producto as p join tb_categoria as c on p.idCategoria = c.IdCategoria;");

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell("$" + rs.getString(4));
                        tabla.addCell(rs.getString(5) + "%");
                        tabla.addCell(rs.getString(6));
                    } while (rs.next());
                    doc.add(tabla);
                }
            } catch (SQLException e) {
                System.out.println("Error en" + e);
            }
            doc.close();
            JOptionPane.showMessageDialog(null, "Reporte Creado");
        } catch (DocumentException | IOException e) {
            System.out.println("Error en: " + e);
        }

    }

    //metodo para crear reportes de categorias registradas
    public void reportesCategoria() {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/lolod/OneDrive/Documentos/Reporte_categorias.pdf"));
            Image img = Image.getInstance("src/img/iconImage2.png");
            img.scaleToFit(100, 100);
            img.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por\nLorenzo Doleatto\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de CATEGORIAS\n\n");

            doc.open();
            doc.add(img);
            doc.add(parrafo);

            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Codigo");
            tabla.addCell("Descripcion");
            tabla.addCell("Estado");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("select * from tb_categoria");

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                    } while (rs.next());
                    doc.add(tabla);
                }
            } catch (SQLException e) {
                System.out.println("Error en" + e);
            }
            doc.close();
            JOptionPane.showMessageDialog(null, "Reporte Creado");
        } catch (DocumentException | IOException e) {
            System.out.println("Error en: " + e);
        }

    }

    //metodo para crear reportes de ventas registradas
    public void reportesVentas() {
        Document doc = new Document();
        try {

            PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/lolod/OneDrive/Documentos/Reporte_ventas.pdf"));
            Image img = Image.getInstance("src/img/iconImage2.png");
            img.scaleToFit(100, 100);
            img.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por\nLorenzo Doleatto\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de VENTAS\n\n");

            doc.open();
            doc.add(img);
            doc.add(parrafo);

            float[] anchoColumnas = {3, 9, 4, 5, 3};

            PdfPTable tabla = new PdfPTable(anchoColumnas);
            tabla.addCell("Cod");
            tabla.addCell("Cliente");
            tabla.addCell("Tot. Pagar");
            tabla.addCell("Fecha");
            tabla.addCell("Estado");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select v.idVenta, concat(c.nombre, ' ',c.apellido), v.valorPagar, v.fechaVenta, v.estado "
                        + "from tb_venta as v join tb_cliente as c on v.idCliente = c.IdCliente;");

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell("$" + rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));

                    } while (rs.next());
                    doc.add(tabla);
                }
            } catch (SQLException e) {
                System.out.println("Error en" + e);
            }
            doc.close();
            JOptionPane.showMessageDialog(null, "Reporte Creado");
        } catch (DocumentException | IOException e) {
            System.out.println("Error en: " + e);
        }

    }
}
