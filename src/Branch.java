import java.awt.*;

/**
 * Branch record to store information regarding branch construction, and to draw each branch
 * @param x1     x coordinate 1
 * @param y1     y coordinate 1
 * @param x2     x coordinate 2
 * @param y2     y coordinate 2
 * @param width  width of branch
 * @param color  color of branch
 */
public record Branch(int x1, int y1,int x2, int y2, int width,Color color) implements FractalElement{

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Stroke lineWidth = new BasicStroke(width);
        g2d.setStroke(lineWidth);
        g2d.setColor(color);
        g2d.drawLine(x1, y1, x2, y2);
    }
}
