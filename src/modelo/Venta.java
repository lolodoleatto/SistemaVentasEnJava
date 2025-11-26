package modelo;

public class Venta {

    private int idVenta;
    private int idCliente;
    private double valorPagar;
    private String fechaVenta;
    private int estado;

    public Venta() {
        this.idVenta = 0;
        this.idCliente = 0;
        this.valorPagar = 0;
        this.fechaVenta = "";
        this.estado = 1;
    }

    public Venta(int idVenta, int idCliente, double valorPagar, String fechaVenta, int estado) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.valorPagar = valorPagar;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
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

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", idCliente=" + idCliente + ", valorPagar=" + valorPagar + ", fechaVenta=" + fechaVenta + ", estado=" + estado + '}';
    }
    
    
}
