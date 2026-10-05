import Controller.PedidoController;
import Controller.ProductoController;
import View.PedidoView;
import View.ProductoView;

public class App {
    public static void main(String[] args) throws Exception {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Ventana de Productos
                ProductoView prodView = new ProductoView();
                ProductoController prodController = new ProductoController(prodView);
                prodView.setController(prodController);
                prodView.setVisible(true);

                // Ventana de Pedidos
                PedidoView pedidoView = new PedidoView();
                PedidoController pedidoController = new PedidoController(pedidoView);
                pedidoView.setController(pedidoController);
                pedidoView.setVisible(true);
            }
        });
    }
}