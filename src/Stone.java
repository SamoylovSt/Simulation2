public class Stone extends Entity {
    private final String color;
    private final Coordinates coordinates;

    @Override
    public String getColor() {
        return this.color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Stone(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.color = Sprites.STONE_COLOR;
    }
}

