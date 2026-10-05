package Model;

public class DetallePedido {
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    // constructor
    public DetallePedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecioUnitario();
        this.subtotal = calcularSubtotal();
    }

    // Método para calcular el subtotal
    public double calcularSubtotal() {
        return cantidad * precioUnitario;
    }

    // getters y setters
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
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

    @Override
    public String toString() {
        return producto.getNombre() + " | Cant: " + cantidad + " | Precio: (" + precioUnitario + " | Subtotal: "
                + subtotal + ")";
    }
}
