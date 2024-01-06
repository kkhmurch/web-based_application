package study.coco.csb.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    System.out.println("Hello, world!");

    SpringApplication.run(Application.class, args);
    // Goto http://localhost:8080/
  }

  @RequestMapping("/")
  public String index() {
    return new Date().toString();
  }

}
