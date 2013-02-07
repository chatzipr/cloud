package cloud.problem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.SerializationUtils;
import org.moeaframework.core.PRNG;
import org.moeaframework.core.Variable;

/**
 * This class represents a Cloud resources configuration as a chromosome.
 * 
 * @author chatzipr
 * 
 */
public class CloudChromosomeVariable implements Variable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4220606602150453469L;

	private ArrayList<PMChromosome> gene; // lists all PM sub-genes.

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param gene
	 */
	public CloudChromosomeVariable(ArrayList<PMChromosome> gene) {

		this.gene = gene;

	}

	public CloudChromosomeVariable(int numberOfVMs, int maxMipsSize) {

		this.gene = generateGenes(numberOfVMs, maxMipsSize);
	}

	/**
	 * This function generates random VM Genes, to create one candidate.
	 * 
	 * @param numberOfVMs
	 * @param minMipsSize
	 * @param maxMipsSize
	 */
	public ArrayList<PMChromosome> generateGenes(int numberOfVMs,
			int mipsCapacity) {

		int vmGeneId = 1;
		int pmGeneId = 1;
		int cardinality = 0;
		int remainingVMs = numberOfVMs;
		int lastGene = 0;
		ArrayList<VM> vmList;
		ArrayList<PMChromosome> pmChromosomeList = new ArrayList<PMChromosome>();

		while (remainingVMs > 0) {

			// Number of VMs to be created
			cardinality = PRNG.nextInt(1, remainingVMs);

			vmList = new ArrayList<VM>();

			// Create PMChromosome to host VMChromosome
			PMChromosome pmChromosome = new PMChromosome(mipsCapacity, vmList);
			pmChromosome.initVMs(lastGene, cardinality, mipsCapacity);

			pmChromosomeList.add(pmChromosome);

			lastGene = lastGene + cardinality;
			pmGeneId++;
			vmGeneId++;
			remainingVMs = remainingVMs - cardinality;

		}

		return pmChromosomeList;
	}

	// TODO: ???
	public void crossover(ArrayList<PMChromosome> gene) {
		this.gene = SerializationUtils.clone(gene);
	}

	@Override
	public Variable copy() {

		return new CloudChromosomeVariable(gene);
	}

	public ArrayList<PMChromosome> getPMGenes() {
		return gene;
	}

	public void setPMGenes(ArrayList<PMChromosome> gene) {
		this.gene = gene;
	}
}
