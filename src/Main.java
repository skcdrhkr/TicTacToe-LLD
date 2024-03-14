import model.GameDriver;

public class Main {
    public static void main(String[] args) {
        GameDriver driver = GameDriver.initializeGame(3, 2);

        driver.getPlayerInfo();
        driver.startGame();
    }
}