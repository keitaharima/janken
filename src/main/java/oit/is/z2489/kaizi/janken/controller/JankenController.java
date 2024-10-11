package oit.is.z2489.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import oit.is.z2489.kaizi.janken.model.Janken;

@Controller
public class JankenController {

  @PostMapping("/janken")
  public String janken(@RequestParam String username, Model model) {
    // ユーザー名をモデルに追加
    model.addAttribute("username", username);
    return "janken";
  }

  @GetMapping("/janken/play")
  public String playJanken(@RequestParam String choice, Model model) {
    // じゃんけんのロジックを呼び出す
    Janken janken = new Janken();
    String cpuHand = janken.getCpuHand(); // CPUの手（例えば、固定で"rock"を返す）
    String result = janken.judge(choice, cpuHand); // 勝敗の判定

    // モデルに必要な情報を追加
    model.addAttribute("userHand", choice);
    model.addAttribute("cpuHand", cpuHand);
    model.addAttribute("result", result);

    return "janken";
  }
}
