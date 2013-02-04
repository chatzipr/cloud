package cloud.problem;

import org.moeaframework.core.Solution;
import org.moeaframework.problem.AbstractProblem;

public class ConfigurationCalculationProblem extends AbstractProblem {

	private final int numberOfVMs;
	private final int minMipsSize;
	private final int maxMipsSize;

	public ConfigurationCalculationProblem(int numberOfVMs, int minMipsSize,
			int maxMipsSize) {

		super(1, 1);

		this.numberOfVMs = numberOfVMs;

		// Minimum PM Mips capacity
		this.minMipsSize = minMipsSize;
		// Minimum PM Mips capacity
		this.maxMipsSize = maxMipsSize;

	}

	@Override
	public void evaluate(Solution solution) {
		// TODO

	}

	@Override
	public Solution newSolution() {
		Solution solution = new Solution(1, 1);

		CloudChromosomeVariable cloud = new CloudChromosomeVariable(
				numberOfVMs, maxMipsSize);

		solution.setVariable(0, new CloudChromosomeVariable(numberOfVMs,
				maxMipsSize));

		return solution;
	}

}
