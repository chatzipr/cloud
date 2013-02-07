package cloud.test;

import java.io.IOException;

import cloud.init.RandomCloudInitializer;
import cloud.operators.CloudCrossover;
import cloud.problem.CloudChromosomeVariable;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestCrossover extends TestCase{
	
	protected int numberOfVMs = 12;
	protected int numberOfPMs = 7;

	protected int mipsTotalCapacity = 50;

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		return new TestSuite(Test1.class);
	}

	/**
	 * Test Cloud Chromosome Initialization.
	 * 
	 * @throws IOException
	 */
	public void testVMChromosomeInitialization() throws IOException {

		RandomCloudInitializer initializer = new RandomCloudInitializer(null, 0);

		CloudChromosomeVariable cloud1 = new CloudChromosomeVariable(
				numberOfVMs, mipsTotalCapacity);

		initializer.storeIndividualToFile(cloud1, "cloud_output/out.txt");
		
		CloudChromosomeVariable cloud2 = new CloudChromosomeVariable(
				numberOfVMs, mipsTotalCapacity);

		initializer.storeIndividualToFile(cloud2, "cloud_output/out.txt");

		CloudCrossover crossover = new CloudCrossover(0.5);
		crossover.evolve(cloud1, cloud2);
		
		initializer.storeIndividualToFile(cloud1, "cloud_output/out.txt");
		initializer.storeIndividualToFile(cloud2, "cloud_output/out.txt");
		
	
		
	}

}
