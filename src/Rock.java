public class Rock extends Entyty {
    private final String color;
    private final Coordinates coordinates;


    @Override
    public String getColor() {
        return this.color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Rock(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.color = "\uD83E\uDDF1";
    }


}

