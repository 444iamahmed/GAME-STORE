package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class TitlesDBHandler {

    File TitlesFile;
    Scanner TitleScanner;
    public TitlesDBHandler(String fileName)
    {
        TitlesFile = new File(fileName);
    }
    public ArrayList<Titles> getTitles() {
        ArrayList<Titles> tempList = new ArrayList<>();
        try {
            
            TitleScanner = new Scanner(namesFile);
            String temp;
            while (namesScanner.hasNextLine()) {
                temp = (TitleScanner.nextLine());
                Scanner LineScanner = new Scanner(temp);
                LineScanner.UseDelimiter(' ');
                String tempname = LineScanner.next();
                Date tempdate = Date(LineScanner.next());
                String tempdesc = LineScanner.next();
                String tempdev = LineScanner.next();
                String tempplat = LineScanner.next();
                ArrayList<String> tempgenres = new ArrayList<>;
                while(!LineScanner.hasNextFloat())
                    tempgenres.add(LineScanner.next());
                Float temprat = LineScanner.nextFloat();
                Float tempprice = LineScanner.nextFloat();
                Title tempTitle = new Title(tempname,tempdate,tempdesc,tempdev,tempgenres,tempplat,temprat,tempprice);
                tempList.add(tempTitle);
                LineScanner.close();
            }
            TitleScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return tempList;

    }



}
