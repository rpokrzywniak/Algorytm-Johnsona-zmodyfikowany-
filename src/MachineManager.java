import java.util.ArrayList;

public class MachineManager {
	public static ArrayList<Machine> machines = new ArrayList<Machine>();

	public static void addMachine(Machine machine) {
		machines.add(machine);
	}

	public static void showMachines() {
		System.out.println("\nMACHINES:");

		int maxScheldue = 0;
		for (Machine machine : machines) {
			if (maxScheldue < machine.getSchedule().size()) {
				maxScheldue = machine.getSchedule().size();
			}
		}

		System.out.print("{Timeline}\t");
		for (int i = 1; i <= maxScheldue; i++) {
			System.out.print(i+ "\t");
		}
		System.out.println("\n");

		for (Machine machine : machines) {
			System.out.print("{M"+machine.getMachineNumber()+"}\t\t");
			for (Task task : machine.getSchedule()) {
				if (task.getTaskNumber() == 0) {
					System.out.print("|||\t");
				} else {
					System.out.print("Z"+task.getTaskNumber() + "\t");
				}
			}
			System.out.print("\n");
		}
	}

}
