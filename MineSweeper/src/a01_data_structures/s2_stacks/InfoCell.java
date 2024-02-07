package a01_data_structures.s2_stacks;

/**
 * Auto Generated Java Class.
 */
import javax.swing.*;
import java.awt.*;
public class InfoCell {
    private int _row;
    private int _col;
    private int _numadj;
    private String _status;
    private int horizontalPosition;
    private int verticalPosition;
    private Image image;
    private ImageIcon a;
    /* ADD YOUR CODE HERE */
    public InfoCell(int row, int column, int numOfAdjacentMines){
        _status = Configuration.STATUS_COVERED;
        _row = row;
        _col = column;
        horizontalPosition = column*Configuration.CELL_SIZE;
        verticalPosition = row * Configuration.CELL_SIZE;
        _numadj = numOfAdjacentMines;
    }
    public void draw(Graphics g){
        image = getImage();
        g.drawImage(image, horizontalPosition, verticalPosition, null);

    }
    public int getHorizontalPosition(){
        return horizontalPosition;
    }
    public int getVerticalPosition(){
        return verticalPosition;
    }
    public String getStatus() {
        return _status;
    }
    public void setStatus(String status){
        if(status.equals(Configuration.STATUS_OPENED) || status.equals(Configuration.STATUS_COVERED) || status.equals(Configuration.STATUS_MARKED) || status.equals(Configuration.STATUS_WRONGLY_MARKED))
            _status = status;
    }
    public Image getImage() {
        String sta = getStatus();
        sta = sta.toLowerCase();
        if(_status.equals(Configuration.STATUS_COVERED)){
            a = new ImageIcon("img//covered_cell.png");
        }
        else if(_status.equals(Configuration.STATUS_MARKED)){
            a = new ImageIcon("img//marked_cell.png");
        }
        else if(_status.equals(Configuration.STATUS_WRONGLY_MARKED)){
            a = new ImageIcon("img//wrong_mark.png");
        }
        else if(_status.equals(Configuration.STATUS_OPENED)){
            int num = getNumOfAdjacentMines();
            if(num==0){
                a = new ImageIcon("img//info_0.png"  );
            }
            else if(num==1){
                a = new ImageIcon("img//info_1.png");
            }
            else if(num==2){
                a = new ImageIcon("img//info_2.png");
            }
            else if(num==3){
                a = new ImageIcon("img//info_3.png");
            }
            else if(num==4){
                a = new ImageIcon("img//info_4.png");
            }
            else if(num==5){
                a = new ImageIcon("img//info_5.png");
            }
            else if(num==6){
                a = new ImageIcon("img//info_6.png");
            }
            else if(num==7){
                a = new ImageIcon("img//info_7.png");
            }
            else if(num==8){
                a = new ImageIcon("img//info_8.png");
            }
        }

        return a.getImage();
    }

    public int getNumOfAdjacentMines(){
        return _numadj;
    }
}
