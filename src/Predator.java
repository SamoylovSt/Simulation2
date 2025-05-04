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

    @Override
    public Coordinates setCoordinates(Coordinates coordinates) {
        return this.coordinates = coordinates;
    }

    public Predator(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.color = "\uD83D\uDC3A";
    }

    public void eatHerbivore(Coordinates targetCoordinates, Coordinates predatorCoordinates, Map map, HashSet<Herbivore> rabits) {
        if (predatorCoordinates.equals(targetCoordinates) && map.getEntyty(targetCoordinates) instanceof Herbivore) {
            rabits.remove(map.getEntyty(targetCoordinates));
            map.deleteEntyty(targetCoordinates);
        }
    }

    @Override
    public void makeMove(Creature creature, Coordinates targetCoordinates, Map map) {
        super.makeMove(creature, targetCoordinates, map);
    }


}
