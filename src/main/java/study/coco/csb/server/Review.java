package study.coco.csb.server;

import java.util.Date;

public class Review {

    private String comment;
    private int rating;
    private Date submittedAt;
    
    public Review(String comment, int rating) {
        this.comment = comment;
        this.rating = rating;
        submittedAt = new Date();
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
