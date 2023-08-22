package api.payload;

public class ProgramPayload {

	String programName;
	String programStatus;
	String programDescription;
	String creationTime;
		int programId;

	public int getProgramId() {
			return programId;
		}
		public void setProgramId(int programId) {
			this.programId = programId;
		}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
		
	}
	public String getProgramStatus() {
		return programStatus;
	}
	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}
	public String getProgramDescription() {
		return programDescription;
	}
	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}
}
