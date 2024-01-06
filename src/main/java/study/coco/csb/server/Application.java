package study.coco.csb.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@SpringBootApplication
public class Application {

  List<String> reviews = new ArrayList<>();

  public static void main(String[] args) {
    System.out.println("Hello, world!");

    SpringApplication.run(Application.class, args);
    // Goto http://localhost:8080/
  }

  public Application() {
    initReviews();
  }

  @RequestMapping("/reviews")
  public List<String> index() {
    return reviews;
  }

  private void initReviews() {
    reviews.add("All good!");
    reviews.add("First class service and excellent product.");
    reviews.add("Bad communication and quite a delay before dispatch.");
  }

}
