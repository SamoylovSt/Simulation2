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

//    public void eatGrass(Coordinates targetCoordinates, Coordinates herbCoordinates, GameMap gameMap) {
//        if (herbCoordinates.equals(targetCoordinates) && gameMap.getEntyty(targetCoordinates) instanceof Grass) {
//            gameMap.deleteEntyty(targetCoordinates);
//        }
//
//    }
public void eatGrass(List<Coordinates> putb, Creature creature, GameMap gameMap) {

    if (putb != null) {
        try {
            if(gameMap.getEntyty(putb.get(0))instanceof Grass && creature.getCoordinates().equals(putb.get(0))){

                gameMap.deleteEntyty(putb.get(0));
            }
        }catch (IndexOutOfBoundsException e){
            creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN(), creature.getCoordinates().getROW()));
        }

    }
}


    public void makeMove(Creature creature, List<Coordinates> putb, GameMap gameMap) {
        if (putb != null) {
            try {
                Coordinates temp = putb.get(0);
                creature.setCoordinates(new Coordinates(temp.getCOLUMN(), temp.getROW()));
            }catch (IndexOutOfBoundsException e){
                creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN(), creature.getCoordinates().getROW()));
            }
        }
    }
//    public void makeMove(Creature creature, Coordinates targetCoordinates, GameMap gameMap) {
//        int nextColumn = (int) Math.signum(targetCoordinates.getCOLUMN() - creature.getCoordinates().getCOLUMN());
//        int nextRow = (int) Math.signum(targetCoordinates.getROW() - creature.getCoordinates().getROW());
//
//        Entity entityInNextCell = gameMap.getEntyty(new Coordinates(creature.getCoordinates().getCOLUMN() + nextColumn, creature.getCoordinates().getROW() + nextRow));
//        if (creature.getCoordinates().getCOLUMN() + nextColumn > 15 ||
//                creature.getCoordinates().getCOLUMN() + nextColumn < 1) {
//            nextColumn = 0;
//        }
//        if (creature.getCoordinates().getROW() + nextRow > 15 ||
//                creature.getCoordinates().getROW() + nextRow < 1) {
//            nextRow = 0;
//        }
//        if (entityInNextCell instanceof Stone) {
//            if (entityInNextCell.getCoordinates().getCOLUMN() > creature.getCoordinates().getCOLUMN()) {
//                creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN(), creature.getCoordinates().getROW() + 1));
//            }
//            if (entityInNextCell.getCoordinates().getROW() > creature.getCoordinates().getROW()) {
//                creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN() + 1, creature.getCoordinates().getROW()));
//            }
//            if (entityInNextCell.getCoordinates().getROW() < creature.getCoordinates().getROW()) {
//                creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN() - 1, creature.getCoordinates().getROW()));
//            }
//            if (entityInNextCell.getCoordinates().getCOLUMN() < creature.getCoordinates().getCOLUMN()) {
//                creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN() - 1, creature.getCoordinates().getROW() + 1));
//            }
//        }
//        creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN() + nextColumn, creature.getCoordinates().getROW() + nextRow));
//    }
}

