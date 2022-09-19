import java.util.List;

class Grid2D<E> {
    private final ImList<E> list;
    private final int numOfCols;

    Grid2D(List<E> theList, int numOfCols) {
        int len = theList.size();
        if (len > 0) {
            ImList<E> newList = new ImList<E>();
            for (int i = 0; i < len; i++) {
                newList = newList.add(theList.get(i));
            }
            this.list = newList;
        } else {
            this.list = new ImList<E>();
        }
        this.numOfCols = numOfCols;
    }

    Grid2D(ImList<E> theImList, int numOfCols) {
        this.list = theImList;
        this.numOfCols = numOfCols;
    }

    Grid2D(int numOfCols) {
        this.list = new ImList<E>();
        this.numOfCols = numOfCols;
    }

    public ImList<E> getList() {
        return this.list;
    }

    public int getNumOfCols() {
        return this.numOfCols;
    }

    public String toString() {
        int len = this.list.size();
        if (len == 0) {
            return "{}";
        } else {
            String result = "{";
            for (int i = 0; i < len - 1; i++) {
                result = result + list.get(i);
                if ((i + 1) % numOfCols == 0) {
                    result = result + ";";
                } else {
                    result = result + ",";
                }
            }
            result = result + list.get(len - 1);
            result = result + "}";
            return result;
        }
    }

    public Grid2D<E> add(E elem) {
        ImList<E> theList = this.getList();
        theList = theList.add(elem);
        return new Grid2D<E>(theList, numOfCols);
    }

    public E get(int r, int c) {
        int index = r * this.getNumOfCols() + c;
        return this.getList().get(index);
    }

    public Grid2D<E> set(int r, int c, E elem) {
        ImList<E> theList = this.getList();
        int index = r * this.getNumOfCols() + c;
        theList = theList.set(index, elem);
        return new Grid2D<E>(theList, numOfCols);
    }
}
