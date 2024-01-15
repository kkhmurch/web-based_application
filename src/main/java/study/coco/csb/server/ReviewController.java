package study.coco.csb.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

  @PostMapping("/reviews/submitForShop")
  public void submitReviewForShop(@RequestParam("shopId") Long shopId, @RequestParam("comment") String comment, @RequestParam("rating") int rating) {
    Review newReview = new Review(comment,rating);
    reviewService.submitReviewForShop(newReview, shopId);
  }

  @PostMapping(path="/reviews")
  public void submitReview(@RequestBody Review review) {
    reviewService.submitReview(review);
  }

  @GetMapping("/reviews/{id}")
  public ResponseEntity<Review> getReview(@PathVariable("id") Long id)
  {
    Review result = reviewService.getReview(id);
    if (result != null) {
      return ResponseEntity.ok(result);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping(path="/reviews/{id}")
  public Review updateReview(@PathVariable("id") Long id, @RequestParam("comment") String comment, @RequestParam("rating") int rating) {
    return reviewService.updateReview(id, comment, rating);
  }

  @DeleteMapping(path="/reviews/{id}")
  public Review deleteReview(@PathVariable("id") Long id) {
    return reviewService.deleteReview(id);
  }
}
