package com.yigitozkavci.opensourcepokemon;
import java.util.Scanner;
import java.util.Random;

import com.yigitozkavci.opensourcepokemon.pokemons.Pokemon;
public class Game {
	String gameState = "Pokemon Select";
	boolean isPokemonSelected;
	Pokemon selectedPokemon;
	int pokemonLevel = 1;
	int pokemonExp = 0;
	public Game(){
		this.isPokemonSelected = false;
	}
	public void start(){
		openMenu("Pokemon Select");
		openMenu("Take Action");
	}
	public void openMenu(String query){
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		if(query == "Pokemon Select"){
			System.out.println("Select your pokemon:");
			int i = 0;
			for(Pokemon pokemon : Pokemon.getPokemons()){
				i++;
				System.out.println(pokemon.getName()+"("+i+")");
			};
			if(scan.hasNextInt()){
				int pokemonSelectInput = scan.nextInt();
				if(pokemonSelectInput > 0 && pokemonSelectInput <= i){
					Pokemon selectedPokemon = Pokemon.getPokemon(pokemonSelectInput-1);
					this.isPokemonSelected = true;
					this.selectedPokemon = selectedPokemon;
					gameState = "Free";
					System.out.println("You have selected: "+selectedPokemon.getName());
				}
				else{
					System.out.println("Wrong input. Try again.");
					openMenu("Pokemon Select");
				}
			}
			else{
				System.out.println("Wrong input. Try again.");
				openMenu("Pokemon Select");
			}
		}
		if(query == "Take Action"){
			if(gameState == "Free"){
				System.out.println("\nWhat do you want to do?");
				System.out.println("1) Move Around 2) Show Pokemon Status 3) Exit Game");
				if(scan.hasNextInt()){
					int selectedMove = scan.nextInt();
					if(selectedMove == 1){
						if(rand.nextInt(100)>30){
							gameState = "Encounter";
							openMenu("Take Action");
						}
						else{
							System.out.println("You're walking . . and nothing happens . .");
							openMenu("Take Action");
						}
					}
					else if(selectedMove == 2){
						requestPokemonStatus();
						openMenu("Take Action");
					}
					else if(selectedMove == 3){
						System.exit(0);
					}
					else{
						System.out.println("Wrong input. Try again.");
						openMenu("Take Action");
					}
				}
				else{
					System.out.println("Wrong input. Try again.");
					openMenu("Take Action");
				}
			}
			else if(gameState == "Encounter"){
				System.out.println("-- BATTLE --");
				int naturalLevel = 1+rand.nextInt(3);
				Pokemon naturalPokemon = Pokemon.createNaturalPokemon(naturalLevel);

				beginBattle(selectedPokemon, naturalPokemon, naturalLevel);
				
			}
		}
	}
	/**
	 * @param p1 Player 1 for the battle.
	 * @param p2 Player 2 for the battle.
	 */
	public void beginBattle(Pokemon player, Pokemon foe, int naturalLevel){
		player.showStats();
		foe.showStats(); System.out.print("(Level: "+naturalLevel+")");
		Random rand = new Random();
		String fightStatus = "Fighting";
		
		Scanner scan = new Scanner(System.in);
		while(player.getHealth() > 0 && foe.getHealth() > 0){
			System.out.println();
			System.out.println("1) Attack it(will hurt you but reward exp points.");
			System.out.println("2) Run(will couse you to lose atk & def stats.)");
			int attackMove = scan.nextInt();
			
			
			if(attackMove == 1){
				player.attackPokemon(foe);
				System.out.println("Attacked.");
				player.showStats(); 
				foe.showStats(); System.out.print("(Level: "+naturalLevel+")");
				continue;
			}
			else if(attackMove == 2){
				player.decreaseStats();
				fightStatus = "Given up";
				break;
			}
		}
		int XP;
		if(fightStatus == "Fighting"){
			XP = rand.nextInt(10)+naturalLevel*20;
		}else{
			XP = 0;
		}
		
		System.out.println("\nBattle Over[XP Gained: "+XP+" XP]");
		gainExp(XP);
		gameState = "Free";
		openMenu("Take Action");
	}
	public void requestPokemonStatus(){
		System.out.println("\n--- Pokemon Stats ---");
		
		System.out.println("Pokemon: "+selectedPokemon.getName()+"("+
		selectedPokemon.getType()+") HP: "+selectedPokemon.getHealth());
		
		System.out.println("Attack: "+selectedPokemon.getAttack()+" Defense: "+
		selectedPokemon.getDefense());
		
		System.out.println("Level: "+pokemonLevel+" XP: "+pokemonExp);
	}
	public void levelUp(){
		pokemonLevel++;
	}
	public void gainExp(int amount){
		pokemonExp+=amount;
		if(pokemonExp > 100){
			levelUp();
			pokemonExp -= 100;
		}
	}
}
/* Start by choosing a pokemon with a list.
 * 
 * 
 * 
 * 
 */
