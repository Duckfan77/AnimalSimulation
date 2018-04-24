package Simulation.Animal;

import Simulation.Cell;
import Simulation.Values;

public class Pred extends Animal{
	public Pred(Cell home) {
		super(Values.PREDH_INIT, home, Values.PREDSPAWN);
	}

	@Override
	public Actions act(int extraCost) {
		return null;
	}

	@Override
	public boolean eat(int cost) {
		health-=cost;
		if(Math.random() * (loc.getPreyCount()/Values.CAP_PREY)>0.5){
			health+=Values.PREDGAIN_EAT;
			loc.removeAnimal(loc.getPrey());
			return true;
		}
		return false;
	}

	/**
	 * Creates a new {@code Pred}
	 *
	 * <p>
	 *     Creates a new {@code Pred} in the same {@code Cell} as {@code this}, and with half the health of
	 *     {@code this}. Fails if insufficient health is present.
	 * </p>
	 *
	 * @param cost the cost of the spawning
	 * @return {@code true} if succeeds<br/>{@code false} if fails.
	 */
	@Override
	public boolean spawn(int cost) {
		if(health<Values.PREDH_SPAWN){
			return false;
		}

		Pred newAnimal = new Pred(loc);
		newAnimal.health=health/2;
		loc.addAnimal(newAnimal);

		health-=cost;

		return true;
	}
}
