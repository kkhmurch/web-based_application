package study.coco.csb.server;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

  UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<User> getUsers() {
    return userService.getUsers();
  }

  @GetMapping("/users/{id}")
  public User getUser(@PathVariable("id") int id) {
    return userService.getUser(id);
  }

  @PostMapping("/users/submit")
  public void registerUser(@RequestParam("name") String name, @RequestParam("email") String email) {
    userService.createUser(name,email);
  }

  @DeleteMapping("/users/{id}")
  public User deleteUser(@PathVariable("id") int id) {
    return userService.deleteUser(id);
  }

  @PutMapping("/users/{id}")
  public void updateUserEmail(@PathVariable("id") int id, @RequestParam("email") String newEmail) {
    userService.updateUserEmail(id, newEmail);
  }
}