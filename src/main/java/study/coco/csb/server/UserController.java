package study.coco.csb.server;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

  public List<String> getUsers() {
    return new ArrayList<String>();
  }
}