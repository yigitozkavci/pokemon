package com.yigitozkavci.opensourcepokemon.pokemons.attacks;
import java.util.Random;
public class EggBomb extends Attack {
	
	public EggBomb(){
		Random rand = new Random();
		this.setName("Egg Bomb");
		this.setDamage(10);
		this.setDamageInterval(30);
		this.setLevelMult(2.3f);
		this.setDamageType(DamageType.TYPE_NONE);
	}
}
