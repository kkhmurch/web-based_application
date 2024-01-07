package study.coco.csb.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * HTTP endpoints for review-related HTTP requests.
 */
@RestController
public class ReviewController {

  private ReviewService reviewService;

  // https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/core.html#beans-constructor-injection
  @Autowired
  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @RequestMapping("/reviews")
  public List<String> getReviews() {
    return reviewService.getReviews();
  }

  @RequestMapping("/reviews/submit")
  public void submitReview(@RequestParam("comment") String comment) {
    reviewService.submitReview(comment);
  }

  @RequestMapping("/reviews/{id}")
  public String getReview(@PathVariable("id") int index)
  {
    return reviewService.getReview(index);
  }

}
