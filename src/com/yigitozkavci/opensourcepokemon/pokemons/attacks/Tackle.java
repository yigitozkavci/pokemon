package com.yigitozkavci.opensourcepokemon.pokemons.attacks;

public class Tackle extends Attack {

	public Tackle() {
		this.setName("Tackle");
		this.setDamage(20f);
		this.setLevelMult(2.3f);
		this.setDamageType(DamageType.TYPE_NONE);
	}
	
	@Override
	public boolean special() {
		return false;
	}

	@Override
	public boolean specialEveryTurn() {
		return false;
	}

}
