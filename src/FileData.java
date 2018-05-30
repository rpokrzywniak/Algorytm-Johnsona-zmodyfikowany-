import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileData {
	static ArrayList<String> fileData = new ArrayList<String>();
	static ArrayList<ArrayList<String>> fileTasks = new ArrayList<ArrayList<String>>();

	public static void getData(String fileName) {
		try {
			Scanner file = new Scanner(new FileReader(fileName));
			while (file.hasNextLine()) {
				fileData.add(file.nextLine());
			}
			file.close();
		} catch (FileNotFoundException e) {
			Main.error(e.getMessage());
		}
		for (String line : fileData) {
			String[] splited = line.split(" ");
			ArrayList<String> splitResult = new ArrayList<String>();
			for (String part : splited) {
				splitResult.add(part);
			}
			fileTasks.add(splitResult);
		}

		if (fileTasks.size() <= 1) {
			Main.error("No data acquired");
		}
		int machines = Integer.parseInt(fileTasks.get(0).get(0));
		for (int i = 1; i <= machines; i++) {
			MachineManager.addMachine(new Machine((i)));
		}

		for (int i = 1; i < fileTasks.size(); i++) {
			int taskNumber = Integer.parseInt(fileTasks.get(i).get(0));
			if (taskNumber > fileTasks.size() - 1) {
				Main.error("Task numbers not in sequence");
			}
			TaskManager.addTask(new Task(taskNumber));
		}

		for (int i = 1; i < fileTasks.size(); i++) {
			if (fileTasks.get(i).size() - 1 < MachineManager.machines.size()) {
				Main.error(fileTasks.get(i) + " - not enough data, should be: "
						+ (MachineManager.machines.size()));
			} else if (fileTasks.get(i).size() - 1 > MachineManager.machines.size()) {
				Main.error(fileTasks.get(i) + " - too much data, should be: "
						+ (MachineManager.machines.size()));
			} else {
				int taskNumber = Integer.parseInt(fileTasks.get(i).get(0));
				ArrayList<Integer> durations = new ArrayList<Integer>();
				for (int j = 1; j <= MachineManager.machines.size(); j++) {
					int duration = Integer.parseInt(fileTasks.get(i).get(j));
					durations.add(duration);
				}
				TaskManager.tasks.get(taskNumber - 1).setDurations(durations);
			}
		}
	}
}