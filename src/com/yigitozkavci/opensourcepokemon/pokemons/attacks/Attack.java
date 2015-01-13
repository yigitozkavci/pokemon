package com.yigitozkavci.opensourcepokemon.pokemons.attacks;

import com.yigitozkavci.opensourcepokemon.pokemons.Pokemon;

/**
 * <p>This is a Pokemon's attack. A {@link Pokemon} with a damage type of none deals
 * (damage + levelMult * level) damage to the opposing {@link Pokemon} without any
 * resistances.</p> 
 * 
 * <p>A damage type that is effective on opposing {@link Pokemon} (If you attack Charrizard
 * with a water type of attack), it'll deal twice as much damage before any resistances.
 * Similar rule applies if a damage type is not effective on opposing {@link Pokemon}. The
 * total damage will be halved in this case.</p>
 * 
 * <p>Almost every {@link Pokemon} starts with {@link Tackle} attack learned. (Magikarp starts with,
 * splash attack which deals no damage. Poor Magikarp :/)</p>
 * 
 * <p>If the attack has a special effect on use (blind or confuse the opposing {@link Pokemon}),
 * it must be implemented in special method.</p>
 * 
 * <p><strong>NOTE:</strong> Special may need a reference of 
 * our {@link Pokemon} and the opposing {@link Pokemon} which I will probably implement later.</p>
 * 
 * <p>Every {@link Pokemon} can learn up to 4 attacks. To learn an additional type of attack he/she
 * should forget one of his/her attacks.</p>
 * 
 * @author KaracaSoft
 *
 */
public abstract class Attack {
	
	public enum DamageType {
		TYPE_NONE,
		TYPE_FIRE,
		TYPE_WATER,
		TYPE_GRASS
	}
	
	private String name;
	private float damage;
	private float levelMult;
	private DamageType damageType;
	private int damageInterval;
	/**
	 * <p>Special method is implemented if {@link Pokemon} has a special effect on use of
	 * this attack. (Drain attack, absorbs health from opposing {@link Pokemon} according to
	 * the total damage dealt.)</p>
	 * @return if the special method is used successfully.
	 */
	public boolean isSpecial() {
		return false;
	}
	/**
	 * <p>If special type of attack triggers every turn, we use this method to handle it.
	 * (Torterra's Leech Seed attack deals damage and heals him every turn for 2 or 3 turns.)</p>
	 * @return
	 */
	public boolean specialEveryTurn() {
		return false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getDamage() {
		return damage;
	}
	public void setDamage(float damage) {
		this.damage = damage;
	}
	public int getDamageInterval(){
		return damageInterval;
	}
	public void setDamageInterval(int damageInterval){
		this.damageInterval = damageInterval;
	}
	public DamageType getDamageType() {
		return damageType;
	}
	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}
	public float getLevelMult() {
		return levelMult;
	}
	public void setLevelMult(float levelMult) {
		this.levelMult = levelMult;
	}
}
