package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.weapon.*;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class PlayerTest {

    BlockingQueue<GameCharacter> queue;

    private BlackMage blackmage1;
    private BlackMage blackmage2;
    private BlackMage blackmage3;

    private WhiteMage whitemage1;
    private WhiteMage whitemage2;
    private WhiteMage whitemage3;

    private Engineer engineer1;
    private Engineer engineer2;
    private Engineer engineer3;

    private Knight knight1;
    private Knight knight2;
    private Knight knight3;

    private Thief thief1;
    private Thief thief2;
    private Thief thief3;

    private Knife knife;
    private Bow bow;
    private Axe axe;
    private Staff staff;
    private Sword sword;



    @Before public void setUp() throws InvalidStatValueException {
        queue = new LinkedBlockingQueue<>();

        blackmage1 = new BlackMage("blackmage", 50, 50, 20, queue);
        blackmage2 = new BlackMage("blackmage", 50, 50, 20, queue);
        blackmage3 = new BlackMage("blackmage3", 30, 40, 60, queue);

        whitemage1 = new WhiteMage("whitemage", 50, 50, 20, queue);
        whitemage2 = new WhiteMage("whitemage", 50, 50, 20, queue);
        whitemage3 = new WhiteMage("whitemage3", 20, 40, 25, queue);

        engineer1 = new Engineer("engineer", 55, 60,  queue);
        engineer2 = new Engineer("engineer", 55, 60,  queue);
        engineer3 = new Engineer("engineer3", 40, 90,  queue);

        knight1 = new Knight("knight", 50, 65,  queue);
        knight2 = new Knight("knight", 50, 65,  queue);
        knight3 = new Knight("knight3", 40, 45,  queue);

        thief1 = new Thief("thief", 40, 50,  queue);
        thief2 = new Thief("thief", 40, 50,  queue);
        thief3 = new Thief("thief3", 30, 55,  queue);

        knife = new Knife("knife", 10, 10);
        bow = new Bow("bow", 20, 30);
        axe = new Axe("axe", 30,20);
        staff = new Staff("staff", 50, 50);
        sword = new Sword("sword", 60, 40);
    }

    @Test
    public void testEquals() {

        assertTrue(blackmage1.equals(blackmage2));
        assertTrue(whitemage1.equals(whitemage2));
        assertTrue(engineer1.equals(engineer2));
        assertTrue(knight1.equals(knight2));
        assertTrue(thief1.equals(thief2));

        assertTrue(blackmage3.equals(blackmage3));
        assertTrue(whitemage3.equals(whitemage3));
        assertTrue(engineer3.equals(engineer3));
        assertTrue(knight3.equals(knight3));
        assertTrue(thief3.equals(thief3));

        assertFalse(blackmage1.equals(blackmage3));
        assertFalse(whitemage1.equals(whitemage3));
        assertFalse(engineer1.equals(engineer3));
        assertFalse(knight1.equals(knight3));
        assertFalse(thief1.equals(thief3));

        assertFalse(blackmage1.equals(whitemage1));
        assertFalse(whitemage1.equals( blackmage1));
        assertFalse(blackmage1.equals(engineer1));
        assertFalse(engineer1.equals(whitemage1));
        assertFalse(knight1.equals(whitemage1));
        assertFalse(knight1.equals(engineer1));
        assertFalse(thief1.equals(whitemage1));

    }
    @Test public void testHasCode(){

        assertEquals(blackmage1.hashCode(),blackmage2.hashCode());
        assertEquals(whitemage1.hashCode(), whitemage2.hashCode());
        assertEquals(engineer1.hashCode(),engineer2.hashCode());
        assertEquals(knight1.hashCode(),knight2.hashCode());
        assertEquals(thief1.hashCode(),thief2.hashCode());

        assertEquals(blackmage3.hashCode(),blackmage3.hashCode());
        assertEquals(whitemage3.hashCode(), whitemage3.hashCode());
        assertEquals(engineer3.hashCode(),engineer3.hashCode());
        assertEquals(knight3.hashCode(),knight3.hashCode());
        assertEquals(thief3.hashCode(),thief3.hashCode());

        assertNotEquals(blackmage1.hashCode(),blackmage3.hashCode());
        assertNotEquals(whitemage1.hashCode(), whitemage3.hashCode());
        assertNotEquals(engineer1.hashCode(),engineer3.hashCode());
        assertNotEquals(knight1.hashCode(),knight3.hashCode());
        assertNotEquals(thief1.hashCode(),thief3.hashCode());

        assertNotEquals(blackmage1.hashCode(),whitemage1.hashCode());
        assertNotEquals(whitemage1.hashCode(), blackmage1.hashCode());
        assertNotEquals(engineer1.hashCode(),knight1.hashCode());
        assertNotEquals(knight1.hashCode(),thief1.hashCode());
        assertNotEquals(thief1.hashCode(),engineer1.hashCode());
    }
@Test public void testToString() {

    assertEquals(blackmage1.toString(), blackmage2.toString());
    assertEquals(whitemage1.toString(), whitemage2.toString());
    assertEquals(engineer1.toString(), engineer2.toString());
    assertEquals(knight1.toString(), knight2.toString());
    assertEquals(thief1.toString(), thief2.toString());

    assertEquals(blackmage3.toString(), blackmage3.toString());
    assertEquals(whitemage3.toString(), whitemage3.toString());
    assertEquals(engineer3.toString(), engineer3.toString());
    assertEquals(knight3.toString(), knight3.toString());
    assertEquals(thief3.toString(), thief3.toString());

    assertNotEquals(blackmage1.toString(), blackmage3.toString());
    assertNotEquals(whitemage1.toString(), whitemage3.toString());
    assertNotEquals(engineer1.toString(), engineer3.toString());
    assertNotEquals(knight1.toString(), knight3.toString());
    assertNotEquals(thief1.toString(), thief3.toString());

    assertEquals(blackmage1.toString(),"BlackMage{currentMp="+blackmage1.getCurrentMp()+", maxMp="+blackmage1.getMaxMp()+", maxHp="+blackmage1.getMaxHp()+", defense="+blackmage1.getDefense()+", name='"+blackmage1.getName()+"'}");
    assertEquals(whitemage1.toString(),"WhiteMage{currentMp="+whitemage1.getCurrentMp()+", maxMp="+whitemage1.getMaxMp()+", maxHp="+whitemage1.getMaxHp()+", defense="+whitemage1.getDefense()+", name='"+whitemage1.getName()+"'}");
    assertEquals(knight1.toString(),"Knight{maxHp="+knight1.getMaxHp()+", defense="+knight1.getDefense()+", name='"+knight1.getName()+"'}");
    assertEquals(thief1.toString(),"Thief{maxHp="+thief1.getMaxHp()+", defense="+thief1.getDefense()+", name='"+thief1.getName()+"'}");
    assertEquals(engineer1.toString(),"Engineer{maxHp="+engineer1.getMaxHp()+", defense="+engineer1.getDefense()+", name='"+engineer1.getName()+"'}");
    }

    @Test public void testCurrent() throws InvalidStatValueException {

        blackmage1.setCurrentMp(3);
        assertEquals(blackmage1.getCurrentMp(),3);

        whitemage1.setCurrentMp(4);
        assertEquals(whitemage1.getCurrentMp(),4);

        engineer1.setCurrentHp(5);
        assertEquals(engineer1.getCurrentHp(),5);

        knight1.setCurrentHp(6);
        assertEquals(knight1.getCurrentHp(),6);

        thief1.setCurrentHp(7);
        assertEquals(thief1.getCurrentHp(),7);
    }
    @Test public void testEquipWeapons() {

        blackmage1.equip(sword);
        assertEquals(blackmage1.getEquippedWeapon(),null);
        blackmage1.equip(knife);
        assertEquals(blackmage1.getEquippedWeapon(),knife);
        blackmage1.equip(axe);
        assertEquals(blackmage1.getEquippedWeapon(),knife);
        blackmage1.equip(staff);
        assertEquals(blackmage1.getEquippedWeapon(),staff);
        blackmage1.equip(bow);
        assertEquals(blackmage1.getEquippedWeapon(),staff);


        whitemage1.equip(sword);
        assertEquals(whitemage1.getEquippedWeapon(),null);
        whitemage1.equip(knife);
        assertEquals(whitemage1.getEquippedWeapon(),null);
        whitemage1.equip(axe);
        assertEquals(whitemage1.getEquippedWeapon(),null);
        whitemage1.equip(staff);
        assertEquals(whitemage1.getEquippedWeapon(),staff);
        whitemage1.equip(bow);
        assertEquals(whitemage1.getEquippedWeapon(),staff);


        engineer1.equip(sword);
        assertEquals(engineer1.getEquippedWeapon(),null);
        engineer1.equip(axe);
        assertEquals(engineer1.getEquippedWeapon(),axe);
        engineer1.equip(knife);
        assertEquals(engineer1.getEquippedWeapon(),axe);
        engineer1.equip(bow);
        assertEquals(engineer1.getEquippedWeapon(),bow);
        engineer1.equip(staff);
        assertEquals(engineer1.getEquippedWeapon(),bow);


        knight1.equip(staff);
        assertEquals(knight1.getEquippedWeapon(),null);
        knight1.equip(sword);
        assertEquals(knight1.getEquippedWeapon(),sword);
        knight1.equip(bow);
        assertEquals(knight1.getEquippedWeapon(),sword);
        knight1.equip(axe);
        assertEquals(knight1.getEquippedWeapon(),axe);
        knight1.equip(knife);
        assertEquals(knight1.getEquippedWeapon(),knife);


        thief1.equip(axe);
        assertEquals(thief1.getEquippedWeapon(),null);
        thief1.equip(sword);
        assertEquals(thief1.getEquippedWeapon(),sword);
        thief1.equip(staff);
        assertEquals(thief1.getEquippedWeapon(),sword);
        thief1.equip(knife);
        assertEquals(thief1.getEquippedWeapon(),knife);
        thief1.equip(bow);
        assertEquals(thief1.getEquippedWeapon(),bow);
    }
    @Test public void testQueue() throws InterruptedException {

        blackmage1.equip(knife);
        blackmage1.waitTurn();

        whitemage1.equip(staff);
        whitemage1.waitTurn();

        engineer1.equip(bow);
        engineer1.waitTurn();

        knight1.equip(axe);
        knight1.waitTurn();

        thief1.equip(sword);
        thief1.waitTurn();

        Thread.sleep(6000);

        assertEquals(queue.poll(),blackmage1);
        assertEquals(queue.poll(),knight1);
        assertEquals(queue.poll(),engineer1);
        assertEquals(queue.poll(),thief1);
        assertEquals(queue.poll(),whitemage1);


    }

}