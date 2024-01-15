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
  }

  public List<Review> getReviews() {
    return reviewRepository.findAllReviewsSorted();
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
