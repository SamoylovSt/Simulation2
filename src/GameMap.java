import java.util.HashMap;
import java.util.Map;

public class GameMap {
    Map<Coordinates, Entity> map = new HashMap<>();

    public void reMap(HashMap<Coordinates, Entity> newMap) {
        this.map = newMap;
    }

    public void setEntyty(Coordinates coordinates, Entity entity) {
        map.put(coordinates, entity);
    }

    public Entity getEntyty(Coordinates coordinates) {
        return map.get(coordinates);
    }

    public boolean emptyCoordainates(Coordinates coordinates) {
        return map.containsKey(coordinates);
    }

    public void deleteEntyty(Coordinates coordinates) {
        map.remove(coordinates);
    }

}
