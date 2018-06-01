package DiseaseCA;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;

import javax.imageio.ImageIO;

import DiseaseCA.CellIndividual.DiseaseCycle;
import DiseaseCA.CellIndividual.StateType;




import cellular.automaton.AutomatonDimension;
import cellular.automaton.CellProperty;
import cellular.automaton.CellularAutomaton;
import cellular.automaton.Coordinate;

public class DiseaseSpreadCellularAutomaton extends CellularAutomaton {
	public static int timers = 0;

	public static int minValue = 255;
	public static int maxValue = 0;

	@SuppressWarnings("unchecked")
	public void initField(AutomatonDimension dim, int[] pixels) {
		 this.setDimension(dim);
		 long areaCells = 0;
		 long permanentAreaCells = 0;
	
		 for (int j = 0; j < dim.getYDim(); j++) {
			 for (int i = 0; i < dim.getXDim(); i++) {
				 int numberofindividuals = performPixelOperation(i, j, pixels[(int) (j * dim.getXDim() + i)]);
					    	
				 if (numberofindividuals != -1) permanentAreaCells++;
				 if (numberofindividuals > 1) areaCells++;
					    			    	
				 if (numberofindividuals == -1) numberofindividuals = 0;
					    	
				 @SuppressWarnings("unused")
				 CellProperty cp = new CellProperty(numberofindividuals);
				 DiseaseCell dc = new DiseaseCell(numberofindividuals);
					    	
				 if ((i == 1220) && (j == 320)) {
					 CellIndividual regInd = dc.getIndividuals().get(0);
					 regInd.setInfectedSinceDays(1);
					 regInd.setStateType(StateType.INFECTIVE);
					 regInd.setDiseaseCycle(DiseaseCycle.LATENT);
				 }
					    	
					    	
				 dc.addNeighbourList(dc.add2DMooreMatrixNeighbours(dim));
				 this.getCellList().add(dc);
			 }
		 }
	}

	private void setDimension(DiseaseCA.AutomatonDimension dim) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param x
	 *            coordinate of the pixel
	 * @param y
	 *            coordinate of the pixel
	 * @param pixel
	 *            the pixel itself <br>
	 *            the pixel is handled and some parameters are set for the
	 *            simulation
	 */
	public int performPixelOperation(int x, int y, int pixel) {
		@SuppressWarnings("unused")
		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;

		// calculate populousness from density map and return the value
		int singleColor = (int) (0.3 * red + 0.59 * green + 0.11 * blue);
		int individuals = 0;

		// density function map -- this one is optimized for the area of schwaz
		if (singleColor == 0) {
			individuals = -1;
		}

		if (singleColor == 255) {
			individuals = 0;
		}

		if ((singleColor > 0) && (singleColor < 60)) {
			individuals = 4;
		}

		if ((singleColor > 60) && (singleColor < 100)) {
			individuals = 5;
		}

		if ((singleColor > 100) && (singleColor < 160)) {
			individuals = 7;
		}

		if ((singleColor > 160) && (singleColor < 220)) {
			individuals = 7;
		}

		if ((singleColor > 220) && (singleColor < 255)) {
			individuals = 9;
		}

		return individuals;
	}

	/**
	 * method needs to be overwritten because each cell has a lot of objects
	 * (individuals) the operation is performed on the individuals - and these
	 * individual parameters need to be updated for performing the simulation in
	 * the next time step correctly
	 */
	public void initializeNextStep() {
	}

	/**
	 * simulate one time step (=one day) of the ca disease spread model and
	 * write the result into a) an image for analyzing the spread of the disease
	 * spread b) a file for performing further statistical analysis
	 */
	public void compute() {
		StepResult sRes = StepResult.getInstance();
		InfectionParameter simParam = InfectionParameter.getInstance();
		
		long timer;
		
		simParam.setHandleMedication(true);
		
		// draw statisticsl header
		System.out.println (sRes.getHeader());
		
		for (timer = this.getStartTime(); timer <= this.getStopTime(); timer++) {
			// System.out.print ("Simulating day " + timer + " ---- ");
			System.out.print(timer + "\t");

			// simulate one time step (one day)
			super.compute();
			
			sRes.setIndividuals(countIndividuals());
			
			this.getMinMax();	
		
			// draw the statistics
			System.out.println (sRes.toString());
			
			//this.writeSpread("individuals", false);
			//this.writeSpread("susceptible", false);
			this.writeSpread("infected", false);
			this.writeSpread("recovered", false);
			this.writeSpread("combined", true);
			
			adaptParameters(timer, 5, true, false, simParam, sRes, 1.2d, 1.5d, 1.1d, 1.05d); //15
			useQuarantineAfter (timer, 10, true, simParam); //25
			
			sRes.initValues();
		}
	}
	
