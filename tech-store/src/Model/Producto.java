package Model;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    private int Id;
    private String Codigo;
    private String Nombre;
    private String Categoría;
    private double PrecioUnitario;
    private int StockDisponible;
    private static List<Producto> productosListProductos = new ArrayList<>();

    // constructor
    public Producto(int id, String codigo, String nombre, String categoría, double precioUnitario,
            int stockDisponible) {
        Id = id;
        Codigo = codigo;
        Nombre = nombre;
        Categoría = categoría;
        PrecioUnitario = precioUnitario;
        StockDisponible = stockDisponible;

    }

    // getters and setters
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCategoría() {
        return Categoría;
    }

    public void setCategoría(String categoría) {
        Categoría = categoría;
    }

    public double getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        PrecioUnitario = precioUnitario;
    }

    public int getStockDisponible() {
        return StockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        StockDisponible = stockDisponible;
    }

    public static List<Producto> getProductosListProductos() {
        return productosListProductos;
    }

    public static void setProductosListProductos(List<Producto> productosListProductos) {
        Producto.productosListProductos = productosListProductos;
    }

    // operaciones del modelo

    // registrar
    public static void addProducto(Producto producto) {
        productosListProductos.add(producto);
    }

    // listar
    public static List<Producto> listarProductos() {
        return productosListProductos;
    }

    // Buscar por Id
    public static Producto buscarPorId(int id) {
        for (Producto p : productosListProductos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // editar producto
    public static boolean editarProducto(Producto productoActualizado) {
        Producto productoExistente = buscarPorId(productoActualizado.getId());

        if (productoExistente != null) {
            // Actualizan los campos del producto
            productoExistente.setCodigo(productoActualizado.getCodigo());
            productoExistente.setNombre(productoActualizado.getNombre());
            productoExistente.setCategoría(productoActualizado.getCategoría());
            productoExistente.setPrecioUnitario(productoActualizado.getPrecioUnitario());
            productoExistente.setStockDisponible(productoActualizado.getStockDisponible());
            return true; // se logro la modificación
        }

        return false; // el producto no existia(id)
    }

    // eliminar producto
    public static boolean eliminarProducto(int id) {
        Producto productoAEliminar = buscarPorId(id);

        if (productoAEliminar != null) {
            productosListProductos.remove(productoAEliminar);
            return true; // Se elimino el producto
        }

        return false; // No se encontró el id
    }

    @Override
    public String toString() {
        return "ID: " + Id + " | Cód: " + Codigo + " | Nombre: " + Nombre +
                " | Cat: " + Categoría + " | Precio: $" + PrecioUnitario +
                " | Stock: " + StockDisponible;
    }
}
