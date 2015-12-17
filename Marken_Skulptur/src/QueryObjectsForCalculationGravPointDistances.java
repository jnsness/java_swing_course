/**
 * Hilfsklasse um Werte in ein passendes Objekt zu �berf�hren. Wird ben�tigt um Arraylist zu bilden f�r Distanzberechnung aller Logos zum Schwerpunkt
 * 
 * @author Jnsness
 *
 */


public class QueryObjectsForCalculationGravPointDistances {
	
	private int distanceFromGravPoint;
	private String brandName;

	public QueryObjectsForCalculationGravPointDistances(int distanceFromGravPoint, String brandName) {
		
		this.distanceFromGravPoint = distanceFromGravPoint;
		this.brandName = brandName;
		
	}

	public int getDistanceFromGravPoint() {
		return distanceFromGravPoint;
	}

	public String getBrandName() {
		return brandName;
	}


	
}
