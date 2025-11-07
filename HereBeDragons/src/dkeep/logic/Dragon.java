package dkeep.logic;

import java.util.Random;

public class Dragon extends Elements {
    private boolean alive;
    private Random random;

    public Dragon(int x, int y) {
        super(x, y);
        this.alive = true;
        this.random = new Random();
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public char getRepresentation() {
        return alive ? 'D' : ' ';  // 'D' means drake alive 
    }

    public void moveRandomly(Map map) {
        int dx = 0, dy = 0;
        while (dx == 0 && dy == 0) {
            int direction = random.nextInt(4);
            switch (direction) {
                case 0: dx = -1; break;  // Up
                case 1: dx = 1; break;   // Down
                case 2: dy = -1; break;  // Left
                case 3: dy = 1; break;   // Right
            }
        }

        int newX = this.x + dx;
        int newY = this.y + dy;

        if (map.getElementAt(newX, newY) == ' ') {
            map.setElementAt(this.x, this.y, ' ');
            this.x = newX;
            this.y = newY;
            map.setElementAt(this.x, this.y, 'D');
        }
    }

    public void breatheFire(Map map, Hero hero) {
        // Similar a la versión anterior
        int dx = 0, dy = 0;
        int direction = random.nextInt(4);
        switch (direction) {
            case 0: dx = -1; break;
            case 1: dx = 1; break;
            case 2: dy = -1; break;
            case 3: dy = 1; break;
        }

        for (int i = 1; i <= 3; i++) {
            int fireX = this.x + i * dx;
            int fireY = this.y + i * dy;

            if (map.getElementAt(fireX, fireY) == ' ') {
                map.setElementAt(fireX, fireY, '*');
            } else {
                break;  // El fuego se detiene cuando encuentra un muro u objeto
            }

            if (fireX == hero.getX() && fireY == hero.getY()) {
                System.out.println("¡El dragón te quema con su fuego! ¡Juego terminado!");
                break;
            }
        }
    }
}

