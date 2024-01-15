package study.coco.csb.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopService {

    ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<Shop> getShops() {
        return shopRepository.findAll();
    }

    public Shop getShop(Long id) {
        return shopRepository.findById(id).get();
    }

    public void createShop(String name, String url) {
        shopRepository.save(new Shop(name, url, new Date()));
    }

    public Shop deleteShop(Long id) {
        Shop deletedShop = shopRepository.findById(id).get();
        shopRepository.deleteById(id);
        return deletedShop;
    }
}
