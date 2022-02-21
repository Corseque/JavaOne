package lesson1;

public class FirstApp {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign(1 , -2);
        checkSumSign(1 , 2);
        printColor(-1);
        printColor(1);
        printColor(101);
        compareNumbers(1 , 2);
        compareNumbers(2 , 1);
    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign(int a, int b) {
        if ((a + b) > 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor(int value) {
        if (value > 0) {
            if (value <= 100) {
                System.out.println("Желтый");
            } else {
                System.out.println("Зеленый");
            }
        } else {
            System.out.println("Красный");
        }
    }

    public static void compareNumbers(int a, int b) {
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }
}
