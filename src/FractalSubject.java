import java.awt.*;
import java.util.ArrayList;

/**
 * Interface for assembling observers and data
 */
public interface FractalSubject {
    /**
     * add an observer method
     * @param obs observers
     */
    void attach(FractalObserver obs);

    /**
     * remove an observer method
     * @param obs observers
     */
    void detach(FractalObserver obs);

    /**
     * send updates to all observers
     */
    void notifyObservers();

    /**
     * retrieve all branches
     * @return arraylist of branches
     */
    ArrayList<FractalElement> getFractalData();

    /**
     * assign data to global variables
     * @param recDepth     recursion depth
     * @param childRatio   parent child ratio
     * @param leftAngle    angle of left side
     * @param rightAngle   angle of right side
     * @param trunkLength  length of branches
     * @param trunkWidth   width of branches
     * @param trunkColor   color of trunk
     * @param leafColor    color of leaves
     * @param screenSize   size of monitor
     */
    void setData(int recDepth, int childRatio, int leftAngle, int rightAngle,
                        int trunkLength, int trunkWidth, Color trunkColor,
                        Color leafColor, Dimension screenSize
    );
}
