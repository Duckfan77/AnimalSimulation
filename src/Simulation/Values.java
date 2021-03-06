package Simulation;

public class Values {
	public static final int CAP_PREY=10;
	public static final int CAP_PRED=5;
	public static final int CAP_PLANTS=30;

	public static final int DIM_WIDTH=5;
	public static final int DIM_HEIGHT=5;

	public static final int PREYH_INIT=10;
	public static final int PREDH_INIT=12;

	public static final double PREYSPAWN=0.5;
	public static final double PREDSPAWN=0.5;

	public static final int PREYH_SPAWN=6;
	public static final int PREDH_SPAWN=10;

	public static final int PREYCOST_SPAWN=4;
	public static final int PREYCOST_MOVE=2;
	public static final int PREYCOST_EAT=1;
	public static final int PREYGAIN_EAT=7;

	public static final int PREDCOST_SPAWN=6;
	public static final int PREDCOST_MOVE=1;
	public static final int PREDCOST_EAT=1;
	public static final int PREDGAIN_EAT=13;

	public static final int CELL_PREDINIT=2;
	public static final int CELL_PREYINIT=5;
	public static final int CELL_PLANTINIT=10;

	public static final int SIMTIME=1000;
	public static final String outfile="output.csv";

}
