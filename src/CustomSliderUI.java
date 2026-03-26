import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * code sourced from:
 * <a href="https://stackoverflow.com/questions/62609789/change-thumb-and-color-of-jslider">...</a>
 * @author weisj
 * @version 06/27/2020
 *
 */

public class CustomSliderUI extends BasicSliderUI {
    /** height of the track */
    private static final int TRACK_HEIGHT = 10;
    /** width of the track */
    private static final int TRACK_WIDTH = 8;
    /** arc of slider track */
    private static final int TRACK_ARC = 5;
    /** size of slider button */
    private static final Dimension THUMB_SIZE = new Dimension(20, 20);
    /** shape of track */
    private final RoundRectangle2D.Float trackShape = new RoundRectangle2D.Float();
    /** instantiate an image */
    private BufferedImage thumbImage;

    /**
     * Alter UI for custom slider design
     * @param b a slider to modify
     */
    public CustomSliderUI(final JSlider b) {
        super(b);
        try {
            // Adjust path as needed: it should be relative to classpath or absolute
            thumbImage = ImageIO.read(new File("resources/sakura.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Could not load thumb image: " + e.getMessage());
        }
    }

    /**
     * Calculate the location of the slider track
     */
    @Override
    protected void calculateTrackRect() {
        super.calculateTrackRect();
        if (isHorizontal()) {
            trackRect.y = trackRect.y + (trackRect.height - TRACK_HEIGHT) ;
            trackRect.height = TRACK_HEIGHT;
        } else {
            trackRect.x = trackRect.x + (trackRect.width - TRACK_WIDTH) / 2;
            trackRect.width = TRACK_WIDTH;
        }
        trackShape.setRoundRect(trackRect.x, trackRect.y, trackRect.width, trackRect.height, TRACK_ARC, TRACK_ARC);
    }

    /**
     * Calculate the location of the slider track
     */
    @Override
    protected void calculateThumbLocation() {
        super.calculateThumbLocation();
        if (isHorizontal()) {
            thumbRect.y = trackRect.y + (trackRect.height - thumbRect.height) / 2;
        } else {
            thumbRect.x = trackRect.x + (trackRect.width - thumbRect.width) / 2;
        }
    }

    /**
     * retrieve slider dimensions
     * @return size of slider button
     */
    @Override
    protected Dimension getThumbSize() {
        return THUMB_SIZE;
    }

    /**
     * determine slider orientation
     * @return true if horizontal slider
     */
    private boolean isHorizontal() {
        return slider.getOrientation() == JSlider.HORIZONTAL;
    }

    /**
     * modify paint method
     * @param g the <code>Graphics</code> context in which to paint
     * @param c the component being painted;
     *          this argument is often ignored,
     *          but might be used if the UI object is stateless
     *          and shared by multiple components
     */
    @Override
    public void paint(final Graphics g, final JComponent c) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(g, c);
    }

    /**
     * design the slider
     * @param g the graphics
     */
    @Override
    public void paintTrack(final Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Shape clip = g2.getClip();

        boolean horizontal = isHorizontal();
        boolean inverted = slider.getInverted();

        // Paint shadow.
        g2.setColor(new Color(216, 226, 220));
        g2.fill(trackShape);

        // Paint track background.
        g2.setColor(new Color(232, 232, 228));
        g2.setClip(trackShape);
        trackShape.y += 1;
        g2.fill(trackShape);
        trackShape.y = trackRect.y;

        g2.setClip(clip);

        // Paint selected track.
        if (horizontal) {
            boolean ltr = slider.getComponentOrientation().isLeftToRight();
            if (ltr) inverted = !inverted;
            int thumbPos = thumbRect.x + thumbRect.width / 2;
            if (inverted) {
                g2.clipRect(0, 0, thumbPos, slider.getHeight());
            } else {
                g2.clipRect(thumbPos, 0, slider.getWidth() - thumbPos, slider.getHeight());
            }

        } else {
            int thumbPos = thumbRect.y + thumbRect.height / 2;
            if (inverted) {
                g2.clipRect(0, 0, slider.getHeight(), thumbPos);
            } else {
                g2.clipRect(0, thumbPos, slider.getWidth(), slider.getHeight() - thumbPos);
            }
        }
        g2.setColor(new Color(252, 213, 206));
        g2.fill(trackShape);
        g2.setClip(clip);
    }

    /**
     * design the slider button
     * @param g the graphics
     */
    @Override
    public void paintThumb(final Graphics g) {
        if (thumbImage != null) {
            int w = thumbRect.width;
            int h = thumbRect.height;
            // Draw scaled image to fit inside thumbRect
            g.drawImage(thumbImage, thumbRect.x, thumbRect.y, w, h, null);
        } else {
            // Fallback if image not available
            g.setColor(new Color(244, 151, 142));
            g.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
        }
    }

    /**
     * this method does nothing
     * @param g the graphics
     */
    @Override
    public void paintFocus(final Graphics g) {}
}

