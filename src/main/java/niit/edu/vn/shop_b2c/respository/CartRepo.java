package niit.edu.vn.shop_b2c.respository;

import niit.edu.vn.shop_b2c.models.Cart;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends PagingAndSortingRepository<Cart, Long> {

}
