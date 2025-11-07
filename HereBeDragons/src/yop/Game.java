package yop;

import java.util.*;

public class Game {
    public static void main(String[] args) throws Exception {
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

        //Special Points
        map[1][1]= 'H';
        map[3][1]= 'D';
        map[8][1]= 'K';
        map[5][9]= 'E';

        

        //Start position of the hero,key and dragon
        int[] hero= {1,1};
        int[] dragon ={3,1};
        int[] key = {8,1};
        int[] exit={5,9};

        //Starting of the game
        Scanner sc = new Scanner(System.in);
        boolean gameover = false;
        boolean keyObtained = false;
        System.out.println("Hero, You need to obtain the key before reaching the exit, stay away from the dragon!");


        while(!gameover)
        {
            drawmap(map);
            System.out.println("Move with U(up) D(down) L(left) R(right) ");
            char move = sc.nextLine().toUpperCase().charAt(0);
            
            //We implement the keys
            int posX = hero[0];
            int posY = hero[1];
            switch(move){
                case('U'):
                    posX = hero[0] -1 ;
                    break;
                case('D'):
                    posX = hero[0] + 1;
                    break;    
                case('L'):
                    posY = hero[1] - 1;
                    break;
                case('R'):
                    posY = hero[1] + 1;    
                    break;
                default:
                System.out.println("Can't move, please use U, D, L, R.");
                continue;
            




            }
               //Map rules
    
            if(map[posX][posY] != 'X'){ //There is no wall
                map[hero[0]][hero[1]] = ' '; //Delete position
                hero[0] = posX;
                hero[1] = posY;
                map[hero[0]][hero[1]] = 'H';

                //Key Obtained
                if (hero[0]==key[0] && hero[1]==key[1]){
                    System.out.println("You obtained the key!");
                    map[key[0]][key[1]]='H'; //Clear the 'K' sign
                    keyObtained = true;
                    if(hero[0]==7 && hero[1]==1){
                        map[key[0]][key[1]]=' ';
                    }

                //Teleport hero near dragon 
                teleport(hero,dragon,map);
                System.out.println("You have been teleported!");
                }


                //Hero mess Dragon
                if(dragonAwake(hero,dragon)){
                    System.out.println("You woke up the Dragon, game over!");
                    gameover=true;
                }



                //Hero reaches exit
                if(hero[0]==exit[0] && hero[1]==exit[1]){
                    if(keyObtained){
                        System.out.println("Congratulations hero, you escaped!");
                        gameover = true;
                    } else{
                        System.out.println("You need the key.");
                    }

                
            }

        }



        }

            }
    

 
            

        //Drawing of the map
    public static void drawmap(char[][]map){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println(); // Jumps to the next line
        }
    }
    
    public static boolean dragonAwake(int[] hero, int[] dragon){
        boolean near = false;
        int difRows = Math.abs(hero[0] - dragon[0]);
        int difCols = Math.abs(hero[1] - dragon[1]);
        if(difRows <= 1 && difCols <=1){ //This triggers if hero is in an adyacent position 
            near = true;
        }
        return (near);

    }

    public static void teleport(int[] hero, int[] dragon, char[][] map){
        int[][] positions = {
            {dragon[0] - 2, dragon[1]},
            {dragon[0] + 2, dragon[1]} //We only need up and down because we have walls around
        };
        //Selects random position
        Random random = new Random();
        int randNum = random.nextInt(2);

        int posX = positions[randNum][0];
        int posY = positions[randNum][1];
        
        //Change the position of the hero and teleports him

        map[hero[0]][hero[1]] = ' ';
        hero[0] = posX;
        hero[1] = posY;
        map[hero[0]][hero[1]] = 'H'; 
    }


 

}
