package DS;
public class Knight {
    public final static int SIZE = 5;

    public boolean[][] board = new boolean[SIZE][SIZE];
    public String[] moves = new String[SIZE * SIZE];
    private int max = 0;

    public Knight() {

        for (int i = 0; i < 5; i++ ) {
            for (int j = 0; j < 5; j++ ) {
                board[i][j] = false;
            }
        }

    }

    public boolean tour(final int x, final int y, final int n)
    {
       
        if (n >= SIZE * SIZE) {
            return true;
        } else if (x >= 5 || y >= 5 || x < 0 || y < 0) { // out of bounds
            return false;
        } else if (board[x][y]) {
            return false;
        } else {
            
            // System.out.println("Checking " + n + ": " + x + "," + y);
            board[x][y] = true;
            moves[n] = x + "-" + y;
            if (tour(x + 2, y + 1, n + 1)) {
                return true;
            }
            if (tour(x + 2, y - 1, n + 1)) {
                return true;
            }
            if (tour(x + 1, y - 2, n + 1)) {
                return true;
            }
            if (tour(x - 1, y - 2, n + 1)) {
                return true;
            }
            if (tour(x + 1, y + 2, n + 1)) {
                return true;
            }
            if (tour(x - 1, y + 2, n + 1)) {
                return true;
            }
            if (tour(x - 2, y - 1, n + 1)) {
                return true;
            }
            if (tour(x - 2, y + 1, n + 1)) {
                return true;
            }
            board[x][y] = false;
            moves[n] = null;
            return false;
        }
    }

    public static void main(final String[] args) {
        final Knight k = new Knight();

        for (int i = 0; i < SIZE; i++ ) {
            System.out.println("Starting at " + i + " 0");

            if (k.tour(i, 0, 0)) {
                k.printTour(true);
                break;
            }
            //k.printTour(true);
        }
    }

    /**
     * @param result
     */
    private void printTour(final boolean result) {
        System.out.println("Found tour: " + result);
        int i = 0;
        if (result) {
            for (final String m : moves) {
                System.out.println("M-" + i + ": " + moves[i]);
                i++ ;
            }
        }
    }
}