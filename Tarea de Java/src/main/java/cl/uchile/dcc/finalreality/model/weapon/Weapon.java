package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;

/**
 * This represents a weapons from the game.
 * A weapon can be equiped by a character
 *
 *  @author  <a href="https://github.com/camilaF2022">R8V</a>
 *  @author Camila Fuentes
 *
 **/
public interface Weapon {

  /**
   * Returns this weapon's name.
   */
  String getName();

  /**
   * Returns this weapon's damage.
   */

  int getDamage();

  /**
   * Returns this weapon's weight.
   */

  int getWeight();

  /**
   * Check if the knight character can equip the weapon.
   **/
  void checkKnight(Knight knight);

  /**
   * Check if the engineer character can equip the weapon.
   **/
  void checkEngineer(Engineer engineer);

  /**
   * Check if the thief character can equip the weapon.
   **/
  void checkThief(Thief thief);

  /**
   * Check if the blackmage character can equip the weapon.
   **/
  void checkBlackMage(BlackMage blackmage);

  /**
   * Check if the whitemage character can equip the weapon.
   **/
  void checkWhiteMage(WhiteMage whitemage);

}
