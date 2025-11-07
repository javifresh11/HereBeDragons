package dkeep.cli;

import dkeep.logic.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Cli {
    public static void main(String[] args) {
        // Create a scanner to capture user input
        Scanner scanner = new Scanner(System.in);

        // Define game's map
        char map[][] = new char[10][10];
    
        /* Here we run the array and we fill it with blanket spaces */
        for (int i = 0; i<10;i++){
            for (int j = 0; j<10; j++){
                map[i][j] = ' ';

            }
        }
        for (int i = 0; i < 10; i++) {
            map[i][0] = 'X'; // Left
            map[i][9] = 'X'; // Right
            map[0][i] = 'X'; // Up
            map[9][i] = 'X'; // Down

        } 

        //Map Adjustments
        
        for(int i= 2; i<5; i++){
            for(int j =2; j<4; j++){
                map [i][j] = 'X';
            }
        } 
        for(int i= 2; i<5; i++){
                map [i][5] = 'X';
            }
        for(int i= 2; i<5; i++){
            map [i][5] = 'X';
        }
        for(int i= 6; i<8; i++){
            map [i][5] = 'X';
        }    
        for (int i=2; i<8; i++){
            map[i][7] = 'X';
        }

        for(int i= 6; i<9; i++){
            for(int j =2; j<4; j++){
                map [i][j] = 'X';
            }
        } 

        /* //Special Points
        map[1][1]= 'H';
        map[3][1]= 'D';
        map[8][1]= 'K';
        map[5][9]= 'E';

        

        //Start position of the hero,key and dragon
        int[] hero= {1,1};
        int[] dragon ={3,1};
        int[] key = {8,1};
        int[] exit={5,9}; */

        // Start position of the elements
        Hero hero = new Hero(1, 1);
        Sword sword = new Sword(8, 1);
        map[5][9] = 'E'; //Exit

       
        System.out.print("How much dragons will be? ");
        int numDragons = scanner.nextInt();

        // ArraList of dragons
        ArrayList<Dragon> dragons = new ArrayList<>();
        dragons.add(new Dragon(3, 1)); 
        for (int i = 1; i < numDragons; i++) {
            dragons.add(new Dragon(i + 1, i + 2));  // Adds more dragons depending on the number
        }

        // Creates an instancy for a new game
        Game game = new Game(map, hero, sword, dragons);

    
        while (!game.isGameOver()) {
            // Prints actual game state (map)
            game.printGameState();

            // Prints movement
            System.out.print("Move with 'W' (UP) S(DOWN) A(LEFT) D(RIGHT): "); //more comfortable than (UDLR)
            char move = scanner.next().toUpperCase().charAt(0);

            // Move hero
            game.moveHero(move);
        }

        // Close scanner when game ends.
        scanner.close();
    }
}
