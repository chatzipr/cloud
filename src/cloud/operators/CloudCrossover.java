package cloud.operators;

import java.util.ArrayList;
import java.util.Arrays;

import org.moeaframework.core.FrameworkException;
import org.moeaframework.core.PRNG;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variable;
import org.moeaframework.core.Variation;
import org.moeaframework.core.variable.Permutation;

import cloud.problem.CloudChromosomeVariable;
import cloud.problem.PMChromosome;

public class CloudCrossover implements Variation {

	/**
	 * The probability of applying this operator.
	 */
	private final double probability;

	/**
	 * Construct Cloud Operator with specified probability
	 * 
	 * @param probability
	 */
	public CloudCrossover(double probability) {
		super();
		this.probability = probability;
	}

	/**
	 * Implementing one point crossover.
	 * 
	 * @param c1
	 * @param c2
	 */
	public static void evolve(CloudChromosomeVariable c1,
			CloudChromosomeVariable c2) {

		int size1 = c1.getPMGenes().size();
		int size2 = c2.getPMGenes().size();

		int selectedsize = (size1 < size2) ? size1 : size2;

		// select cutting points
		int cuttingPoint = PRNG.nextInt(1, selectedsize);
		
		System.out.println("Cutting Point " + cuttingPoint);
		
		

		ArrayList<PMChromosome> pmsOffspring1 = new ArrayList<PMChromosome>();
		ArrayList<PMChromosome> pmsOffspring2 = new ArrayList<PMChromosome>();

		// TODO: clone

		// TODO: Better code?

		for (int i = 0; i < cuttingPoint; i++) {
			pmsOffspring1.add(i, c1.getPMGenes().get(i));
			pmsOffspring2.add(i, c2.getPMGenes().get(i));
		}

		for (int i = cuttingPoint; i < c1.getPMGenes().size(); i++) {
			pmsOffspring2.add(i, c1.getPMGenes().get(i));
		}

		for (int i = cuttingPoint; i < c2.getPMGenes().size(); i++) {
			pmsOffspring1.add(i, c2.getPMGenes().get(i));
		}

		// TODO: clone
		// POSA BUGS THA PETAXEI ARAGE?
		c1.crossover(pmsOffspring1);
        c2.crossover(pmsOffspring2);
	}

	@Override
	public Solution[] evolve(Solution[] parents) {
		Solution result1 = parents[0].copy();
		Solution result2 = parents[1].copy();

		for (int i = 0; i < result1.getNumberOfVariables(); i++) {
			Variable variable1 = result1.getVariable(i);
			Variable variable2 = result2.getVariable(i);

			if ((PRNG.nextDouble() <= probability)
					&& (variable1 instanceof CloudChromosomeVariable)
					&& (variable2 instanceof CloudChromosomeVariable)) {
				evolve((CloudChromosomeVariable) variable1,
						(CloudChromosomeVariable) variable2);
			}
		}

		return new Solution[] { result1, result2 };

	}

	@Override
	public int getArity() {
		// Number of created solutions.
		return 2;
	}

}
