package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.*;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.*;

public class WeaponTest {

    BlockingQueue<GameCharacter> queue;
    private Axe axe1;
    private Axe axe2;
    private Axe axe3;

    private Bow bow1;
    private Bow bow2;
    private Bow bow3;

    private Knife knife1;
    private Knife knife2;
    private Knife knife3;

    private Staff staff1;
    private Staff staff2;
    private Staff staff3;

    private Sword sword1;
    private Sword sword2;
    private Sword sword3;
    private BlackMage blackmage1;
    private WhiteMage whitemage1;
    private Thief thief1;
    private Engineer engineer1;
    private Knight knight1;

    @Before
    public void setUp() throws InvalidStatValueException {

        queue = new LinkedBlockingQueue<>();

        axe1 = new Axe("axe", 30, 10);
        axe2 = new Axe("axe", 30, 10);
        axe3 = new Axe("axe3", 60, 40);

        bow1 = new Bow("bow", 20, 15);
        bow2 = new Bow("bow", 20, 15);
        bow3 = new Bow("bow3", 40, 10);

        knife1 = new Knife("knife", 10, 5);
        knife2 = new Knife("knife", 10, 5);
        knife3 = new Knife("knife3", 30, 8);

        staff1 = new Staff("staff", 50, 20);
        staff2 = new Staff("staff", 50, 20);
        staff3 = new Staff("staff3", 30, 8);

        sword1 = new Sword("sword", 60, 40);
        sword2 = new Sword("sword", 60, 40);
        sword3 = new Sword("sword3", 60, 40);

        blackmage1 = new BlackMage("blackmage", 50, 50, 20, queue);
        whitemage1 = new WhiteMage("whitemage", 50, 50, 20, queue);
        engineer1 = new Engineer("engineer", 55, 60,  queue);
        knight1 = new Knight("knight", 50, 65,  queue);
        thief1 = new Thief("thief", 40, 50,  queue);

    }


    @Test
    public void testEquals() {

        assertTrue(axe1.equals(axe2));
        assertTrue(bow1.equals(bow2));
        assertTrue(knife1.equals(knife2));
        assertTrue(staff1.equals(staff2));
        assertTrue(sword1.equals(sword2));

        assertTrue(axe3.equals(axe3));
        assertTrue(bow3.equals(bow3));
        assertTrue(knife3.equals(knife3));
        assertTrue(staff3.equals(staff3));
        assertTrue(sword3.equals(sword3));

        assertFalse(axe1.equals(axe3));
        assertFalse(bow1.equals(bow3));
        assertFalse(knife1.equals(knife3));
        assertFalse(staff1.equals(staff3));
        assertFalse(sword1.equals(sword3));

        assertFalse(axe1.equals(sword3));
        assertFalse(bow1.equals(sword3));
        assertFalse(knife1.equals(sword3));
        assertFalse(staff1.equals(sword3));
        assertFalse(sword1.equals(axe3));

    }

    @Test
    public void testHashCode() {

        assertEquals(axe1.hashCode(), axe2.hashCode());
        assertEquals(bow1.hashCode(), bow2.hashCode());
        assertEquals(knife1.hashCode(), knife2.hashCode());
        assertEquals(staff1.hashCode(), staff2.hashCode());
        assertEquals(sword1.hashCode(), sword2.hashCode());

        assertEquals(axe3.hashCode(), axe3.hashCode());
        assertEquals(bow3.hashCode(), bow3.hashCode());
        assertEquals(knife3.hashCode(), knife3.hashCode());
        assertEquals(staff3.hashCode(), staff3.hashCode());
        assertEquals(sword3.hashCode(), sword3.hashCode());

        assertNotEquals(axe1.hashCode(), axe3.hashCode());
        assertNotEquals(bow1.hashCode(), bow3.hashCode());
        assertNotEquals(knife1.hashCode(), knife3.hashCode());
        assertNotEquals(staff1.hashCode(), staff3.hashCode());
        assertNotEquals(sword1.hashCode(), sword3.hashCode());
    }

