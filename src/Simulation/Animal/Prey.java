package Simulation.Animal;

import Simulation.Cell;
import Simulation.Values;

public class Prey extends Animal{

	public Prey(Cell home) {
		super(Values.PREYH_INIT, home, Values.PREYSPAWN);
	}

	@Override
	public Actions act(int extraCost) {
		return null;
	}

	@Override
	public boolean eat() {
		return false;
	}

	/**
	 * Creates a new {@code Prey}
	 *
	 * <p>
	 *     Creates a new {@code Prey} in the same {@code Cell} as {@code this}, and with half the health of
	 *     {@code this}. Fails if insufficient health is present.
	 * </p>
	 *
	 * @return {@code true} if succeeds<br/>{@code false} if fails.
	 */
	@Override
	public boolean spawn(int cost) {
		if(health<Values.PREYH_SPAWN){
			return false;
		}

		Prey newAnimal = new Prey(loc);
		newAnimal.health=health/2;
		loc.addAnimal(newAnimal);

		health-=cost;

		return true;
	}
}
