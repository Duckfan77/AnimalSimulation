package Simulation;

import Simulation.Animal.Animal;
import Simulation.Animal.Pred;
import Simulation.Animal.Prey;

//import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cell {
	private int plantCount;
	private List<Animal> animals;
	private List<Prey> prey;
	private List<Pred> pred;
	private int preyCount=0;
	private int predCount=0;
	private Cell[][] area;
	private int row;
	private int col;

	public Cell(Cell[][] area, int row, int col, int preyCount, int predCount, int plantCount){
		this.area=area;
		this.plantCount=plantCount;

		animals=new CopyOnWriteArrayList<>();
		pred=new CopyOnWriteArrayList<>();
		prey=new CopyOnWriteArrayList<>();

		for(int i=0;i<preyCount;i++){
			Prey p = new Prey(this);
			animals.add(p);
			prey.add(p);
		}

		for(int i=0;i<predCount;i++){
			Pred p = new Pred(this);
			animals.add(p);
			pred.add(p);
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
		if(row!=Values.DIM_HEIGHT-1){
			c.push(area[row+1][col]);
		}
		if(col!=Values.DIM_WIDTH-1){
			c.push(area[row][col+1]);
		}

		Cell[] out = new Cell[c.size()];
		for(int i=0;i<=c.size();i++){
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
				prey.remove(a);
			}else{
				predCount--;
				pred.remove(a);
			}
			animals.remove(a);
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
				prey.add((Prey) a);
			}else{
				predCount++;
				pred.add((Pred) a);
			}
			return true;
		}else{
			return false;
		}
	}

	public int getPlantCount(){
		return plantCount;
	}

	public int getPreyCount(){
		return preyCount;
	}

	public int getPredCount(){
		return predCount;
	}

	public Prey getPrey(){
		if(preyCount>0)
			return prey.get(0);
		return null;
	}

	public Pred getPred(){
		if(predCount>0)
			return pred.get(0);
		return null;
	}

	public void decrementPlant(){
		if(plantCount>0)
			plantCount--;
	}

	/**
	 * Causes each {@code Animal} to act, and updates the amount of plants.
	 */
	public void tick(){
		System.out.println("\n"+this);
		for(Animal a:animals){
			int extraCost=0;
			if(a instanceof Prey && preyCount>Values.CAP_PREY){
				extraCost=preyCount-Values.CAP_PREY;
			}else if(predCount>Values.CAP_PRED){
				extraCost=predCount-Values.CAP_PRED;
			}

			a.act(extraCost);
		}

	}
}
