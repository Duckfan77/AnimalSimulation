package Simulation;

import java.io.FileWriter;
import java.io.IOException;

public class Simulation {
	public static void main(String[] args) {
		Cell[][] area = new Cell[Values.DIM_HEIGHT][Values.DIM_WIDTH];
		try {
			FileWriter out = new FileWriter(Values.outfile, true);

			for (int i = 0; i < area.length; i++) {
				for (int j = 0; j < area[i].length; j++) {
					area[i][j] = new Cell(area, i, j, Values.CELL_PREYINIT, Values.CELL_PREDINIT, Values.CELL_PLANTINIT);
				}
			}

			for (int time = 0; time < Values.SIMTIME; time++) {
				int plantCount=0;
				int preyCount=0;
				int predCount=0;
				for (Cell[] row : area) {
					for (Cell c : row) {
						c.tick();
						plantCount+=c.getPlantCount();
						preyCount+=c.getPreyCount();
						predCount+=c.getPredCount();
					}
				}
				out.write(""+time+", "+plantCount+", "+preyCount+", "+predCount+"\n");
				if(preyCount==0 && predCount==0){
					break;
				}
			}

			out.close();
		} catch (IOException e) {
			System.out.println("ERROR");
			System.out.println(e);
		}
	}
}
