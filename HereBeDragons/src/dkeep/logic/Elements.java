package dkeep.logic;

public abstract class Elements {
    protected int x;
    protected int y;

    public Elements(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract char getRepresentation();  // Cada elemento tiene una representación específica
}
