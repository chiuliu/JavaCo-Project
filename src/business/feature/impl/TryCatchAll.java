package business.feature.impl;

import java.util.Scanner;

public class TryCatchAll {
    public static int inputNumber(Scanner scanner) {
        do {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Bạn đã nhập sai mục, vui lòng nhập lại");
            }
        } while (true);
    }
}
