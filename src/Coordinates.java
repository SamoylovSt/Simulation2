import java.util.Objects;

public class Coordinates {
public final Integer COLUMN;
public final Integer ROW;

    public Coordinates(Integer COLUMN, Integer ROW) {
        this.COLUMN = COLUMN; //row
        this.ROW = ROW; //column
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(COLUMN, that.COLUMN) && Objects.equals(ROW, that.ROW);
    }

    @Override
    public int hashCode() {
        return Objects.hash(COLUMN, ROW);
    }
//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        Coordinates that = (Coordinates) o;
//        return Objects.equals(COLUMN, that.COLUMN) && Objects.equals(ROW, that.ROW);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(COLUMN, ROW);
//    }
}
