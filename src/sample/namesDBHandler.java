package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class namesDBHandler {

    File namesFile;
    Scanner namesScanner;
    public namesDBHandler(String fileName)
    {
        namesFile = new File(fileName);
    }
    public ArrayList<String> getNames() {
        ArrayList<String> tempList = new ArrayList<>();
        try {
            namesScanner = new Scanner(namesFile);

            while (namesScanner.hasNextLine()) {
                tempList.add(namesScanner.nextLine());
            }
            namesScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return tempList;

    }



}
