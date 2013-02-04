package cloud.problem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class depicts a PM.
 * 
 * @author chatzipr
 * 
 */
public class PMChromosome implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -273838319665033064L;

	private int id; // Chromosome id.

	private int pmId; // The pm gene.

	private int mips; // Capacity.

	private ArrayList<VMChromosome> gene;

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param pmSubGene
	 * @param vmSubGene
	 */
	public PMChromosome(int id, int pmId, int mips, ArrayList<VMChromosome> gene) {

		this.setId(id);
		this.setPmId(pmId);
		this.mips=mips;
		this.setGene(gene);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setGene(ArrayList<VMChromosome> gene) {
		this.gene = gene;
	}

	public ArrayList<VMChromosome> getGene() {
		return gene;
	}

	public void setPmId(int pmId) {
		this.pmId = pmId;
	}

	public int getPmId() {
		return pmId;
	}

	public int getMips() {
		return mips;
	}

	public void setMips(int mips) {
		this.mips = mips;
	}

}