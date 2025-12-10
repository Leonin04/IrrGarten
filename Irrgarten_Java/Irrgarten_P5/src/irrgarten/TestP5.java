/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import irrgarten.UI.VisualUI;
import irrgarten.Controller.Controller;

/**
 *
 * @author leonin04
 */
public class TestP5 {
    
    public static void main(String[] args) {
        VisualUI vista = new VisualUI();
        int players = 2;
        Game juego = new Game(players);
        Controller controlador = new Controller (juego,vista);

        controlador.play();
    }
}

