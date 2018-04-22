package Simulation;

public class Simulation {
	public static void main(String[] args) {
		Cell[][] area = new Cell[Values.DIM_HEIGHT][Values.DIM_WIDTH];
		for(int i=0;i<area.length;i++){
			for(int j=0;j<area[i].length;j++){
				area[i][j]=new Cell(area,Values.CELL_PREYINIT,Values.CELL_PREDINIT,Values.CELL_PLANTINIT);
			}
		}

		for(int time=0; time<Values.SIMTIME; time++){
			for(Cell[] row:area){
				for(Cell c:row){
					c.tick();
				}
			}
		}
	}
}
