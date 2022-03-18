package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FrameApp extends JFrame {
    private final String TITLE_confirm = "Окно подтверждения";
    public static final int SIZE = 3;
    public static final int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = ' ';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    public FrameApp() {
        setBounds(300, 300, 100 * SIZE, 100 * SIZE);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));
        JButton[][] buttons = new JButton[SIZE][SIZE];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton(String.valueOf(DOT_EMPTY));
                add(buttons[i][j]);
            }
        }

        final int[] countFreeCell = {SIZE * SIZE};
        for (int i = 0; i < buttons.length; i++) {
            int finalI = i;
            for (int j = 0; j < buttons[i].length; j++) {
                int finalJ = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isCellValid(buttons, finalI, finalJ)) {
                            buttons[finalI][finalJ].setText(String.valueOf(DOT_X));
                            countFreeCell[0]--;
                            //проверка на выигрыш человека
                            if (checkWin(DOT_X, buttons)) {
                                int input = JOptionPane.showConfirmDialog(FrameApp.this, "Вы выиграли! Играем ещё?", TITLE_confirm, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                                choiceOnJOptionPane(input, buttons);
                            } else if (isMapFull(buttons)) {
                                int input = JOptionPane.showConfirmDialog(FrameApp.this, "Ничья! Играем ещё?", TITLE_confirm, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                                choiceOnJOptionPane(input, buttons);
                            }
                            //проверка на выигрыш AI или человека
                            int[] dot;
                            do {
                                dot = winDot(DOT_O, buttons);
                                if (isCellValid(buttons, dot[0], dot[1])) {
                                    buttons[dot[0]][dot[1]].setText(String.valueOf(DOT_O));
                                } else {
                                    dot = winDot(DOT_X, buttons);
                                    if (isCellValid(buttons, dot[0], dot[1])) {
                                        buttons[dot[0]][dot[1]].setText(String.valueOf(DOT_O));
                                    } else {
                                        dot[0] = new Random().nextInt(buttons.length);
                                        dot[1] = new Random().nextInt(buttons[finalI].length);
                                        if (isCellValid(buttons, dot[0], dot[1])) {
                                            buttons[dot[0]][dot[1]].setText(String.valueOf(DOT_O));
                                        }
                                    }
                                }
                            } while (buttons[dot[0]][dot[1]].equals(Character.toString(DOT_O))/*!isCellValid(buttons,dot[0],dot[1])*/ /*&& countFreeCell[0] != 0*/);
                            countFreeCell[0]--;
                            if (checkWin(DOT_O, buttons)) {
                                int input = JOptionPane.showConfirmDialog(FrameApp.this, "Выиграл компьютер! Играем ещё?", TITLE_confirm, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                                choiceOnJOptionPane(input, buttons);
                            } else if (isMapFull(buttons)) {
                                int input = JOptionPane.showConfirmDialog(FrameApp.this, "Ничья! Играем ещё?", TITLE_confirm, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                                choiceOnJOptionPane(input, buttons);
                            }
                        }
                    }
                });
            }
        }
        setVisible(true);
    }

    public static void main(String[] args) {
        new FrameApp();
    }

    private void choiceOnJOptionPane(int choiceOnJOptionPane, JButton[][] buttons) {
        switch (choiceOnJOptionPane) {
            case 0:
                for (int i = 0; i < buttons.length; i++) {
                    for (int j = 0; j < buttons[i].length; j++) {
                        buttons[i][j].setText(String.valueOf(DOT_EMPTY));
                    }
                }
                break;

            case 1:
                FrameApp.this.dispose();
                break;
        }
    }

    /**
     * Проверка заполненности поля
     *
     * @return
     */
    private static boolean isMapFull(JButton[][] buttons) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (buttons[i][j].getText().equals(Character.toString(DOT_EMPTY))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Проверка того, что координаты точки внутри заданного поля
     * и поле не занято знаком игрока
     *
     * @return
     */
    private static boolean isCellValid(JButton[][] buttons, int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        return buttons[x][y].getText().equals(Character.toString(DOT_EMPTY));
    }

    /**
     * Проверка выигрыша
     *
     * @param sym символ для проверки победы
     * @return
     */
    private static boolean checkWin(char sym, JButton[][] buttons) {

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
                        diagonalLeftToRight = (buttons[k + i][k + j].getText().equals(Character.toString(sym)) && diagonalLeftToRight < DOTS_TO_WIN) ? ++diagonalLeftToRight : --diagonalLeftToRight;
                        diagonalRightToLeft = (buttons[DOTS_TO_WIN - k - 1 + i][k + j].getText().equals(Character.toString(sym)) && diagonalRightToLeft < DOTS_TO_WIN) ? ++diagonalRightToLeft : --diagonalRightToLeft;

                        for (int l = 0; l < DOTS_TO_WIN; l++) {
                            horizontal = (buttons[k][l].getText().equals(Character.toString(sym)) && horizontal < DOTS_TO_WIN) ? ++horizontal : --horizontal;
                            vertical = (buttons[l][k].getText().equals(Character.toString(sym)) && vertical < DOTS_TO_WIN) ? ++vertical : --vertical;
                        }
                        vertical = (vertical < DOTS_TO_WIN) ? 0 : vertical;
                        horizontal = (horizontal < DOTS_TO_WIN) ? 0 : horizontal;
                    }
                }
                diagonalLeftToRight = (diagonalLeftToRight < DOTS_TO_WIN) ? 0 : diagonalLeftToRight;
                diagonalRightToLeft = (diagonalRightToLeft < DOTS_TO_WIN) ? 0 : diagonalRightToLeft;
            }
        }
        return (diagonalLeftToRight == DOTS_TO_WIN || diagonalRightToLeft == DOTS_TO_WIN || vertical == DOTS_TO_WIN || horizontal == DOTS_TO_WIN);
    }

    /**
     * Проверка возможного выигрыша на следующем шагу
     *
     * @param sym
     * @param buttons
     * @return
     */
    private static int[] winDot(char sym, JButton[][] buttons) {
        int vertical = 0;
        int horizontal = 0;
        int diagonalLeftToRight = 0;
        int diagonalRightToLeft = 0;
        int[] dot = {-1, -1};

        for (int i = 0; i < SIZE - DOTS_TO_WIN + 2; i++) {
            for (int j = 0; j < SIZE - DOTS_TO_WIN + 2; j++) {
                for (int k = 0; k < DOTS_TO_WIN - 1; k++) {
                    if (diagonalLeftToRight == DOTS_TO_WIN - 1 || diagonalRightToLeft == DOTS_TO_WIN - 1 || vertical == DOTS_TO_WIN - 1 || horizontal == DOTS_TO_WIN - 1) {
                        break;
                    } else {
                        diagonalLeftToRight = (buttons[k + i][k + j].getText().equals(Character.toString(sym)) && diagonalLeftToRight < DOTS_TO_WIN - 1) ? ++diagonalLeftToRight : (diagonalLeftToRight == 0) ? diagonalLeftToRight : --diagonalLeftToRight;

                        if (diagonalLeftToRight == DOTS_TO_WIN - 1 && isCellValid(buttons, k + i + 1, k + j + 1)) {
                            dot[0] = k + i + 1;
                            dot[1] = k + j + 1;
                            break;
                        } else if (diagonalLeftToRight == DOTS_TO_WIN - 1 && isCellValid(buttons, k + i - (DOTS_TO_WIN - 1), k + j - (DOTS_TO_WIN - 1))) {
                            dot[0] = k + i - (DOTS_TO_WIN - 1);
                            dot[1] = k + j - (DOTS_TO_WIN - 1);
                            break;
                        }

                        diagonalRightToLeft = (buttons[DOTS_TO_WIN - 1 - k - 1 + i][k + j].getText().equals(Character.toString(sym)) && diagonalRightToLeft < DOTS_TO_WIN - 1) ? ++diagonalRightToLeft : (diagonalRightToLeft == 0) ? diagonalRightToLeft : --diagonalRightToLeft;

                        if (diagonalRightToLeft == DOTS_TO_WIN - 1 && isCellValid(buttons, DOTS_TO_WIN - 1 - k - 1 + i - 1, k + j + 1)) {
                            dot[0] = DOTS_TO_WIN - 1 - k - 1 + i - 1;
                            dot[1] = k + j + 1;
                            break;
                        } else if (diagonalRightToLeft == DOTS_TO_WIN - 1 && isCellValid(buttons, DOTS_TO_WIN - 1 - k - 1 + i + DOTS_TO_WIN - 1, k + j - (DOTS_TO_WIN - 1))) {
                            dot[0] = DOTS_TO_WIN - 1 - k - 1 + i + DOTS_TO_WIN - 1;
                            dot[1] = k + j - (DOTS_TO_WIN - 1);
                            break;
                        }
                        for (int l = 0; l < DOTS_TO_WIN - 1; l++) {
                            horizontal = (buttons[k][l].getText().equals(Character.toString(sym)) && horizontal < DOTS_TO_WIN - 1) ? ++horizontal : (horizontal == 0) ? horizontal : --horizontal;

                            if (isCellValid(buttons, k, l + 1) && horizontal == DOTS_TO_WIN - 1) {
                                dot[0] = k;
                                dot[1] = l + 1;
                                break;
                            } else if (isCellValid(buttons, k, l - (DOTS_TO_WIN - 1)) && horizontal == DOTS_TO_WIN - 1) {
                                dot[0] = k;
                                dot[1] = l - (DOTS_TO_WIN - 1);
                                break;
                            }

                            vertical = (buttons[l][k].getText().equals(Character.toString(sym)) && vertical < DOTS_TO_WIN - 1) ? ++vertical : (vertical == 0) ? vertical : --vertical;

                            if (isCellValid(buttons, l + 1, k) && vertical == DOTS_TO_WIN - 1) {
                                dot[0] = l + 1;
                                dot[1] = k;
                                break;
                            } else if (isCellValid(buttons, l - DOTS_TO_WIN - 1, k - DOTS_TO_WIN - 1) && vertical == DOTS_TO_WIN - 1) {
                                dot[0] = l - (DOTS_TO_WIN - 1);
                                dot[1] = k;
                                break;
                            }
                        }
                        vertical = (vertical < DOTS_TO_WIN - 1 || (vertical == DOTS_TO_WIN - 1 && !isCellValid(buttons, dot[0], dot[1]))) ? 0 : vertical;
                        horizontal = (horizontal < DOTS_TO_WIN - 1 || (horizontal == DOTS_TO_WIN - 1 && !isCellValid(buttons, dot[0], dot[1]))) ? 0 : horizontal;
                    }
                }
                diagonalLeftToRight = (diagonalLeftToRight < DOTS_TO_WIN - 1 || (diagonalLeftToRight == DOTS_TO_WIN - 1 && !isCellValid(buttons, dot[0], dot[1]))) ? 0 : diagonalLeftToRight;
                diagonalRightToLeft = (diagonalRightToLeft < DOTS_TO_WIN - 1 || (diagonalRightToLeft == DOTS_TO_WIN - 1 && !isCellValid(buttons, dot[0], dot[1]))) ? 0 : diagonalRightToLeft;
            }
        }
        return dot;
    }
}
