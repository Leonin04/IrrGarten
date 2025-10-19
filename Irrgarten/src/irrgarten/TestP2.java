
package irrgarten;

/**
 *
 * @author leonin04
 */

public class TestP2 {
    static void pruebaMonster(){
        Monster m = new Monster("Monstruito", 10, 10);
        System.out.println(m.toString());
        System.out.println("¿Muerto? " + m.dead());
        System.out.println("Daño: " + m.attack());
        System.out.println("Colocando monstruo en el (1,1)...");
        m.setPos(1, 1);
        System.out.println(m.toString());
        System.out.println("Matando monstruo...");
        m.gotWounded();
        System.out.println(m.toString());
        m.gotWounded();
        m.gotWounded();
        m.gotWounded();
        m.gotWounded();
        System.out.println(m.toString());
        System.out.println("¿Muerto? " + m.dead());
    }
    
    static void pruebaPlayer(){
        Player p = new Player('1',10,10);
        System.out.println(p.toString());
        System.out.println("Colocando en 0,0 ");
        p.setPos(0,0);
        System.out.println("Colocado en: (" + p.getRow() + "," + p.getCol() + ")");
        System.out.println("¿Muerto? " + p.dead());
        System.out.println("Atacando: " + p.attack());
        //System.out.println("Defendiendo: " + p.defend(5));        
    }
    
    public static void main(String[] args) {
       System.out.println("Probando monstruo: ");
       pruebaMonster();
       
       System.out.println("Probando player: ");
       pruebaPlayer();

    }
    
}
