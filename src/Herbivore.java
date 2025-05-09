import java.util.List;

public class Herbivore extends Creature {
    private final String color;
    private Coordinates coordinates;

    public Herbivore(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.color = Sprites.HERBIVORE_COLOR;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Coordinates setCoordinates(Coordinates coordinates) {
        return this.coordinates = coordinates;
    }

    public void eatGrass(List<Coordinates> putb, Creature creature, GameMap gameMap) {
        if (putb != null) {
            try {
                if (gameMap.getEntyty(putb.get(0)) instanceof Grass && creature.getCoordinates().equals(putb.get(0))) {
                    gameMap.deleteEntyty(putb.get(0));
                }
            } catch (IndexOutOfBoundsException e) {
                creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN(), creature.getCoordinates().getROW()));
            }
        }
    }

    public void makeMove(Creature creature, List<Coordinates> putb, GameMap gameMap) {
        if (putb != null) {
            try {
                Coordinates temp = putb.get(0);
                creature.setCoordinates(new Coordinates(temp.getCOLUMN(), temp.getROW()));
            } catch (IndexOutOfBoundsException e) {
                creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN(), creature.getCoordinates().getROW()));
            }
        }
    }
}

