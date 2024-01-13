package study.coco.csb.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Handle all business operations for reviews.
 */
@Service
public class ReviewService {

  private ReviewRepository reviewRepository;

  @Autowired
  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
    initReviews();
  }

  private void initReviews() {
    reviewRepository.save(new Review("All good!", 4));
    reviewRepository.save(new Review("First class service and excellent product.", 5));
    reviewRepository.save(new Review("Bad communication and quite a delay before dispatch.", 1));
  }

  public List<Review> getReviews() {
    return reviewRepository.findAll();
  }

  public Review getReview(Long id) {
    return reviewRepository.findById(id).orElse(null);
  }

  public void submitReview(String comment, int rating) {
    reviewRepository.save(new Review (comment, rating));
  }

  public void submitReview(Review review) {
    reviewRepository.save(review);
  }

  public Review updateReview(Long id, String comment, int rating) {
    Review review = getReview(id);
    review.setComment(comment);
    review.setRating(rating);
    return reviewRepository.save(review);
  }

  public Review deleteReview(Long id) {
    Review reviewToDelete = reviewRepository.findById(id).get();
    reviewRepository.deleteById(id);
    return reviewToDelete;
  }

}
