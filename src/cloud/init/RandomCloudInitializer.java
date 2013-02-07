package cloud.init;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.moeaframework.core.Initialization;
import org.moeaframework.core.Problem;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variable;

import cloud.problem.CloudChromosomeVariable;
import cloud.problem.PMChromosome;
import cloud.problem.VM;

public class RandomCloudInitializer implements Initialization {

	private static final String delimiter1 = " [ ";
	private static final String delimiter2 = " ] ";
	private static final String delimiter3 = "  *  ";
	private static final String space = " ";
	/**
	 * The problem.
	 */
	private final Problem problem;

	/**
	 * The initial population size.
	 */
	private final int populationSize;

	public RandomCloudInitializer(Problem problem, int populationSize) {
		super();
		this.problem = problem;
		this.populationSize = populationSize;
	}

	/**
	 * Create initial Cloud configurati ons population.
	 */
	@Override
	public Solution[] initialize() {

		Solution[] initialPopulation = new Solution[populationSize];

		// TODO: Create population size of different lengths.

		for (int i = 0; i < populationSize; i++) {
			Solution solution = problem.newSolution();

			CloudChromosomeVariable cloud = (CloudChromosomeVariable) solution
					.getVariable(0);
			try {
				// print my initial population
				storeIndividualToFile(cloud, "cloud_output/out.txt");
			} catch (IOException e) {

				e.printStackTrace();
			}
			initialPopulation[i] = solution;
		}

		return initialPopulation;

	}

	/**
	 * This function prints and stores to file the particular cloud individual.
	 * 
	 * @param cloud
	 * @param filepath
	 * @throws IOException
	 */
	public void storeIndividualToFile(CloudChromosomeVariable cloud,
			String filepath) throws IOException {

		String candidate = "";
		FileWriter fstream = new FileWriter(filepath, true);
		BufferedWriter out = new BufferedWriter(fstream);
		String vmIds = "";
		String pmId = null;
		int pmCounter = 1;
		int vmCounter = 1;

		// Print each individual
		for (PMChromosome pm : cloud.getPMGenes()) {

			vmIds = "";
			pmId = Integer.toString(pmCounter);

			vmCounter = 1;

			for (VM vm : pm.getGene()) {
				vmIds = vmIds + Integer.toString(vmCounter) + delimiter1
						+ Integer.toString(vm.getMips()) + delimiter2;
				vmCounter++;
			}

			vmIds = vmIds + delimiter2;

			candidate = candidate + delimiter1 + pmId + space + delimiter1
					+ Integer.toString(pm.getMips()) + delimiter2 + space
					+ delimiter1 + vmIds + space + delimiter2 + delimiter3;

			pmCounter++;

		}

		out.append("Candidate:" + candidate + "\n");
		System.out.println(candidate);
		out.close();

	}

}
