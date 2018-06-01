package cellular.automaton;

/**
 * 
 * @author pfeiferb; biomed
 * 
 */
public class AutomatonDimension {
	/**
	 * Represents the dimension of the cellular automaton
	 */
	protected long xDim, yDim, zDim;

	/**
	 * @param xDim x dimension of the automaton
	 * @param yDim y dimension of the automaton
	 * @param zDim z dimension of the automaton
	 */
	public AutomatonDimension(long xDim, long yDim, long zDim) {
		super();

		this.xDim = xDim;
		this.yDim = yDim;
		this.zDim = zDim;
	}

	/**
	 * Standard constructor; the dimension is initialized with 0 in each dimension
	 */
	public AutomatonDimension() {
		super();
		
		this.xDim = this.yDim = this.zDim = 0;
	}

	/**
	 * @return Returns the xDim.
	 */
	public long getXDim() {
		return xDim;
	}

	/**
	 * @param dim
	 *             xDim to set.
	 */
	public void setXDim(long dim) {
		xDim = dim;
	}

	/**
	 * @return Returns  yDim.
	 */
	public long getYDim() {
		return yDim;
	}

	/**
	 * @param dim
	 *             yDim to set.
	 */
	public void setYDim(long dim) {
		yDim = dim;
	}

	/**
	 * @return Returns zDim.
	 */
	public long getZDim() {
		return zDim;
	}

	/**
	 * @param dim
	 *            zDim to set.
	 */
	public void setZDim(long dim) {
		zDim = dim;
	}
}
