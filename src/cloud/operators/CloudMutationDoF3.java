package cloud.operators;

import java.util.ArrayList;

import org.moeaframework.core.PRNG;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variable;
import org.moeaframework.core.Variation;

import cloud.problem.CloudChromosomeVariable;
import cloud.problem.PMChromosome;
import cloud.problem.VM;

/**
 * This class implements a mutation operator based on the DoF3 (Switch on a VM)
 * 
 * @author chatzipr
 * 
 */
public class CloudMutationDoF3 implements Variation {

	/**
	 * The probability of mutating a variable.
	 */
	private final double probability;

	/**
	 * Capacity of new VM.
	 */
	private static int mipsCapacity;

	/**
	 * Constructs a DoF1 mutation operator with the specified probability of
	 * mutating a variable.
	 * 
	 * @param probability
	 *            the probability of mutating a variable
	 */
	public CloudMutationDoF3(double probability, int mipsCapacity) {
		super();
		this.probability = probability;
		this.mipsCapacity = mipsCapacity;
	}

	/**
	 * A new PM is added to the Cloud configuration
	 * 
	 * @param permutation
	 */
	public static void evolve(CloudChromosomeVariable cloud) {

		int size = cloud.getPMGenes().size();
		int i = PRNG.nextInt(1, size - 1);

		ArrayList<PMChromosome> pmChromosomeList = cloud.getPMGenes();
		PMChromosome randomSelection = cloud.getPMGenes().get(i);
		int freeMIPS = randomSelection.calculateFreeMIPS();

		if (freeMIPS >= mipsCapacity) {
			VM vm = new VM(mipsCapacity);
			randomSelection.getGene().add(vm);
		}
	}

	@Override
	public Solution[] evolve(Solution[] parents) {
		Solution result = parents[0].copy();

		for (int i = 0; i < result.getNumberOfVariables(); i++) {
			Variable variable = result.getVariable(i);

			if ((PRNG.nextDouble() <= probability)
					&& (variable instanceof CloudChromosomeVariable)) {
				evolve((CloudChromosomeVariable) variable);
			}
		}

		return new Solution[] { result };
	}

	@Override
	public int getArity() {
		return 1;
	}

}
