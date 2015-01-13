package com.yigitozkavci.opensourcepokemon.pokemons;
import java.util.Random;

import com.yigitozkavci.opensourcepokemon.pokemons.attacks.Attack;
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
	
	private Attack[] attacks = new Attack[4];
	
	private PokemonType type;
	private String name;
	private int attack, defense, health;
	public Pokemon(String name, PokemonType type, int attack, int defense, int health){
		this.name = name;
		this.type = type;
		this.attack = attack;
		this.defense = defense;
		this.health = health;
		if(!name.equals("Magikarp"))
		{
			attacks[0] = new Tackle();
		}
	}
	public static Pokemon[] getPokemons(){
		if(pokemons != null)
		{
			return pokemons;
		}
		return pokemons = new Pokemon[]{
			new Pokemon("Bulbasaur", PokemonType.TYPE_GRASS, 60, 60, 200),
			new Pokemon("Blastoise", PokemonType.TYPE_WATER, 60, 60, 200),
			new Pokemon("Charizard", PokemonType.TYPE_FIRE, 60, 60, 200),
			new Pokemon("Alakazam", PokemonType.TYPE_PSYSIC, 60, 60, 200),
			new Pokemon("Snorlax", PokemonType.TYPE_NORMAL, 60, 60, 200),
			new Pokemon("Starly", PokemonType.TYPE_WATER, 60, 60, 200)
		};
	}
	public void inflictDamage(int amount){
		this.defense -= amount;
	}
	public void attackPokemon(Pokemon foe){
		if(attack > foe.defense/3){
			foe.health -= attack - foe.defense/3;
		}
		if(foe.health <= 0){
			return;
		}
		if(foe.attack > defense/3){
			health -= foe.attack - foe.defense/3;
		}
		return;
	}
	public void showStats(){
		System.out.print("\nStats of "+name+" | Attack: "+attack+" Defense: "
	+defense+" Health: "+health);
	}
	public void decreaseStats(){
		Random rand = new Random();
		attack -= 1+rand.nextInt(4);
		defense -= 1+rand.nextInt(4);
	}
	public static Pokemon getPokemon(int id){
		return getPokemons()[id];
	}
	public static Pokemon createNaturalPokemon(int level){
		Random rand = new Random();
		int health = level*60 + rand.nextInt(level*60);
		int damage = 25 + rand.nextInt(level*10);
		int defense = 35 + rand.nextInt(level*10);
		return new Pokemon("Natural", PokemonType.TYPE_GRASS, damage, defense, health);
	}
	public String getName(){
		return name;
	}
	public PokemonType getType(){
		return type;
	}
	public int getAttack(){
		return attack;
	}
	public int getDefense(){
		return defense;
	}
	public int getHealth(){
		return health;
	}
	
	
}
