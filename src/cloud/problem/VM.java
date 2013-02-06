package cloud.problem;

import java.io.Serializable;

public class VM implements Serializable {

	/**
	 * This class represents the VM entity.
	 */
	private static final long serialVersionUID = -3485795313814093993L;

	private int mips;

	/**
	 * VM Constructor.
	 * @param mips
	 */
	public VM(int mips) {

		this.setMips(mips);
	}

	public VM createVM(int mips) {
		VM vm = new VM(mips);
		return vm;
	}

	public void setMips(int mips) {
		this.mips = mips;
	}

	public int getMips() {
		return mips;
	}

}
