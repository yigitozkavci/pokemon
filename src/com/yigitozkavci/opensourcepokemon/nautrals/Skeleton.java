package com.yigitozkavci.opensourcepokemon.nautrals;
import com.yigitozkavci.opensourcepokemon.pokemons.attacks.Tackle;

public class Skeleton extends Neutral {
	public Skeleton(int level){
		this.setName("Skeleton");
		this.setDefense(30+level*5);
		this.setLevel(level);
		this.setHealth(100+level*10);
		this.setDamage(15+level*5);
		this.setDamageInterval(level*5);
	}
}
