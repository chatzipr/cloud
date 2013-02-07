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
 * This class implements a mutation operator based on the DoF4 (Switch off a VM)
 * 
 * @author chatzipr
 * 
 */
public class CloudMutationDoF4 implements Variation {

	/**
	 * The probability of mutating a variable.
	 */
	private final double probability;

	/**
	 * Constructs a DoF4 mutation operator with the specified probability of
	 * mutating a variable.
	 * 
	 * @param probability
	 *            the probability of mutating a variable
	 */
	public CloudMutationDoF4(double probability) {
		super();
		this.probability = probability;

	}

	/**
	 * Random VM switched off.
	 * 
	 * @param cloud
	 */
	public static void evolve(CloudChromosomeVariable cloud) {
		int size1 = cloud.getPMGenes().size();
		int i = PRNG.nextInt(1, size1 - 1);

		PMChromosome randomPM = cloud.getPMGenes().get(i);

		int size2 = randomPM.getGene().size();
		int j = PRNG.nextInt(1, size2 - 1);

		ArrayList<VM> vmList = randomPM.getGene();

		vmList.remove(j);
		randomPM.setGene(vmList);

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
