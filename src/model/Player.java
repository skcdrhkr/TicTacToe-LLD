package model;

public class Player {
    private String name;
    private Character piece;

    public Player(String name, Character piece) {
        this.name = name;
        this.piece = piece;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getPiece() {
        return piece;
    }

    public void setPiece(Character piece) {
        this.piece = piece;
    }
}
