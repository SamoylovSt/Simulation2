public class Grass extends Entity {
    private final String color ;
    private final Coordinates coordinates;

    @Override
    public String getColor() {
        return this.color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Grass(Coordinates coordinates) {
        this.color =Sprites.GRASS_COLOR; ;
        this.coordinates = coordinates;
    }
}

