package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.Require;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the mages in the game.
 *
 * @author <a href="https://github.com/camilaF2022">R8V</a>
 * @author ~Camila Fuentes
 */

public abstract class AbstractMage extends AbstractPlayerCharacter {

  protected int currentMp;

  protected final int maxMp;

  /**
   * Creates a new mage.
   *
   * @param name
   *     the mage's name
   * @param maxHp
   *     the mage's max hp
   * @param defense
   *     the mage's defense
   * @param turnsQueue
   *     the queue with the mages waiting for their turn
   * @param maxMp
   *      the mage's max Mp
   */
  protected AbstractMage(final @NotNull String name, final int maxHp, final int defense,
                        int maxMp, final @NotNull BlockingQueue<GameCharacter> turnsQueue)
            throws InvalidStatValueException {
    super(name, maxHp, defense, turnsQueue);
    Require.statValueAtLeast(0, maxMp, "Max MP");
    this.maxMp = maxMp;
    this.currentMp = maxMp;
  }

  /**
   * Returns the current MP of the mage.
   */
  protected int getCurrentMp() {
    return currentMp;
  }

  /**
   * Sets the current MP of the mage to {@code newMp}.
   */

  protected void setCurrentMp(final int currentMp) throws InvalidStatValueException {
    Require.statValueAtLeast(0, currentMp, "Current MP");
    Require.statValueAtMost(maxMp, currentMp, "Current MP");
    this.currentMp = currentMp;
  }
  /**
   * Returns the max MP of the mage.
   */

  protected int getMaxMp() {
    return maxMp;
  }
}

