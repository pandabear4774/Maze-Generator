import java.awt.*;
import javax.swing.*;
public class FrameStuff extends JFrame{
    private PanelStuff panel;
    public FrameStuff(){
        int rows = Integer.valueOf(JOptionPane.showInputDialog("Enter Row Number"));
        int collumns = Integer.valueOf(JOptionPane.showInputDialog("Enter Collumn Number"));
        panel = new PanelStuff(rows, collumns, 40);
        MouseInput mouse = new MouseInput();
        addMouseListener(mouse);
        setSize(80 + rows*40,80 + collumns*40);
        setTitle("MAZE GENERATOR");
        add(panel);
        setLocationRelativeTo(null);
    }
    public void reset(){
        int rows = Integer.valueOf(JOptionPane.showInputDialog("Enter Row Number"));
        int collumns = Integer.valueOf(JOptionPane.showInputDialog("Enter Collumn Number"));
        panel = new PanelStuff(rows, collumns, 40);
        setSize(80 + rows*40,80 + collumns*40);
        setTitle("MAZE GENERATOR2");
        setLocationRelativeTo(null);
    }
}