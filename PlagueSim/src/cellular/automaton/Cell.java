package cellular.automaton;

import java.util.ArrayList;

/**
 * @author pfeiferb; biomed
 * 
 */
public abstract class Cell {
	// list that holds all neighbour cell indices
	protected ArrayList<Long> neighbourCellIndexList;

	// holds the properties of the cell
	protected CellProperty cellProperty;

	// index of the cell
	public long cellIndex;

	// counts the cells that have been created via new command
	public static long cellCounter = 0;

	/**
	 * Constructor; creates the neighbourcellList for a cell which is important
	 * for the simulation
	 */
	public Cell() {
		super();
		neighbourCellIndexList = new ArrayList<Long>();
		cellProperty = null;

		cellIndex = cellCounter++;
	}

	/**
	 * Constructor; creates the neighbourcellList for a cell which is important
	 * for the simulation; furthermore, you can set the cell property, which is
	 * important for simulation using this constructor
	 */
	public Cell(CellProperty cellproperty) {
		super();
		neighbourCellIndexList = new ArrayList<Long>();

		cellIndex = cellCounter++;

		this.cellProperty = cellproperty;
	}

	/**
	 * This is the main function. This method performs the simulation of a
	 * single cell incorporating the values and parameters of the neighboured
	 * cells (if implemented) The new cell properties (for the current state)
	 * are stored in the property object This member function must be overrided
	 * in order to create any simulation!
	 * 
	 * @param list
	 *            of cells
	 * @param dimension
	 *            dimension of the simulation board
	 */
	//abstract public boolean performCellAction(AutomatonDimension dimension,
		//	ArrayList cells);
	
	abstract public boolean performCellAction(CellularAutomaton ca);
	
	
	/**
	 * standard toString Method; only returns the value of the cell converted into a 
	 * string object.
	 */
	public String toString () {
		return ((Double) cellProperty.getValue()).toString();
	}
	

	/**
	 * Adds a list of neighboursa to the cell
	 * 
	 * @param nList
	 *            ArrayList representing all indices which are in the defined
	 *            neighbourhood of the cell
	 */
	public void addNeighbourList(ArrayList<Long> nList) {
		this.neighbourCellIndexList = nList;
	}

	/**
	 * Add a new neighbour cell to the dynamic list The parameter is of Type
	 * LONG
	 * 
	 * @param index
	 */
	@SuppressWarnings("unchecked")
	public void addNeighbour(Long index) {
		neighbourCellIndexList.add(index);
	}

	/**
	 * remove a neighbour of Type LONG from dynamic list
	 * 
	 * @param index
	 */
	public void removeNeighbour(Long index) {
		neighbourCellIndexList.remove(index);
	}

	/**
	 * check if a object is in the dynamic list
	 * 
	 * @param index
	 * @return true if object is in list otherwise false
	 */
	public boolean isNeighbour(Long index) {
		return neighbourCellIndexList.contains(index);
	}

	/**
	 * Returns all neighbour of the Cell in a LONG Typed object array
	 * 
	 * @return array with all neighbour objects
	 */
	public ArrayList<Long> getNeighbours() {
		return neighbourCellIndexList;
	}

	/**
	 * Returns the count of all existing neighbours to a cell object
	 * 
	 * @return counts of neighbour cells
	 */
	public long getNeighbourCount() {
		return (long) neighbourCellIndexList.size();
	}

	/**
	 * returns the property object of the cell
	 * 
	 * @return cell property object
	 */
	public CellProperty getCellProperty() {
		return cellProperty;
	}

	/**
	 * set cell property using the cell property object
	 * 
	 * @param cellProperty
	 */
	public void setCellProperty(CellProperty cellProperty) {
		this.cellProperty = cellProperty;
	}

