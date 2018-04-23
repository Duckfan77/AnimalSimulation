package Simulation;

import Simulation.Animal.Animal;
import Simulation.Animal.Pred;
import Simulation.Animal.Prey;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
		Stack<Cell> c=new Stack();
		if(row!=0){
			c.push(area[row-1][col]);
		}
		if(col!=0){
			c.push(area[row][col-1]);
		}
		if(row!=Values.DIM_WIDTH){
			c.push(area[row+1][col]);
		}
		if(col!=Values.DIM_HEIGHT){
			c.push(area[row][col+1]);
		}

		Cell[] out = new Cell[c.size()];
		for(int i=0;i<c.size();i++){
			out[i]=c.pop();
		}

		return out;
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
