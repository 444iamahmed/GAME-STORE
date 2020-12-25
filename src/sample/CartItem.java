package sample;

public class CartItem {
    public String name;
    public String developer;
    public String platform;
    CartItem(){}
    CartItem(String s1,String s2,String s3){
        name=s1;
        developer=s2;
        platform=s3;
    }
    public boolean compare(Title t){
        if(name.equals(t.getName()) && developer.equals(t.getDeveloper()) && platform.equals(t.getPlatform())){
            return true;
        }
        return false;
    }
}
