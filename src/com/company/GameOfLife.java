package com.company;

import java.util.Random;

public class GameOfLife {
    private boolean[][] matrix;

    public GameOfLife(int d) {
        this.matrix = new boolean[d][d];
    }

    public void setCellAlive(int i, int j) {
        matrix[i][j] = true;
    }

    public void setCellDead(int i, int j) {
        matrix[i][j] = false;
    }

    public void show() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print((matrix[i][j] ? "X" : ".") + " ");
            }
            System.out.print("\n");
        }
        for (int i = 0; i < matrix.length; i++){
            System.out.print("==");
        }
        System.out.print("\n");
    }

    public boolean[][] copy(boolean[][] matrix){
        boolean[][] newMatrix = new boolean[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                if (matrix[i][j]){
                    newMatrix[i][j] = true;
                }
            }
        }
        return newMatrix;
    }

    public void execCycle() {
        boolean[][] oldMatrix = copy(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int n = countNeighbours(i, j, oldMatrix);
                if (oldMatrix[i][j]) {
                    if (n < 2){
                        // dies alone
                        setCellDead(i, j);
                    } else if (n > 3){
                        // dies of overpopulation
                        setCellDead(i, j);
                    }
                }
                else{
                    if (n == 3){
                        setCellAlive(i, j);
                    }
                }
            }
        }
    }

    public int countNeighbours(int i, int j, boolean[][] m) {
        boolean wallLeft = j == 0;
        boolean wallRight = j == matrix.length - 1;
        boolean wallUp = i == 0;
        boolean wallDown = i == matrix.length - 1;

        int sum = 0;

        // check left
        if (!wallLeft) {
            if (m[i][j - 1]) sum++;

            // check top left
            if (!wallUp) {
                if (m[i - 1][j - 1]) sum++;
            }

            // check bottom left
            if (!wallDown) {
                if (m[i + 1][j - 1]) sum++;
            }
        }

        // check right
        if (!wallRight){
            if (m[i][j + 1]) sum++;

            // check top right
            if (!wallUp){
                if (m[i - 1][j + 1]) sum++;
            }

            // check bottom right
            if (!wallDown){
                if (m[i + 1][j + 1]) sum++;
            }
        }

        // check top
        if (!wallUp){
            if (m[i - 1][j]) sum++;
        }

        // check down
        if (!wallDown){
            if (m[i + 1][j]) sum++;
        }

        return sum;
    }

    public void populateRandom(int n){
        Random r = new Random();
        int alive = 0;
        while (alive < n) {
            int i = r.nextInt(matrix.length);
            int j = r.nextInt(matrix.length);
            if (!matrix[i][j]){
                setCellAlive(i, j);
                alive++;
            }
        }
    }

    public void placePentaDecathlon(){
        int middle = matrix.length / 2;
        for (int i = middle - 4; i < middle + 4; i++){
            setCellAlive(i, middle - 1);
            setCellAlive(i, middle + 1);
            setCellAlive(i, middle);
        }
        setCellDead(middle - 3, middle);
        setCellDead(middle + 2, middle);
    }
}
