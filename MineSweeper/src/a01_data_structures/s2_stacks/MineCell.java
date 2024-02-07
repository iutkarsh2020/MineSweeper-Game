package a01_data_structures.s2_stacks;

import javax.swing.*;
import java.awt.*;
public class MineCell {
    private String status;
    private int horizontalPosition;
    private int verticalPosition;
    private ImageIcon a;
    private int _row;
    private int _col;
    private Image image;
    // will need to see this constructor
    public MineCell(int row,int column){
        status = Configuration.STATUS_COVERED;
        _row = row;
        _col = column;
        horizontalPosition = _col*Configuration.CELL_SIZE;
        verticalPosition = _row*Configuration.CELL_SIZE;
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
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Image getImage() {
        String sta = getStatus();
        sta = sta.toLowerCase();
        if(status.equals(Configuration.STATUS_COVERED)){
            a = new ImageIcon("img//covered_cell.png");
        }
        else if(status.equals(Configuration.STATUS_MARKED)){
            a = new ImageIcon("img//marked_cell.png");
        }
        else if(status.equals(Configuration.STATUS_OPENED)){
            a = new ImageIcon("img//mine_cell.png");
        }

        return image = a.getImage();
    }


}
