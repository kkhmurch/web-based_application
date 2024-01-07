package study.coco.csb.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handle various helper endpoints.
 */
@RestController
public class SentenceController {

  @RequestMapping(path = "/sentence-split")
  public String splitSentence( @RequestParam("url_parameter_name_sentence") String sentenceJavaMethodParameter) {
    String header = "<!DOCTYPE html><html><body><ol>";
    String footer = "</ol></body></html>";

    String[] words = sentenceJavaMethodParameter.split(" ");
    String htmlWordList = "";
    for (String word : words) {
      htmlWordList = htmlWordList + "<li>" + word + "</li>";
    }
    return header + htmlWordList + footer;
  }
}
