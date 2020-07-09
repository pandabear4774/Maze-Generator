import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class PanelStuff extends JPanel{
    public static int rowNumber;
    public static int collumnNumber;
    private static int squareSize;
    public static Square[][] squares;
    public static ArrayList<Stack> stack = new ArrayList<Stack>();
    public static int[] currentLocation = new int[2];
    public static int numberOfUncheckedBoxes;
    public PanelStuff(int row, int collumn, int size){
        setSize(80 + row*40,80 + collumn*40);
        rowNumber = row;
        collumnNumber = collumn;
        squareSize = size;
        squares = new Square[rowNumber][collumnNumber];
        for(int i = 0; i < rowNumber; i++){
            for(int j = 0; j < collumnNumber; j++){
                squares[i][j] = new Square(squareSize + squareSize*i, squareSize + squareSize*j,squareSize,i,j);
            }
        }
        numberOfUncheckedBoxes = row*collumn;
    }
    public void CreateGrid(){
        try{
            squares[currentLocation[0]][currentLocation[1]].setVisited(true);
            squares[currentLocation[0]][currentLocation[1]].findNewTarget();
            repaint();
            Thread.sleep(50);
        } catch(Exception e){
            System.out.println("Maze Generation Done");
        }
    }
    public void paint(Graphics g) {
        g.setColor(new Color(0,255,0,100));
        g.fillRect(40,5,80,30);
        g.setColor(Color.BLACK);
        g.drawString("RESTART",55,25);
        for(int i = 0; i < rowNumber; i++){
            for(int j = 0; j < collumnNumber; j++){
                squares[i][j].paint(g);
            }
        }
        if(numberOfUncheckedBoxes > 0){
            CreateGrid();
        }
    }
}