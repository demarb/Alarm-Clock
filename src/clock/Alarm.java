package clock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Alarm {

	private String currentTime;
	private String pathToSound;
	private String alarmName;
	private int hour;
	private int minute;
	private int second;
	
	//Constructor
	public Alarm(String currentTime, String pathToSound, String alarmName, int hour, int minute, int second) {
		this.pathToSound = pathToSound;
		this.alarmName = alarmName;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public Alarm() {
		this.pathToSound = "";
		this.alarmName = "NULL";
		this.hour = 1;
		this.minute = 1;
		this.second = 1;
	}

	//Accessors and mutators
	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	
	public String getPathToSound() {
		return pathToSound;
	}

	public void setPathToSound(String pathToSound) {
		this.pathToSound = pathToSound;
	}

	public String getAlarmName() {
		return alarmName;
	}

	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	//ToString
	@Override
	public String toString() {
		return "Alarm [pathToSound=" + pathToSound + ", alarmName=" + alarmName + ", hour=" + hour + ", minute="
				+ minute + ", second=" + second + "]";
	}
	
	//Methods
	public void updateCurrentTime() {
		LocalTime time = LocalTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		this.setCurrentTime(time.format(dtf));
	}
	
}
