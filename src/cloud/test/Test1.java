package cloud.test;

import java.io.IOException;
import java.util.ArrayList;

import cloud.init.RandomCloudInitializer;
import cloud.problem.CloudChromosomeVariable;
import cloud.problem.PMChromosome;
import cloud.problem.VM;
import cloud.problem.VMChromosome;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Test1 extends TestCase {

	protected int numberOfVMs = 12;
	protected int numberOfPMs = 5;

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

		CloudChromosomeVariable cloud = new CloudChromosomeVariable(
				numberOfVMs, mipsTotalCapacity);

		initializer.storeIndividualToFile(cloud, "cloud_output/out.txt");

		assertTrue(cloud.getPMGenes() != null);
	}

}
