package com.yigitozkavci.opensourcepokemon.nautrals;

import java.util.Random;

import com.yigitozkavci.opensourcepokemon.pokemons.Pokemon;
import com.yigitozkavci.opensourcepokemon.pokemons.attacks.Attack;

/**
 * <p>This is a neutral mob. They do not have special
 * {@link Attack} types. They have <code>attacks</code> & <code>defense</code>s with a constant an
 * interval which increases by their <code>level</code>s. They will be encountered by the
 * player while walking freely. They will yield <code>XP</code>. </p>
 * @author Yiðit Özkavcý
 */
public abstract class Neutral {
	private String name;
	private int level, defense, damage;
	private float health;
	int damageInterval;
	
	// Getter & Setters
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getLevel(){
		return level;
	}
	public void setLevel(int level){
		this.level = level;
	}
	public int getDefense(){
		return defense;
	}
	public void setDefense(int defense){
		this.defense = defense;
	}
	public float getHealth(){
		return health;
	}
	public void setHealth(float health){
		this.health = health;
	}
	public int getDamage(){
		return damage;
	}
	public void setDamage(int damage){
		this.damage = damage;
	}
	public float getDamageInterval(){
		return damageInterval;
	}
	public void setDamageInterval(int damageInterval){
		this.damageInterval = damageInterval;
	}
	public float attackPlayer(Pokemon player){
		Random rand = new Random();
		int bareDamage = damage+rand.nextInt(damageInterval);
		int netDamage = 0;
		if(bareDamage > player.getDefense()/3){
			netDamage = bareDamage - player.getDefense()/3;
			player.setHealth(player.getHealth() - netDamage);
		}
		return netDamage;
	}
	public void showStatus(){
		
	}
}
