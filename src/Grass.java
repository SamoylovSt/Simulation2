public class Grass extends Entyty {
    private String color="☘\uFE0F";
    private Coordinates coordinates;

    @Override
    public String getColor() {
         super.getColor();
       return this.color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Grass(Coordinates coordinates) {//,String color
        //super(color, coordinates);
        this.color = "☘\uFE0F";
        this.coordinates = coordinates;
    }
}

