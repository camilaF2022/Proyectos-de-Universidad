package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;
import java.util.Objects;
/**
 * A sword is a type of weapon that can be used by a character.
 *
 * @author  <a href="https://github.com/camilaF2022">R8V</a>
 * @author Camila Fuentes
 */

public class Sword extends AbstractWeapon {

  /**
   * Creates a sword with a name, a base damage, weight.
   *
   * @param name
   *      is the sword's name
   * @param damage
   *      is the sword's damage
   * @param weight
   *      is the sword's weight
   */
  public Sword(String name, int damage, int weight) {
    super(name, damage, weight);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Sword weapon)) {
      return false;
    }
    return hashCode() == weapon.hashCode()
                && damage == weapon.damage
                && weight == weapon.weight
                && name.equals(weapon.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Sword.class, name, damage, weight);
  }

  @Override
  public String toString() {
    return "Weapon{name='%s', damage=%d, weight=%d}"
                .formatted(name, damage, weight);
  }

  @Override
  public void checkKnight(Knight knight) {
    knight.equippedWeapon = this;
  }

  @Override
  public void checkEngineer(Engineer engineer) {

  }

  @Override
  public void checkThief(Thief thief) {
    thief.equippedWeapon = this;
  }

  @Override
  public void checkBlackMage(BlackMage blackmage) {

  }

  @Override
  public void checkWhiteMage(WhiteMage whitemage) {

  }
}
