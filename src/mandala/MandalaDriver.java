package mandala;

import javafx.application.Application;

/**
 * Driver for the MandalaApp class.
 */
public class MandalaDriver {
    /**
     * Entry-point into the MandalaApp application.
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        try {
            Application.launch(MandalaApp.class, args);
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
            System.exit(1);
        } // try
    } // main
} // MandalaDriver
