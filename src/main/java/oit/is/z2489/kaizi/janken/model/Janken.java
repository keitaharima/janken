package oit.is.z2489.kaizi.janken.model;

public class Janken {

  public String getCpuHand() {
    // CPUの手は固定で"rock"とする
    return "rock";
  }

  public String judge(String userHand, String cpuHand) {
    if (userHand.equals(cpuHand)) {
      return "引き分け";
    } else if ((userHand.equals("rock") && cpuHand.equals("scissors")) ||
        (userHand.equals("scissors") && cpuHand.equals("paper")) ||
        (userHand.equals("paper") && cpuHand.equals("rock"))) {
      return "勝ち";
    } else {
      return "負け";
    }
  }
}
