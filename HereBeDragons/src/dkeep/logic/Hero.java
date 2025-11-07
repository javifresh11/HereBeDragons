package dkeep.logic;

public class Hero extends Elements {
    private boolean armed;

    public Hero(int x, int y) {
        super(x, y);
        this.armed = false;  // Starts unarmed
    }

    public boolean isArmed() {
        return armed;
    }

    public void pickUpSword() {
        this.armed = true;  // Hero is now armed
    }

    public char getRepresentation() {
        return armed ? 'A' : 'H';  // 'A' for armed, 'H' for unarmed
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
