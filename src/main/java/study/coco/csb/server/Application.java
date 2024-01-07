package study.coco.csb.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    System.out.println("Hello, world!");

    SpringApplication.run(Application.class, args);
    // Goto http://localhost:8080/
  }

}
