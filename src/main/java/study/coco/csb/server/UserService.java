package study.coco.csb.server;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

  private ArrayList<String> users = new ArrayList<>();

  public UserService() {
    users.add("Max Mustermann");
    users.add("Erika Mustermann");
    users.add("John Doe");
  }

  public List<String> getUsers() {
    return users;
  }

  public String getUser(int id) {
    return users.get(id);
  }

  public String deleteUser(int id) {
    return users.remove(id);
  }

  public void createUser(String name, String email) {
    users.add(name);
  }
}
