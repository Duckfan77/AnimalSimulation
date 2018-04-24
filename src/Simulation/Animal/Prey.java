package Simulation.Animal;

import Simulation.Cell;
import Simulation.Values;

public class Prey extends Animal{

	public Prey(Cell home) {
		super(Values.PREYH_INIT, home, Values.PREYSPAWN);
	}

	@Override
	public Actions act(int extraCost) {
		if(health>Values.PREYH_SPAWN){
			if(Math.random()>spawnRate){
				spawn(Values.PREYCOST_SPAWN+extraCost);
				return Actions.kSpawn;
			}
		}
		int num = (int)(Math.random()*2);
		if(health<Values.PREYCOST_MOVE*2){
			num=0;
		}
		Actions action=null;
		switch (num){
			case 0:
				eat(Values.PREYCOST_EAT+extraCost);
				action=Actions.kEat;
				break;
			case 1:
				move(Values.PREYCOST_MOVE+extraCost);
				action=Actions.kMove;
				break;
		}

		if(health<0){
			die();
			action=Actions.kDie;
		}

		return action;
	}

	@Override
	public boolean eat(int cost) {
		health-=cost;
		if(Math.random() * (loc.getPlantCount()/Values.CAP_PLANTS)>0.5){
			health+=Values.PREYGAIN_EAT;
			loc.decrementPlant();
			return true;
		}
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
		newAnimal.health=Values.PREYH_INIT;
		loc.addAnimal(newAnimal);

		health-=cost;

		return true;
	}
}
