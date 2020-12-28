package sample;

public class CartItem {
    private String name;
    private String developer;
    private String platform;
    private Double price;
    private Title title;
    private Key key;
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    CartItem(){}
    CartItem(Title t, Key k){
        title = t;
        key = k;
    }
    public boolean compare(Title t){
        if(name.equals(t.getName()) && developer.equals(t.getDeveloper()) && platform.equals(t.getPlatform())){
            return true;
        }
        return false;
    }
    public Title getTitle()
    {return title;}
    public Key getKey()
    {return key;}


}
