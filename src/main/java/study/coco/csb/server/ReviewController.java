package study.coco.csb.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReviewController {

  List<String> reviews = new ArrayList<>();

  public ReviewController() {
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

  @RequestMapping("/reviews/specific")
  public String getReview(@RequestParam("index") int index)
  {
    return reviews.get(index);
  }

  private void initReviews() {
    reviews.add("All good!");
    reviews.add("First class service and excellent product.");
    reviews.add("Bad communication and quite a delay before dispatch.");
  }

}
