package model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Integer playerCount;
    private final List<Player> players;
    private State state;

    private String winner;
    private Board board;

    private Integer turn;

    public Game(int playersCount) {
        this.state = State.NOT_STARTED;
        this.playerCount = playersCount;
        players = new ArrayList<>();
        turn = 0;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getWinner() {
        return null;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public boolean makeMove(int x, int y) {
        if (!isValidMove(x, y)) return false;
        Player playerWithTurn = players.get(turn);
        board.update(x, y, playerWithTurn);
        updateState(x, y);
        moveToNextTurn();
        return true;
    }

    private void moveToNextTurn() {
        turn = (turn + 1) % playerCount;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public boolean hasWinner() {
        return winner != null;
    }

    public boolean isDrawn() {
        return board.getEmptyCells() == 0;
    }

    public void printWinner() {
        if (hasWinner()) {
            System.out.println(winner + " won the game");
        }
    }

    public void printGameOver() {
        System.out.println("Game Over");
    }

    public boolean isValidMove(Integer xpos, Integer ypos) {
        return board.getFieldValue(xpos, ypos).equals('-');
    }

    public void updateState(int x, int y) {
        if (wasWinningMove(x, y)) {
            state = State.WON;
            setWinner(players.get(turn).getName());
        } else if (isDrawn()) {
            state = State.DRAW;
        }
    }

    private boolean wasWinningMove(int x, int y) {
        return (board.rowMatch(x, y) || board.columnMatch(x, y) || board.diagonalMatch(x, y));
    }

    public void printGrid() {
        System.out.println(board.toString());
    }

    public boolean isRunning() {
        return State.RUNNING.equals(state);
    }

    public void initialize() {
        printGrid();
        setState(State.RUNNING);
    }
}
