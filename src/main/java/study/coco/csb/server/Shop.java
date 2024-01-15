package study.coco.csb.server;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    private Date collectingSince;

    public Shop() {
    }

    public Shop(String name, String url, Date collectingSince) {
        this.name = name;
        this.url = url;
        this.collectingSince = collectingSince;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCollectingSince() {
        return collectingSince;
    }

    public void setCollectingSince(Date collectingSince) {
        this.collectingSince = collectingSince;
    }
}
