package a01_data_structures.s2_stacks;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Dimension;

public class Board extends JPanel
{
    private int _boardHeight;
    private int _boardWidth;
    private int _mines;
    private JLabel sb;
    private Minefield mf;
    private boolean decide;
    public Board(int height, int width, int mines, JLabel statusbar)
    {
        _boardWidth = width;
        _boardHeight= height;
        _mines = mines;
        sb = statusbar;
        decide = false;
        mf = new Minefield(Configuration.ROWS,Configuration.COLS,Configuration.MINES);
  /*
   Your code goes here

   Do not edit the two lines below
  */
        setPreferredSize(new Dimension(Configuration.BOARD_WIDTH, Configuration.BOARD_HEIGHT));
        addMouseListener(new MouseReader(this));
    }
    // This method is for getting Minefield ojbect
    public Minefield getMinefield() {
        return mf;
    }


    @Override
    public void paintComponent(Graphics g)
    {
        mf.draw(g);

        // Here, you must invoke method draw(g) of the Minefield object
    }

    public void mouseClickOnLocation(int x, int y, String button)
    {
        if(x>Configuration.CELL_SIZE*Configuration.COLS)
            return;
        if(y>Configuration.CELL_SIZE*Configuration.ROWS)
            return;
        //Your code goes here.
        if(decide==true) // if game is over, there is no point in clicking
            return;
        Object cell = mf.getCellByScreenCoordinates(x,y);
        //now it contains a cell object which can be infocell or a minecell
        //row and col are now indexes to be used in minefield class
        if(button.equals("left")){
            // youwant to uncover the covered cell
            if(cell instanceof MineCell ){
                // it was a MineCell and you clicked left,it is gameOver
                MineCell cel = (MineCell) cell;
                // now we can use methods of MineCell class
                if(cel.getStatus().equals(Configuration.STATUS_COVERED)){
                    //game over
                    cel.setStatus(Configuration.STATUS_OPENED);
                    decide = true;
                    //once it is gamePver you draw a new image of the cel
                    //cel.draw(cel.getImage(x,y,null);
                    setStatusbar("Game over - You lost!");

                }
            }
            else{
                // the cell where used clicked left is a InfoCell
                InfoCell cel = (InfoCell) cell;
                if(cel.getStatus().equals(Configuration.STATUS_COVERED)){
                    mf.openCells(cell);
                    //this will open this cell and adjacent cells if adjacent mines are 0
                    // if all infocells are opened then game over and player won the game
                    int num = mf.countCellsWithStatus(Configuration.STATUS_OPENED);
                    // it will tell how many infocells are opened
                    int totalCells = mf.getNumCells();
                    if(totalCells-num==Configuration.MINES){
                        //player won the game
                        decide = true;
                        setStatusbar("Game over - You won!");
                    }

                }
            }
            //redraw the board here
        }
        else if(button.equals("right")){
            // used for marking a cell as a minecell
            if(cell instanceof MineCell ){
                MineCell cel = (MineCell) cell;
                if(cel.getStatus().equals(Configuration.STATUS_COVERED)){
                    removeMine();//1st we decrease number of mines
                    cel.setStatus(Configuration.STATUS_MARKED);// mark this cell as marked
                    //now we need to change the  image of this cell
                }
                else if(cel.getStatus().equals(Configuration.STATUS_MARKED)){
                    // you clicked on a cell which was marked and now you want to unmark it
                    addMine();
                    cel.setStatus(Configuration.STATUS_COVERED);
                    //now we need to change the icon of the cell
                }
            }
            else{
                InfoCell cel = (InfoCell) cell;
                //selected cell is infocell
                if(cel.getStatus().equals(Configuration.STATUS_COVERED)){
                    removeMine();//1st we decrease number of mines
                    cel.setStatus(Configuration.STATUS_MARKED);// mark this cell as marked
                    //now we need to change the  image of this cell
                }
                else if(cel.getStatus().equals(Configuration.STATUS_MARKED)){
                    // you clicked on a cell which was marked and now you want to unmark it
                    addMine();
                    cel.setStatus(Configuration.STATUS_COVERED);
                    //now here we need to change the icon of the cell
                }
            }
        }
        else if(button.equals("")){
            // mouse click but it is not a left click or a right click
        }
        //Do not remove the invocation of repaint() below

        repaint();
    }
    public boolean isGameOver() {
        return decide;
        // taking care of the value of decide

    }
    public void setStatusbar(String text){
        sb.setText(text);

    }
    public String getStatusbar(){
        return sb.getText();
    }
    public boolean removeMine() {
        // if number of mines are less equal to 0, the player cannot mark any other cell as a minecell else marking is possible
        if(_mines<=0){
            setStatusbar("Invalid action");
            return false;
        }
        else{
            _mines--;
            setStatusbar(""+_mines+" mines remaining");
            return true;
        }
    }
    public boolean addMine(){
        // right clicking on already marked cells will remove their marks
        if(_mines>=Configuration.MINES){
            setStatusbar("Invalid action");
            return false;
        }
        _mines++;
        setStatusbar(""+_mines+" mines remaining");
        return true;
    }
}
