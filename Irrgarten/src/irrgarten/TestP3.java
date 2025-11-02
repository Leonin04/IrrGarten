/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import irrgarten.UI.TextUI;
import irrgarten.Controller.Controller;

/**
 *
 * @author leonin04
 */
public class TestP3 {
    public static void estado(Game g){
        System.out.println("¿Hay ganador? " + g.finished());
        GameState gm = g.getGameState();
        System.out.println(gm.getLabyrinth());
    }
    
    public static void main(String[] args) {
        TextUI vista = new TextUI();
        int players = 3;
        Game juego = new Game(players);
        Controller controlador = new Controller (juego,vista);

        controlador.play();
        estado(juego);
    }
}

