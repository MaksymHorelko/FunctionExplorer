
import java.util.Scanner;

public class TestApplication {
	private static void processChoise(String choise) {

		if (choise.equals("1")) {

		} else if (choise.equals("2")) {

		}

		else if (choise.equals("3")) {

		} else if (choise.equals("4")) {

		} else if (choise.equals("5")) {

		}

	}

	public static void main(String[] args) {

		Application application = new Application();
		application.testAll();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Введіть що вам потрібно" + "ВСІ. Протестувати всі функції" + "1. Протестувати першу функцію"
				+ "2. Протестувати другу функцію" + "3. Протестувати третю функцію" + "4. Протестувати CSV файл"
				+ "5. Відобразити графік функцій"

		);

		while (true) {
			String choise = scanner.nextLine();

			processChoise(choise);
		}
//		scanner.close();

	}
}
