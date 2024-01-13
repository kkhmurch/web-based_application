package study.coco.csb.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

  private ArrayList<String> users = new ArrayList<>();

  public UserController() {
    users.add("Max Mustermann");
    users.add("Erika Mustermann");
    users.add("John Doe");
  }

  @GetMapping("/users")
  public List<String> getUsers() {
    return users;
  }

  @GetMapping("/users/{id}")
  public String getUser(@PathVariable("id") int id) {
    return users.get(id);
  }
}