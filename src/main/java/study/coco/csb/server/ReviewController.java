package study.coco.csb.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
  public List<Review> getReviews() {
    return reviewService.getReviews();
  }

  @RequestMapping("/reviews/submit")
  public void submitReview(@RequestParam("comment") String comment, @RequestParam("rating") int rating) {
    Review newReview = new Review(comment,rating);
    reviewService.submitReview(newReview);
  }

  @RequestMapping(path="/reviews", method = RequestMethod.POST)
  public void submitReview(@RequestBody Review review) {
    reviewService.submitReview(review);
  }

  @RequestMapping("/reviews/{id}")
  public Review getReview(@PathVariable("id") int index)
  {
    return reviewService.getReview(index);
  }

}
