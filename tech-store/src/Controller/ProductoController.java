package Controller;

import Model.Producto;
import View.ProductoView;
import java.awt.Color;
import java.util.List;

public class ProductoController {
    private ProductoView view;

    public ProductoController(ProductoView view) {
        this.view = view;
        // Asignamos la referencia de este controlador a la vista
        this.view.setController(this);
    }

    // Iniciar la vista
    public void iniciar() {
        view.setVisible(true);
        viewProductos(); // Muestra los productos cargados inicialmente (si los hay)
    }

    // --- MÉTODOS Mapeados a las acciones de la vista ---

    // 1. Agregar Producto
    public void addProducto() {
        Producto nuevoProducto = view.getProductoInput();

        if (nuevoProducto != null) {
            // Verificar si el ID ya existe antes de agregar
            if (Producto.buscarPorId(nuevoProducto.getId()) != null) {
                view.showMessage("Ya existe un producto con el ID " + nuevoProducto.getId(), Color.RED);
                return;
            }

            // Guardar en el modelo (lista)
            Producto.addProducto(nuevoProducto);
            view.showMessage("Producto agregado correctamente.", Color.GREEN);

            // Actualizar la lista en la vista
            viewProductos();
        }
    }

    // 2. Actualizar Producto existente
    public void updateProducto() {
        Producto productoEditado = view.getProductoInput();

        if (productoEditado != null) {
            boolean exito = Producto.editarProducto(productoEditado);

            if (exito) {
                view.showMessage("Producto actualizado exitosamente.", Color.GREEN);
                viewProductos();
            } else {
                view.showMessage("No se encontró ningún producto con el ID " + productoEditado.getId(), Color.RED);
            }
        }
    }

    // 3. Ver/Listar Productos
    public void viewProductos() {
        List lista = Producto.listarProductos();
        view.displayProductos(lista);
    }

    // 4. Eliminar Producto por ID
    public void removeProducto() {
        int idAEliminar = view.getIdInput();

        if (idAEliminar != -1) {
            boolean exito = Producto.eliminarProducto(idAEliminar);

            if (exito) {
                view.showMessage("Producto eliminado correctamente.", Color.GREEN);
                viewProductos(); // Actualizar la lista visual
            } else {
                view.showMessage("No se encontró ningún producto con el ID " + idAEliminar, Color.RED);
            }
        }
    }
}