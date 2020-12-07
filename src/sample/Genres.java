package sample;

import java.util.ArrayList;

public class Genres {
    private static Genres single_instance = null;

    // variable of type String
    public ArrayList<String> genres;
    private namesDBHandler handler;
    // private constructor restricted to this class itself
    private Genres()
    {
        handler = new namesDBHandler("D:\\WORK\\Sem_5\\SDA\\GAME-STORE\\src\\sample\\GenresFile.txt");
        genres = handler.getNames();
    }

    // static method to create instance of Singleton class
    public static Genres getInstance()
    {
        if (single_instance == null)
            single_instance = new Genres();

        return single_instance;
    }

}
