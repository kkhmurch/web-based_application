package study.coco.csb.server;

import org.springframework.web.bind.annotation.*;

/**
 * Handle various helper endpoints.
 */
@RestController
public class SentenceController {

  @PostMapping(path = "/sentence-split")
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
