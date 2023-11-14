/*
 * "Final Reality" (c) by R8V and ~Your name~
 * "Final Reality" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 */

package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.weapon.AbstractWeapon;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A Black Mage is a type of player character that can cast black magic.
 *
 * @author <a href="https://github.com/camilaF2022">R8V</a>
 * @author Camila Fuentes
 */
public class BlackMage extends AbstractMage {

  /**
   * Creates a new Black Mage.
   *
   * @param name
   *     the black mage's name
   * @param maxHp
   *     the black mage's max hp
   * @param defense
   *     the black mage's defense
   * @param maxMp
   *     the black mage's max mp
   * @param turnsQueue
   *     the queue with the black mages waiting for their turn
   */
  public BlackMage(final @NotNull String name, final int maxHp, final int defense,
      int maxMp, final @NotNull BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    super(name, maxHp, defense, maxMp, turnsQueue);
  }

  // region : ACCESSORS

  // endregion

  // region : UTILITY METHODS
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final BlackMage that)) {
      return false;
    }
    return hashCode() == that.hashCode()
        && name.equals(that.name)
        && maxHp == that.maxHp
        && defense == that.defense
        && maxMp == that.maxMp;
  }

  @Override
  public String toString() {
    return "BlackMage{currentMp=%d, maxMp=%d, maxHp=%d, defense=%d, name='%s'}"
        .formatted(currentMp, maxMp, maxHp, defense, name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(BlackMage.class, name, maxHp, defense, maxMp);
  }

  @Override
  public void equip(AbstractWeapon weapon) {
    weapon.checkBlackMage(this);
  }
  // endregion
}
