package niit.edu.vn.shop_b2c.services;

import niit.edu.vn.shop_b2c.models.Attribute;
import niit.edu.vn.shop_b2c.models.AttributeValue;
import niit.edu.vn.shop_b2c.respository.AttributeValueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeValueService {
    @Autowired
    private AttributeValueRepo attributeValueRepo;

    public AbService<Attribute, AttributeValueRepo> getService() {
        return new AbService<>(this.attributeValueRepo);
    }

    public boolean save(AttributeValue attribute_value) {
        try {
            AttributeValue saveAttribute_value = new AttributeValue();
            saveAttribute_value.setValue(attribute_value.getValue());
            saveAttribute_value.setAttribute_id(attribute_value.getAttribute_id());
            attributeValueRepo.save(saveAttribute_value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void deleteAttributeValue(Long id){
        attributeValueRepo.deleteById(id);
    }
}
