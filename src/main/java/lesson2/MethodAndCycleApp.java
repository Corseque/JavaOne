package lesson2;

public class MethodAndCycleApp {
    public static void main(String[] args) {

        //Задача 1
        int sum = (int) (Math.random() * 20) + (int) (Math.random() * 20);
        if (isSumInRange(sum)) {
            System.out.println("Задача 1. Сумма  двух чисел равна " + sum + " и находится в дапазоне от 10 до 20");
        } else {
            System.out.println("Задача 1. Сумма  двух чисел равна " + sum + " и не находится в дапазоне от 10 до 20");
        }
        System.out.println();

        //Задача 2
        int num = (int) (Math.random() * 200) - (int) (Math.random() * 100);
        isPositive(num);

        //Задача 3
        System.out.println("Задача 3. " + isPositiveFlag(num));
        System.out.println();

        //Задача 4
        printString ("Hello", (int) (Math.random() * ((10-1)+1)));

        //Задача 5
        System.out.println("Задача 5.");
        int year;
        for (int i = 0; i < 11; i++) {
            year = (int) (Math.random() * (2022));
//            int year = 1222; //760, 1600
            if (isLeapYear(year)) {
                System.out.println(year + " - високосный.");
            } else {
                System.out.println(year + " - не високосный.");
            }
        }
    }

    public static void printString (String str, int num) {
        System.out.println("Задача 4. Число строк: " + num);
        for (int i = 0; i < num; i++) {
            System.out.println(str);
        }
        System.out.println();
    }

    public static void isPositive (int num) {
        if (num >=0) {
            System.out.println("Задача 2. Число " + num + " положительное.");
        } else {
            System.out.println("Задача 2. Число " + num + " отрицательное.");
        }
        System.out.println();
    }

    public static boolean isPositiveFlag (int num) {
        return num >= 0;
    }

    public static boolean isSumInRange (int sum) {
        return (sum >= 10) && (sum <= 20);
    }

    public static boolean isLeapYear (int year) {
        if (year % 4 == 0) {
            if (year % 400 == 0) {
                return true;
            } else return year % 100 != 0;
        } else return false;
    }
}
