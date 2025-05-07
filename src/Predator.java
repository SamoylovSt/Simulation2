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
        this.color = Sprites.PREDATOR_COLOR;
    }

    public void eatHerbivore(Coordinates targetCoordinates, Coordinates predatorCoordinates, GameMap gameMap, HashSet<Herbivore> rabits) {
        if (predatorCoordinates.equals(targetCoordinates) && gameMap.getEntyty(targetCoordinates) instanceof Herbivore) {
            rabits.remove(gameMap.getEntyty(targetCoordinates));
            gameMap.deleteEntyty(targetCoordinates);
        }
    }

    @Override
    public void makeMove(Creature creature, Coordinates targetCoordinates, GameMap gameMap) {
        int nextColumn = (int) Math.signum(targetCoordinates.getCOLUMN() - creature.getCoordinates().getCOLUMN());
        int nextRow = (int) Math.signum(targetCoordinates.getROW() - creature.getCoordinates().getROW());

        Entity entityInNextCell = gameMap.getEntyty(new Coordinates(creature.getCoordinates().getCOLUMN() + nextColumn, creature.getCoordinates().getROW() + nextRow));
        if (creature.getCoordinates().getCOLUMN() + nextColumn > 15 ||
                creature.getCoordinates().getCOLUMN() + nextColumn < 1) {
            nextColumn = 0;
        }
        if (creature.getCoordinates().getROW() + nextRow > 15 ||
                creature.getCoordinates().getROW() + nextRow < 1) {
            nextRow = 0;
        }
        if (entityInNextCell instanceof Stone) {
            if (entityInNextCell.getCoordinates().getCOLUMN() > creature.getCoordinates().getCOLUMN()) {
                creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN(), creature.getCoordinates().getROW() + 1));
            }
            if (entityInNextCell.getCoordinates().getROW() > creature.getCoordinates().getROW()) {
                creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN() + 1, creature.getCoordinates().getROW()));
            }
            if (entityInNextCell.getCoordinates().getROW() < creature.getCoordinates().getROW()) {
                creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN() - 1, creature.getCoordinates().getROW()));
            }
            if (entityInNextCell.getCoordinates().getCOLUMN() < creature.getCoordinates().getCOLUMN()) {
                creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN() - 1, creature.getCoordinates().getROW() + 1));
            }
        }
        creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN() + nextColumn, creature.getCoordinates().getROW() + nextRow));
    }


}
