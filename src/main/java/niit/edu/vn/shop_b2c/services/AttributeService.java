package niit.edu.vn.shop_b2c.services;

import niit.edu.vn.shop_b2c.models.Attribute;
import niit.edu.vn.shop_b2c.respository.AttributeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {
    @Autowired
    private AttributeRepo attributeRepo;

    public AbService<Attribute, AttributeRepo> getService() {
        return new AbService<>(this.attributeRepo);
    }

    public boolean save(Attribute attribute) {
        try {
            Attribute saveAttribute = new Attribute();
            saveAttribute.setName(attribute.getName());
            attributeRepo.save(saveAttribute);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void deleteAttribute(Long id){
        attributeRepo.deleteById(id);
    }
}
