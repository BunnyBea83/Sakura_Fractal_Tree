import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * FractalDrawing is responsible for drawing the fractal using the fractal data in an ArrayList.
 * @author Bea Sauve
 * @version 05/30/2025
 */
public class FractalDrawing extends JFrame implements FractalObserver{
    /** instantiate arraylist of fractal data */
    private static final ArrayList<FractalElement> fractalData = new ArrayList<>();
    /** instantiate Fractal subject */
    private final FractalSubject subject;
    /** instantiate a drawing panel */
    private final DrawArea drawPanel;

    /**
     * Constructor for drawing all fractal branches on a panel
     * @param subject each fractal
     */
    public FractalDrawing(FractalSubject subject){
        // set up the drawing panel
        this.subject = subject;
        subject.attach(this);
        drawPanel = new DrawArea();
        add(drawPanel);

        //finalize details for the panel
        setTitle("Fractal Display");
        Toolkit toolKit = getToolkit();
        Dimension screenSize = toolKit.getScreenSize();
        int width = screenSize.width/2;
        int height = screenSize.height-(screenSize.height/5);
        setSize(width,height);
        setLocation(screenSize.width-((screenSize.width/3) *2),100);
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Update method to update all fractal data and redraw with new information
     */
    @Override
    public void update() {
        fractalData.clear();
        fractalData.addAll(subject.getFractalData());
        drawPanel.repaint();
    }

    //----------------------------------------------------------------------
    //            Draw Area
    //----------------------------------------------------------------------
    /**
     * Draw area class to draw all tree branches
     */
    private static class DrawArea extends JPanel{
        /**
         * Constructor to generate draw area
         */
        public DrawArea(){

            setBackground(Color.BLACK);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Image image;
            try {
                image = javax.imageio.ImageIO.read(new File("resources/panelbackground.jpg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
           for(FractalElement element : fractalData){
               element.draw(g);
           }
        }
    }
}
