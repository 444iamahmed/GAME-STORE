package sample;

import java.util.ArrayList;
import java.util.Vector;

class Account extends Displayable{
    String username;
    String email;
    String password;

    public Account() {
        this.username = " ";
        this.email = " ";
        this.password = " ";
    }

    public Account(Account account) {
        this.username = account.username;
        this.email = account.email;
        this.password = account.password;
    }
    public Account(String username, String email, String password)
    {
        this.username = username;
        this.email = email;
        this.password = password;
    }


}