	/**
	 * this is a helper function which can be used to define the neighbours of a
	 * 2d matrix. before using the index it has to be checked if the index lies
	 * between the dimension or is outside the board
	 * 
	 * @param aDim
	 */
	@SuppressWarnings("unchecked")
	public ArrayList add2DMooreMatrixNeighbours(AutomatonDimension aDim) {
		ArrayList neighbourCellIndexList;
		neighbourCellIndexList = new ArrayList();

		// 2 neighbours are 1d coord +- 1 in x direction
		neighbourCellIndexList.add(cellIndex + 1);
		neighbourCellIndexList.add(cellIndex - 1);
		// 2 neighbours are 1d coord +- xDim y direction
		neighbourCellIndexList.add(cellIndex + aDim.xDim);
		neighbourCellIndexList.add(cellIndex - aDim.xDim);
		// 4 neighbours are 1d coord +- xDim +- 1 x&y direction
		neighbourCellIndexList.add(cellIndex + 1 + aDim.xDim);
		neighbourCellIndexList.add(cellIndex + 1 - aDim.xDim);
		neighbourCellIndexList.add(cellIndex - 1 + aDim.xDim);
		neighbourCellIndexList.add(cellIndex - 1 - aDim.xDim);

		return neighbourCellIndexList;
	}
	
	
	/**
	 * this is a helper function which can be used to define the neighbours of a
	 * 3d matrix. before using the index it has to be checked if the index lies
	 * between the dimension or is outside the board
	 * 
	 * @param aDim
	 */
	@SuppressWarnings("unchecked")
	public ArrayList add3DMooreMatrixNeighbours(AutomatonDimension aDim) {
		ArrayList neighbourCellIndexList;
		neighbourCellIndexList = new ArrayList();

		// 2 neighbours are 1d coord +- 1 in x direction
		neighbourCellIndexList.add(cellIndex + 1);
		neighbourCellIndexList.add(cellIndex - 1);
		// 2 neighbours are 1d coord +- xDim y direction
		neighbourCellIndexList.add(cellIndex + aDim.xDim);
		neighbourCellIndexList.add(cellIndex - aDim.xDim);
		// 4 neighbours are 1d coord +- xDim +- 1 x&y direction
		neighbourCellIndexList.add(cellIndex + 1 + aDim.xDim);
		neighbourCellIndexList.add(cellIndex + 1 - aDim.xDim);
		neighbourCellIndexList.add(cellIndex - 1 + aDim.xDim);
		neighbourCellIndexList.add(cellIndex - 1 - aDim.xDim);
		
		// set 3d dimension neighbours

		return neighbourCellIndexList;
	}
	
	
	

	/**
	 * this is a helper function which can be used to define the neighbours of a
	 * 2d matrix. before using the index it has to be checked if the index lies
	 * between the dimension or is outside the board
	 * 
	 * @param aDim
	 */
	@SuppressWarnings("unchecked")
	public ArrayList add2DNeumannMatrixNeighbours(AutomatonDimension aDim) {
		ArrayList neighbourCellIndexList;
		neighbourCellIndexList = new ArrayList();

		// 2 neighbours are 1d coord +- 1 in x direction
		neighbourCellIndexList.add(cellIndex + 1);
		neighbourCellIndexList.add(cellIndex - 1);
		// 2 neighbours are 1d coord +- xDim y direction
		neighbourCellIndexList.add(cellIndex + aDim.xDim);
		neighbourCellIndexList.add(cellIndex - aDim.xDim);
		return neighbourCellIndexList;
	}
	
	
	/**
	 * this is a helper function which can be used to define the neighbours of a
	 * 3d matrix. before using the index it has to be checked if the index lies
	 * between the dimension or is outside the board
	 * 
	 * @param aDim
	 */
	@SuppressWarnings("unchecked")
	public ArrayList add3DNeumannMatrixNeighbours(AutomatonDimension aDim) {
		ArrayList neighbourCellIndexList;
		neighbourCellIndexList = new ArrayList();

		// 2 neighbours are 1d coord +- 1 in x direction
		neighbourCellIndexList.add(cellIndex + 1);
		neighbourCellIndexList.add(cellIndex - 1);
		// 2 neighbours are 1d coord +- xDim y direction
		neighbourCellIndexList.add(cellIndex + aDim.xDim);
		neighbourCellIndexList.add(cellIndex - aDim.xDim);
		
		// set 3d dimension neighbours
		
		
		return neighbourCellIndexList;
	}
	
}
