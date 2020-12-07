package sample;

import java.util.ArrayList;

public class GamePlatforms {
    private static GamePlatforms single_instance = null;

    // variable of type String
    public ArrayList<String> platforms;
    private namesDBHandler handler;
    // private constructor restricted to this class itself
    private GamePlatforms()
    {
        handler = new namesDBHandler("D:\\WORK\\Sem_5\\SDA\\GAME-STORE\\src\\sample\\PlatformsFile.txt");
        platforms = handler.getNames();
    }

    // static method to create instance of Singleton class
    public static GamePlatforms getInstance()
    {
        if (single_instance == null)
            single_instance = new GamePlatforms();

        return single_instance;
    }

}
