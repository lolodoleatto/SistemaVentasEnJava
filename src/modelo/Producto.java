
package modelo;


public class Producto {
    
    private int idProducto;
    private String descripcion;
    private double precio;
    private double iva;
    private int cantidad;
    private int idCategoria;
    private int estado;

    public Producto() {
        this.idProducto = 0;
        this.descripcion = "";
        this.precio = 0.0;
        this.iva = 0.0;
        this.idCategoria = 0;
        this.cantidad = 0;
        this.estado = 0; 
    }

    public Producto(int idProducto, String descripcion, double precio, double iva, int cantidad, int idCategoria, int estado) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.iva = iva;
        this.cantidad = cantidad;
        this.idCategoria = idCategoria;
        this.estado = estado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
    
    
}
