import java.util.HashSet;

public class Predator extends Creature {
    private final String color;
    private Coordinates coordinates;


    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Predator(Coordinates coordinates) {//, String color
        this.coordinates = coordinates;
        this.color = "\uD83D\uDC3A";
    }

    public void eatHerbivore(Coordinates targetCoordinates, Coordinates predatorCoordinates, Map map, HashSet<Herbivore> rabits) {
        if (predatorCoordinates.equals(targetCoordinates) && map.getEntyty(targetCoordinates) instanceof Herbivore) {
            rabits.remove(map.getEntyty(targetCoordinates));
            map.deleteEntyty(targetCoordinates);
        }

    }


    public void makeMove(Predator predator, Coordinates targetCoordinates, Map map) {//

        int dx = (int) Math.signum(targetCoordinates.COLUMN - predator.coordinates.COLUMN);
        int dy = (int) Math.signum(targetCoordinates.ROW - predator.coordinates.ROW);

        Entyty nextTurnentyty = map.getEntyty(new Coordinates(predator.coordinates.COLUMN + dx, predator.coordinates.ROW + dy));


        if (predator.coordinates.COLUMN + dx > 15) {
            dx = 0;
        }
        if (predator.coordinates.COLUMN + dx < 1) {
            dx = 0;
        }
        if (predator.coordinates.ROW + dy > 15) {
            dy = 0;
        }
        if (predator.coordinates.ROW + dy < 1) {
            dy = 0;
        }

        if (nextTurnentyty instanceof Rock || nextTurnentyty instanceof Predator | nextTurnentyty instanceof Grass) {//
            if (nextTurnentyty.getCoordinates().COLUMN > predator.coordinates.COLUMN) {

                predator.coordinates = new Coordinates(predator.coordinates.COLUMN, predator.coordinates.ROW + 1);
            }
            if (nextTurnentyty.getCoordinates().ROW > predator.coordinates.ROW) {
                predator.coordinates = new Coordinates(predator.coordinates.COLUMN - 1, predator.coordinates.ROW);
            }
            if (nextTurnentyty.getCoordinates().ROW < predator.coordinates.ROW) {
                predator.coordinates = new Coordinates(predator.coordinates.COLUMN - 1, predator.coordinates.ROW); // тут изменял
            }
            if (nextTurnentyty.getCoordinates().COLUMN < predator.coordinates.COLUMN) {
                predator.coordinates = new Coordinates(predator.coordinates.COLUMN - 1, predator.coordinates.ROW + 1);
            }

        }
        predator.coordinates = new Coordinates(predator.coordinates.COLUMN + dx, predator.coordinates.ROW + dy);
    }
}
