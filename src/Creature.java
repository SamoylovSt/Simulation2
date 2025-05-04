abstract public class Creature extends Entyty {
    private String color;
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


    public void makeMove(Creature creature, Coordinates targetCoordinates, Map map) {//
        int dx = (int) Math.signum(targetCoordinates.COLUMN - creature.getCoordinates().COLUMN);
        int dy = (int) Math.signum(targetCoordinates.ROW - creature.getCoordinates().ROW);

        Entyty nextTurnentyty = map.getEntyty(new Coordinates(creature.getCoordinates().COLUMN + dx, creature.getCoordinates().ROW + dy));

        if (creature.getCoordinates().COLUMN + dx > 15) {
            dx = 0;
        }
        if (creature.getCoordinates().COLUMN + dx < 1) {
            dx = 0;
        }
        if (creature.getCoordinates().ROW + dy > 15) {
            dy = 0;
        }
        if (creature.getCoordinates().ROW + dy < 1) {
            dy = 0;
        }

        if (nextTurnentyty instanceof Rock) {
            if (nextTurnentyty.getCoordinates().COLUMN > creature.getCoordinates().COLUMN) {
                creature.setCoordinates(new Coordinates(creature.getCoordinates().COLUMN, creature.getCoordinates().ROW + 1));
            }
            if (nextTurnentyty.getCoordinates().ROW > creature.getCoordinates().ROW) {
                creature.setCoordinates(new Coordinates(creature.getCoordinates().COLUMN + 1, creature.getCoordinates().ROW));
            }
            if (nextTurnentyty.getCoordinates().ROW < creature.getCoordinates().ROW) {
                creature.setCoordinates(new Coordinates(creature.getCoordinates().COLUMN - 1, creature.getCoordinates().ROW));
            }
            if (nextTurnentyty.getCoordinates().COLUMN < creature.getCoordinates().COLUMN) {
                creature.setCoordinates(new Coordinates(creature.getCoordinates().COLUMN - 1, creature.getCoordinates().ROW + 1));
            }
        }
        creature.setCoordinates(new Coordinates(creature.getCoordinates().COLUMN + dx, creature.getCoordinates().ROW + dy));
    }

}
