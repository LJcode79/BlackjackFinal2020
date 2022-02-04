import java.io.*;
import java.lang.*;
import java.util.*;
public class createFile
{
    private Formatter f;
    public void openFile(){
        //Text I/O: Opens a new print writer
        try{
            f = new Formatter("lastRoundLog.txt");
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }
    /**
     * Adds the last round to the text file "lastRoundLog.txt"
     * @param String playerNmae
     * @param double MoneyChange
     */
    public void addRound(String playerName, double moneyChange){
        f.format("%-10s%-10f%n",playerName, moneyChange);
    }
    public void closeFile(){
        //Closes the print writer
        f.close();
    }
}
