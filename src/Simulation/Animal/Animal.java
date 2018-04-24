package Simulation.Animal;

import Simulation.Cell;

public abstract class Animal {
	int health;
	double spawnRate;
	Cell loc;

	public Animal(int health, Cell home, double spawnRate){
		this.health=health;
		loc=home;
		this.spawnRate=spawnRate;
	}

	enum Actions{
		kEat,
		kMove,
		kSpawn,
		kDie
	}

	public abstract Actions act(int extraCost);

	public abstract boolean eat();

	/**
	 * Creates a new {@code Animal}
	 *
	 * <p>
	 *     Creates a new {@code Animal} in the same {@code Cell} as {@code this}, and with half the health of
	 *     {@code this}. Fails if insufficient health is present.
	 * </p>
	 *
	 * @param cost the cost of spawning
	 * @return {@code true} if succeeds<br/>{@code false} if fails.
	 */
	public abstract boolean spawn(int cost);

	/**
	 * Moves this to a random neighboring cell
	 * @param cost the cost of the movement
	 * @return true if movement succeeds<br/>false if movement fails
	 */
	public boolean move(int cost){
		health-=cost;
		Cell[] options = loc.getNeighbors();

		if(options.length==0){
			return false;
		}

		int choice = (int)(Math.random()*options.length);
		loc.removeAnimal(this);
		options[choice].addAnimal(this);
		loc=options[choice];
		return true;
	}
}
