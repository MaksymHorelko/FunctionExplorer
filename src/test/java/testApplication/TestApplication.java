package testApplication;

public class TestApplication {
	private boolean running = true;
	private final InputOutputManager ioManager = new InputOutputManager();

	private final ConcreteFunctionProcessor functionProcessor = new ConcreteFunctionProcessor();
	private final ConreteCsvFileProcessor csvFileProcessor = new ConreteCsvFileProcessor();
	private final ChartProcessor chartProcessor = new ChartProcessor();
	private final String filePathToCsvFile = "/home/mint/java-workspace/FunctionExplorer/csvFile.csv";

	public void run() {
		while (running) {
			String choice = ioManager.getInput("Введіть що вам потрібно:\n" + "1. Обчислення заданих функцій\n"
					+ "2. Обчислення CSV файлу\n" + "3. Обчислення власноруч введеної функції\n"
					+ "4. Демонстрація функції\n" + "0. Вихід\n" + "Вибір: ");
			processMainChoice(choice);
		}
	}

	// process first choice in menu
	private String taskProcessFunctions() {
		String message = "1. Функція f(x) = exp(-x^2) * sin(x)\n" + "2. Функція f(x) = exp(-a * x^2) * sin(x)\n"
				+ "3. Таблична функція з файлу third_function\nВаш вибір:";
		String pickedFunction = ioManager.getInput(message);
		return pickedFunction;
	}

	private void testFunction(String function) {
		if (function.equals("1")) {
			String firstFunctionOutput = "output_first_fun";
			functionProcessor.processFirstFunction(firstFunctionOutput);
			ioManager.displayMessage("Перша фунція оброблена. Результат збережеий в " + firstFunctionOutput + "\n");
		}

		else if (function.equals("2")) {
			String secondFunctionOutput = "output_second_fun_";
			functionProcessor.processSecondFunction(secondFunctionOutput);
			ioManager.displayMessage("Друга фунція оброблена. Результат збережеий в " + secondFunctionOutput + "\n");
		}

		else if (function.equals("3")) {
			String thirdFunctionOutput = "output_third_fun";
			String filePath = "third_function";
			functionProcessor.processThirdFunction(filePath, thirdFunctionOutput);
			ioManager.displayMessage("Третя фунція оброблена. Результат збережеий в " + thirdFunctionOutput + "\n");
		}

		else {
			ioManager.displayMessage("Невірний вибір");
		}
	}

	// process second choice in menu
	private String taskProcessCsvFile() {
		String message = "Виберіть куди зберегти значення з файлу:\n1. TreeSet\n2. TreeMap\n\nВаш вибір: ";
		String choise = ioManager.getInput(message);
		return choise;
	}

	private void testCsvFile(String structureType) {
		if (structureType.equals("1")) {
			ioManager.displayMessage(
					"Результат конветації файлу " + filePathToCsvFile.substring(filePathToCsvFile.lastIndexOf("/") + 1)
							+ ": " + csvFileProcessor.saveDataToTreeSet(filePathToCsvFile));
		}

		else if (structureType.equals("2")) {
			ioManager.displayMessage(
					"Результат конветації файлу " + filePathToCsvFile.substring(filePathToCsvFile.lastIndexOf("/") + 1)
							+ ": " + csvFileProcessor.saveDataToTreeMap(filePathToCsvFile));
		}

		else {
			ioManager.displayMessage("Невірний вибір");
		}

		ioManager.displayMessage("CSV файл оброблений\n");
	}

	// process third choice in menu
	private String taskProcessCustomFunction() {
		String message = "Що ви бажаєте зробити?\n1. Обчислити\n2. Відобразити фунцію на графіку";
		return ioManager.getInput(message);
	}

	private void testCustomFunction(String function, String operaion) {
		if (operaion.equals("1")) {
			String customFunctionOutput = "output_custom_fun";
			functionProcessor.processCustomUserFunction(function, customFunctionOutput);
			ioManager.displayMessage("Ваша фунція оброблена. Результат збережеий в" + customFunctionOutput + "\n");
		}

		else if (operaion.equals("2")) {
			chartProcessor.showCustomFunction(function);
		}
	}

	// process fourth choice in menu
	private void displayFunction(String pickedFunction) {
		String message = "Виберіть функцію яку бажаєте побачити на графіку: ";
		ioManager.displayMessage(message);

		if (pickedFunction.equals("1")) { /* f(x) = exp(-x^2) * sin(x) */
			chartProcessor.showFirstFunction();
		}

		else if (pickedFunction.equals("2")) { /* f(x) = exp(-a * x^2) * sin(x) */
			chartProcessor.showSecondFunction();
		}

		else if (pickedFunction.equals("3")) { // table function
			chartProcessor.showThirdFunction();
		}

		else {
			ioManager.displayMessage("Невірний вибір");
		}

	}

	// process main choice
	private void processMainChoice(String choice) {
		switch (choice) {
		case "1":
			String pickedFunction = taskProcessFunctions();
			testFunction(pickedFunction);
			break;

		case "2":
			String pickedStructure = taskProcessCsvFile();
			testCsvFile(pickedStructure);
			break;

		case "3":
			String customUserFunction = ioManager.getInput("Введіть вашу фунцію: ");
			String operaion = taskProcessCustomFunction();
			testCustomFunction(customUserFunction, operaion);
			break;

		case "4":
			String pickedFunctionToDisplay = taskProcessFunctions();
			displayFunction(pickedFunctionToDisplay);
			break;

		case "0":
			running = false;
			break;

		default:
			ioManager.displayMessage("Невірний вибір");
		}
	}

	public static void main(String[] args) {
		TestApplication test = new TestApplication();
		test.run();
	}
}
