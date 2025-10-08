
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
    
    public static void main(String[] args) {
       pruebaMonster();

    }
    
}
