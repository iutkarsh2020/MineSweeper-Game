package a01_data_structures.s2_stacks;

import java.util.Random;
import java.awt.*;
    class Minefield {

    private Object[][] field;
    //  private int[][] adjmat;
    private int numins;
    public Minefield(){
        field = new Object[10][10];
        numins = 10;
//    adjmat = new int[10][10];
        mineLaying(numins); // first 10 mines are distributed in the 10*10 matrix
        addInfoCells();// these infocells are placed inside the 2D array named field
    }
    public int getNumCells(){
        return field.length+field[0].length;
    }
    public Minefield(int numRows, int numColumns, int numMines) {
        field = new Object[numRows][numColumns];// field 2D array has been created
        numins = numMines; //  number of mines in this array should be equal to numins now
        mineLaying(numins); // mineLaying has taken care of randomly puting these numin mines inside the field array
        addInfoCells(); // This method will create infocells in field array and will use the
    }
    public void mineLaying(int numOfMines) {
        // creates numins number of mines in the 2D array named field
        Random r = new Random();
        int i = 0;
        while(i < numins) {
            int I = r.nextInt(field.length);
            int J = r.nextInt(field[0].length);
            if(field[I][J] == null){
                field[I][J] = new MineCell(I,J);
                i++;
            }
        }
    }
    //helper function to count number of adjacent mines of each infocell
    public int countadjMines(int I,int J){
        if(field[I][J]==null){
            int count = 0;
            if(I-1>=0 && J-1>=0){
                if(field[I-1][J-1]!=null)
                    count++;
            }
            if(J-1>=0){
                if(field[I][J-1]!=null)
                    count++;
            }
            if(I+1<field.length && J-1>=0){
                if(field[I+1][J-1]!=null)
                    count++;
            }
            if(I-1>=0){
                if(field[I-1][J]!=null)
                    count++;
            }
            if(I+1<field.length){
                if(field[I+1][J]!=null)
                    count++;
            }
            if(I-1>=0 && J+1<field[0].length){
                if(field[I-1][J+1]!=null)
                    count++;
            }
            if(J+1<field[0].length){
                if(field[I][J+1]!=null)
                    count++;
            }
            if(I+1<field.length && J+1<field[0].length){
                if(field[I+1][J+1]!=null)
                    count++;
            }
            return count;
        }

        return 0;


    }
    public void addInfoCells(){
        // adds infocells to the 2D matrix named field
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[0].length; j++) {
                if(field[i][j] == null) {
                    int adj = countadjMines(i,j);
                    field[i][j] = new InfoCell(i,j,adj);
                }
            }
        }
    }
    public void draw(Graphics g) {
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[0].length; j++) {
                if(field[i][j] instanceof MineCell) {
                    MineCell mc = (MineCell) field[i][j];
                    mc.draw(g);
                }
                else{
                    InfoCell ic = (InfoCell) field[i][j];
                    ic.draw(g);
                }
            }
        }
    }
    public Object getCellByScreenCoordinates(int x, int y) {
        double size = Configuration.CELL_SIZE;
        int i = (int)(y / size);
        int j = (int)(x / size);
        return field[i][j];
    }
    public Object getCellByRowCol(int row, int col) {
        return field[row][col];
    }
    public void setMineCell(int row, int col, MineCell cell) {
        if(cell==null){
            field[row][col] = null;
        }
        field[row][col] = cell;
    }
    public void setInfoCell(int row, int col, InfoCell cell) {
        if(cell==null){
            field[row][col] = null;
        }
        field[row][col] = cell;
    }
    public int countCellsWithStatus(String status) {
        // This method will count the number of cells with status as same as String status
        int count = 0;
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[0].length; j++) {

                if(field[i][j] instanceof MineCell){
                    MineCell cell = (MineCell) field[i][j];
                    if(cell.getStatus().equals(status)) {
                        count = count + 1;
                    }
                }
                else{
                    InfoCell cell = (InfoCell) field[i][j];
                    if(cell.getStatus().equals(status)) {
                        count = count + 1;
                    }
                }

            }
        }
        return count;
    }
    public void openCells(Object cell) {
        //here we are not taking care of the clicks
        // if a cell InfoCell and number of adjacent mines are 0 then we open all the adjecent cells  as well
        if(cell instanceof InfoCell) {
            InfoCell cel = (InfoCell) cell;
            cel.setStatus(Configuration.STATUS_OPENED);

            if(cel.getNumOfAdjacentMines() == 0) {
                int I =0,J=0;
                for(int i=0;i<field.length;i++){
                    for(int j=0;j<field[0].length;j++){
                        if(field[i][j]==cel){
                            I = i;
                            J = j;
                            break;
                        }
                    }
                }
                if(I-1>=0 && J-1>=0){
                    cel = (InfoCell)field[I-1][J-1];
                    cel.setStatus(Configuration.STATUS_OPENED);
                }
                if(J-1>=0){
                    cel = (InfoCell)field[I][J-1];
                    cel.setStatus(Configuration.STATUS_OPENED);
                }
                if(I+1<field.length && J-1>=0){
                    cel = (InfoCell)field[I+1][J-1];
                    cel.setStatus(Configuration.STATUS_OPENED);
                }
                if(I-1>=0){
                    cel = (InfoCell)field[I-1][J];
                    cel.setStatus(Configuration.STATUS_OPENED);
                }
                if(I+1<field.length){
                    cel = (InfoCell)field[I+1][J];
                    cel.setStatus(Configuration.STATUS_OPENED);
                }
                if(I-1>=0 && J+1<field[0].length){
                    cel = (InfoCell)field[I-1][J+1];
                    cel.setStatus(Configuration.STATUS_OPENED);
                }
                if(J+1<field[0].length){
                    cel = (InfoCell)field[I][J+1];
                    cel.setStatus(Configuration.STATUS_OPENED);
                }
                if(I+1<field.length && J+1<field[0].length){
                    cel = (InfoCell)field[I+1][J+1];
                    cel.setStatus(Configuration.STATUS_OPENED);
                }
            }
        }
    }
    public void revealIncorrectMarkedCells() {// if a cell is InfoCell and its status was STATUS_MARKED it means user marked it as a mine and it was incorrect
        for(int i=0;i<field.length;i++){
            for(int j=0;j<field[0].length;j++){
                if((field[i][j] instanceof InfoCell)  ){
                    InfoCell cell = (InfoCell) field[i][j];
                    if( cell.getStatus().equals(Configuration.STATUS_MARKED))
                        cell.setStatus(Configuration.STATUS_WRONGLY_MARKED);
                }
            }
        }
    }
}
