import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.BoxLayout;

/**
 * FractalGui is responsible for gathering user data via the settings dialog.
 * When the user changes any settings, all settings information
 * is sent to the Subject (real-time updates).
 */
public class FractalGUI extends JFrame implements ChangeListener {
    /**  instantiate branches */
    private final FractalSubject subject;
    /**  instantiate slider for recursion */
    private final JSlider recursionSlider;
    /**  instantiate slider for ratios */
    private final JSlider ratioSlider;
    /**  instantiate slider for left angle */
    private final JSlider leftSlider;
    /**  instantiate slider for right angle */
    private final JSlider rightSlider;
    /**  instantiate slider for branch length*/
    private final JSlider lengthSlider;
    /**  instantiate slider for branch width*/
    private final JSlider widthSlider;
    /**  instantiate color of trunk */
    private Color trunkColor;
    /**  instantiate color of leaves */
    private Color leafColor;
    /** background for gui and sliders */
    Color GUI_BACKGROUND = new Color(142, 192, 212);
    /**  instantiate size of monitor */
    private final Dimension screenSize;
    /** instantiate images  */
    Image image;


    /**
     * Constructor to build settings UI
     * @param subject each branch
     */
    public FractalGUI(FractalSubject subject) {
        this.subject = subject;

        try
        {
            image = javax.imageio.ImageIO.read(new File("resources/boarder1.png"));
        }
        catch (Exception e) { /*handled in paintComponent()*/ }

        //set up JFrame
        Toolkit toolKit = getToolkit();
        screenSize = toolKit.getScreenSize();
        int width = screenSize.width/6;
        int height = screenSize.height-(screenSize.height/5);
        setSize(width,height);
        setLocation(screenSize.width-(width*5),100);
        setTitle("Fractal Settings");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        SoundEffect.MUSIC.play();

        //panel
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        getContentPane().add(panel);
        panel.setBackground(GUI_BACKGROUND);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50,50,100,50));

        // add sliders
        recursionSlider = addASlider(4,20,2,2,17);
        panel.add(addALabel("Recursion depth"));
        panel.add(recursionSlider);
        recursionSlider.addChangeListener(this);
        panel.add(Box.createVerticalStrut(20));

        ratioSlider = addASlider(40,80,10,2,70);
        panel.add(addALabel("Child to Parent Ratio"));
        panel.add(ratioSlider);
        ratioSlider.addChangeListener(this);
        panel.add(Box.createVerticalStrut(20));

        leftSlider = addASlider(0,90,10,2,20);
        panel.add(addALabel("Left Child Angle"));
        panel.add(leftSlider);
        leftSlider.addChangeListener(this);
        panel.add(Box.createVerticalStrut(20));

        rightSlider = addASlider(0,90,10,2,45);
        panel.add(addALabel("Right Child Angle"));
        panel.add(rightSlider);
        rightSlider.addChangeListener(this);
        panel.add(Box.createVerticalStrut(20));

        lengthSlider = addASlider(100,400,100,4,200);
        panel.add(addALabel("Trunk Length"));
        panel.add(lengthSlider);
        lengthSlider.addChangeListener(this);
        panel.add(Box.createVerticalStrut(20));

        widthSlider = addASlider(10,50,10,2,30);
        panel.add(addALabel("Trunk Width"));
        panel.add(widthSlider);
        widthSlider.addChangeListener(this);
        panel.add(Box.createVerticalStrut(20));

        //Trunk color button
        JButton trunkButton = new JButton("Trunk Color");
        trunkButton.setBackground(new Color(252, 213, 206));
        trunkButton.setFont(new Font("Lucida Fax", Font.BOLD, 12));
        trunkColor = new Color(82, 57, 51);
        trunkButton.addActionListener(e -> {
                Color selectedColor = JColorChooser.showDialog(null, "Pick Color of Tree", trunkColor);
                if (selectedColor != null) {
                    trunkColor = selectedColor;
                    stateChanged(null);
                }
        });
        panel.add(trunkButton);
        panel.add(Box.createVerticalStrut(20));

        //Leaf color button
        JButton leafButton = new JButton("Leaf Color");
        leafButton.setBackground(new Color(252, 213, 206));
        leafButton.setFont(new Font("Lucida Fax", Font.BOLD, 12));
        leafColor = new Color(227, 218, 225);
        leafButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(null,"Pick Color of Leaves",leafColor);
            if (selectedColor != null) {
                leafColor = selectedColor;
                stateChanged(null);
            }
        });
        panel.add(leafButton);
        panel.add(Box.createVerticalStrut(20));

        //random button
        JButton randButton = new JButton("Randomize");
        randButton.setBackground(new Color(252, 213, 206));
        randButton.setFont(new Font("Lucida Fax", Font.BOLD, 12));
        randButton.addActionListener(e -> {
            Random random = new Random();
            trunkColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            leafColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            recursionSlider.setValue(randomValue(recursionSlider));
            ratioSlider.setValue(randomValue(ratioSlider));
            leftSlider.setValue(randomValue(leftSlider));
            rightSlider.setValue(randomValue(rightSlider));
            lengthSlider.setValue(randomValue(lengthSlider));
            widthSlider.setValue(randomValue(widthSlider));
        });
        panel.add(randButton);

        //finalize
        setVisible(true);
        stateChanged(null);
    }
    //---------------------------------------------------------------------------------------------------------------------------
    //                        Helper Methods
    //--------------------------------------------------------------------------------------------------------------------

    /**
     * breezy slider adder
     * @param min        minimum value
     * @param max        maximum value
     * @param increment  increment of slider
     * @param tick       increment in between increments
     * @param value      value to begin sliders at
     * @return           slider widget
     */
    public static JSlider addASlider(int min, int max,int increment,int tick, int value) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL,min,max,value) {
            @Override
            public void updateUI() {
                setUI(new CustomSliderUI(this));
            }
        };
        slider.setMajorTickSpacing(increment);
        slider.setMinorTickSpacing(increment/tick);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(new Color(142, 192, 212));
        slider.setForeground(Color.WHITE);
        slider.setPreferredSize(new Dimension(30,70));
        return slider;
    }

    /**
     * label maker for sliders
     * @param label   name of slider
     * @return        label for slider
     */
    public static JLabel addALabel (String label){
        JLabel aLabel = new JLabel(label,JLabel.CENTER);
        aLabel.setFont(new Font("Lucida Fax", Font.BOLD, 12));
        aLabel.setForeground(Color.WHITE);

        return aLabel;
    }

    /**
     * Invoked when the target of the listener has changed its state.
     *
     * @param e a ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {

        int recDepth = recursionSlider.getValue();
        int childRatio = ratioSlider.getValue();
        int leftAngle = leftSlider.getValue();
        int rightAngle = rightSlider.getValue();
        int trunkLength = lengthSlider.getValue();
        int trunkWidth = widthSlider.getValue();
        subject.setData(recDepth,childRatio,leftAngle, rightAngle,
                trunkLength, trunkWidth, trunkColor, leafColor, screenSize);
    }

    /**
     * generates a random value within a slider
     * @param   slider slider for UI
     * @return  a random value for the slider
     */
    private int randomValue(JSlider slider) {
        int min = slider.getMinimum();
        int max = slider.getMaximum();
        return (int)(Math.random() * (max - min + 1)) + min;
    }
        /**play sound effects */

    public enum SoundEffect {
        /**  Background Music */
        MUSIC("resources/bgMusic.wav");

        /** Nested class for specifying volume */
        public enum Volume {
            /** Sound levels MUTE, LOW, MEDIUM, HIGH */
            MUTE,
            /**  volume level low  */
            LOW
        }
        /** set volume level */
        public static final Volume volume = Volume.LOW;

        /** Each sound effect has its own clip, loaded with its own sound file. */
        private final Clip clip;

        /** Constructor to construct each element of the enum with its own sound file.
         * @param soundFileName name of soundfile
         */
        SoundEffect(String soundFileName) {
            try {
                File audioFile = new File(soundFileName);
                // Set up an audio input stream piped from the sound file.
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
                // Get a clip resource.
                clip = AudioSystem.getClip();
                // Open audio clip and load samples from the audio input stream.
                clip.open(audioInputStream);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                throw new IllegalArgumentException();
            }
        }

        /** Play or Re-play the sound effect from the beginning, by rewinding. */
        public void play() {
            if (volume != Volume.MUTE) {
                if (clip.isRunning())
                    clip.stop();   // Stop the player if it is still running
                clip.setFramePosition(0); // rewind to the beginning
                clip.start();// Start playing
            }
        }
    }
}
