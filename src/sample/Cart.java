package sample;

import java.util.ArrayList;

public class Cart {

    public static Cart instance=null;
    private ArrayList<CartItem> cartItems;
    private Double price;
    Inventory inventory;

    public Cart(){
        cartItems=new ArrayList<>();
        inventory = Inventory.getInstance();
        price=0.0;
    }

    public static Cart getInstance(){
        if(instance==null){
            instance=new Cart();
        }
        return instance;
    }
    public void add(Title t){

        for(CartItem i: cartItems)
        {
            if(t.equals(i.getTitle()))
                t.removeKey(i.getKey());
        }

        CartItem temp=new CartItem(t, t.popKey());
        cartItems.add(temp);
        price+=t.getPrice();
    }
    public void remove(Title t){
        for(int i=0;i<cartItems.size();i++){
            CartItem currItem = cartItems.get(i);
            if(currItem.getTitle().equals(t)){
                price-=t.getPrice();
                currItem.getTitle().addKey(currItem.getKey());
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
