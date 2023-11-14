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
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * A White Mage is a type of player character that can cast White magic.
 *
 * @author @author <a href="https://github.com/camilaF2022">R8V</a>
 * @author Camila Fuentes
 */
public class WhiteMage extends AbstractMage {

  /**
   * Creates a new white mage.
   *
   * @param name
   *     the white mage's name
   * @param maxHp
   *     the white mage's max hp
   * @param defense
   *     the white mage's defense
   * @param maxMp
   *     the white mage's max Mp
   * @param turnsQueue
   *     the queue with the white mages waiting for their turn
   */
  public WhiteMage(final @NotNull String name, final int maxHp, final int defense,
      int maxMp, final @NotNull BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    super(name, maxHp, defense, maxMp, turnsQueue);
  }

  /**
   * The Mage wait his turn.
   */
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(
            /* command = */ this::addToQueue,
            /* delay = */ equippedWeapon.getWeight() / 10,
            /* unit = */ TimeUnit.SECONDS);

  }

  @Override
  public void equip(AbstractWeapon weapon) {
    weapon.checkWhiteMage(this);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final WhiteMage that)) {
      return false;
    }
    return hashCode() == that.hashCode()
        && maxMp == that.maxMp
        && name.equals(that.name)
        && maxHp == that.maxHp
        && defense == that.defense;
  }

  @Override
  public int hashCode() {
    return Objects.hash(WhiteMage.class, name, maxHp, defense, maxMp);
  }

  @Override
  public String toString() {
    return "WhiteMage{currentMp=%d, maxMp=%d, maxHp=%d, defense=%d, name='%s'}"
        .formatted(currentMp, maxMp, maxHp, defense, name);
  }

}
