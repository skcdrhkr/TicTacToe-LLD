package model;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Board {
    private int size;
    private Character[][] field;
    private int emptyCells;
    private int filledCells;

    public Board(int size) {
        this.size = size;
        this.emptyCells = size * size;
        this.filledCells = 0;
        this.field = new Character[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(field[i], '-');
        }
    }

    public int getEmptyCells() {
        return emptyCells;
    }

    public int getFilledCells() {
        return filledCells;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Character[][] getField() {
        return field;
    }

    public void setField(Character[][] field) {
        this.field = field;
    }

    public Character getFieldValue(int row, int col) {
        return field[row][col];
    }

    public void setFieldValue(int row, int col, Character val) {
        field[row][col] = val;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < size; row++) {
            builder.append(Arrays.stream(field[row]).map(String::valueOf).collect(Collectors.joining(" "))).append("\n");
        }
        return builder.toString().strip();
    }

    public void update(int x, int y, Player playerWithTurn) {
        setFieldValue(x, y, playerWithTurn.getPiece());
        filledCells += 1;
        emptyCells -= 1;
    }

    public boolean rowMatch(int x, int y) {
        Character currentChar = field[x][y];
        for (int i = 0; i < size; i++) {
            if (field[x][i] != currentChar) return false;
        }
        return true;
    }

    public boolean columnMatch(int x, int y) {
        Character currentChar = field[x][y];
        for (int i = 0; i < size; i++) {
            if (field[i][y] != currentChar) return false;
        }
        return true;
    }

    public boolean diagonalMatch(int x, int y) {
        if (x != y) return false;
        return leftDiagonalMatch(x, y) || rightDiagonalMatch(x, y);
    }

    private boolean rightDiagonalMatch(int x, int y) {
        Character currentChar = field[x][y];
        for (int i = 0; i < size; i++) {
            if (field[i][size - i - 1] != currentChar) return false;
        }
        return true;
    }

    private boolean leftDiagonalMatch(int x, int y) {
        Character currentChar = field[x][y];
        for (int i = 0; i < size; i++) {
            if (field[i][i] != currentChar) return false;
        }
        return true;
    }
}