    @Test
    public void testToString() {

        assertEquals(axe1.toString(), axe2.toString());
        assertEquals(bow1.toString(), bow2.toString());
        assertEquals(knife1.toString(), knife2.toString());
        assertEquals(staff1.toString(), staff2.toString());
        assertEquals(sword1.toString(), sword2.toString());

        assertEquals(axe3.toString(), axe3.toString());
        assertEquals(bow3.toString(), bow3.toString());
        assertEquals(knife3.toString(), knife3.toString());
        assertEquals(staff3.toString(), staff3.toString());
        assertEquals(sword3.toString(), sword3.toString());

        assertNotEquals(axe1.toString(), axe3.toString());
        assertNotEquals(bow1.toString(), bow3.toString());
        assertNotEquals(knife1.toString(), knife3.toString());
        assertNotEquals(staff1.toString(), staff3.toString());
        assertNotEquals(sword1.toString(), sword3.toString());


        assertEquals(axe1.toString(), "Weapon{name='" + axe1.getName() + "', damage=" + axe2.getDamage() + ", weight=" + axe1.getWeight() + "}");
        assertEquals(bow1.toString(), "Weapon{name='" + bow1.getName() + "', damage=" + bow2.getDamage() + ", weight=" + bow1.getWeight() + "}");
        assertEquals(knife1.toString(), "Weapon{name='" + knife1.getName() + "', damage=" + knife2.getDamage() + ", weight=" + knife1.getWeight() + "}");
        assertEquals(staff1.toString(), "Weapon{name='" + staff1.getName() + "', damage=" + staff2.getDamage() + ", weight=" + staff1.getWeight() + "}");
        assertEquals(sword1.toString(), "Weapon{name='" + sword1.getName() + "', damage=" + sword2.getDamage() + ", weight=" + sword1.getWeight() + "}");

        assertNotEquals(axe3.toString(), "Weapon{name='" + axe1.getName() + "', damage=" + axe2.getDamage() + ", weight=" + axe1.getWeight() + "}");
        assertNotEquals(bow3.toString(), "Weapon{name='" + bow1.getName() + "', damage=" + bow2.getDamage() + ", weight=" + bow1.getWeight() + "}");
        assertNotEquals(knife3.toString(), "Weapon{name='" + knife1.getName() + "', damage=" + knife2.getDamage() + ", weight=" + knife1.getWeight() + "}");
        assertNotEquals(staff3.toString(), "Weapon{name='" + staff1.getName() + "', damage=" + staff2.getDamage() + ", weight=" + staff1.getWeight() + "}");
        assertNotEquals(sword3.toString(), "Weapon{name='" + sword1.getName() + "', damage=" + sword2.getDamage() + ", weight=" + sword1.getWeight() + "}");

    }

    @Test public void testChecksAndEquip() {

        blackmage1.equip(sword1);
        assertEquals(blackmage1.getEquippedWeapon(),null);
        blackmage1.equip(knife1);
        assertEquals(blackmage1.getEquippedWeapon(),knife1);
        blackmage1.equip(axe1);
        assertEquals(blackmage1.getEquippedWeapon(),knife1);
        blackmage1.equip(staff1);
        assertEquals(blackmage1.getEquippedWeapon(),staff1);
        blackmage1.equip(bow1);
        assertEquals(blackmage1.getEquippedWeapon(),staff1);


        whitemage1.equip(sword1);
        assertEquals(whitemage1.getEquippedWeapon(),null);
        whitemage1.equip(knife1);
        assertEquals(whitemage1.getEquippedWeapon(),null);
        whitemage1.equip(axe1);
        assertEquals(whitemage1.getEquippedWeapon(),null);
        whitemage1.equip(staff1);
        assertEquals(whitemage1.getEquippedWeapon(),staff1);
        whitemage1.equip(bow1);
        assertEquals(whitemage1.getEquippedWeapon(),staff1);


        engineer1.equip(sword1);
        assertEquals(engineer1.getEquippedWeapon(),null);
        engineer1.equip(axe1);
        assertEquals(engineer1.getEquippedWeapon(),axe1);
        engineer1.equip(knife1);
        assertEquals(engineer1.getEquippedWeapon(),axe1);
        engineer1.equip(bow1);
        assertEquals(engineer1.getEquippedWeapon(),bow1);
        engineer1.equip(staff1);
        assertEquals(engineer1.getEquippedWeapon(),bow1);


        knight1.equip(staff1);
        assertEquals(knight1.getEquippedWeapon(),null);
        knight1.equip(sword1);
        assertEquals(knight1.getEquippedWeapon(),sword1);
        knight1.equip(bow1);
        assertEquals(knight1.getEquippedWeapon(),sword1);
        knight1.equip(axe1);
        assertEquals(knight1.getEquippedWeapon(),axe1);
        knight1.equip(knife1);
        assertEquals(knight1.getEquippedWeapon(),knife1);


        thief1.equip(axe1);
        assertEquals(thief1.getEquippedWeapon(),null);
        thief1.equip(sword1);
        assertEquals(thief1.getEquippedWeapon(),sword1);
        thief1.equip(staff1);
        assertEquals(thief1.getEquippedWeapon(),sword1);
        thief1.equip(knife1);
        assertEquals(thief1.getEquippedWeapon(),knife1);
        thief1.equip(bow1);
        assertEquals(thief1.getEquippedWeapon(),bow1);
    }
}
