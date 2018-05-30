import java.util.ArrayList;
import java.util.Comparator;

public class Task {
	private int taskNumber; // numer zadania
	private ArrayList<Integer> durations = new ArrayList<Integer>(); // czasy trwania na maszynach
	private int t1 = 0; // zmodyfikowany czas wykonania 1
	private int t2 = 0; // zmodyfikowany czas wykonania 2

	public Task(int taskNumber) {
		this.taskNumber = taskNumber;
	}

	public int getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(int taskNumber) {
		this.taskNumber = taskNumber;
	}

	public ArrayList<Integer> getDurations() {
		return durations;
	}

	public void setDurations(ArrayList<Integer> durations) {
		this.durations = durations;
	}

	public int getT1() {
		return t1;
	}

	public void setT1(int modDuration1) {
		this.t1 = modDuration1;
	}

	public int getT2() {
		return t2;
	}

	public void setT2(int modDuration2) {
		this.t2 = modDuration2;
	}
}

class T1Comparator implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        return t1.getT1() - t2.getT1();
    }
}
class T2Comparator implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        return t1.getT2() - t2.getT2();
    }
}