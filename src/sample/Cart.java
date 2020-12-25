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
    public void addtoCart(Title t){
        CartItem temp=new CartItem(t.getName(),t.getDeveloper(),t.getPlatform());
        cartItems.add(temp);
        price+=t.getPrice();
    }
    public void remove(Title t){
        for(int i=0;i<cartItems.size();i++){
            if(cartItems.get(i).compare(t)){
                price-=t.getPrice();
                cartItems.remove(i);
                break;
            }
        }
    }

    public Double getCartTotal(){
        return price;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void purchase(){
        //Add key(s) of purchased titles into order_history table
        PersistenceDBHandler db=MySQLHandler.getInstance();

        //Clear Cart
        //Proceed to Payment
    }

}
