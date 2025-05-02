import java.util.HashSet;

public class Predator extends Creature {
    private String color ;
    private Coordinates coordinates;


    @Override
    public String getColor() {
        // super.getColor();
        return this.color;
    }

    @Override
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Predator(Coordinates coordinates) {//, String color
        //super(color, coordinates);
        this.coordinates = coordinates;
        this.color = "\uD83D\uDC3A";
    }

    public void eatHerbivore(Coordinates targetCoordinates, Coordinates predatorCoordinates, Map map, HashSet<Herbivore> rabits) {
        if (predatorCoordinates.equals(targetCoordinates) && map.getEntyty(targetCoordinates) instanceof Herbivore) {
                rabits.remove(map.getEntyty(targetCoordinates));
            map.deleteEntyty(targetCoordinates);
        }

    }



    public Coordinates makeMove(Predator predator, Coordinates targetCoordinates, Map map) {//

        int dx = (int) Math.signum(targetCoordinates.COLUMN - predator.coordinates.COLUMN);
        int dy = (int) Math.signum(targetCoordinates.ROW - predator.coordinates.ROW);
        //  return predator.coordinates=new Coordinates(predator.coordinates.x + dx, predator.coordinates.y + dy);

        Entyty nextTurnentyty = map.getEntyty(new Coordinates(predator.coordinates.COLUMN + dx, predator.coordinates.ROW + dy));


        if (predator.coordinates.COLUMN +dx>20){
            dx=0;
        }
        if (predator.coordinates.COLUMN +dx<1){
            dx=0;
        }
        if (predator.coordinates.ROW +dy>20){
            dy=0;
        }
        if (predator.coordinates.ROW +dy<1){
            dy=0;
        }
//
        if (nextTurnentyty instanceof Rock || nextTurnentyty instanceof Predator |nextTurnentyty instanceof Grass) {//
            if (nextTurnentyty.getCoordinates().COLUMN > predator.coordinates.COLUMN) {

                 return predator.coordinates = new Coordinates(predator.coordinates.COLUMN, predator.coordinates.ROW + 1);
            }
            if (nextTurnentyty.getCoordinates().ROW > predator.coordinates.ROW) {
                return predator.coordinates = new Coordinates(predator.coordinates.COLUMN - 1, predator.coordinates.ROW);
            }
            if (nextTurnentyty.getCoordinates().ROW < predator.coordinates.ROW) {
                return predator.coordinates = new Coordinates(predator.coordinates.COLUMN -1, predator.coordinates.ROW); // тут изменял
            }
            if (nextTurnentyty.getCoordinates().COLUMN < predator.coordinates.COLUMN) {
                return predator.coordinates = new Coordinates(predator.coordinates.COLUMN -1, predator.coordinates.ROW + 1);
            }


        }
        return predator.coordinates = new Coordinates(predator.coordinates.COLUMN + dx, predator.coordinates.ROW + dy);
    }
  }
