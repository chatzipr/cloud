package cloud.problem;

import java.io.Serializable;

public class VM implements Serializable {

	/**
	 * This class represents the VM entity.
	 */
	private static final long serialVersionUID = -3485795313814093993L;

	private int vmId;
	private int mips;

	public VM(int vmId, int mips) {
		this.setVmId(vmId);
		this.setMips(mips);
	}

	public VM createVM(int id, int mips) {
		VM vm = new VM(id, mips);
		return vm;
	}

	public void setVmId(int vmId) {
		this.vmId = vmId;
	}

	public int getVmId() {
		return vmId;
	}

	public void setMips(int mips) {
		this.mips = mips;
	}

	public int getMips() {
		return mips;
	}

}
