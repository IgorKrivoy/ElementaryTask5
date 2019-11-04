package task.util;

import java.util.Scanner;

public class Input {
    Scanner scanner;

    public int getInt() throws Exception {
        scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public String getString() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
