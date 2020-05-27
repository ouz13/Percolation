import java.util.Random;

public class percolation {
    private int[][] grid;

    public percolation(int n) {
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void drawGrid(int n) {
        grid = getGrid();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    if (j == n - 1) {
                        System.out.println("X ");
                    } else {
                        System.out.print("X ");
                    }
                } else {
                    if (j == n - 1) {
                        System.out.println("_ ");
                    } else {
                        System.out.print("_ ");
                    }
                }
            }
        }
    }

    public void openSite(int n, int row, int col) {
        grid = getGrid();
        int n1 = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int adjCount = 0;
        grid[row][col] = row * 10 + col;
        if (row == 0) {
            if (col == 0) {
                n1 = grid[0][1];
                n2 = grid[1][0];
            } else if (col == n - 1) {
                n1 = grid[0][n - 2];
                n2 = grid[1][n - 1];
            } else {
                n1 = grid[0][col - 1];
                n2 = grid[0][col + 1];
                n3 = grid[1][col];
            }

        } else if (row == n - 1) {
            if (col == 0) {
                n1 = grid[n - 1][1];
                n2 = grid[n - 2][0];
            } else if (col == n - 1) {
                n1 = grid[n - 1][n - 2];
                n2 = grid[n - 2][n - 1];
            } else {
                n1 = grid[n - 1][col - 1];
                n2 = grid[n - 1][col + 1];
                n3 = grid[n - 2][col];
            }
        } else {
            if (col == 0) {
                n1 = grid[row][1];
                n2 = grid[row - 1][0];
                n2 = grid[row + 1][0];
            } else if (col == n - 1) {
                n1 = grid[row][n - 2];
                n2 = grid[row - 1][n - 1];
                n3 = grid[row + 1][n - 1];
            } else {
                n1 = grid[row][col - 1];
                n2 = grid[row][col + 1];
                n3 = grid[row - 1][col];
                n4 = grid[row + 1][col];
            }
        }

        if (n1 == 0 && n2 == 0 && n3 == 0 && n4 == 0) {
            grid[row][col] = row * 10 + col;
        }

        if (n1 != 0) {
            adjCount++;
        }
        if (n2 != 0) {
            adjCount++;
        }
        if (n3 != 0) {
            adjCount++;
        }
        if (n4 != 0) {
            adjCount++;
        }

        if (adjCount == 1) {
            if (n1 != 0) {
                grid[row][col] = n1;
            }
            if (n2 != 0) {
                grid[row][col] = n2;
            }
            if (n3 != 0) {
                grid[row][col] = n3;
            }
            if (n4 != 0) {
                grid[row][col] = n4;
            }
        }
        if (adjCount > 1) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((n1 != 0) && grid[i][j] == n1) {
                        grid[i][j] = grid[row][col];
                    }
                    if ((n2 != 0) && grid[i][j] == n2) {
                        grid[i][j] = grid[row][col];
                    }
                    if ((n3 != 0) && grid[i][j] == n3) {
                        grid[i][j] = grid[row][col];
                    }
                    if ((n4 != 0) && grid[i][j] == n4) {
                        grid[i][j] = grid[row][col];
                    }
                }
            }
        }


    }


    public boolean isOpen(int row, int col) {
        grid = getGrid();
        if (grid[row][col] == 0) {
            return false;
        } else {
            return true;
        }
    }

    public int numberOfOpenSites(int n) {
        grid = getGrid();
        int noSites = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {

                } else {
                    noSites++;
                }
            }
        }
        return noSites;
    }

    public boolean percolates(int n, int row, int col) {
        grid = getGrid();
        if (!isOpen(row, col)) {
            openSite(n, row, col);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((grid[0][i] != 0 && grid[n - 1][j] != 0) && (grid[0][i] == grid[n - 1][j])) {
                    return true;
                }
            }
        }
        return false;
    }


    public int[][] getGrid() {
        return grid;
    }


    public static void main(String[] args) {
        double total = 0;
        double mean = 0;
        double probability = 0;
        int grid_size = 10;
        int number_of_trials = 10000;
        for (int i = 0; i < number_of_trials; i++) {
            percolation pc1 = new percolation(grid_size);
            Random rand = new Random();
            int n1 = 0;
            int n2 = 0;
            int counter = 0;
            while (!pc1.percolates(grid_size, n1, n2)) {
                n1 = rand.nextInt(grid_size);
                n2 = rand.nextInt(grid_size);
                pc1.percolates(grid_size, n1, n2);
                counter++;
                if (counter == 15000) {
                    break;
                }

            }
            total = total + pc1.numberOfOpenSites(grid_size);
        }
        mean = total / number_of_trials;
        probability = mean / (grid_size * grid_size);
        System.out.println("mean = " + mean);

    }

}
