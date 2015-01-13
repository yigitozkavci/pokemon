package com.yigitozkavci.opensourcepokemon.pokemons.attacks;

import java.util.ArrayList;

import com.yigitozkavci.opensourcepokemon.pokemons.Pokemon;

/**
 * <p>This is a {@link Pokemon}'s attack. A {@link Pokemon} deals
 * (damage + levelMult * level) damage to the opposing {@link Pokemon} without any
 * resistances.</p> 
 * 
 * <p>A damage type that is effective on opposing {@link Pokemon} (If you attack Charizard
 * with a water type of attack), it'll deal twice as much damage before any resistances.
 * Similar rule applies if a damage type is not effective on opposing {@link Pokemon}. The
 * total damage will be halved in this case.</p>
 * 
 * <p>Almost every {@link Pokemon} starts with Tackle attack learned. (Magikarp starts with 
 * Splash attack which deals no damage. Poor Magikarp :/)</p>
 * 
 * <p>If the attack has a special effect on use (blind or confuse the opposing {@link Pokemon}),
 * it must be implemented with the {@link SpecialEffect} interface which includes 
 * {@link SpecialEffect.#special()} method. One attack may have more than one special
 *  effects.</p>
 * 
 * <p>Every {@link Pokemon} can learn up to 4 attacks. To learn an additional type of attack 
 * he/she should forget one of his/her attacks.</p>
 * 
 * @author triforce930
 *
 */
public class Attack {
	
	private ArrayList<SpecialEffect> effects = new ArrayList<>();
	private String name;
	private float damage;
	private float levelMult;
	private DamageType damageType;
	
	/**
	 * <p>Empty constructor is implemented to create the most basic 
	 * attack, Tackle, in the game.</p>
	 */
	public Attack(){
		this.name = "Tackle";
		this.damage = 20f;
		this.damageType = DamageType.TYPE_NONE;
		this.levelMult = 2.6f;
	}
	/**
	 * <p>Special constructor to create any other attack in the game</p> 
	 * @param name The name of the attack.
	 * @param type Type of the attack
	 * @param damage Damage of the attack
	 * @param levelMult Level multiplier of the attack.
	 */
	public Attack(String name, DamageType type, float damage, float levelMult){
		this.name = name;
		this.damageType = type;
		this.damage = damage;
		this.levelMult = levelMult;
	}
	/**
	 * <p>Enumeration to define Damage types in the game.</p>
	 * @author triforce930
	 */
	public enum DamageType {
		TYPE_NONE,
		TYPE_FIRE,
		TYPE_WATER,
		TYPE_GRASS
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
	public ArrayList<SpecialEffect> getEffects() {
		return effects;
	}
}
