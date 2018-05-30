import java.util.ArrayList;

public class N {
	private int number;
	private ArrayList<Task> tasks = new ArrayList<Task>();

	public N(int number){
		this.number=number;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void addTasks(Task task) {
		this.tasks.add(task);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
