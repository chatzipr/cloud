package cloud.operators;

import java.util.ArrayList;

import org.moeaframework.core.PRNG;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variable;
import org.moeaframework.core.Variation;
import org.moeaframework.core.variable.Permutation;

import cloud.problem.CloudChromosomeVariable;
import cloud.problem.PMChromosome;

/**
 * This Class implements a Mutation Operator based on the DoF1 (switch-on a PM)
 * 
 * @author chatzipr
 * 
 */
public class CloudMutationDoF1 implements Variation {

	/**
	 * The probability of mutating a variable.
	 */
	private final double probability;

	/**
	 * Capacity of new PM.
	 */
	private static int mipsCapacity;

	/**
	 * Constructs a DoF1 mutation operator with the specified probability of
	 * mutating a variable.
	 * 
	 * @param probability
	 *            the probability of mutating a variable
	 */
	public CloudMutationDoF1(double probability, int mipsCapacity) {
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

		ArrayList<PMChromosome> pmChromosomeList = cloud.getPMGenes();
        pmChromosomeList.add(new PMChromosome(mipsCapacity, null));
		cloud.setPMGenes(pmChromosomeList);
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
