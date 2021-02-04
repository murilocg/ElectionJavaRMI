package server;

public class Vote {
	
	private String voterId;
	private String candidateId;
	private boolean voted;
	
	public Vote(String voterId, String candidateId) {
		setCandidateId(candidateId);
		setVoterId(voterId);
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
	public void setCandidateId(String candidate) {
		this.candidateId = candidate;
	}

	public boolean isVoted() {
		return voted;
	}

	public void setVoted(boolean voted) {
		this.voted = voted;
	}
}
