package service;

public class CannotVoteTwiceException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MSG = "You're already voted!";
	private String voterId;
	private String candidateId;
	
	public CannotVoteTwiceException(String voterId, String candidateId) {
		super(MSG);
		this.setVoterId(voterId);
		this.setCandidateId(candidateId);
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
}
