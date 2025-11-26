package modelo;

public class DetalleVenta {

    private int idDetalleVenta;
    private int idVenta;
    private int idProducto;
    private String descripcion;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private double descuento;
    private double iva;
    private double totalPagar;
    private String fechaVenta;
    private int estado;

    public DetalleVenta() {
        this.idDetalleVenta = 0;
        this.idVenta = 0;
        this.idProducto = 0;
        this.descripcion = "";
        this.cantidad = 0;
        this.precioUnitario = 0.0;
        this.subtotal = 0;
        this.descuento = 0;
        this.iva = 0;
        this.totalPagar = 0;
        this.fechaVenta = "";
        this.estado = 0;
    }

    public DetalleVenta(int idDetalleVenta, int idVenta, int idProducto, String descripcion, int cantidad, double precioUnitario, double subtotal, double descuento, double iva, double totalPagar, String fechaVenta, int estado) {
        this.idDetalleVenta = idDetalleVenta;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.iva = iva;
        this.totalPagar = totalPagar;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
    }

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "idDetalleVenta=" + idDetalleVenta + ", idVenta=" + idVenta + ", idProducto=" + idProducto + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", subtotal=" + subtotal + ", descuento=" + descuento + ", iva=" + iva + ", totalPagar=" + totalPagar + ", fechaVenta=" + fechaVenta + ", estado=" + estado + '}';
    }

}
