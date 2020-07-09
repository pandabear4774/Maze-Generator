import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class MouseInput implements MouseListener {
    public int xMouseLocation;
    public int yMouseLocation;
    
    public void mousePressed(MouseEvent e) 
    { 
  
    } 
  
    // this function is invoked when the mouse is released 
    public void mouseReleased(MouseEvent e) 
    { 
  
    } 
  
    // this function is invoked when the mouse exits the component 
    public void mouseExited(MouseEvent e) 
    { 
        
    } 
  
    // this function is invoked when the mouse enters the component 
    public void mouseEntered(MouseEvent e) 
    { 
  
    } 
  
    // this function is invoked when the mouse is pressed or released 
    public void mouseClicked(MouseEvent e) 
    { 
        xMouseLocation = e.getX();
        yMouseLocation = e.getY();
        if(xMouseLocation >= 40 && xMouseLocation <= 120 && yMouseLocation >= 30 && yMouseLocation <= 60){
            System.out.println("Btn Clicked");
            Runner.frame.reset();
        }
    } 
}