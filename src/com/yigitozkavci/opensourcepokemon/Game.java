package com.yigitozkavci.opensourcepokemon;
import java.util.Scanner;
import java.util.Random;

import com.yigitozkavci.opensourcepokemon.nautrals.Neutral;
import com.yigitozkavci.opensourcepokemon.nautrals.Skeleton;
import com.yigitozkavci.opensourcepokemon.pokemons.Pokemon;
import com.yigitozkavci.opensourcepokemon.pokemons.attacks.Attack;
/**
 * <p>Main game class where everything starts and everything ends. Managing {@code Pokemon Select} and 
 * menu {@code openMenu} recursion which will be documented by its own.</p>
 * @author Yi�it �zkavc�
 */
public class Game {
	boolean isPokemonSelected;
	Pokemon selectedPokemon;
	public enum fightStatus{
		STATUS_FIGHTING,
		STATUS_GIVEN_UP,
		STATUS_OVER
	}
	int pokemonLevel = 1;
	int pokemonExp = 0;
	public Game(){
		this.isPokemonSelected = false;
	}
	public void start(){
		openMenu("Pokemon Select");
	}
	/**
	 * <p>Opens the main recursive non-ending menu and game starts. </p>
	 * <p><b>Phases: </b></p>
	 * <ul>
	 * 		<li>
	 * 			<p><b>Pokemon Select: </b>This is the first game phase, where player chooses his/her pokemon
	 * 			and adventure begins.(Next Phase: "Free")</p>
	 * 		</li>
	 * 		<li>
	 * 			<p><b>Free: </b>In this phase, player is allowed to move freely. He/she may move,
	 * 			request pokemon status or exit the game. If he/she moves, he may encounter a
	 * 			skeleton (Next Phase: "Skeleton Fight"), a pokemon (Next Phase: "Pokemon Fight");
	 * 			find an item(Next Phase:"Found Chest") or find nothing(Next Phase: "Free"). </p>
	 * 		</li>
	 * 		<li>
	 * 			<p><b>Skeleton Fight: </b>In this phase, player either fights to skeleton(Next Phase: "Free")
	 * 			or runs away(Next Phase: "Free").</p>
	 * 		</li>
	 * 		<li>
	 * 			<p><b>Pokemon Fight: </b>In this phase, player either fights to pokemon in order to capture or
	 * 			kill it(Next Phase: "Free") or runs away(Next Phase: "Free").</p>
	 * 		</li>
	 * </ul>
	 * @author Yi�it �zkavc�
	 * @param query Phase name to pass through menu function.
	 */
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
					System.out.println("You have selected: "+selectedPokemon.getName());
					openMenu("Free");
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
		else if(query == "Free"){
			System.out.println("\nWhat do you want to do?");
			System.out.println("1) Move Around 2) Show Pokemon Status 3) Exit Game");
			if(scan.hasNextInt()){
				int selectedMove = scan.nextInt();
				if(selectedMove == 1){
					int walkEncounter = 1+rand.nextInt(100);
					if(walkEncounter>0 && walkEncounter<=10){
						openMenu("Pokemon Fight");
					}
					else if(walkEncounter>10 && walkEncounter<=30){
						openMenu("Skeleton Fight");
					}
					else if(walkEncounter>30 && walkEncounter<=50){
						System.out.println("You've found an item!");
						openMenu("Free");
					}
					else{
						System.out.println("You're walking . . and nothing happens . .");
						openMenu("Free");
					}
				}
				else if(selectedMove == 2){
					requestPokemonStatus();
					openMenu("Free");
				}
				else if(selectedMove == 3){
					System.exit(0);
				}
				else{
					System.out.println("Wrong input. Try again.");
					openMenu("Free");
				}
			}
			else{
				System.out.println("Wrong input. Try again.");
				openMenu("Free");
			}
		}else if(query == "Skeleton Fight"){
			beginNeutralBattle(selectedPokemon, new Skeleton(1+rand.nextInt(3)));
			openMenu("Free");
		}else if(query == "Pokemon Fight"){
			System.out.println("-- BATTLE --");
			int neutralLevel = 3+rand.nextInt(3);
			Pokemon neutralPokemon = Pokemon.createNaturalPokemon(neutralLevel);
			beginPokemonBattle(selectedPokemon, neutralPokemon, neutralLevel);
			openMenu("Free");
		}
		else{
			System.out.println("Wow Haram");
		}
	}
	/**
	 * <p>Begins battle between the player's pokemon and the neutral Pokemon. 
	 * Because there is no capture functionality yet, player either kills it
	 * or runs away. </p>
	 *  
	 * @param player Player.
	 * @param foe Neutral pokemon which player is facing with.
	 * @param neutralLevel Level of the neutral pokemon. Will be used to scale it's defense and health.
	 * @author Yi�it �zkavc�
	 */
	public void beginPokemonBattle(Pokemon player, Pokemon foe, int neutralLevel){
		player.showStats();
		foe.showStats(); System.out.print("(Level: "+neutralLevel+")");
		Random rand = new Random();
		fightStatus pokemonBattleStatus = fightStatus.STATUS_FIGHTING;
		Scanner scan = new Scanner(System.in);
		while(player.getHealth() > 0 && foe.getHealth() > 0){
			System.out.println();
			System.out.println("1) Attack it(will hurt you but reward exp points.");
			System.out.println("2) Run(will couse you to lose atk & def stats.)");
			int battleMove = scan.nextInt();
			if(battleMove == 1){
				for(int i = 0; i<player.getAttacks().length; i++){
					System.out.println((i+1)+") "+player.getAttacks()[i].getName()+"\n");
				}
				int attackMove = scan.nextInt();
				Attack usedAttack = player.getAttacks()[attackMove-1];
				float damageInflicted = player.attackPokemon(foe, usedAttack);
				System.out.println("You used: "+usedAttack.getName()+", Damage Inflicted: "+damageInflicted);
				
				Attack takenAttack = foe.getAttacks()[rand.nextInt(foe.getAttacks().length)];
				float damageTaken = foe.attackPokemon(player, takenAttack);
				System.out.println(foe.getName()+" Used: "+takenAttack.getName()+", Damage Taken: "+damageTaken);
				
				player.showStats(); 
				foe.showStats(); System.out.print("(Level: "+neutralLevel+")");
				continue;
			}
			else if(battleMove == 2){
				player.decreaseStats();
				pokemonBattleStatus = fightStatus.STATUS_GIVEN_UP;
				break;
			}
		}
		
		int XP;
		if(pokemonBattleStatus == fightStatus.STATUS_FIGHTING){
			XP = rand.nextInt(10)+neutralLevel*20;
		}else{
			XP = 0;
		}
		pokemonBattleStatus = fightStatus.STATUS_OVER;
		System.out.println("\nBattle Over[XP Gained: "+XP+" XP]");
		gainExp(XP);
	}
	/**
	 * Begins the battle between player and a {@link Neutral}. Player uses an {@link Attack} on {@link Neutral}
	 * as its move, and {@link Neutral} attacks back with an {@code attack} amount of which is being determined
	 * by its {@code level}.
	 * @param player Player
	 * @param neutral Neutral
	 * @author Yi�it �zkavc�
	 */
	public void beginNeutralBattle(Pokemon player, Neutral neutral){
		fightStatus neutralBattleStatus = fightStatus.STATUS_FIGHTING;
		
	}
	/**
	 * Prints whole data about the Pokemon player chose.
	 */
	public void requestPokemonStatus(){
		System.out.println("\n--- Pokemon Stats ---");
		
		System.out.println("Pokemon: "+selectedPokemon.getName()+"("+
		selectedPokemon.getType()+") HP: "+selectedPokemon.getHealth());
		
		System.out.println(" Defense: "+
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
