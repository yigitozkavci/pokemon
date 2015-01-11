import java.util.Random;


public class Pokemon {
	private String name, type;
	private int attack, defense, health;
	public Pokemon(String name, String type, int attack, int defense, int health){
		this.name = name;
		this.type = type;
		this.attack = attack;
		this.defense = defense;
		this.health = health;
	}
	public static Pokemon[] getPokemons(){
		Pokemon[] pokemonList = new Pokemon[6];
		pokemonList[0] = new Pokemon("Balbazar", "grass", 60, 60, 200);
		pokemonList[1] = new Pokemon("Blastoise", "water", 60, 60, 200);
		pokemonList[2] = new Pokemon("Charizard", "fire", 60, 60, 200);
		pokemonList[3] = new Pokemon("Alakazam", "psychonic", 60, 60, 200);
		pokemonList[4] = new Pokemon("Snorlax", "napper", 60, 60, 200);
		pokemonList[5] = new Pokemon("Starli", "water", 60, 60, 200);
		return pokemonList;
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
		return new Pokemon("Natural", "natural", damage, defense, health);
	}
	public String getName(){
		return name;
	}
	public String getType(){
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
