
import Controller.MenuController;
import View.MenuView;

public class App {
    public static void main(String[] args) throws Exception {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                MenuView view = new MenuView();
                MenuController controller = new MenuController(view);

                view.setController(controller);
                view.setVisible(true); // Display the GUI
            }
        });
    }
}