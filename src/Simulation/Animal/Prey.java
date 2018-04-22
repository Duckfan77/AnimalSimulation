package Simulation.Animal;

import Simulation.Cell;
import Simulation.Values;

public class Prey extends Animal{

	public Prey(Cell home) {
		super(Values.PREYH_INIT, home, Values.PREYSPAWN);
	}

	@Override
	public Actions act() {
		return null;
	}
}
