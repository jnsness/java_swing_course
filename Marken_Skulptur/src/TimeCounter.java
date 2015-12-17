/**
 * Hilfsklasse - Verwendet jeweils die Systemzeit als Startzeit im Konstruktur
 * beim zweiten Anstoß wird Methode setEndTime aufgerufen, die erneut die Systemzeit nimmt. Das Delta entspricht der abgelaufenen Zeit und wird zurückgegeben
 * 
 * @author Jnsness
 *
 */

public class TimeCounter {

	long startTime;
	long endTime;
	long tDelta;

	public TimeCounter() {
		this.startTime = System.currentTimeMillis();
	}

	/**
	 * nimmt aktuelle Systemzeit als Endzeit auf und bildet Differenz aus Endzeit und Startzeit
	 * 
	 * @param endTime - Delta aus Startzeit und Endzeit
	 * @return - liefer die vergange Zeit zwischen Start und Ende zurück (berechnet aus Systemzeit)
	 */

	public double setEndTime(long endTime) {
		this.endTime = endTime;
		tDelta = endTime - startTime;
		double elapsedSeconds = tDelta / 1000;
		return elapsedSeconds;
		
	}
	
	
}