	private long getStopTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	private long getStartTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long countIndividuals() {
		long individuals = 0;
		for (Iterator cellIterator = this.getCellList().iterator(); cellIterator.hasNext ();) {
			DiseaseCell cell = (DiseaseCell) cellIterator.next();
			
			for (Iterator individualIterator = cell.getIndividuals().iterator(); individualIterator.hasNext();) {
				CellIndividual indiv = (CellIndividual) individualIterator.next();
				
				if ((indiv.getStateType() != StateType.DIED) && (indiv.getStateType() != StateType.KILLEDBYDISEASE))
					individuals++;
			}
		}
		
		return individuals;
	}
	
	public void useQuarantineAfter (long timer, int time, boolean doIt, InfectionParameters simParam) {
		if ((timer >= time) && (doIt)) 
			simParam.setUseQuarantine(true);	
	}

	public void adaptParameters(long timer, long reduceAfter, boolean doIt, boolean stopSpontanous, InfectionParameters simParam, StepResult sRes, double reduceSpontanousFactor, double reduceMorbidityFactor, double reduceContactFactor, double reduceVectoredFactor) {			
		 // stop spontanous infection
		if ((stopSpontanous) && (sRes.getInfective() > 0))
				simParam.setSpontaneous_infection_rate(0.0d);
			
		if ((doIt) && ((timer % reduceAfter) == 0)) {
			if (sRes.getInfective() > 0) simParam.setSpontaneous_infection_rate(simParam.getSpontaneous_infection_rate() / reduceSpontanousFactor);
			 
			simParam.setVirus_morbidity_percent(simParam.getVirus_morbidity_percent() / reduceMorbidityFactor);
			simParam.setContact_infection_rate(simParam.getContact_infection_rate() / reduceContactFactor);
			simParam.setVectored_infection_rate(simParam.getVectored_infection_rate() / reduceVectoredFactor);
		}
	}

	public void getMinMax() {
		 int count = 0;
		 for (Iterator cellIterator = this.getCellList().iterator();cellIterator.hasNext ();) {
			 DiseaseCell cell = (DiseaseCell) cellIterator.next ();
			 count = cell.getIndividuals().size();
						
			 if (count < DiseaseSpreadCellularAutomaton.minValue) DiseaseSpreadCellularAutomaton.minValue = count;
			 if (count > DiseaseSpreadCellularAutomaton.maxValue) DiseaseSpreadCellularAutomaton.maxValue = count;
		 }
	}

	protected int getStretechedColor(int value) {
		 int stretchedColor;
		 try {
			 stretchedColor = 254 * (value - DiseaseSpreadCellularAutomaton.minValue) /
			 	(DiseaseSpreadCellularAutomaton.maxValue - DiseaseSpreadCellularAutomaton.minValue);
		 } catch (Exception e) {
			 stretchedColor = 0;
		 }
		 return stretchedColor;
	}

