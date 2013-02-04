package cloud.test;
import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Run all JUnit Tests.
 * @author chatzipr
 *
 */
public class RunTestSuits {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("All JUnit Tests");
		suite.addTest(Test1.suite());
		return suite;
	}
}
