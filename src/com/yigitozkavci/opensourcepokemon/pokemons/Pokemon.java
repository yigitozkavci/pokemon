package com.yigitozkavci.opensourcepokemon.pokemons;
import java.util.Random;

import com.yigitozkavci.opensourcepokemon.pokemons.attacks.Attack;
import com.yigitozkavci.opensourcepokemon.pokemons.attacks.EggBomb;
import com.yigitozkavci.opensourcepokemon.pokemons.attacks.Tackle;


public class Pokemon {
	
	private static Pokemon[] pokemons;
	
	public enum PokemonType{
		TYPE_NORMAL,
		TYPE_FIRE,
		TYPE_WATER,
		TYPE_GRASS,
		TYPE_PSYSIC
	}
	
	private Attack[] attacks = new Attack[2];
	private PokemonType type;
	private String name;
	private int defense, health;
	public Pokemon(String name, PokemonType type, int defense, int health){
		this.name = name;
		this.type = type;
		
		
		attacks[0] = new Tackle();
		attacks[1] = new EggBomb();
		this.defense = defense;
		this.health = health;
	}
	/*
	 * @return List of all Pokemons.
	 */
	public static Pokemon[] getPokemons(){
		if(pokemons != null)
		{
			return pokemons;
		}
		return pokemons = new Pokemon[]{
			new Pokemon("Bulbasaur", PokemonType.TYPE_GRASS, 60, 200),
			new Pokemon("Blastoise", PokemonType.TYPE_WATER, 60, 200),
			new Pokemon("Charizard", PokemonType.TYPE_FIRE, 60, 200),
			new Pokemon("Alakazam", PokemonType.TYPE_PSYSIC, 60, 200),
			new Pokemon("Snorlax", PokemonType.TYPE_NORMAL, 60, 200),
			new Pokemon("Starly", PokemonType.TYPE_WATER, 60, 200)
		};
	}
	public float attackPokemon(Pokemon foe, Attack attack){
		Random rand = new Random();
		float damage = attack.getDamage()+rand.nextInt(attack.getDamageInterval());
		float netDamage = 0;
		if(damage > foe.defense/3){
			netDamage = damage - foe.defense/3;
			foe.health -= netDamage;
		}
		return netDamage;
	}
	public void showStats(){
		System.out.print("\nStats of "+name+" | Defense: "
	+defense+" Health: "+health);
	}
	public void decreaseStats(){
		Random rand = new Random();
		defense -= 1+rand.nextInt(4);
	}
	public static Pokemon getPokemon(int id){
		return getPokemons()[id];
	}
	public static Pokemon createNaturalPokemon(int level){
		Random rand = new Random();
		int health = level*60 + rand.nextInt(level*60);
		int defense = 35 + rand.nextInt(level*10);
		return new Pokemon("Natural", PokemonType.TYPE_GRASS, defense, health);
	}
	public String getName(){
		return name;
	}
	public PokemonType getType(){
		return type;
	}
	public Attack[] getAttacks(){
		return attacks;
	}
	public int getDefense(){
		return defense;
	}
	public int getHealth(){
		return health;
	}
	
	
}
