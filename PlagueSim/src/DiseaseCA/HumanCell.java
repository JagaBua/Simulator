package DiseaseCA;


public class HumanCell {
	
	public enum stateType{IMMUNE,SUSCEPTIBLE, INFECTIVE, RECOVERED, KILLEDBYDISEASE, DIED}
	
	public enum ageType{KID, TEEN, ADULT, OLD}
	
	public enum treatmentType{TREATMENT, NOTREATMENT}
	
	public enum quarantineType{NORMAL, QUARANTINE} 
	
	public enum diseaseCycle{HEALTHY, LATENT, INFECTIOUS, REMOVED, NIL}
	
	private stateType StateType;
	private ageType AgeType;
	private quarantineType QuarantineType;
	private treatmentType TreatmentType;
	private diseaseCycle DiseaseCycle;
	
	private int infectedSinceDays;
	
	private int susceptibleInDays;
	
	private double mortalityRateFactor = 1d;
	
	public double getMortalityRateFactor() {
		return mortalityRateFactor;
	}
	
	public boolean isTheCase(double percentageValue) {
		return (InfectionParameter.randomGenerator.nextDouble() < percentageValue);
	}
	
	public HumanCell () {
		this.setStateType(StateType.SUSCEPTIBLE);	
		this.setTreatmentType(TreatmentType.NOTREATMENT);
		this.setDiseaseCycle(DiseaseCycle.HEALTHY);
		this.setSusceptibleInDays(0);
		this.setInfectedSinceDays(0);
		
		int ageClass = InfectionParameter.randomGenerator.nextInt(4);
		switch (ageClass) {
		  case 0 : this.setAgeType(AgeType.KID); break;
		  case 1 : this.setAgeType(AgeType.TEEN); break;
		  case 2 : this.setAgeType(AgeType.ADULT); break;
		  case 3 : this.setAgeType(AgeType.OLD); break;
		  default : this.setAgeType(AgeType.ADULT); break;
		}   
	}
	//Konstruktor für HUMANCELL
	
	protected double computeMortalityRate (double morbidityValue, InfectionParameter simParam) {
		double value = 1.0d;
		
		if (simParam.isHandleMedication()) {
			value = 0.5d ;
		}
		
		return morbidityValue / value;
	}
	protected void  updateStateType (InfectionParameter simParam) {
		switch (StateType)
		{
		  case IMMUNE:
			  this.setSusceptibleInDays(this.getSusceptibleInDays()-1);
			  if (this.getSusceptibleInDays() < 1) {
				  this.setSusceptibleInDays(0);
				  this.setInfectedSinceDays(0);
				  //Immunity of the newborn
				  this.setStateType(StateType.SUSCEPTIBLE);
				  this.setDiseaseCycle(DiseaseCycle.HEALTHY);
			  }	
			break;
	       
		  case SUSCEPTIBLE:
			  this.setInfectedSinceDays(0);
			  this.setSusceptibleInDays(0);
			  this.setDiseaseCycle(DiseaseCycle.HEALTHY);
			  break;
          
		  case INFECTIVE:
			  this.setInfectedSinceDays(this.getInfectedSinceDays()+1);
			  
			  if (this.getInfectedSinceDays() >= simParam.getRecoveredRemovedAfterDays()) {
				  double mortalityRate = computeMortalityRate (simParam.getVirus_morbidity_percent(), simParam);
				  
				  if (this.isTheCase(mortalityRate)) {
					  this.setStateType(StateType.KILLEDBYDISEASE);
					  this.setDiseaseCycle(DiseaseCycle.REMOVED);
				  } else {
					  this.setStateType(StateType.RECOVERED);
					  this.setDiseaseCycle(DiseaseCycle.HEALTHY);
					  this.setInfectedSinceDays(0);
					  this.setSusceptibleInDays(simParam.getSuspectibe_again_after_recover());
				  } 
			  }
			  break;
			  
		  case RECOVERED:
			  this.setSusceptibleInDays(this.getSusceptibleInDays()-1);
			  if (this.getSusceptibleInDays() < 1) {
				  this.setStateType(StateType.SUSCEPTIBLE);
				  this.setDiseaseCycle(DiseaseCycle.HEALTHY);
			  } 
			  break;
			  
		  case KILLEDBYDISEASE:
			  this.setDiseaseCycle(DiseaseCycle.REMOVED);
			  break;	  
		  case DIED:
			  this.setDiseaseCycle(DiseaseCycle.NIL);
			  break;
		}
	}
	
