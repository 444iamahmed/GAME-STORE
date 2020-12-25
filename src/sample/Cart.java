package sample;

import java.util.ArrayList;

public class Cart {

    public static Cart instance=null;
    private ArrayList<CartItem> cartItems;
    private Double price;

    public Cart(){
        cartItems=new ArrayList<>();
        price=0.0;
    }

    public static Cart getInstance(){
        if(instance==null){
            instance=new Cart();
        }
        return instance;
    }
    public void add(Title t){
        CartItem temp=new CartItem(t.getName(),t.getDeveloper(),t.getPlatform(), t.getPrice());
        cartItems.add(temp);
        price+=t.getPrice();
    }
    public void remove(CartItem item){
        for(int i=0;i<cartItems.size();i++){
            if(cartItems.get(i).equals(item)){
                price-=item.getPrice();
                cartItems.remove(i);
                break;
            }
        }
    }

    public Double getTotal(){
        return price;
    }

    public ArrayList<CartItem> getItems() {
        return cartItems;
    }

    public void purchase(){
        //Add key(s) of purchased titles into order_history table
        PersistenceDBHandler db=MySQLHandler.getInstance();

        //Clear Cart
        //Proceed to Payment
    }
    public void clear()
    {
        cartItems.clear();
    }


}
