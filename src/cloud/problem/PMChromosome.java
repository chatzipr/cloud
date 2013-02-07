package cloud.problem;

import java.io.Serializable;
import java.util.ArrayList;

import org.moeaframework.core.PRNG;

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

	private ArrayList<VM> gene; // lists all VMs.

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param pmSubGene
	 * @param vmSubGene
	 */
	public PMChromosome(int mips, ArrayList<VM> gene) {

		this.mips = mips;
		this.setGene(gene);
	}

	public void setGene(ArrayList<VM> gene) {
		this.gene = gene;
	}

	public ArrayList<VM> getGene() {
		return gene;
	}

	public int getMips() {
		return mips;
	}

	public void setMips(int mips) {
		this.mips = mips;
	}

	/**
	 * Calculate free MIPS capacity within this PMChromosome.
	 * 
	 * @return free MIPS.
	 */
	public int calculateFreeMIPS() {
		int totalUsedMIPS = 0;

		for (VM vm : this.gene) {
			totalUsedMIPS += vm.getMips();
		}

		return (this.mips - totalUsedMIPS);
	}

	public void initVMs(int lastGene, int size, int maxMipsSize) {

		int freeMips = maxMipsSize;
		int requiredMips = 0;

		ArrayList<VM> vmlist = new ArrayList<VM>();

		// Each VM may have different MIPS capabilities.
		for (int i = lastGene + 1; i <= lastGene + size; i++) {

			requiredMips = PRNG.nextInt(0, freeMips);

			vmlist.add(new VM(requiredMips));

			freeMips = freeMips - requiredMips;
		}

		this.gene = vmlist;

	}

}
