package cloud.problem;

import java.io.Serializable;
import java.util.ArrayList;

import org.moeaframework.core.PRNG;

/**
 * This class depicts a group of VMs.
 * 
 * @author chatzipr
 * 
 */
public class VMChromosome implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7183978958617807868L;

	private int id; // VM Chromosome id.

	private ArrayList<VM> gene; // lists all VMs.

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param gene
	 */
	public VMChromosome(int id, ArrayList<VM> gene) {
		this.id = id;
		this.gene = gene;

	}

	/**
	 * Construct VM Chromosome
	 * 
	 * @param id
	 * @param lastGene
	 * @param size
	 * @param minMipsSize
	 * @param maxMipsSize
	 */
	public VMChromosome(int id, int lastGene, int size, int maxMipsSize) {

		this.id = id;
		int freeMips = maxMipsSize;
		int requiredMips = 0;

		ArrayList<VM> vmlist = new ArrayList<VM>();

		// Each VM may have different MIPS capabilities.
		for (int i = lastGene + 1; i <= lastGene + size; i++) {

			requiredMips = PRNG.nextInt(0, freeMips);

			vmlist.add(new VM(i, requiredMips));

			freeMips = freeMips - requiredMips;
		}

		this.gene = vmlist;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<VM> getGene() {
		return gene;
	}

	public void setGene(ArrayList<VM> gene) {
		this.gene = gene;
	}

}
