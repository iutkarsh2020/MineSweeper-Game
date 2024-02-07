package a01_data_structures.s2_stacks;
import java.util.Scanner;
import java.io.File;
import javax.print.DocFlavor;
public class Configuration {


    public static int ROWS;
    public static int COLS;
    public static int CELL_SIZE;
    public static int MINES;
    public static int BOARD_WIDTH;
    public static int BOARD_HEIGHT;
    public static String STATUS_COVERED;
    public static String STATUS_OPENED;
    public static String STATUS_MARKED;
    public static String STATUS_WRONGLY_MARKED;
    public static void loadParameters(String filename) {
        File F = new File(filename);
        Scanner s;
        try{
            s = new Scanner(F);
        }
        catch(Exception e){
            System.out.println("THE FILE COULD NOT BE OPENED");
            return;
        }
        while(s.hasNext()) {
            String add = s.next();
            if(add.equals("ROWS")) {
                int a = s.nextInt();
                ROWS = a;
            }
            else if(add.equals("COLS")) {
                int a = s.nextInt();
                COLS = a;
            }
            else if(add.equals("CELL_SIZE")) {
                int a = s.nextInt();
                CELL_SIZE = a;
            }
            else if(add.equals("MINES")) {
                int a = s.nextInt();
                MINES = a;
            }
            else if(add.equals("STATUS_COVERED")) {
                String sdr = s.next();
                STATUS_COVERED = sdr;
            }
            else if(add.equals("STATUS_OPENED")) {
                String sdr = s.next();
                STATUS_OPENED = sdr;
            }
            else if(add.equals("STATUS_MARKED")) {
                String sdr = s.next();
                STATUS_MARKED = sdr;

            }
            else if(add.equals("STATUS_WRONGLY_MARKED")) {
                String sdr = s.next();
                STATUS_WRONGLY_MARKED = sdr;
            }
            else{
                System.out.println("Error!");
                break;
            }
        }
        BOARD_WIDTH = COLS * Configuration.CELL_SIZE + 1;
        BOARD_HEIGHT = ROWS * Configuration.CELL_SIZE + 1;



    }

    /* ADD YOUR CODE HERE */

}
