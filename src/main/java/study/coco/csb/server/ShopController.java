package study.coco.csb.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShopController {

    ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shops")
    public List<Shop> getShops() {
        return shopService.getShops();
    }

    @GetMapping("/shops/{id}")
    public Shop getShop(@PathVariable("id") Long id) {
        return shopService.getShop(id);
    }

    @PostMapping("/shops")
    public void createShop(@RequestParam("name") String name,@RequestParam("url") String url) {
        shopService.createShop(name, url);
    }

    @DeleteMapping("/shops/{id}")
    public Shop deleteShop(@PathVariable("id") Long id) {
        return shopService.deleteShop(id);
    }
}
