package dkeep.logic;

public class Sword extends Elements {

    public Sword(int x, int y) {
        super(x, y);
    }

    @Override
    public char getRepresentation() {
        return 'S';  // La espada se representa con 'S' en el mapa
    }
}
