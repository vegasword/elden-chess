package org.codingfactory;

public class Player {
    String pseudo;
    int x,y;

    public Player(String pseudo, int x, int y) {
        this.pseudo = pseudo;
        this.x = x;
        this.y = y;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
