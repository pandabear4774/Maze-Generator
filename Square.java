import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Square{
    private int xArrayLocation;
    private int yArrayLocation;
    private int xLocation;
    private int yLocation;
    private int squareSize;
    private boolean[] wallExist;
    private boolean visited;
    private boolean current;
    public int numberOfNeighbors;
    private ArrayList<Square> neighbors = new ArrayList<Square>();
    private ArrayList<Integer> neighborLocation = new ArrayList<Integer>();
    private int newNeighbor;
    public Square(int x, int y, int size, int xArray, int yArray){
        xLocation = x;
        yLocation = y;
        squareSize = size;
        wallExist = new boolean[4];
        for(int i = 0; i < 4; i++){
            wallExist[i] = true;
        }
        visited = false;
        xArrayLocation = xArray;
        yArrayLocation = yArray;
    }
    public void setWallExistFalse(int index){
        wallExist[index] = false;
    }
    public int getXArrayLocation(){
        return xArrayLocation;
    }
    public int getYArrayLocation(){
        return yArrayLocation;
    }
    public void setVisited(boolean newValue){
        visited = newValue;
    }
    public boolean getVisited(){
        return visited;
    }
    public void paint(Graphics g) {
        //top wall
        if(wallExist[0]){
            g.drawLine(xLocation,yLocation,xLocation+squareSize,yLocation);
        }
        //left wall
        if(wallExist[1]){
            g.drawLine(xLocation,yLocation,xLocation,yLocation + squareSize);
        }
        //right wall
        if(wallExist[2]){
           g.drawLine(xLocation+squareSize,yLocation,xLocation + squareSize, yLocation + squareSize); 
        }
        //bottom wall
        if(wallExist[3]){
            g.drawLine(xLocation,yLocation + squareSize, xLocation + squareSize, yLocation + squareSize);
        }
        //colors visited squares
        if(visited){
            if(current){
                g.setColor(new Color(255,0,0,100));
            } else {
                g.setColor(new Color(0,0,255,100));
            }
            g.fillRect(xLocation,yLocation,squareSize,squareSize);
            g.setColor(Color.BLACK);
           
        }
    }
    public void findNewTarget(){
        numberOfNeighbors = 0;
        neighbors.clear();
        neighborLocation.clear();
        //right neighbor
        if(xArrayLocation < PanelStuff.rowNumber-1 && !PanelStuff.squares[xArrayLocation+1][yArrayLocation].getVisited()){
            numberOfNeighbors++;
            System.out.println("RIGHT");
            neighbors.add(PanelStuff.squares[xArrayLocation+1][yArrayLocation]);
            neighborLocation.add(0);
        }
        //top neighbor
        if(yArrayLocation > 0 && !PanelStuff.squares[xArrayLocation][yArrayLocation-1].getVisited()){
            numberOfNeighbors++;
            System.out.println("TOP");
            neighbors.add(PanelStuff.squares[xArrayLocation][yArrayLocation - 1]);
            neighborLocation.add(1);
        }
        //left neighbor
        if(xArrayLocation > 0 && !PanelStuff.squares[xArrayLocation-1][yArrayLocation].getVisited()){
            numberOfNeighbors++;
            System.out.println("LEFT");
            neighbors.add(PanelStuff.squares[xArrayLocation-1][yArrayLocation]);
            neighborLocation.add(2);
        }
        //bottom neighbor
        if(yArrayLocation < PanelStuff.collumnNumber -1  && !PanelStuff.squares[xArrayLocation][yArrayLocation+1].getVisited()){
            numberOfNeighbors++;
            System.out.println("BOTTOM");
            neighbors.add(PanelStuff.squares[xArrayLocation][yArrayLocation+1]);
            neighborLocation.add(3);
        }
        if(numberOfNeighbors > 0){
            newNeighbor = new Random().nextInt(numberOfNeighbors);
            PanelStuff.currentLocation[0] = neighbors.get(newNeighbor).getXArrayLocation();
            PanelStuff.currentLocation[1] = neighbors.get(newNeighbor).getYArrayLocation();
            removeWall();
            PanelStuff.numberOfUncheckedBoxes--;
            PanelStuff.stack.add(new Stack(xArrayLocation,yArrayLocation));
        } else {
            System.out.println("NO NEIGHBORS");
            setVisited(true);
            PanelStuff.currentLocation[0] = PanelStuff.stack.get(PanelStuff.stack.size()-1).getX();
            PanelStuff.currentLocation[1] = PanelStuff.stack.get(PanelStuff.stack.size()-1).getY();
            PanelStuff.stack.remove(PanelStuff.stack.size()-1);
        }
        this.current = false;
        PanelStuff.squares[PanelStuff.currentLocation[0]][PanelStuff.currentLocation[1]].setCurrentTrue();
        System.out.println(PanelStuff.currentLocation[0] + " " + PanelStuff.currentLocation[1]);
    }
    public void setCurrentTrue(){
        current = true;
    }
    public void removeWall(){
        int wallIndex = neighborLocation.get(newNeighbor);
        System.out.println("Neighbors: " + wallIndex);
        switch(wallIndex){
            case(0):
                //rightWall
                wallExist[2] = false;
                PanelStuff.squares[PanelStuff.currentLocation[0]][PanelStuff.currentLocation[1]].setWallExistFalse(1);
                System.out.println("Break Right Wall");
                break;
            case(1):
                //top wall
                wallExist[0] = false;
                PanelStuff.squares[PanelStuff.currentLocation[0]][PanelStuff.currentLocation[1]].setWallExistFalse(3);
                System.out.println("Break Top Wall");
                break;
            case(2):
                //left wall
                wallExist[1] = false;
                PanelStuff.squares[PanelStuff.currentLocation[0]][PanelStuff.currentLocation[1]].setWallExistFalse(2);
                System.out.println("Break Left Wall");
                break;
            case(3):
                //bottom wall
                wallExist[3] = false;
                PanelStuff.squares[PanelStuff.currentLocation[0]][PanelStuff.currentLocation[1]].setWallExistFalse(0);
                System.out.println("Break Bottom Wall");
                break;
            default:
                System.out.println("WALL NOT HERE");
                break;
        }
    }
}