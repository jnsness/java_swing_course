/**
 * Hilfsklasse um Werte in ein passendes Objekt zu �berf�hren. Wird ben�tigt um Arraylist zu bilden f�r Distanzberechnung aller Logos untereinander
 * 
 * @author Jnsness
 *
 */


public class QueryObjectsForCalculationDistances {
	
	
	private String uuidFromQuery;
	private String brandName;
	private int x;
	private int y;

	public QueryObjectsForCalculationDistances(String uuidFromQuery, String brandName, int x, int y) {
		
		this.uuidFromQuery = uuidFromQuery;
		this.brandName = brandName;
		this.x = x;
		this.y = y;
		
	}

	public String getUuidFromQuery() {
		return uuidFromQuery;
	}

	public String getBrandName() {
		return brandName;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
	
}
