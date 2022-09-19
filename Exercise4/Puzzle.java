class Puzzle {
    private final int n;
    private final Grid2D<Integer> grid2d;

    Puzzle(int n) {
        this.n = n;
        ImList<Integer> newList = new ImList<Integer>();
        for (int i = 0; i < n * n - 1; i++) {
            newList = newList.add(i + 1);
        }
        newList = newList.add(0);
        this.grid2d = new Grid2D<Integer>(newList, n);
    }

    Puzzle(Grid2D<Integer> grid, int n) {
        this.n = n;
        this.grid2d = grid;
    }

    public int getCols() {
        return this.n;
    }

    public Grid2D<Integer> getGrid() {
        return this.grid2d;
    }

    public String toString() {
        String result = "\n";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.grid2d.get(i, j) == 0) {
                    result = result + ".";
                } else {
                    result = result + this.grid2d.get(i, j);
                }
                if ((j + 1) % n == 0) {
                    result = result + "\n";
                } else {
                    result = result + " ";
                }
            }
        }
        return result;
    }

    public Puzzle move(int num) {
        Grid2D<Integer> theGrid = this.getGrid();
        int cols = this.getCols();
        int numX = 0;
        int numY = 0;
        int dotX = 0;
        int dotY = 0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < cols; j++) {
                if (theGrid.get(i, j) == num) {
                    numX = i;
                    numY = j;
                } else if (theGrid.get(i, j) == 0) {
                    dotX = i;
                    dotY = j;
                } 
            }
        }
        if ((numX == dotX && numY - dotY == -1) || (numX == dotX && numY - dotY == 1) ||
            (numY == dotY && numX - dotX == -1) || (numY == dotY && numX - dotX == 1)) {
            theGrid = theGrid.set(numX, numY, 0).set(dotX, dotY, num);
        } 
        return new Puzzle(theGrid, this.getCols());
    }

    public boolean isSolved() {
        Puzzle puzzleToCompare = new Puzzle(this.getCols());
        String toCompare = puzzleToCompare.toString();
        String own = this.toString();
        return own.equals(toCompare);
    }
}
