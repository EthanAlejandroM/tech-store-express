import Controller.ProductoController;
import View.ProductoView;

public class App {
    public static void main(String[] args) throws Exception {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ProductoView view = new ProductoView();
                ProductoController controller = new ProductoController(view);

                view.setController(controller);
                view.setVisible(true);;  // Display the GUI
            }
        });
    }
}
