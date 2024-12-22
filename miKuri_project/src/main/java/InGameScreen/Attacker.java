package InGameScreen;
import javafx.scene.image.ImageView;
import java.util.List;
import java.util.Random;

public class Attacker {
    private static InGameController controller;

    //çağrıldığında gelen controller nesnesini yenisi ile değiştirir
    public Attacker(InGameController controller) {
        Attacker.controller = controller;
    }

    //bilgisayarın (CPU'nun) hamlesini yaptığı metottur
    public static void enemyAttack() {
        List<ImageView> blueTeamImages = InGameController.getBlueTeamImages();
        List<ImageView> redTeamImages = InGameController.getRedTeamImages();
        if (blueTeamImages.isEmpty()) {
            controller.checkWin();
            return;
        }
        if (redTeamImages.isEmpty()) {
            return;
        }
        //CPU burada rastgele hamleler yapacak bir biçimde tanımlanır
        Random random = new Random();
        ImageView randomTarget = blueTeamImages.get(random.nextInt(blueTeamImages.size()));
        Card targetCard = InGameController.getCardForImageView(randomTarget);
        if (targetCard == null) {
            return;
        }
        ImageView randomEnemyAttacker = redTeamImages.get(random.nextInt(redTeamImages.size()));

        //Hedeflenen kartın hp'sini azaltmak için yöntem aşırı yüklemesi yoluyla işlevi çağırır
        enemyAttack(randomEnemyAttacker, randomTarget, blueTeamImages);
    }

    //
    public static void enemyAttack(ImageView attacker, ImageView target, List<ImageView> blueTeamImages){
        if (attacker == null || target == null) {
            return;
        }

        Card targetCard = InGameController.getCardForImageView(target);
        Card attackerCard = InGameController.getCardForImageView(attacker);

        if(targetCard == null || attackerCard == null) {
            return;
        }

        //cana hasar verme mekaniği yazılıyor
        int damage = attackerCard.getAttacks();
        int updatedHp = targetCard.getHp() - damage;
        targetCard.setHp(updatedHp);

        //eğer CPU bir canlıyı yok etmişse o kartı siler ve kazanıp kazanmadığına bakar
        if(targetCard.getHp() <= 0){
            targetCard.setHp(0);
            controller.removeCardFromStage(target);
            blueTeamImages.remove(target);
            if(blueTeamImages.isEmpty()){
                controller.checkWin();
            } else{
                String faintedMessage = targetCard.getName() + " has fainted!!!";
                InGameController.updateContentWindow(faintedMessage);
            }
        }
        //eğer CPU bir canlıyı yok edememiş ise o canlıya verdiği hasarı ekrana yansıtır
        else {
            String attackMessage = "Enemy's " + attackerCard.getName() + " attacked\n" + targetCard.getName() + "\nfor " + damage + " damage!\n";
            InGameController.updateContentWindow(attackMessage);
        }
    }
}