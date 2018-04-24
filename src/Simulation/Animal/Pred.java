package Simulation.Animal;

import Simulation.Cell;
import Simulation.Values;

public class Pred extends Animal{
	public Pred(Cell home) {
		super(Values.PREDH_INIT, home, Values.PREDSPAWN);
	}

	@Override
	public Actions act(int extraCost) {
		if(health>Values.PREDH_SPAWN){
			if(Math.random()>spawnRate){
				spawn(Values.PREDCOST_SPAWN+extraCost);
				return Actions.kSpawn;
			}
		}


		int num = (int)(Math.random()*2);
		if(health<Values.PREDCOST_MOVE*2){
			num=0;
		}
		Actions action=null;
		switch (num){
			case 0:
				eat(Values.PREDCOST_EAT+extraCost);
				action=Actions.kEat;
				break;
			case 1:
				move(Values.PREDCOST_MOVE+extraCost);
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
		newAnimal.health=Values.PREDH_INIT;
		loc.addAnimal(newAnimal);

		health-=cost;

		return true;
	}
}
