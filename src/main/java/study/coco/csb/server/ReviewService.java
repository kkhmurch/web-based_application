package study.coco.csb.server;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle all business operations for reviews.
 */
@Service
public class ReviewService {

  List<Review> reviews = new ArrayList<>();

  public ReviewService() {
    initReviews();
  }

  private void initReviews() {
    reviews.add(new Review("All good!", 4));
    reviews.add(new Review("First class service and excellent product.", 5));
    reviews.add(new Review("Bad communication and quite a delay before dispatch.", 1));
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public Review getReview(int index) {
    return reviews.get(index);
  }

  public void submitReview(String comment) {
    reviews.add(new Review (comment, 5));
  }

  public void submitReview(Review review) {
    reviews.add(review);
  }

}
