package cellular.automaton;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implementation of the superclass for a cellular automaton. The superclass is
 * abstract, therefore, a special implementation like the calculation of the
 * excitation in the human heart have to be implemented in order to use the ca
 * for simulation.
 * 
 * @author pfeiferb; biomed
 * 
 */
public abstract class CellularAutomaton {

	/**
	 * start time of the simulation
	 */
	private long startTime;

	/**
	 * stop time of the simulation
	 */
	private long stopTime;

	/**
	 * single cells of the CA integrated into a dynamic growable array can and
	 * should be used for storing the single cells representing the cellular
	 * automaton
	 */
	private ArrayList<Cell> cellList;
	
	
	/**
	 * represents the simulation board 1d, 2d or 3d; + time
	 */
	private AutomatonDimension dimension;
	

	/**
	 * Standard Constructor for the CA. Memory for the Cell-List is allocated
	 * and the start-time is set to zero, the stop-time is set to one
	 */
	public CellularAutomaton() {
		super();
		
		cellList = new ArrayList<Cell>();
		dimension = new AutomatonDimension();
		startTime = 0;
		stopTime = 1;
	}

	/**
	 * Constructor with the possibility to define the start and stoptime.
	 * 
	 * @param startTime
	 * @param stopTime
	 */
	public CellularAutomaton(long startTime, long stopTime) {
		super();

		cellList = new ArrayList<Cell>();
		this.startTime = startTime;
		this.stopTime = stopTime;
	}

	/**
	 * Constructor with dimension initialization
	 * 
	 * @param dim
	 *            Dimension of the CA simulation board
	 */
	public CellularAutomaton(AutomatonDimension dim) {
		super();

		cellList = new ArrayList<Cell>();
		this.dimension = dim;
		this.startTime = 0;
		this.stopTime = 1;
	}

	/**
	 * Constructor with dimension and start stop time of the CA
	 * 
	 * @param startTime
	 * @param stopTime
	 * @param dim
	 *            Dimension of the CA simulation board
	 */
	public CellularAutomaton(long startTime, long stopTime,
			AutomatonDimension dim) {
		super();

		cellList = new ArrayList<Cell>();
		this.dimension = dim;
		this.startTime = startTime;
		this.stopTime = stopTime;
	}

	/**
	 * member function, which implements the simulation of the CA Usually the
	 * method iterates through the cells and calls the performCellAction method
	 * iteratioon is performed for one timestep only!
	 */
	public void compute() { 
		for (Iterator it = cellList.iterator(); it.hasNext();) {
			((Cell) it.next()).performCellAction(this); 
		}
	}

	/**
	 * After performing a time step the t+1 values have to be state t for the
	 * next step Therefore, the results need to be restored in the "simulation"
	 * variables, which is performed using this method;
	 * 
	 */
	public void initializeNextStep() {
		for (Iterator it = cellList.iterator(); it.hasNext();) {
			((Cell) it.next()).getCellProperty().initializeValue();
		}
	}
	
	
	/**
	 * returns the actual state as a string
	 */
	public String toString () {
		StringBuffer sb = new StringBuffer ();
		long ctr = 0;
		
		for (Iterator it = cellList.iterator(); it.hasNext();) {	
			sb.append(((Cell) it.next()).toString() + ' ');
			ctr++;
			if (ctr >= dimension.xDim) {
				sb.append("\n");
				ctr = 0;
			}
		}	
		return sb.toString();
	}

	/**
	 * @return Returns the dimension.
	 */
	public AutomatonDimension getDimension() {
		return dimension;
	}
	
	/**
	 * @param index of the cell
	 * @return the cell with the index passed by the parameter
	 */
	public Cell getCell (Long index) {
		return  (Cell) cellList.get(index.intValue());		
	}

	/**
	 * @param dimension
	 *            The dimension to set.
	 */
	public void setDimension(AutomatonDimension dimension) {
		this.dimension = dimension;
	}

	/**
	 * @return Returns the startTime.
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            The startTime to set.
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return Returns the stopTime.
	 */
	public long getStopTime() {
		return stopTime;
	}

	/**
	 * @param stopTime
	 *            The stopTime to set.
	 */
	public void setStopTime(long stopTime) {
		this.stopTime = stopTime;
	}

	/**
	 * @return Returns the cellList.
	 */
	public ArrayList<Cell> getCellList() {
		return cellList;
	}

	/**
	 * @param cellList
	 *            The cellList to set.
	 */
	public void setCellList(ArrayList<Cell> cellList) {
		this.cellList = cellList;
	}
}
