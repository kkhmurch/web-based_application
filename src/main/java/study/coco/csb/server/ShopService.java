package study.coco.csb.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ShopService {

    ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
        try {
            init();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void init() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        shopRepository.save(new Shop("SockenShop24", "http://www.sockenshop24.de", format.parse("2020-01-01")));
        shopRepository.save(new Shop("Donau", "http://www.donau.de",format.parse("2009-01-01")));
        shopRepository.save(new Shop("klamotten", "http://www.klamotten.com",format.parse("2015-01-01")));
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
