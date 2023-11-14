package cl.uchile.dcc.finalreality.model.weapon;

/**
 * A class that holds all the information of the weapon  in the game.
 *
 * @author  <a href="https://github.com/camilaF2022">R8V</a>
 * @author Camila Fuentes
 */
public abstract class AbstractWeapon implements Weapon {
  protected final String name;
  protected final int damage;
  protected final int weight;

  /**
   * Creates a weapon with a name, a base damage, weight.
   *
   * @param name
   *     the weapon's name
   * @param damage
   *     the weapon's damage
   * @param weight
   *     the weapon's weight
   */
  public AbstractWeapon(final String name, final int damage, final int weight) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
  }

  /**
   * Returns the name of the weapon.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Returns the damage to the weapon.
   */
  @Override
  public int getDamage() {
    return damage;
  }

  /**
  * Returns the weight of the weapon.
  */
  @Override
  public int getWeight() {
    return weight;
  }
}
