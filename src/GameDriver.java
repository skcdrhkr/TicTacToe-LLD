package model;

import java.util.Scanner;

public class GameDriver {
    static Scanner scanner = new Scanner(System.in);
    private Game game;

    private GameDriver() {
    }

    private GameDriver(Game game) {
        this.game = game;
    }

    public static GameDriver initializeGame(int size, int playersCount) {
        Board board = new Board(size);
        Game game = new Game(playersCount);
        game.setBoard(board);
        return new GameDriver(game);
    }

    public void getPlayerInfo() {
        int playerInput = 0;
        while (playerInput < game.getPlayerCount()) {
            String input = scanner.nextLine();
            if (input.split(" ").length != 2) {
                System.out.println("Invalid Input. Try again");
                continue;
            }
            String[] playerInfo = input.split(" ");
            game.addPlayer(new Player(playerInfo[1], playerInfo[0].charAt(0)));
            playerInput += 1;
        }
    }

    public void startGame() {
        game.initialize();
        while (true) {

            String input = scanner.nextLine();
            if ("exit".equals(input)) {
                break;
            }
            String[] inputFields = input.split(" ");
            if (inputFields.length != 2) {
                System.out.println("Invalid Input");
                continue;
            }
            int xpos, ypos;
            try {
                xpos = Integer.parseInt(inputFields[0]) - 1;
                ypos = Integer.parseInt(inputFields[1]) - 1;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Input numbers");
                continue;
            }
            if (!game.isRunning())
                continue;
            if (!game.makeMove(xpos, ypos)) {
                System.out.println("Invalid Move");
            } else {
                game.printGrid();
            }
            if (State.WON.equals(game.getState())) {
                game.printWinner();
            }
            if (State.DRAW.equals(game.getState())) {
                game.printGameOver();
            }
        }
    }
}