	public void writeSpread(String type, boolean countUp) {
		 @SuppressWarnings("unused")
		 CellProperty cp;
					
		 int individuals = 0;
		 int susceptible = 0;
		 int infected = 0;
		 int recovered = 0;
		 
		 int value = 0;
		 
					
		 long total = 0;
		
		 // create a graphic
		 BufferedImage bi = new BufferedImage((int) this.getDimension().getXDim(), (int) this.getDimension().getYDim(), BufferedImage.TYPE_INT_RGB);
		 Graphics2D g2d = bi.createGraphics();
					
		 individuals = 0;
		 int x = 0;
		 int y = 0;
		 for (Iterator cellIterator = this.getCellList().iterator(); cellIterator.hasNext ();) {
			 DiseaseCell cell = (DiseaseCell) cellIterator.next();
						
			 individuals = 0;
			 susceptible =0;
			 infected = 0;
			 recovered = 0;
			 value = 0;
			 for (Iterator individualIterator = cell.getIndividuals().iterator(); individualIterator.hasNext();) {
				 CellIndividual regIndiv = (CellIndividual) individualIterator.next();
							
				 if (type.compareTo("individuals") == 0) {
					 individuals++;
				 }
											 
				 if (regIndiv.getStateType() == StateType.SUSCEPTIBLE) 
					susceptible++;
				 							
				if (regIndiv.getStateType() == StateType.INFECTIVE)
						 infected++;
										
				if (regIndiv.getStateType() == StateType.RECOVERED)
						 recovered++;
							
			 }
			 
			 if (type.compareTo("individuals") == 0) {
				 value = individuals;
			 }
						
			 if (type.compareTo("susceptible") == 0) {
				value = susceptible;
			 }
						
			 if (type.compareTo("infected") == 0) {
				value = infected;
			 }
									
			 if (type.compareTo("recovered") == 0) {
				 value = recovered;
			 }
			 
				
			 if (type.compareTo("combined") != 0) {
				int stretchedColor;
				stretchedColor = this.getStretechedColor(value);
				if (value == 0) value = 255; else value = stretchedColor;
				g2d.setColor(new Color(value, value, value));
			 } else {
				int s1, s2, s3;
				s1 = this.getStretechedColor(susceptible);
				s2 = this.getStretechedColor(infected);
				s3 = this.getStretechedColor(recovered);
				
				if (s1 == 0) s1 = 255; 
				if (s2 == 0) s2 = 255; 
				if (s3 == 0) s3 = 255; 
				
				g2d.setColor(new Color(s1, s2, s3));	 
			 }	    
						
			x = (int) Coordinate.get2DXCoordinateFromIndex(cell.cellIndex, this.getDimension());
			y = (int) Coordinate.get2DYCoordinateFromIndex(cell.cellIndex, this.getDimension());
						
			g2d.drawLine(x, y, x, y);
						
			total += individuals;
		 }
		
		 // save the result as a png file
		 g2d.dispose();
		 String typ = "png";
		
		 DecimalFormat df = new DecimalFormat("0000");
		
					
		 InfectionParameter regParam = InfectionParameter.getInstance();
		 File datei = new File(regParam.getSaveResultToPath() + type + "_" + df.format(timers) + ".".concat(typ));
		 try {
			 ImageIO.write(bi, typ, datei);
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
					
		 if (countUp) timers++;
	}

	
	public static void main (String[] args) {
		@SuppressWarnings("unused")
		StepResult sRes = StepResult.getInstance();
		@SuppressWarnings("unused")
		InfectionParameters simParam = InfectionParameters.getInstance();
				
		if (args.length > 0) {
			simParam.setSaveResultToPath(args[0]);
		} else {
		  simParam.setSaveResultToPath("C:\\Users\\chris\\Desktop\\Uni\\Softwareprojekt Biomedizinische Informatik\\Simulation");
		}
		
		System.out.println ("Simulation of disease spread started. DSCA");
		
		long start = System.currentTimeMillis();
	
		DiseaseSpreadCellularAutomaton ca = new DiseaseSpreadCellularAutomaton ();
		ca.setStartTime(0);
		ca.setStopTime(365);
		
		RegionDensityMap view = new RegionDensityMap("/Volumes/Macintosh HD/Users/pfeiferb/Documents/2008/Kongresse/biomed/austria.jpg");
		
		Image theImage = view.getImage();
		int imageWidth = theImage.getWidth(null);
		int imageHeight = theImage.getHeight(null);
			
		AutomatonDimension aDim = new AutomatonDimension(imageWidth, imageHeight, 0);

		int[] pixels = new int[imageWidth * imageHeight];
		PixelGrabber pg = new PixelGrabber(theImage, 0, 0, imageWidth, imageHeight, pixels, 0, imageWidth);
		try {
			pg.grabPixels();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		ca.initField(aDim, pixels);
	
		ca.compute();
		
		long endtime = System.currentTimeMillis();
		System.out.println("Simulation of the disease spread ended in " + (endtime - start) + " ms.");	
		
	}

}
