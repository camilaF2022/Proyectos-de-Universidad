package cl.uchile.dcc.finalreality.model.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EnemyTest {

    BlockingQueue<GameCharacter> queue;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Enemy enemy4;


    @Before
    public void setUp() throws InvalidStatValueException {

        queue = new LinkedBlockingQueue<>();

        enemy1 = new Enemy("enemy",30, 40 ,50,queue);
        enemy2 = new Enemy("enemy",30, 40, 50,queue);
        enemy3 = new Enemy("enemy3",20, 45, 55,queue);
        enemy4 = new Enemy("enemy3",10, 45, 55,queue);

    }
    @Test
    public void testEquals() {

        assertEquals(enemy1, enemy2);
        assertEquals(enemy1, enemy1);
        assertNotEquals(enemy1, enemy3);
    }
    @Test public void testHashCode(){

        assertEquals(enemy1.hashCode(), enemy2.hashCode());
        assertEquals(enemy1.hashCode(), enemy1.hashCode());
        assertNotEquals(enemy1.hashCode(), enemy3.hashCode());
    }

    @Test public void testGettersSetters() throws InvalidStatValueException {

        assertEquals(enemy1.getName(),"enemy");
        assertEquals(enemy1.getWeight(),30);
        assertEquals(enemy1.getMaxHp(),40);
        assertEquals(enemy1.getDefense(),50);

        assertEquals(enemy1.getName(),enemy2.getName());
        assertEquals(enemy1.getWeight(),enemy2.getWeight());
        assertEquals(enemy1.getMaxHp(),enemy2.getMaxHp());
        assertEquals(enemy1.getDefense(),enemy2.getDefense());

        assertNotEquals(enemy1.getName(),enemy3.getName());
        assertNotEquals(enemy1.getWeight(),enemy3.getWeight());
        assertNotEquals(enemy1.getMaxHp(),enemy3.getMaxHp());
        assertNotEquals(enemy1.getDefense(),enemy3.getDefense());

        enemy1.setCurrentHp(30);
        enemy3.setCurrentHp(40);
        assertEquals(enemy1.getCurrentHp(),30);
        assertEquals(enemy2.getCurrentHp(),40);
    }

    @Test public void testQueue() throws InterruptedException {

        enemy2.waitTurn();
        enemy3.waitTurn();
        enemy4.waitTurn();


        Thread.sleep(6000);

        assertEquals(queue.poll(),enemy4);
        assertEquals(queue.poll(),enemy3);
        assertEquals(queue.poll(),enemy2);

    }

}
