package projet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

public class TP2 {
    public static final int FRAME_WIDTH = 938;
    public static final int FRAME_HEIGHT = 650;
    public static final int MENU_HEIGHT = 500;
    public static final int MENU_WIDTH = 200;

    private JFrame frame;
    
    /**
     * Constructor.
     */
    public TP2() {
        // Set the aplication window :
        frame = new JFrame("3iL - Graphic interfaces & 3D - TP2");
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set horizontal menu :
        JMenuBar horizontalMenu = new JMenuBar();
        frame.setJMenuBar(horizontalMenu);
        JMenu fileMenu = new JMenu("File");
        JMenuItem closeMenuItem = new JMenuItem("Close", new ImageIcon("images/logo-icon.png"));
        fileMenu.add(closeMenuItem);
        horizontalMenu.add(fileMenu);
        JMenu menuEdition = new JMenu("About");
        JMenuItem helpMenuItem = new JMenuItem("Help", new ImageIcon("images/botbtn2low.png"));
        menuEdition.add(helpMenuItem);
        horizontalMenu.add(menuEdition);

        // Set listeners on horizontal menu items :
        closeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        helpMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "This is the TP2 about Java and OpenGL.");
            }
        });

        // Set lateral menu :
        JPanel lateralMenu = new JPanel();
        lateralMenu.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        lateralMenu.setBorder(BorderFactory.createEtchedBorder());
        frame.add(lateralMenu, BorderLayout.EAST);

        // Set button in lateral menu :
        JButton rotateButton = new JButton("Start/Stop rotation");
        lateralMenu.add(rotateButton);

        //Set comboBox
        Object[] elements = new Object[]{"Carré","Triangle","Cube"};
        JComboBox comboBox = new JComboBox(elements);
        lateralMenu.add(comboBox);

        //Set liste des objets
        ArrayList<GLEventListener> liste = new ArrayList<GLEventListener>();

        //Canvas
        Cube c = new Cube();
        Square s = new Square();
        Triangle t = new Triangle();

        // Set the OpenGL zone :
        
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(c);
        liste.add(c);
        canvas.requestFocus();
        frame.add(canvas, BorderLayout.CENTER);
        
        // Set an animator manager :
        Animator animator = new Animator(canvas);
        animator.start();

        // Set listeners on lateral menu buttons :
        rotateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (animator.isStarted()) {
                    animator.stop();
                } else {
                    animator.start();
                }
            }
        });

        // Set listeners on lateral comboBox :
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if ((String)comboBox.getSelectedItem() == "Carré") {
                    canvas.removeGLEventListener((liste.get(liste.size()-1)));
                    
                    canvas.addGLEventListener(s);
                    //canvas.repaint();
                    canvas.requestFocus();
                    frame.add(canvas, BorderLayout.CENTER);
                } else if ((String)comboBox.getSelectedItem() == "Triangle") {
                    canvas.removeGLEventListener((liste.get(liste.size()-1)));
                    canvas.addGLEventListener(t);
                    //canvas.repaint();
                    canvas.requestFocus();
                    frame.add(canvas, BorderLayout.CENTER);
                } else if ((String)comboBox.getSelectedItem() == "Cube") {
                    canvas.removeGLEventListener((liste.get(liste.size()-1)));
                    canvas.addGLEventListener(c);
                    //canvas.repaint();
                    canvas.requestFocus();
                    frame.add(canvas, BorderLayout.CENTER);
                }
            }
        });
        

        // Show the JFrame :
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Entry point.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TP2();
            }
        });
    }
}
