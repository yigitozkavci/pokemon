package com.yigitozkavci.opensourcepokemon.pokemons.attacks;

import com.yigitozkavci.opensourcepokemon.pokemons.Pokemon;
/**
 * Interface to define special effects of different attacks. For example,
 * if an {@link Attack} blinds the opposing {@link Pokemon}, this should be
 * implemented here. 
 * @author triforce930
 *
 */
public interface SpecialEffect {
	/**
	 * <p>Special method is implemented if {@link Pokemon} has a special effect on use of
	 * this attack. (Drain attack, absorbs health from opposing {@link Pokemon} according to
	 * the total damage dealt.)</p>
	 * @return if the special method is used successfully.
	 */
	public boolean special();
}
