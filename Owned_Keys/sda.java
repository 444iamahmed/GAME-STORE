import java.util.Scanner;
import java.util.Vector;
import java.io.*;

class Account {
    String email;
    String password;
    Vector<key> klist;

    public Account() {
        email = " ";
        password = " ";
        /*
         * key k1 = new key(); k1.email = "hasanriaz@outlook.com"; k1.tname = "GOW";
         * k1.kval = "2124435355";
         */
        klist = new Vector<key>();
        klist.add(k1);
    }

    public Account(Account obj) {
        email = obj.email;
        password = obj.password;
        klist = new Vector<key>();
        if (obj.klist.size() > 0) {
            for (int i = 0; i < obj.klist.size(); i++) {
                klist.add(obj.klist.get(i));
            }
        }
    }

    Vector<key> getKey(String email) {
        Vector<key> temp = new Vector<key>();
        for (int i = 0; i < klist.size(); i++) {
            if (klist.get(i).email == email) {
                temp.add(klist.get(i));
            }
        }
        return temp;
    }
}

class key {
    String kval;
    String email;
    String tname;

    key() {
    }
};

class title {
    String name;
    String publisher;
    String rating;
    String release_date;

    title() {
    }
};

// Stores Available Games as Titles and Keys
class inventory {

    Vector<key> klist;
    Vector<title> tlist;

    inventory() {
        klist = new Vector<key>();

        tlist = new Vector<title>();
    }

    public void addTitle(Title obj) {
        tlist.add(obj);
    }

    public void addKey(key obj) {
        klist.add(key);
    }

    public void getTitleInfo(String title) {
        for (int i = 0; i < tlist.size(); i++) {
            if (tlist.get(i).tname == tname) {
                System.out.println(tlist.get(i).name + "\nBy " + tlist.get(i).publisher + "/n Rating: "
                        + tlist.get(i).rating + "\n Release Date: " + tlist.get(i).release_date + "\n");
            }
        }
    }

    Vector<key> getKey(String tname) {
        Vector<key> temp = new Vector<key>();
        for (int i = 0; i < klist.size(); i++) {
            if (klist.get(i).tname == tname) {
                temp.add(klist.get(i));
            }
        }
        return temp;
    }
};

// Main Controller to access all other classes
class gstore {
    inventory iv;
    Vector<Account> acclist;

    gstore() {
        iv = new inventory();
        acclist = new Vector<Account>();
    }

    public void addAccount(Account obj) {
        acclist.add(obj);
    }

    public void addKey(Key obj) {
        iv.addKey(obj);
    }

    public void addTitle(Title obj) {
        iv.addTitle(obj);
    }

    public void viewAccount() {
        // Displays all accounts registered with the store, admin-access only
    }

    public void viewAccount(String email) {
        for (int i = 0; i < acclist.size(); i++) {
            if (acclist.get(i).email == email) {
                // Output Relevant Account Details
                int k = 0;
                Scanner in = new Scanner(System.in);
                System.out.println("1.Show Owned Games\n2.Exit\n");
                while (k != 2) {
                    if (k == 1) {
                        getKeys(email);
                    }
                    k = in.nextInt();
                }
            }
        }
    }

    public void getKeys(Account obj) {
        Vector<Int> index = new Vector<Int>();
        for (int i = 0; i < obj.klist.size(); i++) {
            System.out.println(i + ". ");
            System.out.println(obj.klist.get(i).tname + "\n");
            System.out.println(obj.klist.get(i).kval + "\n");
            index.add(i);
        }

    }

    public void getTitleInfo(string tname) {
        iv.getTitleInfo(title);
    }

    public void getKeys(String email) {
        for (int i = 0; i < acclist.size(); i++) {
            if (acclist.get(i).email == email) {
                for (int j = 0; j < acclist.get(i).klist.size(); i++) {
                    // Output Key Details
                    System.out.println(obj.klist.get(i).kval + "\n");
                    System.out.println(obj.klist.get(i).tname + "\n");
                }
            }
        }
    }
};

public class sda {
    public static void main(String[] args) {
        Account abc = new Account();
        abc.email = "hasanriaz@outlook.com";
        abc.password = "1234";
        Account def = new Account(abc);
        System.out.println(def.email + "\n" + def.password + "\n");
        for (int i = 0; i < def.klist.size(); i++) {
            System.out.println(def.klist.get(i).kval);
        }

    }

}
