package lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTapToeApp {
    public static char[][] map;
    public static final int SIZE = 3;
    public static final int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final Random RANDOM = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Вы выиграли!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья.");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Выиграл компьютер :(");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья.");
                break;
            }
        }
    }


    /**
     * Ход компьютера
     */
    public static void aiTurn() {
        int x;
        int y;
        int[] dot;
        do {
            dot = winDot(DOT_O);
            if (isCellValid(dot[0], dot[1])) {
                x = dot[0];
                y = dot[1];
            } else {
                dot = winDot(DOT_X);
                if (isCellValid(dot[0], dot[1])) {
                    x = dot[0];
                    y = dot[1];
                } else {
                    x = RANDOM.nextInt(SIZE);
                    y = RANDOM.nextInt(SIZE);
                }
            }
        } while (!isCellValid(x, y));
        System.out.printf("Робот ходит в точку %d %d", x + 1, y + 1);
        System.out.println();
        map[x][y] = DOT_O;
    }

    public static int[] winDot(char sym) {
        int vertical = 0;
        int horizontal = 0;
        int diagonalLeftToRight = 0;
        int diagonalRightToLeft = 0;
        int[] dot = new int[]{-1, -1};

        for (int i = 0; i < SIZE - DOTS_TO_WIN + 2; i++) {
            for (int j = 0; j < SIZE - DOTS_TO_WIN + 2; j++) {
                for (int k = 0; k < DOTS_TO_WIN - 1; k++) {
                    if (diagonalLeftToRight == DOTS_TO_WIN - 1 || diagonalRightToLeft == DOTS_TO_WIN - 1 || vertical == DOTS_TO_WIN - 1 || horizontal == DOTS_TO_WIN - 1) {
                        break;
                    } else {
                        diagonalLeftToRight = (map[k + i][k + j] == sym && diagonalLeftToRight < DOTS_TO_WIN - 1) ? ++diagonalLeftToRight : (diagonalLeftToRight == 0) ? diagonalLeftToRight : --diagonalLeftToRight;

                        if (diagonalLeftToRight == DOTS_TO_WIN - 1 && isCellValid(k + i + 1, k + j + 1)) {
                            dot[0] = k + i + 1;
                            dot[1] = k + j + 1;
                            break;
                        } else if (diagonalLeftToRight == DOTS_TO_WIN - 1 && isCellValid(k + i - (DOTS_TO_WIN - 1), k + j - (DOTS_TO_WIN - 1))) {
                            dot[0] = k + i - (DOTS_TO_WIN - 1);
                            dot[1] = k + j - (DOTS_TO_WIN - 1);
                            break;
                        }

                        diagonalRightToLeft = (map[DOTS_TO_WIN - 1 - k - 1 + i][k + j] == sym && diagonalRightToLeft < DOTS_TO_WIN - 1) ? ++diagonalRightToLeft : (diagonalRightToLeft == 0) ? diagonalRightToLeft : --diagonalRightToLeft;

                        if (diagonalRightToLeft == DOTS_TO_WIN - 1 && isCellValid(DOTS_TO_WIN - 1 - k - 1 + i - 1, k + j + 1)) {
                            dot[0] = DOTS_TO_WIN - 1 - k - 1 + i - 1;
                            dot[1] = k + j + 1;
                            break;
                        } else if (diagonalRightToLeft == DOTS_TO_WIN - 1 && isCellValid(DOTS_TO_WIN - 1 - k - 1 + i + DOTS_TO_WIN - 1, k + j - (DOTS_TO_WIN - 1))) {
                            dot[0] = DOTS_TO_WIN - 1 - k - 1 + i + DOTS_TO_WIN - 1;
                            dot[1] = k + j - (DOTS_TO_WIN - 1);
                            break;
                        }
                        for (int l = 0; l < DOTS_TO_WIN - 1; l++) {
                            horizontal = (map[k][l] == sym && horizontal < DOTS_TO_WIN - 1) ? ++horizontal : (horizontal == 0) ? horizontal : --horizontal;

                            if (horizontal == DOTS_TO_WIN - 1 && isCellValid(k, l + 1)) {
                                dot[0] = k;
                                dot[1] = l + 1;
                                break;
                            } else if (horizontal == DOTS_TO_WIN - 1 && isCellValid(k, l - (DOTS_TO_WIN - 1))) {
                                dot[0] = k;
                                dot[1] = l - (DOTS_TO_WIN - 1);
                                break;
                            }

                            vertical = (map[l][k] == sym && vertical < DOTS_TO_WIN - 1) ? ++vertical : (vertical == 0) ? vertical : --vertical;

                            if (vertical == DOTS_TO_WIN - 1 && isCellValid(l + 1, k)) {
                                dot[0] = l + 1;
                                dot[1] = k;
                                break;
                            } else if (vertical == DOTS_TO_WIN - 1 && isCellValid(l - DOTS_TO_WIN - 1, k - DOTS_TO_WIN - 1)) {
                                dot[0] = l - (DOTS_TO_WIN - 1);
                                dot[1] = k;
                                break;
                            }
                        }
                        vertical = (vertical < DOTS_TO_WIN - 1 || (vertical == DOTS_TO_WIN - 1 && !isCellValid(dot[0], dot[1]))) ? 0 : vertical;
                        horizontal = (horizontal < DOTS_TO_WIN - 1 || (horizontal == DOTS_TO_WIN - 1 && !isCellValid(dot[0], dot[1]))) ? 0 : horizontal;
                    }
                }
                diagonalLeftToRight = (diagonalLeftToRight < DOTS_TO_WIN - 1 || (diagonalLeftToRight == DOTS_TO_WIN - 1 && !isCellValid(dot[0], dot[1]))) ? 0 : diagonalLeftToRight;
                diagonalRightToLeft = (diagonalRightToLeft < DOTS_TO_WIN - 1 || (diagonalRightToLeft == DOTS_TO_WIN - 1 && !isCellValid(dot[0], dot[1]))) ? 0 : diagonalRightToLeft;
            }
        }
        return dot;
    }

    /**
     * Проверка выигрыша
     *
     * @param sym символ для проверки победы
     * @return
     */
    public static boolean checkWin(char sym) {

        int vertical = 0;
        int horizontal = 0;
        int diagonalLeftToRight = 0;
        int diagonalRightToLeft = 0;

        for (int i = 0; i < SIZE - DOTS_TO_WIN + 1; i++) {
            for (int j = 0; j < SIZE - DOTS_TO_WIN + 1; j++) {
                for (int k = 0; k < DOTS_TO_WIN; k++) {
                    if (diagonalLeftToRight == DOTS_TO_WIN || diagonalRightToLeft == DOTS_TO_WIN || vertical == DOTS_TO_WIN || horizontal == DOTS_TO_WIN) {
                        break;
                    } else {
                        diagonalLeftToRight = (map[k + i][k + j] == sym && diagonalLeftToRight < DOTS_TO_WIN) ? ++diagonalLeftToRight : --diagonalLeftToRight; //diagonalLeftToRight + changeCount(diagonalLeftToRight, map[k + i][k + j], sym);
                        diagonalRightToLeft = (map[DOTS_TO_WIN - k - 1 + i][k + j] == sym && diagonalRightToLeft < DOTS_TO_WIN) ? ++diagonalRightToLeft : --diagonalRightToLeft; //diagonalRightToLeft + changeCount(diagonalRightToLeft, map[DOTS_TO_WIN - k - 1 + i][k + j], sym);

                        for (int l = 0; l < DOTS_TO_WIN; l++) {
                            horizontal = (map[k][l] == sym && horizontal < DOTS_TO_WIN) ? ++horizontal : --horizontal; //horizontal + changeCount(horizontal, map[k][l], sym);
                            vertical = (map[l][k] == sym && vertical < DOTS_TO_WIN) ? ++vertical : --vertical; //vertical + changeCount(vertical, map[l][k], sym);
                        }
                        vertical = (vertical < DOTS_TO_WIN) ? 0 : vertical;//countZeroing(vertical);
                        horizontal = (horizontal < DOTS_TO_WIN) ? 0 : horizontal;//countZeroing(horizontal);
                    }
                }
                diagonalLeftToRight = (diagonalLeftToRight < DOTS_TO_WIN) ? 0 : diagonalLeftToRight;//countZeroing(diagonalLeftToRight);
                diagonalRightToLeft = (diagonalRightToLeft < DOTS_TO_WIN) ? 0 : diagonalRightToLeft;//countZeroing(diagonalRightToLeft);
            }
        }
        return (diagonalLeftToRight == DOTS_TO_WIN || diagonalRightToLeft == DOTS_TO_WIN || vertical == DOTS_TO_WIN || horizontal == DOTS_TO_WIN);
    }

    /**
     * Инициализация поля
     */
    public static void initMap() {
        map = new char[][]{{'.', 'X', '.'}, {'O', 'X', '.'}, {'.', '0', '.'}};
//        map = new char[][] {{'X', 'X', 'O', '.'}, {'X', '.', '.', '.'}, {'0', '.', '.','0'}, {'.', '.', '.', '.'}};
//        map = new char[SIZE][SIZE];
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE; j++) {
//                map[i][j] = DOT_EMPTY;
//            }
//        }
    }

    /**
     * Печать карты
     */
    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Проверка заполненности поля
     *
     * @return
     */
    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Ход человека
     */
    public static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[x][y] = DOT_X;
    }

    /**
     * Проверка того, что координаты точки внутри заданного поля
     * и поле не занято знаком игрока
     *
     * @param x
     * @param y
     * @return
     */
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        return map[x][y] == DOT_EMPTY;
    }

}
