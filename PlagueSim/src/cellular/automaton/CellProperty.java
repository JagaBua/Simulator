package cellular.automaton;

/**
 * @author pfeiferb; biomed
 * 
 */
public class CellProperty {
	// one standard value that can be used
	private double value = 0d;
	// the value for the calculated time step
	private double valueAfterTimeStep = 0d;

	/**
	 * standard constructor
	 * values are initialized by 0d
	 */
	public CellProperty() {
		super();
		this.value = 0d;
		this.valueAfterTimeStep = 0d;
	}

	/**
	 * @param value the standard attribute is set according to the 
	 *              given specification
	 */
	public CellProperty(double value) {
		super();
		this.value = value;
		this.valueAfterTimeStep = 0d;
	}

	/**
	 * @return Returns the main value.
	 */
	public double getValue() {
		return this.value;
	}

	/**
	 * @param value
	 *            represents the value that has to be set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * sets the re
	 * 
	 * @param value
	 */
	public void setCalculatedValue(double value) {
		this.valueAfterTimeStep = value;
	}
	
	/**
	 * after the simulation of one time step is performed, the result is placed
	 * in the valueAfterTimeStep variable, which needs to be stored in the value
	 * fieds (that is queried during computation) for performing the next
	 * simulation step
	 */
	public void initializeValue() {
		value = valueAfterTimeStep;
		valueAfterTimeStep = 0d;
	}
}
