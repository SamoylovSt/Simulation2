import java.util.HashMap;

public class Map {
    HashMap<Coordinates,Entyty> map = new HashMap<>();

public  void reMap(HashMap<Coordinates,Entyty> newMap){
  this.map=newMap;
}

    public void setEntyty(Coordinates coordinates,Entyty entyty){
        map.put(coordinates, entyty);
    }

    public Entyty getEntyty(Coordinates coordinates){
       return map.get(coordinates);
    }

    public boolean emptyCoordainates(Coordinates coordinates) {
        return map.containsKey(coordinates);
    }

    public void deleteEntyty(Coordinates coordinates){
        map.remove(coordinates);
    }

}
