package study.coco.csb.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DataLoader implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private ReviewRepository reviewRepository;

    private UserRepository userRepository;

    private ShopRepository shopRepository;

    @Autowired
    public DataLoader(ReviewRepository reviewRepository, UserRepository userRepository, ShopRepository shopRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.shopRepository = shopRepository;
    }

    public void run(ApplicationArguments args) throws ParseException {

        logger.info("Loading data...");

        User maxMustermann = new User("Max Mustermann", "max.mustermann@example.com");
        User erikaMustermann = new User("Erika Mustermann", "erika.mustermann@example.com");
        User johnDoe = new User("John Doe", "john.doe@example.com");

        userRepository.save(maxMustermann);
        userRepository.save(erikaMustermann);
        userRepository.save(johnDoe);


        DateFormat formatDateOnly = new SimpleDateFormat("yyyy-MM-dd");
        Shop sockenShop24 = new Shop("SockenShop24", "http://www.sockenshop24.de", formatDateOnly.parse("2020-01-01"));
        Shop donau = new Shop("Donau", "http://www.donau.de", formatDateOnly.parse("2009-01-01"));
        Shop klamotten = new Shop("klamotten", "http://www.klamotten.com", formatDateOnly.parse("2015-01-01"));

        shopRepository.save(sockenShop24);
        shopRepository.save(donau);
        shopRepository.save(klamotten);


        DateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        Review review1 = new Review("All good!", 4);
        review1.setSubmittedAt(formatDateTime.parse("2020-01-06T10:01:00Z"));
        review1.setShop(sockenShop24);

        Review review2 = new Review("First class service and excellent product.", 5);
        review2.setSubmittedAt(formatDateTime.parse("2020-01-06T10:02:00Z"));
        review2.setShop(sockenShop24);

        Review review3 = new Review("Bad communication and quite a delay before dispatch.", 1);
        review3.setSubmittedAt(formatDateTime.parse("2020-01-06T10:03:00Z"));
        review3.setShop(sockenShop24);

        Review review4 = new Review("Very disappointed with the delivery", 3);
        review4.setSubmittedAt(formatDateTime.parse("2019-09-06T10:02:00Z"));
        review4.setShop(donau);

        Review review5 = new Review("No confirmation email, no ETA of delivery, no contact from the company at all.", 3);
        review5.setSubmittedAt(formatDateTime.parse("2019-09-08T10:02:00Z"));
        review5.setShop(donau);

        Review review6 = new Review("Poor quality", 2);
        review6.setSubmittedAt(formatDateTime.parse("2019-09-09T10:02:00Z"));
        review6.setShop(donau);

        Review review7 = new Review("Excellent product Terrible delivery", 2);
        review7.setSubmittedAt(formatDateTime.parse("2019-09-16T10:02:00Z"));
        review7.setShop(klamotten);

        Review review8 = new Review("Everything is fine", 4);
        review8.setSubmittedAt(formatDateTime.parse("2019-10-01T15:35:00Z"));
        review8.setShop(klamotten);

        Review review9 = new Review("Great service", 5);
        review9.setSubmittedAt(formatDateTime.parse("2019-10-05T15:35:00Z"));
        review9.setShop(klamotten);

        Review review10 = new Review("Never again", 1);
        review10.setSubmittedAt(formatDateTime.parse("2019-10-07T15:35:00Z"));
        review10.setShop(klamotten);

        reviewRepository.save(review1);
        reviewRepository.save(review2);
        reviewRepository.save(review3);
        reviewRepository.save(review4);
        reviewRepository.save(review5);
        reviewRepository.save(review6);
        reviewRepository.save(review7);
        reviewRepository.save(review8);
        reviewRepository.save(review9);
        reviewRepository.save(review10);


        maxMustermann.setShop(sockenShop24);
        userRepository.save(maxMustermann);

        logger.info("Data initialization finished.");
    }
}