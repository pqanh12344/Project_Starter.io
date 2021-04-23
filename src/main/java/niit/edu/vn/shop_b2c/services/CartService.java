package niit.edu.vn.shop_b2c.services;

import niit.edu.vn.shop_b2c.models.Cart;
import niit.edu.vn.shop_b2c.respository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;

    public AbService<Cart, CartRepo> getService() {
        return new AbService<>(this.cartRepo);
    }

    public boolean save(Cart cart) {
        try {
            Cart saveCart = new Cart();
            saveCart.setUser_id(cart.getUser_id());
            saveCart.setProduct_id(cart.getProduct_id());
            saveCart.setFilter_value_id(cart.getFilter_value_id());
            saveCart.setQuantity(cart.getQuantity());
            saveCart.setStore_id(cart.getStore_id());
            cartRepo.save(saveCart);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void deleteCart(Long id){
        cartRepo.deleteById(id);
    }
}
