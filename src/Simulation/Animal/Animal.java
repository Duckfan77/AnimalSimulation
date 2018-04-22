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


}
