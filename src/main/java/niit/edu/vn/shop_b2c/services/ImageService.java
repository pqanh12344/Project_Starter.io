package niit.edu.vn.shop_b2c.services;

import niit.edu.vn.shop_b2c.models.Image;
import niit.edu.vn.shop_b2c.respository.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    private ImageRepo imageRepo;

    public AbService<Image, ImageRepo> getService() {
        return new AbService<>(this.imageRepo);
    }

    public boolean save(Image image) {
        try {
            Image saveImage = new Image();
            saveImage.setIs_preview(image.getIs_preview());
            saveImage.setPath(image.getPath());
            imageRepo.save(saveImage);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void deleteImage(Long id){
        imageRepo.deleteById(id);
    }
}
