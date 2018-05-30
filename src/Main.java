public class Main {

	public static void error(String text) {
		System.out.println(text);
		System.exit(0);
	}

	public static void main(String args[]) {
		System.out.println("Johnson\n");

		FileData.getData("data.txt");

		TaskManager.modifiedDurations();
		TaskManager.populateN();

		TaskManager.setSchedules();

		TaskManager.showTasks();
		MachineManager.showMachines();
	}
}