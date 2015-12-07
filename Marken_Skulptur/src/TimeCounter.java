public class TimeCounter {

	long startTime;
	long endTime;
	long tDelta;

	public TimeCounter() {
		this.startTime = System.currentTimeMillis();
	}

	

	public double setEndTime(long endTime) {
		this.endTime = endTime;
		tDelta = endTime - startTime;
		double elapsedSeconds = tDelta / 1000;
		return elapsedSeconds;
		
	}
	
	
}
