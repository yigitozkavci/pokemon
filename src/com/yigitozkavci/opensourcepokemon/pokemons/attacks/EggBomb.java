package com.yigitozkavci.opensourcepokemon.pokemons.attacks;
public class EggBomb extends Attack {
	
	public EggBomb(){
		this.setName("Egg Bomb");
		this.setDamage(10);
		this.setDamageInterval(30);
		this.setLevelMult(2.3f);
		this.setDamageType(DamageType.TYPE_NONE);
	}
}
