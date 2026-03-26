/**
 * Main class to run tree generator program
 * @author Bea Sauve
 * @version 05/31/2025
 */
public class Main {

    /**
     * Constructor that does nothing
     */
    private Main(){
        //This method does nothing
    }

    /**
     * Main method to run tree program
     * @param args arguments
     */
    public static void main(String[] args) {
       FractalGenerator gen = new FractalGenerator();
        new FractalDrawing(gen);
        new FractalGUI(gen);
    }
}