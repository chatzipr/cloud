package cloud.operators;

import java.util.ArrayList;

import org.moeaframework.core.PRNG;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variable;
import org.moeaframework.core.Variation;

import cloud.problem.CloudChromosomeVariable;
import cloud.problem.PMChromosome;

/**
 * This class implements a mutation operator based on the DoF2 (Switch off a PM)
 * 
 * @author chatzipr
 * 
 */
public class CloudMutationDoF2 implements Variation {

	/**
	 * The probability of mutating a variable.
	 */
	private final double probability;

	/**
	 * Constructs a DoF2 mutation operator with the specified probability of
	 * mutating a variable.
	 * 
	 * @param probability
	 *            the probability of mutating a variable
	 */
	public CloudMutationDoF2(double probability) {
		super();
		this.probability = probability;

	}

	/**
	 * A new PM is removed from  the Cloud configuration
	 * 
	 * @param permutation
	 */
	public static void evolve(CloudChromosomeVariable cloud) {
		int size = cloud.getPMGenes().size();
		int i = PRNG.nextInt(1, size - 1);

		ArrayList<PMChromosome> pmChromosomeList = cloud.getPMGenes();
		PMChromosome randomSelection = cloud.getPMGenes().get(i);
		int freeMIPS = randomSelection.calculateFreeMIPS();
		int totalMIPS = randomSelection.getMips();

		if (freeMIPS == totalMIPS) {
			pmChromosomeList.remove(i);
			cloud.setPMGenes(pmChromosomeList);
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
		// Number of created solutions.
		return 1;
	}
}
