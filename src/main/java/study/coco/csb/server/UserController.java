package study.coco.csb.server;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

  UserService userService;

  private ArrayList<String> users = new ArrayList<>();

  public UserController(UserService userService) {
    this.userService = userService;

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

  @PostMapping("/users/submit")
  public void registerUser(@RequestParam("name") String name, @RequestParam("email") String email) {
    users.add(name);
  }

  @DeleteMapping("/users/{id}")
  public String deleteUser(@PathVariable("id") int id) {
    return users.remove(id);
  }
}