import java.util.ArrayDeque;
import java.util.HashSet;

public class Herbivore extends Creature {
    private String color = "\uD83D\uDC30";
    private Coordinates coordinates;

    @Override
    public String getColor() {

        return this.color;
    }

    @Override
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Herbivore(Coordinates coordinates) {
//        super();
        this.coordinates = coordinates;
        this.color = "\uD83D\uDC30";
    }

    public void eatGrass(Coordinates targetCoordinates, Coordinates herbCoordinates, Map map) {
        if (herbCoordinates.equals(targetCoordinates) && map.getEntyty(targetCoordinates) instanceof Grass) {

            map.deleteEntyty(targetCoordinates);
        }

    }



    public Coordinates makeMove(Herbivore herbivore, Coordinates targetCoordinates, Map map) {//
        int dx = (int) Math.signum(targetCoordinates.COLUMN - herbivore.coordinates.COLUMN);
        int dy = (int) Math.signum(targetCoordinates.ROW - herbivore.coordinates.ROW);

        Entyty nextTurnentyty = map.getEntyty(new Coordinates(herbivore.coordinates.COLUMN + dx, herbivore.coordinates.ROW + dy));


// это стоп при границе карты
        if (herbivore.coordinates.COLUMN +dx>20){
            dx=0;
        }
        if (herbivore.coordinates.COLUMN +dx<1){
            dx=0;
        }
        if (herbivore.coordinates.ROW +dy>20){
            dy=0;
        }
        if (herbivore.coordinates.ROW +dy<1){
            dy=0;
        }
        // разбить на два метода
        // это обход
            if (nextTurnentyty instanceof Rock ) {//
                if ( nextTurnentyty.getCoordinates().COLUMN > herbivore.coordinates.COLUMN) {
                    return herbivore.coordinates = new Coordinates(herbivore.coordinates.COLUMN, herbivore.coordinates.ROW + 1);
                }
                if ( nextTurnentyty.getCoordinates().ROW > herbivore.coordinates.ROW) {
                    return herbivore.coordinates = new Coordinates(herbivore.coordinates.COLUMN + 1, herbivore.coordinates.ROW);
                }
                if ( nextTurnentyty.getCoordinates().ROW < herbivore.coordinates.ROW) {
                    return herbivore.coordinates = new Coordinates(herbivore.coordinates.COLUMN - 1, herbivore.coordinates.ROW);
                }
                if ( nextTurnentyty.getCoordinates().COLUMN < herbivore.coordinates.COLUMN) {
                    return herbivore.coordinates = new Coordinates(herbivore.coordinates.COLUMN -1, herbivore.coordinates.ROW + 1);
                }

            }
        return herbivore.coordinates = new Coordinates(herbivore.coordinates.COLUMN + dx, herbivore.coordinates.ROW + dy);
    }




}

