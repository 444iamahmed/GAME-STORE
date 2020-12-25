package sample;

public class CartItem {
    public String name;
    public String developer;
    public String platform;
    public Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    CartItem(){}
    CartItem(String s1,String s2,String s3, Double p){
        name=s1;
        developer=s2;
        platform=s3;
        price = p;
    }
    public boolean compare(Title t){
        if(name.equals(t.getName()) && developer.equals(t.getDeveloper()) && platform.equals(t.getPlatform())){
            return true;
        }
        return false;
    }
}
