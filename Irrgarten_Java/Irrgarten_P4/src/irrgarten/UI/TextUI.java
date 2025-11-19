
package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;
import java.util.Scanner;


public class TextUI {
    
    private static Scanner in = new Scanner(System.in);
    
    private char readChar() {
        String s = in.nextLine();     
        return s.charAt(0);
    }
    

    public Directions nextMove() {
        System.out.print("Where? ");
        
        Directions direction = Directions.DOWN;
        boolean gotInput = false;
        
        while (!gotInput) {
            char c = readChar();
            switch(c) {
                case 'w':
                    System.out.print(" UP\n");
                    direction = Directions.UP;
                    gotInput = true;
                    break;
                case 's':
                    System.out.print(" DOWN\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                    break;
                case 'd':
                    System.out.print("RIGHT\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                    break;
                case 'a':
                    System.out.print(" LEFT\n");
                    direction = Directions.LEFT;
                    gotInput = true;    
                    break;
                
                default:
                    System.out.print("Please choose a valid character: w,a,s,d \n");
            }
        }    
        return direction;
    }
    
    public void showGame(GameState gameState) { 
        System.out.print(gameState.getPlayers() + "\n");
        System.out.print(gameState.getMonsters() + "\n");
        System.out.print("Log:\n" + gameState.getLog() + "\n");
        System.out.print(gameState.getLabyrinth() + "\n");
        if (gameState.getWinner()) {
            System.out.print("Player " + gameState.getCurrentPlayer() + " ha ganado el juego, ¡Felicidades! \n");
        }else{
            System.out.print("Current player: " + gameState.getCurrentPlayer() + "\n");
        }
    }
    
}
