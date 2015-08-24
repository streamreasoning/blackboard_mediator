package it.polimi.deib.blackboard_mediator.utilities;

public class Recommendation {
	
	private String objectName;
	private float recommandationProbability;
	
	public Recommendation() {
		super();
	}

	public Recommendation(String objectName, float recommandationProbability) {
		super();
		this.objectName = objectName;
		this.recommandationProbability = recommandationProbability;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public float getRecommandationProbability() {
		return recommandationProbability;
	}

	public void setRecommandationProbability(float recommandationProbability) {
		this.recommandationProbability = recommandationProbability;
	}
	
}
