package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import vista.JInternalFrameFacturar;

public class VentaPDF {

    private String nombreCliente;
    private String dniCliente;
    private String telefonoCliente;
    private String direccionCliente;

    private String fechaActual = "";
    private String nombreArchivo = "";

    //metodo para obtener datos del cliente
    public void datosCliente(int idCliente) {
        Connection cn = Conexion.conectar();
        String sql = "select * from tb_cliente where idCliente = '" + idCliente + "'";
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nombreCliente = rs.getString("nombre") + " " + rs.getString("apellido");
                dniCliente = rs.getString("dni");
                telefonoCliente = rs.getString("telefono");
                direccionCliente = rs.getString("direccion");

            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del cliente: " + e);
        }
    }

    //metodo para generar la factura
    public void generarFacturaPDF() {
        try {
            
            int numeroFactura = leerNumeroFactura();
            String numeroFormateado = String.format("%04d", numeroFactura);
            //fecha actual
            Date date = new Date();
            fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
            String fechaNueva = fechaActual.replace("/", "_");
            
            String nombreClienteDoc = nombreCliente.replace(" ", "");

            nombreArchivo = "Venta_" + nombreClienteDoc + "_" + fechaNueva + ".pdf";

            FileOutputStream archivo;
            File file = new File("src/pdf/" + nombreArchivo);
            archivo = new FileOutputStream(file);

            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            Image img = Image.getInstance("src/img/iconImage2.png");
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            fecha.add("Factura: " + numeroFormateado + "\nFecha: " + fechaActual + "\n\n");

            PdfPTable encabezado = new PdfPTable(4);
            encabezado.setWidthPercentage(100);
            encabezado.getDefaultCell().setBorder(0);
            //tamaño de celdas
            float[] columnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            encabezado.setWidths(columnaEncabezado);
            encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            //agregar celdas
            encabezado.addCell(img);

            String cuit = "27-46542468-1";
            String nombre = "Ancelotti Carlo";
            String telefono = "+54(3492)453232";
            String direccion = "Casabella 23";
            String razon = "MUCHA MURGA";

            encabezado.addCell("");
            encabezado.addCell("CUIT: " + cuit + "\nNOMBRE: " + nombre + "\nTELEFONO: " + telefono + "\nDIRECCION: " + direccion + "\nRAZON SOCIAL: " + razon);
            encabezado.addCell(fecha);
            doc.add(encabezado);

            //CUERPO
            Paragraph cliente = new Paragraph();
            cliente.add(Chunk.NEWLINE);
            cliente.add("Datos del cliente: " + "\n\n");
            doc.add(cliente);
            
            //DATOS DEL CLIENTE
            PdfPTable tablaCliente = new PdfPTable(4);
            tablaCliente.setWidthPercentage(100);
            tablaCliente.getDefaultCell().setBorder(0);

            float[] columnaCliente = new float[]{20f, 30f, 70f, 40f};
            tablaCliente.setWidths(columnaCliente);
            tablaCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliente1 = new PdfPCell(new Phrase("DNI: ", negrita));
            PdfPCell cliente2 = new PdfPCell(new Phrase("Nombre: ", negrita));
            PdfPCell cliente3 = new PdfPCell(new Phrase("Telefono: ", negrita));
            PdfPCell cliente4 = new PdfPCell(new Phrase("Direccion: ", negrita));

            cliente1.setBorder(0);
            cliente2.setBorder(0);
            cliente3.setBorder(0);
            cliente4.setBorder(0);
            //agregamos a la tabla cliente
            tablaCliente.addCell(cliente1);
            tablaCliente.addCell(cliente2);
            tablaCliente.addCell(cliente3);
            tablaCliente.addCell(cliente4);
            tablaCliente.addCell(dniCliente);
            tablaCliente.addCell(nombreCliente);
            tablaCliente.addCell(telefonoCliente);
            tablaCliente.addCell(direccionCliente);
            //agregamos al documento
            doc.add(tablaCliente);

            //ESPACIO EN BLANCO
            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.add("");
            espacio.setAlignment(Element.ALIGN_CENTER);
            doc.add(espacio);

            //AGREGAR LOS PRODUCTOS
            PdfPTable tablaProd = new PdfPTable(4);
            tablaProd.setWidthPercentage(100);
            tablaProd.getDefaultCell().setBorder(0);

            float[] columnaProd = new float[]{15f, 50f, 15f, 20f,};
            tablaProd.setWidths(columnaProd);
            tablaProd.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell prod1 = new PdfPCell(new Phrase("Cantidad: ", negrita));
            PdfPCell prod2 = new PdfPCell(new Phrase("Descripcion: ", negrita));
            PdfPCell prod3 = new PdfPCell(new Phrase("Precio Unitario: ", negrita));
            PdfPCell prod4 = new PdfPCell(new Phrase("Precio Total: ", negrita));
            prod1.setBorder(0);
            prod2.setBorder(0);
            prod3.setBorder(0);
            prod4.setBorder(0);

            prod1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            prod2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            prod3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            prod4.setBackgroundColor(BaseColor.LIGHT_GRAY);

            tablaProd.addCell(prod1);
            tablaProd.addCell(prod2);
            tablaProd.addCell(prod3);
            tablaProd.addCell(prod4);

            for (int i = 0; i < JInternalFrameFacturar.jTableProductos.getRowCount(); i++) {
                String descripcion = JInternalFrameFacturar.jTableProductos.getValueAt(i, 1).toString();
                String cantidad = JInternalFrameFacturar.jTableProductos.getValueAt(i, 2).toString();
                String precio = JInternalFrameFacturar.jTableProductos.getValueAt(i, 3).toString();
                String total = JInternalFrameFacturar.jTableProductos.getValueAt(i, 7).toString();

                tablaProd.addCell(cantidad);
                tablaProd.addCell(descripcion);
                tablaProd.addCell(precio);
                tablaProd.addCell(total);
            }

            doc.add(tablaProd);

            //TOTAL A PAGAR
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a pagar: " + JInternalFrameFacturar.txtTotal.getText());
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);

            //FIRMA
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("FIRMA Y ACLARACION:\n\n");
            firma.add("_______________________");
            firma.setAlignment(Element.ALIGN_CENTER);

            doc.add(firma);

            //MENSAJE
            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("\n\n¡Gracias por su compra!");
            mensaje.setAlignment(Element.ALIGN_CENTER);

            doc.add(mensaje);

            //Cerrar el doc y el archivo
            doc.close();
            archivo.close();

            //abrir el documento al registrar venta
            Desktop.getDesktop().open(file);
            
            guardarNumeroFactura(numeroFactura + 1);

        } catch (DocumentException | IOException e) {
            System.out.println("Error en: " + e);
        }
    }

    private int leerNumeroFactura() {
        try {
            File file = new File("factura_num.txt");
            if (!file.exists()) {
                return 1;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linea = reader.readLine();
            reader.close();
            return Integer.parseInt(linea);
        } catch (IOException | NumberFormatException e) {
            return 1;
        }
    }

    private void guardarNumeroFactura(int numero) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("factura_num.txt"));
            writer.write(String.valueOf(numero));
            writer.close();
        } catch (IOException e) {
            System.out.println("No se pudo guardar el número de factura: " + e.getMessage());
        }
    }
}
