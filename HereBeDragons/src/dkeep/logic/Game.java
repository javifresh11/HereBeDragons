package dkeep.logic;

import java.util.*;

public class Game {
    private Map map;
    private Hero hero;
    private Sword sword;
    private List<Dragon> dragons;
    private boolean isGameOver = false;

    public Game(char[][] initialMap, Hero hero, Sword sword, List<Dragon> dragons) {
        this.map = new Map(initialMap);
        this.hero = hero;
        this.sword = sword;
        this.dragons = dragons;

        // Sets hero sword and dragon (this was made in map before)
        map.setElementAt(hero.getX(), hero.getY(), hero.getRepresentation());
        map.setElementAt(sword.getX(), sword.getY(), sword.getRepresentation());
        for (Dragon dragon : dragons) {
            map.setElementAt(dragon.getX(), dragon.getY(), dragon.getRepresentation());
        }
    }

    public void moveHero(char direction) {
        int dx = 0, dy = 0;

        switch (direction) {
            case 'W': dx = -1; break;
            case 'S': dx = 1; break;
            case 'A': dy = -1; break;
            case 'D': dy = 1; break;
        }

        int newX = hero.getX() + dx;
        int newY = hero.getY() + dy;
        char nextPos = map.getElementAt(newX, newY);

        if (nextPos == ' ') {
            map.setElementAt(hero.getX(), hero.getY(), ' ');
            hero.move(dx, dy);
            map.setElementAt(hero.getX(), hero.getY(), hero.getRepresentation());
        } else if (nextPos == 'S') {
            // Hero picks up sword with method
            hero.pickUpSword();
            map.setElementAt(hero.getX(), hero.getY(), ' ');
            hero.move(dx, dy);
            map.setElementAt(hero.getX(), hero.getY(), hero.getRepresentation());
        } else if (nextPos == 'D') {
            if (hero.isArmed()) {
                // Kills dragon
                slayDragon(newX, newY);
            } else {
                System.out.println("You have been slain by dragon! Game over!");
                isGameOver = true;
            }
        } else if (nextPos == 'E') {
            if (allDragonsSlain()) {
                System.out.println("You won!");
                isGameOver = true;
            } else {
                System.out.println("You cannot escape without killing all dragons first!");
            }
        }

        if (!isGameOver) {
            moveDragons();
        }
    }

    private void slayDragon(int x, int y) {
        for (Dragon dragon : dragons) {
            if (dragon.getX() == x && dragon.getY() == y && dragon.isAlive()) {
                dragon.setAlive(false);
                map.setElementAt(x, y, ' ');
                System.out.println("¡Has matado al dragón!");
                break;
            }
        }
    }

    private boolean allDragonsSlain() {
        for (Dragon dragon : dragons) {
            if (dragon.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public void moveDragons() {
        for (Dragon dragon : dragons) {
            if (dragon.isAlive()) {
                dragon.moveRandomly(map);
            }
        }
    }

    public void printGameState() {
        map.printMap();
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
