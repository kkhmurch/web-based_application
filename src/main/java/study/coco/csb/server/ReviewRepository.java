package study.coco.csb.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

  @Query("SELECT r FROM Review r ORDER BY r.submittedAt DESC")
  List<Review> findAllReviewsSorted();

  @Query("SELECT r FROM Review r WHERE r.shop = :shop ORDER BY r.submittedAt DESC")
  List<Review> findAllReviewsSortedForShop(Shop shop);

}