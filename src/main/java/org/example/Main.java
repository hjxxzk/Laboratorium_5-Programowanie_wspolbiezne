package org.example;

import org.example.GUI.Board;

import javax.swing.*;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {

        int THREADS = Integer.parseInt(args[1]);
        CyclicBarrier startBarrier = new CyclicBarrier(THREADS);
        CyclicBarrier endBarrier = new CyclicBarrier(THREADS);

        SwingUtilities.invokeLater(() -> {
            Board board = TXTFileReader.raedFile(args[0], THREADS);
            assert board != null;
            board.setVisible(true);

            System.out.println("# " + THREADS +  " threads, column - based partitioning");

            int excess = Board.COLUMNS % THREADS;
            int columnsPerThread = Board.COLUMNS / THREADS;
            int startingColumn = 0;

            for (int threadID = 0; threadID < THREADS; threadID++)   { //tu była zmiana

                int endingColumn = startingColumn + columnsPerThread - 1;

                if(excess != 0) {
                    endingColumn += 1;
                    excess -= 1;
                }

                Thread thread = new Thread(new GameOfLifeWorker(startingColumn, endingColumn, startBarrier, endBarrier, threadID, board));
                thread.start();

                startingColumn = endingColumn + 1;
            }
        });

    }

}