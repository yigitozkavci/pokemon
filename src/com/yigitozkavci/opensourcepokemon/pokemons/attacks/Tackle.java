package com.yigitozkavci.opensourcepokemon.pokemons.attacks;
public class Tackle extends Attack {

	public Tackle() {
		this.setName("Tackle");
		this.setDamage(20);
		this.setDamageInterval(10);
		this.setLevelMult(2.3f);
		this.setDamageType(DamageType.TYPE_NONE);
	}
}
