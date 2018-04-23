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

	/**
	 * Gets the {@code Cell}s that are adjacent to {@code this}
	 *
	 * <p>
	 *     Gets all {@code Cell}s adjacent, where adjacent means the row or column was changed by one, but not
	 *     both.
	 * </p>
	 *
	 * @return an array of {@code Cell}s all adjacent to {@code this}
	 */
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

	/**
	 * Removes {@code Animal a} from the {@code Cell}
	 *
	 * <p>
	 * Removes the {@code Animal} from the {@code Cell}, updating the count of {@code Pred} or {@code Prey} accordingly
	 * </p>
	 *
	 * @param a the {@code Animal} to remove
	 * @return {@code true} if the removal is successful<br/>{@code false} if the removal fails
	 */
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

	/**
	 * Adds {@code Animal a} to the {@code Cell}
	 *
	 * <p>
	 * Adds the {@code Animal} to the {@code Cell}, updating the count of {@code Pred} or {@code Prey} accordingly
	 * </p>
	 *
	 * @param a the {@code Animal} to add
	 * @return {@code true} if the addition is successful<br/>{@code false} if the addition fails
	 */
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

	/**
	 * Causes each {@code Animal} to act, and updates the amount of plants.
	 */
	public void tick(){

	}
}
