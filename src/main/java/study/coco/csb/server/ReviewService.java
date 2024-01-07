package study.coco.csb.server;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle all business operations for reviews.
 */
@Service
public class ReviewService {

  List<String> reviews = new ArrayList<>();

  public ReviewService() {
    initReviews();
  }

  private void initReviews() {
    reviews.add("All good!");
    reviews.add("First class service and excellent product.");
    reviews.add("Bad communication and quite a delay before dispatch.");
  }

  public List<String> getReviews() {
    return reviews;
  }

  public String getReview(int index) {
    return reviews.get(index);
  }

  public void submitReview(String comment) {
    reviews.add(comment);
  }

}
