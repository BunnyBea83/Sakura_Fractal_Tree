import java.awt.*;
import java.util.ArrayList;

/**
 * FractalGenerator is responsible for generating fractal data (using recursion) and storing
 * it in an ArrayList. Each recursive call should be responsible for adding one branch to the list,
 * calculating data necessary for child branches, then making a recursive call for
 * each of the two child branches.
 */
public class FractalGenerator implements FractalSubject {
    /**  instantiate observer list  */
    private final ArrayList<FractalObserver> observers;
    /**  instantiate recursion depth */
    private int recDepth;
    /** instantiate child parent ratio   */
    private int childRatio;
    /**  instantiate left angle  */
    private int leftAngle;
    /**  instantiate right angle  */
    private int rightAngle;
    /**  instantiate length of trunk  */
    private int trunkLength;
    /** instantiate width of trunk   */
    private int trunkWidth;
    /**  instantiate color of trunk  */
    private Color trunkColor;
    /**  instantiate color of leaves  */
    private Color leafColor;
    /**   instantiate monitor dimensions */
    private Dimension screenSize;


    /**
     * Constructor that initializes observers
     */
    public FractalGenerator() {observers = new ArrayList<>();}

    /**
     * attach an observer to list
     * @param obs observers
     */
    @Override
    public void attach(FractalObserver obs) {
        //TODO: preconditions
        observers.add(obs);
    }

    /**
     * remove an observer from list
     * @param obs observers
     */
    @Override
    public void detach(FractalObserver obs) {
        observers.remove(obs);
    }

    /**
     * update all observers
     */
    @Override
    public void notifyObservers() {
        for (FractalObserver observer : observers) {
            observer.update();
        }
    }

    /**
     * generate branches from data and add branch to arraylist
     * @return Arraylist of branches
     */
    @Override
    public ArrayList<FractalElement> getFractalData() {
        ArrayList<FractalElement> fractalData = new ArrayList<>();
        int x = ((screenSize.width/4) );
        int y = screenSize.height - (screenSize.height/5) ;
        //recurse call generation of branches start at 90 degrees
        generateBranch(fractalData,x,y,trunkLength,trunkWidth,-90,recDepth);
        return fractalData;
    }

    /**
     * recursive method for assembling branches with recursion and trigonometry
     * @param data   arraylist of branches
     * @param x      starting coordinate
     * @param y      starting coordinate
     * @param length length of branch
     * @param width  width of branch
     * @param angle  angle of trigonometric recursion
     * @param depth  levels of recursion
     */
    private void generateBranch(ArrayList<FractalElement> data, int x, int y,
                                int length, int width, int angle, int depth) {
        if (depth == 0) return;
        //degree of color change
        double shift = 1 - ((double) depth /recDepth);
        Color shiftColor = shiftColor(trunkColor,leafColor,shift);

        //compute new points
        double radians = Math.toRadians(angle);
        int x2 = (int) ( x + (length * Math.cos(radians)));
        int y2 = (int) ( y + (length * Math.sin(radians)));

        //generate a branch
        FractalElement branch = new Branch(x ,y, x2, y2,width, shiftColor);
        data.add(branch);

        //shrink size
        double scale = (double) childRatio / 100;
        int nLength = (int) Math.round(length * scale);
        int nWidth = Math.max(1,(int) Math.round(width * scale));

        //recurse left and right
        generateBranch(data, x2, y2,nLength,nWidth,angle-leftAngle,depth-1);
        generateBranch(data, x2, y2,nLength,nWidth,angle+rightAngle,depth-1);
    }

    /**
     * assign data to the class
     * @param recDepth    recursion depth
     * @param childRatio  parent child ratio
     * @param leftAngle   angle of left side
     * @param rightAngle  angle of right side
     * @param trunkLength length of branches
     * @param trunkWidth  width of branches
     * @param trunk       color of trunk
     * @param leaf        color of leaves
     * @param screenSize  size of monitor
     */
    @Override
    public void setData(int recDepth, int childRatio, int leftAngle, int rightAngle,
                        int trunkLength, int trunkWidth, Color trunk, Color leaf, Dimension screenSize) {
        this.recDepth = recDepth;       //how many times it recurses
        this.childRatio = childRatio;   // percentage of thinness and shortness
        this.leftAngle = leftAngle;     //angle of left branch
        this.rightAngle = rightAngle;   //angle of right branch
        this.trunkLength = trunkLength; //start length of trunk
        this.trunkWidth = trunkWidth;   //start width of trunk
        this.trunkColor = trunk;
        this.leafColor = leaf;
        this.screenSize = screenSize;
        notifyObservers();
    }

    /**
     * method to generate color gradiant
     * @param start trunk color
     * @param end   leaf color
     * @param shift degree to shift colors
     * @return      new color
     */
    private Color shiftColor(Color start, Color end, double shift) {
        //shift each color with each recursion
        int r = (int) (start.getRed() + shift * (end.getRed()- start.getRed())) ;
        int b = (int)(start.getBlue() + shift * (end.getBlue()- start.getBlue())) ;
        int g = (int)(start.getGreen() + shift * (end.getGreen()- start.getGreen())) ;

        //make sure colors remain in bounds
        r = Math.min(255, Math.max(0, r));
        g = Math.min(255, Math.max(0, g));
        b = Math.min(255, Math.max(0, b));

        return new Color(r, g, b);
    }
}
