package niit.edu.vn.shop_b2c.services;

import niit.edu.vn.shop_b2c.models.AttributeValue;
import niit.edu.vn.shop_b2c.models.Attribute;
import niit.edu.vn.shop_b2c.models.Role;
import niit.edu.vn.shop_b2c.respository.AttributeValueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class AttributeValueService {
    @Autowired
    private AttributeValueRepo attributeValueRepo;

    @Autowired
    AttributeService attributeService;

    public AbService<AttributeValue, AttributeValueRepo> getService() {
        return new AbService<>(this.attributeValueRepo);
    }

    public boolean save(AttributeValue attribute_value) {
        try {
            AttributeValue saveAttribute_value = new AttributeValue();
            saveAttribute_value.setValue(attribute_value.getValue());

            Attribute attribute = attributeService.getService().getById(attribute_value.getAttribute_id());
            
            //Attribute attribute = attributeService.getService().getById(attribute_value.getAttribute_id());
            //saveAttribute_value.setAttributes(attribute);
            saveAttribute_value.setAttribute(attribute) ;
            saveAttribute_value.setAttribute_id(attribute.getId());
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
