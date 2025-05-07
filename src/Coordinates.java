import java.util.Objects;

public class Coordinates {
    private final Integer COLUMN;
    private final Integer ROW;

    public Coordinates(Integer COLUMN, Integer ROW) {
        this.COLUMN = COLUMN;
        this.ROW = ROW;
    }

    public Integer getCOLUMN() {
        return COLUMN;
    }

    public  Integer getROW(){
        return ROW;
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

}
