package study.coco.csb.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @RequestMapping(path = "/sentence-split")
  public String splitSentence( @RequestParam("url_parameter_name_sentence") String sentenceJavaMethodParameter) {
    String header = "<!DOCTYPE html><html><body><ol>";
    String footer = "</ol></body></html>";

    String[] words = sentenceJavaMethodParameter.split(" ");
    String htmlWordList = "";
    for (String word : words) {
      htmlWordList = htmlWordList + "<li>" + word + "</li>";
    }
    return header + htmlWordList + footer;
  }

  @RequestMapping("/reviews")
  public List<String> index() {
    return reviews;
  }

  @RequestMapping("/reviews/submit")
  public void submitReview(@RequestParam("comment") String comment) {
    reviews.add(comment);
  }

  private void initReviews() {
    reviews.add("All good!");
    reviews.add("First class service and excellent product.");
    reviews.add("Bad communication and quite a delay before dispatch.");
  }

}
