package DiseaseCA;

import java.util.Random;

public class InfectionParameter {
	
	private static InfectionParameter instance;

	// declaration of the needed simulation parameters
	protected int latentPeriodDays = 3; // 3
	protected int infectousPeriodDays = 10; // 10
	protected int recoveredRemovedAfterDays = 15; // 15
	protected int incubationPeriodDays = 3; // 3
	protected int symptomaticPeriodDays = 4; // 4

	// simulation parameters with standard values initialized
	protected double birthrate = 0.002d;
	protected double deathrate = 0.001d;

	protected double virus_morbidity_percent = 0.025d;  // 0.63

	protected double spontaneous_infection_rate = 0; //0.000001d; // 0.000001d
	
	protected double vectored_infection_rate = 0.02; //0.10d; // 0.4
	protected double contact_infection_rate = 0.06; //0.12d; // 0.6

	protected double movement_probability = 0.4d;

	protected double immigrantrate = 0.0000001;

	protected boolean useQuarantine = false;

	protected boolean handleMedication = false;
	protected double treatment = 3.5d;
	

	// common parameters for virus simulation
	protected int suspectibe_again_after_recover = 100;
	protected int birthimmunityindays = 20;

	
	protected  double areaInSquarekm = 10628.00;
	protected  double areaOfSettlement = 1600.00;
	protected  long   areaInhabitants = 650074;

	protected long maxCellCapacity = 500;

	// path where the results are stored into
	protected String saveResultToPath = "C:\\Users\\chris\\Desktop\\Uni\\Softwareprojekt Biomedizinische Informatik";

	// for generating random numbers
	protected static Random randomGenerator;

	// generate the Instance or return the existing Instance
	// lazy instantiation is used!
	public static synchronized InfectionParameter getInstance() {
		if (instance == null) {
			randomGenerator = new Random();
			instance = new InfectionParameter();
		}
		return instance;
	}

	// constructor needs to be protected due to singleton specification
	protected InfectionParameter() {
	}

	public long getAreaInhabitants() {
		return areaInhabitants;
	}

	public double getAreaInSquarekm() {
		return areaInSquarekm;
	}

	public double getAreaOfSettlement() {
		return areaOfSettlement;
	}

	public double getBirthrate() {
		return birthrate;
	}

	public void setBirthrate(double birthrate) {
		this.birthrate = birthrate;
	}

	public double getContact_infection_rate() {
		return contact_infection_rate;
	}

	public void setContact_infection_rate(double contact_infection_rate) {
		this.contact_infection_rate = contact_infection_rate;
	}

	public double getDeathrate() {
		return deathrate;
	}

	public void setDeathrate(double deathrate) {
		this.deathrate = deathrate;
	}

	public boolean isHandleMedication() {
		return handleMedication;
	}

	public void setHandleMedication(boolean handleMedication) {
		this.handleMedication = handleMedication;
	}

	public double getImmigrantrate() {
		return immigrantrate;
	}

	public void setImmigrantrate(double immigrantrate) {
		this.immigrantrate = immigrantrate;
	}

	public int getIncubationPeriodDays() {
		return incubationPeriodDays;
	}

	public void setIncubationPeriodDays(int incubationPeriodDays) {
		this.incubationPeriodDays = incubationPeriodDays;
	}

	public int getInfectousPeriodDays() {
		return infectousPeriodDays;
	}

	public void setInfectousPeriodDays(int infectousPeriodDays) {
		this.infectousPeriodDays = infectousPeriodDays;
	}

	public int getLatentPeriodDays() {
		return latentPeriodDays;
	}

	public void setLatentPeriodDays(int latentPeriodDays) {
		this.latentPeriodDays = latentPeriodDays;
	}

	public long getMaxCellCapacity() {
		return maxCellCapacity;
	}

	public void setMaxCellCapacity(long maxCellCapacity) {
		this.maxCellCapacity = maxCellCapacity;
	}

	public double getTreatment() {
		return treatment;
	}

	public void setTreatment(double treatment) {
		this.treatment = treatment;
	}

	public double getMovement_probability() {
		return movement_probability;
	}

	public void setMovement_probability(double movement_probability) {
		this.movement_probability = movement_probability;
	}

	public int getRecoveredRemovedAfterDays() {
		return recoveredRemovedAfterDays;
	}

	public void setRecoveredRemovedAfterDays(int recoveredRemovedAfterDays) {
		this.recoveredRemovedAfterDays = recoveredRemovedAfterDays;
	}

	public String getSaveResultToPath() {
		return saveResultToPath;
	}

	public void setSaveResultToPath(String saveResultToPath) {
		this.saveResultToPath = saveResultToPath;
	}

	public double getSpontaneous_infection_rate() {
		return spontaneous_infection_rate;
	}

	public void setSpontaneous_infection_rate(double spontaneous_infection_rate) {
		this.spontaneous_infection_rate = spontaneous_infection_rate;
	}

	public int getSuspectibe_again_after_recover() {
		return suspectibe_again_after_recover;
	}

	public void setSuspectibe_again_after_recover(int suspectibe_again_after_recover) {
		this.suspectibe_again_after_recover = suspectibe_again_after_recover;
	}

	public int getSymptomaticPeriodDays() {
		return symptomaticPeriodDays;
	}

	public void setSymptomaticPeriodDays(int symptomaticPeriodDays) {
		this.symptomaticPeriodDays = symptomaticPeriodDays;
	}

	public boolean isUseQuarantine() {
		return useQuarantine;
	}

	public void setUseQuarantine(boolean useQuarantine) {
		this.useQuarantine = useQuarantine;
	}

	public double getVectored_infection_rate() {
		return vectored_infection_rate;
	}

	public void setVectored_infection_rate(double vectored_infection_rate) {
		this.vectored_infection_rate = vectored_infection_rate;
	}

	public double getVirus_morbidity_percent() {
		return virus_morbidity_percent;
	}

	public void setVirus_morbidity_percent(double virus_morbidity_percent) {
		this.virus_morbidity_percent = virus_morbidity_percent;
	}

	public int getBirthimmunityindays() {
		return birthimmunityindays;
	}

	public void setBirthimmunityindays(int birthimmunityindays) {
		this.birthimmunityindays = birthimmunityindays;
	}
	
	
}
