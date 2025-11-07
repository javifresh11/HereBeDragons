package dkeep.logic;

public class Map {
    private char[][] map;

    public Map(char[][] map) {
        // Clonamos el mapa para evitar que se modifique el original
        this.map = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            System.arraycopy(map[i], 0, this.map[i], 0, map[i].length);
        }
    }

    public char getElementAt(int x, int y) {
        return map[x][y];
    }

    public void setElementAt(int x, int y, char c) {
        map[x][y] = c;
    }

    public void printMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
