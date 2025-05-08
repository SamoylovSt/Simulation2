import java.util.List;

abstract public class Creature extends Entity {
    private  final String color;
    private Coordinates coordinates;

    public Creature() {
        this.coordinates = coordinates;
        this.color = getColor();
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Coordinates setCoordinates(Coordinates coordinates) {
        return this.coordinates = coordinates;
    }
    public void makeMove(Creature creature, List<Coordinates> putb, GameMap gameMap){}

}
