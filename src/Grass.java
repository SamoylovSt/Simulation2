public class Grass extends Entyty {
    private final String color;
    private final Coordinates coordinates;

    @Override
    public String getColor() {
        return this.color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Grass(Coordinates coordinates) {

        this.color = "☘\uFE0F";
        this.coordinates = coordinates;
    }
}

