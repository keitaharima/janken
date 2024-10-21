package oit.is.z2489.kaizi.janken.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JankenController {

  @GetMapping("/janken")
  public String showJanken(Model model, Authentication auth) {
    String username = auth.getName(); // 認証されたユーザ名を取得
    model.addAttribute("username", username);
    return "janken"; // templates/janken.html を返す
  }

  @RequestMapping("/janken/result")
  public String jankenResult(@RequestParam String hand, Model model) {
    String cpuHand = "rock"; // CPUは常にグーを出す
    String result;

    // 勝敗の判定ロジック
    if (hand.equals(cpuHand)) {
      result = "引き分け";
    } else if ((hand.equals("rock") && cpuHand.equals("scissors")) ||
        (hand.equals("scissors") && cpuHand.equals("paper")) ||
        (hand.equals("paper") && cpuHand.equals("rock"))) {
      result = "勝ち";
    } else {
      result = "負け";
    }

    model.addAttribute("yourHand", hand);
    model.addAttribute("cpuHand", cpuHand);
    model.addAttribute("result", result);

    return "janken.html"; // 結果を janken.html に表示
  }
}
