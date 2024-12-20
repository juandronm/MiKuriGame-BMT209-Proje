package InGameScreen;

import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Random;

public class Attacker {

    private static InGameController controller;

    public Attacker(InGameController controller) {
        Attacker.controller = controller;
    }

    public static void enemyAttack(){
        List<ImageView> blueTeamImages = InGameController.getBlueTeamImages();
        if(blueTeamImages.isEmpty()){
            System.out.println("No character character available to attack!!!");
            return;
        }

        Random random = new Random();
        ImageView randomTarget = blueTeamImages.get(random.nextInt(blueTeamImages.size()));
        Card targetCard = controller.getCardForImageView(randomTarget);
        if (targetCard == null) {
            return;
        }

        int damage = random.nextInt(20) + 10;
        int updatedHp = targetCard.getHp() - damage;
        targetCard.setHp(updatedHp);

        if(targetCard.getHp() <= 0){
            targetCard.setHp(0);
            controller.removeCardFromStage(randomTarget);
            blueTeamImages.remove(randomTarget);

            String faintedMessage = targetCard.getName() + " has fainted!!!";
            InGameController.updateContentWindow(faintedMessage);
        } else {
            String attackMessage = "Enemy attcked\n" + targetCard.getName() + "\nfor " + damage + " damage!\n";
            InGameController.updateContentWindow(attackMessage);
        }

        controller.checkWin();
    }

    public static boolean isOnRedPart(double y){
        return y <= 400;
    }
}
