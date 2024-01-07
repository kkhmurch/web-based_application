package study.coco.csb.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

  private ReviewService reviewService;

  // https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/core.html#beans-constructor-injection
  @Autowired
  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
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
  public List<String> getReviews() {
    return reviewService.getReviews();
  }

  @RequestMapping("/reviews/submit")
  public void submitReview(@RequestParam("comment") String comment) {
    reviewService.submitReview(comment);
  }

  @RequestMapping("/reviews/specific")
  public String getReview(@RequestParam("index") int index)
  {
    return reviewService.getReview(index);
  }

}
