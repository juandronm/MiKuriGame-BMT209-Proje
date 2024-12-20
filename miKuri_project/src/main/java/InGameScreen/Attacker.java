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
        List<ImageView> redTeamImages = InGameController.getRedTeamImages();

        if(blueTeamImages.isEmpty()){
            System.out.println("No character character available to attack!!!");
            return;
        }
        if(redTeamImages.isEmpty()){
            System.out.println("No character character available to attack!!!");
            return;
        }

        Random random = new Random();

        ImageView randomTarget = blueTeamImages.get(random.nextInt(blueTeamImages.size()));
        Card targetCard = controller.getCardForImageView(randomTarget);
        if (targetCard == null) {
            return;
        }

        ImageView randomEnemyAttacker = redTeamImages.get(random.nextInt(redTeamImages.size()));
        Card enemyCard = controller.getCardForImageView(randomEnemyAttacker);

        int damage = enemyCard.getAttacks();//random.nextInt(500) + 10;

        int updatedHp = targetCard.getHp() - damage;
        targetCard.setHp(updatedHp);


        if(targetCard.getHp() <= 0){
            targetCard.setHp(0);

            controller.removeCardFromStage(randomTarget);
            blueTeamImages.remove(randomTarget);

            if(blueTeamImages.isEmpty()){
                controller.checkWin();
            } else{
                String faintedMessage = targetCard.getName() + " has fainted!!!";
                InGameController.updateContentWindow(faintedMessage);
            }
        } else {
            String attackMessage = "Enemy's " + enemyCard.getName() + " attacked\n" + targetCard.getName() + "\nfor " + damage + " damage!\n";
            InGameController.updateContentWindow(attackMessage);
        }
    }
}
