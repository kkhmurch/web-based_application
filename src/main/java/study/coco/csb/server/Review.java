package study.coco.csb.server;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Entity
public class Review {

    private static AtomicLong nextId = new AtomicLong(0);

    @Id
    private Long id;
    private String comment;
    private int rating;
    private Date submittedAt;

    protected Review() {
    }
    
    public Review(String comment, int rating) {
        this.id = nextId.getAndIncrement();
        this.comment = comment;
        this.rating = rating;
        submittedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

}
