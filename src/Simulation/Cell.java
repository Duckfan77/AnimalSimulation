package Simulation;

import Simulation.Animal.Animal;
import Simulation.Animal.Pred;
import Simulation.Animal.Prey;

import java.util.ArrayList;
import java.util.List;

public class Cell {
	private int plantCount;
	private List<Animal> animals;
	private int preyCount=0;
	private int predCount=0;
	private Cell[][] area;
	private int row;
	private int col;

	public Cell(Cell[][] area, int row, int col, int preyCount, int predCount, int plantCount){
		this.area=area;
		this.plantCount=plantCount;

		animals=new ArrayList<>();

		for(int i=0;i<preyCount;i++){
			animals.add(new Prey(this));
		}

		for(int i=0;i<predCount;i++){
			animals.add(new Pred(this));
		}

		this.row=row;
		this.col=col;
		this.predCount=predCount;
		this.preyCount=preyCount;
	}

	public Cell[] getNeighbors(){
		return null;
	}

	public boolean removeAnimal(Animal a){
		boolean out = animals.remove(a);
		if(out){
			if(a instanceof Prey){
				preyCount--;
			}else{
				predCount--;
			}
			return true;
		}else{
			return false;
		}
	}

	public boolean addAnimal(Animal a){
		boolean out = animals.add(a);
		if(out){
			if(a instanceof Prey){
				preyCount++;
			}else{
				predCount++;
			}
			return true;
		}else{
			return false;
		}
	}

	public void tick(){

	}
}
