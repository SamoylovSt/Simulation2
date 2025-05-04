public class Herbivore extends Creature {
    private final String color;
    private Coordinates coordinates;

    public Herbivore(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.color = "\uD83D\uDC30";
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

    public void eatGrass(Coordinates targetCoordinates, Coordinates herbCoordinates, Map map) {
        if (herbCoordinates.equals(targetCoordinates) && map.getEntyty(targetCoordinates) instanceof Grass) {

            map.deleteEntyty(targetCoordinates);
        }
    }

    public void randomWalk(Map map, Herbivore herbivore) {
        int[][] directions = {
                {+1, +0}, {+0, -1}, {+1, +1}, {+1, -1},
                {+0, +1}, {-1, +1}, {-1, +0}, {-1, -1}
        };

        for (int i = 0; i < directions.length; i++) {
            int newRow = directions[i][1];
            int newCol = directions[i][0];
            Coordinates nextCoordinates = new Coordinates(herbivore.getCoordinates().COLUMN + newCol, herbivore.getCoordinates().ROW + newRow);

            if (nextCoordinates.COLUMN > 15) {
                break;
            }
            if (nextCoordinates.COLUMN < 1) {
                break;
            }
            if (nextCoordinates.ROW > 15) {
                break;
            }
            if (nextCoordinates.ROW < 1) {
                break;
            }


            if (!map.emptyCoordainates(nextCoordinates)) {
                herbivore.coordinates = nextCoordinates;
            }
        }
    }

    @Override
    public void makeMove(Creature creature, Coordinates targetCoordinates, Map map) {
        super.makeMove(creature, targetCoordinates, map);
    }
}

