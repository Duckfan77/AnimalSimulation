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
}
