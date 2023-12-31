package org.example.GUI;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {

    public static int ROWS;
    public static int COLUMNS;
    public static boolean[][] board;
    public static int ITERATIONS;

    public Board(int rows, int columns, int size, int[][] coordinates, int iterations)  {

        board = new boolean[rows][columns];
        ITERATIONS = iterations;
        ROWS = rows;
        COLUMNS = columns;
        setTitle("Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);

        JPanel mainPanel = new JPanel(new GridLayout(ROWS, COLUMNS));

        for(int i = 0; i < rows; i++)   {
            for(int j = 0; j < columns; j++)    {
                board[i][j] = false;
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                button.addActionListener(new CellClickListener(i,j, button, board));
                button.setEnabled(false);       //click! option
                mainPanel.add(button);
            }
        }

        add(mainPanel);

        for (int i = 0; i < size; i++)  {       //sett coordinates
            Container container = getContentPane();
            board[coordinates[i][0]][coordinates[i][1]] = true;
            JButton button = (JButton) ((JPanel) container.getComponent(0)).getComponent(coordinates[i][0] * COLUMNS + coordinates[i][1]);
            button.setBackground(Color.BLACK);
        }
    }

    public synchronized void nextBoard(boolean[][] newBoard, int startColumn, int endColumn, Color color)    {

        for (int column = startColumn; column <= endColumn; column++)  {
            for(int row = 0; row <= Board.ROWS - 1; row++) {
                    Container container = getContentPane();
                    board[row][column] = newBoard[row][column];
                    JButton button = (JButton) ((JPanel) container.getComponent(0)).getComponent(row * COLUMNS + column);
                    button.setBackground(board[row][column] ? color : Color.WHITE);
            }
        }
    }


}
