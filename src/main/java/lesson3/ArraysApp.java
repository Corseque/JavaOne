package lesson3;

import java.util.Arrays;

public class ArraysApp {
    public static void main(String[] args) {
        //Задание 1
        System.out.println("Задание 1.");
        int[] arr1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        changePlaceOneAndZero(arr1);
        System.out.println();

        //Задание 2
        System.out.println("Задание 2.");
        int[] arr2 = new int[100];
        fillArr(arr2);
        System.out.println();

        //Задание 3
        System.out.println("Задание 3.");
        int[] arr3 = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        multiplyNumInArrLessSix (arr3);
        System.out.println();

        //Задание 4
        System.out.println("Задание 4.");
        int[][] arr4 = new int[5][5];
        fillDiagonalWithUnits(arr4);
        System.out.println();

        //Задание 5
        System.out.println("Задание 5.");
        int arrLenght = 5;
        int initialValue = 2;
        System.out.println(Arrays.toString(fillArrByInitialValue(arrLenght, initialValue)));
        System.out.println();

        //Задание 6
        System.out.println("Задание 6.");
        int[] arr5 = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        findMinAndMax(arr5);
        System.out.println();

        //Задание 7
        System.out.println("Задание 7.");
        int[] arr6 = {2, 2, 2, 1, 2, 2, 10, 1};
        int[] arr7 = {1, 1, 0, 2, 1, 5, 7, 1, 0, 1};
        int[] arr8 = {1};
        isArrayPartsEqual(arr6);
        isArrayPartsEqual(arr7);
        isArrayPartsEqual(arr8);
        System.out.println();

        //Задание 8
        System.out.println("Задание 8.");
        int n = (int)(Math.random() * 11 - 5);
        int[] arr9 = {1,2,3,4,5};
        shiftArrayElementsByN(arr9, n);
    }

    private static void shiftArrayElementsByN(int[] arr, int n) {
        System.out.println("Элементы массива: " + Arrays.toString(arr) + " нужно сдвинуть на " + n);
        if (n % arr.length == 0) {
            System.out.println("Введенный массив при сдвиге на " + n + " не поменяется");
        } else {
            int flag;
            int i = 0;
            while (i < Math.abs(n)) {
                for (int j = 0; j < arr.length; j++) {
                    if (n > 0) {
                        flag = arr[arr.length - 1];
                        arr[arr.length - 1] = arr[j];
                        arr[j] = flag;
                    } else {
                        flag = arr[0];
                        arr[0] = arr[arr.length - 1 - j];
                        arr[arr.length - 1 - j] = flag;
                    }
                }
                i++;
            }
            System.out.println("Стало: " + Arrays.toString(arr));
        }
    }

    private static boolean isArrayPartsEqual(int[] arr) {
        int i = 0;
        int j = arr.length;
        int leftArrPart = arr[i];
        int rightArrPart = arr[--j];
        if (i == j) {
            System.out.println("Введенный массив состоит из одного элемента.");
            return false;
        } else {
            while (i < j) {
                if ((i + 1 == j) && (leftArrPart == rightArrPart)) {
                    System.out.println("Левая и правая части введенного массива равны.");
                    return true;
                }
                if (leftArrPart == rightArrPart) {
                    leftArrPart = leftArrPart + arr[++i];
                    rightArrPart = rightArrPart + arr[--j];
                } else if (leftArrPart < rightArrPart) {
                    leftArrPart = leftArrPart + arr[++i];
                } else {
                    rightArrPart = rightArrPart + arr[--j];
                }
            }
        }
        System.out.println("Левая и правая части введенного массива не равны.");
        return false;
    }

    public static void findMinAndMax(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            } else if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println("Минимальный элемент массива = " + min);
        System.out.println("Максимальный элемент массива = " + max);
    }

    public static int[] fillArrByInitialValue(int arrLenght, int initialValue) {
        int[] arr = new int[arrLenght];
        for (int i = 0; i < arrLenght; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }

    public static void fillDiagonalWithUnits(int[][] arr) {
        System.out.println("Было: ");
        printlnTwoDimArr(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if ((i == j) || ( j  == arr[i].length -  i - 1)) {
                    arr[i][j] = 1;
                }
            }
        }
        System.out.println("Стало: ");
        printlnTwoDimArr(arr);
    }

    public static void printlnTwoDimArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    public static void multiplyNumInArrLessSix(int[] arr) {
        System.out.println("Было: " + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= 6) {
                arr[i] = arr[i] * 2;
            }
        }
        System.out.println("Стало: " + Arrays.toString(arr));
    }

    public static void fillArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println("Получили массив: " + Arrays.toString(arr));
    }

    public static void changePlaceOneAndZero (int[] arr) {
        System.out.println("Было: " + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }
        System.out.println("Стало: " + Arrays.toString(arr));
    }
}


