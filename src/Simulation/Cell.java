package Simulation;

import Simulation.Animal.Pred;
import Simulation.Animal.Prey;

import java.util.ArrayList;
import java.util.List;

public class Cell {
	private int plantCount;
	private List<Prey> prey;
	private List<Pred> pred;
	private Cell[][] area;

	public Cell(Cell[][] area, int PreyCount, int PredCount, int plantCount){
		this.area=area;
		this.plantCount=plantCount;

		prey=new ArrayList<>();
		for(int i=0;i<PreyCount;i++){
			prey.add(new Prey(this));
		}

		pred=new ArrayList<>();
		for(int i=0;i<PredCount;i++){
			pred.add(new Pred(this));
		}
	}

	public void tick(){

	}
}