	protected void updateDiseaseCycle (InfectionParameter simParam) {
		switch (DiseaseCycle)
		{
		  case HEALTHY:
			 break;
		  case LATENT:
			  if (this.getInfectedSinceDays() > simParam.getLatentPeriodDays())
				  this.setDiseaseCycle(DiseaseCycle.INFECTIOUS);
			  break;
		  case INFECTIOUS:
			  if (this.getStateType() == StateType.KILLEDBYDISEASE)
				  this.setDiseaseCycle(DiseaseCycle.REMOVED);
			  
			  if (this.getStateType() == StateType.RECOVERED)
				  this.setDiseaseCycle(DiseaseCycle.HEALTHY);
			  break;
		  case REMOVED:
			  this.setDiseaseCycle(DiseaseCycle.NIL);
			  break;
		}
	}
	
	public void updateIndividual () {
		InfectionParameter simParam = InfectionParameter.getInstance();
		StepResult sRes = StepResult.getInstance();
		
		updateStateType (simParam);
	    updateDiseaseCycle (simParam);
		
		if (simParam.isUseQuarantine()) checkQuarantine ();
		
		
		adaptStatistics (sRes);
	}
	public void adaptStatistics (StepResult sRes) {
		// create statistics step for MSIR model
		switch (StateType)
		{
		  case IMMUNE:
			  sRes.setPassiveimmunityfrombirth(sRes.getPassiveimmunityfrombirth()+1);
			break;
		  case SUSCEPTIBLE:
			  sRes.setSusceptible(sRes.getSusceptible()+1);
			  break;
		  case INFECTIVE:
			  sRes.setInfective(sRes.getInfective()+1);
			  break;
		  case RECOVERED:
			  sRes.setRecovered(sRes.getRecovered()+1);
			  break;
		  case KILLEDBYDISEASE:
			  sRes.setKilledbydisease(sRes.getKilledbydisease()+1);
			  break;
		  case DIED:
			  sRes.setDied(sRes.getDied()+1);
		}
		// create statistics step for Disease Cycle
		switch (DiseaseCycle)
		{
		  case HEALTHY:
			  sRes.setHealty(sRes.getHealty()+1);
			  break;
		  case LATENT:
			  sRes.setLatent(sRes.getLatent()+1);
			  break;
		  case INFECTIOUS:
			  sRes.setInfectious(sRes.getInfectious()+1);
			  break;
		  case REMOVED:
			  sRes.setRemoved(sRes.getRemoved()+1);
			  break;
		  case NIL:
			  sRes.setRemoved(sRes.getRemoved()+1);
			  break;
			  
		}
		
		
	}	  
	public void checkQuarantine () {
		InfectionParameter simParam = InfectionParameter.getInstance();
		
		switch (StateType)
		{
		case INFECTIVE:
		  // only if the infection is visible the patient can be set to the state QUARANTINE
		  if (this.getInfectedSinceDays() > simParam.getIncubationPeriodDays()) {
			  this.QuarantineType = QuarantineType.QUARANTINE;
		  } else this.QuarantineType = QuarantineType.QUARANTINE;
		  break;
		 default: this.QuarantineType = QuarantineType.QUARANTINE;
		}
	}
	public diseaseCycle getDiseaseCycle() {
		return DiseaseCycle;
	}

	public void setDiseaseCycle(diseaseCycle DiseaseCycle) {
		this.DiseaseCycle = DiseaseCycle;
	}

	public quarantineType getQuarantineType() {
		return QuarantineType;
	}

	public void setQuarantineType(quarantineType QuarantineType) {
		this.QuarantineType = QuarantineType;
	}

	public stateType getStateType() {
		return StateType;
	}

	public void setStateType(stateType StateType) {
		this.StateType = StateType;
	}

	public treatmentType getTreatmentType() {
		return TreatmentType;
	}

	public void setTreatmentType(treatmentType TreatmentType) {
		this.TreatmentType = TreatmentType;
	}

	public int getInfectedSinceDays() {
		return infectedSinceDays;
	}

	public void setInfectedSinceDays(int infectedSinceDays) {
		this.infectedSinceDays = infectedSinceDays;
	}

	public int getSusceptibleInDays() {
		return susceptibleInDays;
	}

	public void setSusceptibleInDays(int susceptibleInDays) {
		this.susceptibleInDays = susceptibleInDays;
	}

	public ageType getAgeType() {
		return AgeType;
	}

	public void setAgeType(ageType AgeType) {
		this.AgeType = AgeType;
	}
}
