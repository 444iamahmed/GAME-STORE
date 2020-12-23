package sample;

import java.util.ArrayList;
import java.util.Vector;

class Account extends Displayable{

    private String username;
    private String email;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


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
