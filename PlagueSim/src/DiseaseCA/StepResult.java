package DiseaseCA;

import DiseaseCA.StepResult;

public class StepResult {
	private static StepResult instance;

	protected long passiveimmunityfrombirth;
	protected long susceptible;
	protected long infective;
	protected long recovered;
	protected long killedbydisease;

	protected long spontanous;
	protected long vectored;
	protected long contact;

	protected long individuals;

	protected long died;
	protected long born;

	protected long moved;
	protected long immigrant;
	
	// disease cycle statistics
	protected long healty;
	protected long latent;
	protected long infectious;
	protected long removed;
	
	protected long lastDied;
	protected long lastKilledDisease;
	

	public static synchronized StepResult getInstance() {
		if (instance == null) {
			instance = new StepResult();
		}
		return instance;
	}

	protected StepResult() {
	}

	public void initValues() {
		this.passiveimmunityfrombirth = 0;
		this.susceptible = 0;
		this.infective = 0;
		this.recovered = 0;
		this.killedbydisease = 0;

		
		this.died = 0;
		this.born = 0;
		
		this.spontanous = 0;
		
		this.vectored = 0;
		this.contact = 0;
		
		this.immigrant = 0;
		
		this.moved = 0;
		
		this.individuals = 0;
				
		this.healty = 0;
		this.latent = 0;
		this.infectious = 0;
		this.removed = 0;
	}
	
	public String getHeader () {
		return "Step \t Ind \t M \t S \t I \t R \t KD \t Died \t Born \t Spo \t Imm \t Hea \t Lat \t Inf \t Rem";
	}
	
	public String toString () {
		long diedStep = died - lastDied;
		lastDied = died;
		
		long killedDisease =  killedbydisease - lastKilledDisease;
		lastKilledDisease = killedbydisease;
		
		return individuals + "\t" + passiveimmunityfrombirth + "\t" + susceptible + "\t" + infective + "\t" + recovered + "\t" + killedDisease + "\t" + diedStep + "\t" + born + "\t" + spontanous + "\t" + "\t" + immigrant  + "\t" + healty + "\t" + latent + "\t" + infectious + "\t" + removed; 
	}

	public long getBorn() {
		return born;
	}

	public void setBorn(long born) {
		this.born = born;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public long getDied() {
		return died;
	}

	public void setDied(long died) {
		this.died = died;
	}

	public long getImmigrant() {
		return immigrant;
	}

	public void setImmigrant(long immigrant) {
		this.immigrant = immigrant;
	}

	public long getIndividuals() {
		return individuals;
	}

	public void setIndividuals(long individuals) {
		this.individuals = individuals;
	}

	public long getInfective() {
		return infective;
	}

	public void setInfective(long infective) {
		this.infective = infective;
	}

	public long getKilledbydisease() {
		return killedbydisease;
	}

	public void setKilledbydisease(long killedbydisease) {
		this.killedbydisease = killedbydisease;
	}

	public long getMoved() {
		return moved;
	}

	public void setMoved(long moved) {
		this.moved = moved;
	}

	public long getPassiveimmunityfrombirth() {
		return passiveimmunityfrombirth;
	}

	public void setPassiveimmunityfrombirth(long passiveimmunityfrombirth) {
		this.passiveimmunityfrombirth = passiveimmunityfrombirth;
	}

	public long getRecovered() {
		return recovered;
	}

	public void setRecovered(long recovered) {
		this.recovered = recovered;
	}

	public long getSpontanous() {
		return spontanous;
	}

	public void setSpontanous(long spontanous) {
		this.spontanous = spontanous;
	}

	public long getSusceptible() {
		return susceptible;
	}

	public void setSusceptible(long susceptible) {
		this.susceptible = susceptible;
	}

	public long getVectored() {
		return vectored;
	}

	public void setVectored(long vectored) {
		this.vectored = vectored;
	}

	public long getHealty() {
		return healty;
	}

	public void setHealty(long healty) {
		this.healty = healty;
	}

	public long getInfectious() {
		return infectious;
	}

	public void setInfectious(long infectious) {
		this.infectious = infectious;
	}

	public long getLatent() {
		return latent;
	}

	public void setLatent(long latent) {
		this.latent = latent;
	}

	public long getRemoved() {
		return removed;
	}

	public void setRemoved(long removed) {
		this.removed = removed;
	}
	

}
