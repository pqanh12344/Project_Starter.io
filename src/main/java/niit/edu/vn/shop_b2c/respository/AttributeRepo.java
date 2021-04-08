package niit.edu.vn.shop_b2c.respository;

import niit.edu.vn.shop_b2c.models.Attribute;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepo extends PagingAndSortingRepository<Attribute, Long> {

}
