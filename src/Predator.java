import java.util.HashSet;
import java.util.List;

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

    public void eatHerbivore(List<Coordinates> putb, Creature creature, GameMap gameMap, HashSet<Herbivore> rabits) {
        if (putb != null) {
            try {
                if(gameMap.getEntyty(putb.get(0))instanceof Herbivore ){//&& creature.getCoordinates().equals(putb.get(0))
                    gameMap.deleteEntyty(putb.get(0));
                    rabits.remove(gameMap.getEntyty(putb.get(0)));
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
               if(gameMap.getEntyty(temp) instanceof  Grass){
                   creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN(), creature.getCoordinates().getROW()));
               }else {
                   creature.setCoordinates(new Coordinates(temp.getCOLUMN(), temp.getROW()));
               }

           }catch (IndexOutOfBoundsException e){
               creature.setCoordinates(new Coordinates(creature.getCoordinates().getCOLUMN(), creature.getCoordinates().getROW()));
           }
       }
   }
}
