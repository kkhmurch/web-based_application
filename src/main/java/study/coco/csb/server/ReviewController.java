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

  @GetMapping("/reviews")
  public List<Review> getReviews() {
    return reviewService.getReviews();
  }

  @PostMapping("/reviews/submit")
  public void submitReview(@RequestParam("comment") String comment, @RequestParam("rating") int rating) {
    Review newReview = new Review(comment,rating);
    reviewService.submitReview(newReview);
  }

  @PostMapping(path="/reviews")
  public void submitReview(@RequestBody Review review) {
    reviewService.submitReview(review);
  }

  @GetMapping("/reviews/{id}")
  public Review getReview(@PathVariable("id") int index)
  {
    return reviewService.getReview(index);
  }

  @PutMapping(path="/reviews/{id}")
  public Review updateReview(@PathVariable("id") int index, @RequestParam("comment") String comment, @RequestParam("rating") int rating) {
    return reviewService.updateReview(index, comment, rating);
  }

  @DeleteMapping(path="/reviews/{id}")
  public Review deleteReview(@PathVariable("id") int index) {
    return reviewService.deleteReview(index);
  }
}
