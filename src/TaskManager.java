import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TaskManager {
	public static ArrayList<Task> tasks = new ArrayList<Task>();
	public static N n1 = new N(1);
	public static N n2 = new N(2);

	public static void addTask(Task task) {
		tasks.add(task);
	}

	public static void showTasks() {
		System.out.println("TASKS:");
		for (Task task : tasks) {
			System.out.println("[T" + task.getTaskNumber() + "]: " + task.getDurations() + ", " + "t1=" + task.getT1()
					+ ", " + "t2=" + task.getT2());
		}
		System.out.print("N1: ");
		for (Task task : n1.getTasks()) {
			System.out.print(task.getTaskNumber() + " ");
		}
		System.out.print("\nN2: ");
		for (Task task : n2.getTasks()) {
			System.out.print(task.getTaskNumber() + " ");
		}
	}

	public static void modifiedDurations() {
		for (Task task : tasks) {
			task.setT1(task.getDurations().get(0) + task.getDurations().get(1));
			task.setT2(task.getDurations().get(1) + task.getDurations().get(2));
		}
	}

	public static void assingCollections() {
		for (Task task : tasks) {
			if (task.getT1() < task.getT2())
				n1.addTasks(task);
			else if (task.getT1() >= task.getT2())
				n2.addTasks(task);
		}
	}

	public static void addTaskToMachine(int machine, Task task) {
		MachineManager.machines.get(machine).getSchedule().add(task);
	}

	public static void setSchedules() {
		ArrayList<Task> N1 = new ArrayList<Task>();
		ArrayList<Task> N2 = new ArrayList<Task>();
		N1 = n1.getTasks();
		N2 = n2.getTasks();

		Collections.sort(N1, new Comparator<Task>() {
			public int compare(Task task1, Task task2) {
				return task1.getT1() - task2.getT1();
			}
		});

		Collections.sort(N2, new Comparator<Task>() {
			public int compare(Task task1, Task task2) {
				return task2.getT2() - task1.getT2();
			}
		});

		int wait = 0;
		ArrayList<Task> schedule = new ArrayList<Task>();
		schedule.addAll(N1);
		schedule.addAll(N2);

		for (Task task : schedule) {
			// pobieram zadania w kolejnosci
			for (int i = 0; i < task.getDurations().get(0); i++) {
				// wrzucam do maszyny 1 kolejne zadanie, tyle razy, jaki ma
				// duration
				addTaskToMachine(0, task);
			}

			for (int i = 0; i < task.getDurations().get(1); i++) {
				// przesowamy sie po timeline, dopoki dlugosc zadan M1 jest
				// wieksza od od M2
				if (MachineManager.machines.get(0).getSchedule().size() > wait && MachineManager.machines.get(0)
						.getSchedule().get(wait).getTaskNumber() == task.getTaskNumber()) {
					addTaskToMachine(1, new Task(0));
					i--;
				} else {
					addTaskToMachine(1, task);
				}
				wait++;
			}
		}

		wait = 0;
		for (Task task : schedule) {
			for (int i = 0; i < task.getDurations().get(2); i++) {
				if (MachineManager.machines.get(0).getSchedule().size() > wait // jezeli scheldue m1 i m2 sa krotsze od czasu aktualnego dla m3, to wypelniamy je 0 
						&& MachineManager.machines.get(1).getSchedule().size() > wait) {
					if (MachineManager.machines.get(0).getSchedule().get(wait).getTaskNumber() == task.getTaskNumber() //idziemy po timeline, gdy task w m3 jest rozny od m1 i m2 mozna dodac do m3
							|| MachineManager.machines.get(1).getSchedule().get(wait).getTaskNumber() == task		   
									.getTaskNumber()) {
						addTaskToMachine(2, new Task(0));
						i--;
					} else {
						addTaskToMachine(2, task);
					}
					wait++;
				} else {
					addTaskToMachine(0, new Task(0));
					addTaskToMachine(1, new Task(0));
					i--;
				}
			}
		}
		int maxScheldue = 0;
		for (Machine machine : MachineManager.machines) {
			if (maxScheldue < machine.getSchedule().size()) {
				maxScheldue = machine.getSchedule().size();
			}
		}
		for (Machine machine : MachineManager.machines) {
			while (machine.getSchedule().size() < maxScheldue)
				addTaskToMachine(machine.getMachineNumber() - 1, new Task(0));
		}

		for (int i = maxScheldue - 1; i > 0; i--) {

			if (MachineManager.machines.get(0).getSchedule().get(i).getTaskNumber() == 0
					&& MachineManager.machines.get(1).getSchedule().get(i).getTaskNumber() == 0
					&& MachineManager.machines.get(2).getSchedule().get(i).getTaskNumber() == 0) {
				MachineManager.machines.get(0).getSchedule().remove(i);
				MachineManager.machines.get(1).getSchedule().remove(i);
				MachineManager.machines.get(2).getSchedule().remove(i);
			} else
				break;
		}
	}
}
