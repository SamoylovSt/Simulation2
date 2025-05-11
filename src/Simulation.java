import java.util.*;

public class Simulation {
    public static int MAP_SIZE = 10;

    public void render(GameMap gameMap) {
        for (int ROW = MAP_SIZE; ROW >= 1; ROW--) {
            String line = "";
            for (int COLUMN = 1; COLUMN <= MAP_SIZE; COLUMN++) {
                Coordinates coordinates = new Coordinates(COLUMN, ROW);
                if (!gameMap.map.containsKey(coordinates)) {
                    line += "\uD83D\uDFEB";
                } else {
                    line += gameMap.getEntyty(coordinates).getColor();
                }
            }
            System.out.println(line);
        }
    }

    public void initStartAction(GameMap gameMap, HashSet rabits, HashSet wolfs) {
        Random random = new Random();
        for (int i = 0; i < MAP_SIZE/2; i++) {
            int COLUMN = random.nextInt(MAP_SIZE)+1;
            int ROW = random.nextInt(MAP_SIZE)+1;
            Stone rock = new Stone(new Coordinates(COLUMN, ROW));
            gameMap.setEntyty(rock.getCoordinates(), rock);
            if (i % 2 == 0) {
                Grass grass2 = new Grass(new Coordinates(ROW, COLUMN));
                gameMap.setEntyty(grass2.getCoordinates(), grass2);
            } else if (i == 1) {
                Predator predator = new Predator(new Coordinates(COLUMN, ROW));
                gameMap.setEntyty(predator.getCoordinates(), predator);
                wolfs.add(predator);
            } else if (i == 5) {
                Herbivore herbivore1 = new Herbivore(new Coordinates(COLUMN, ROW));
                gameMap.setEntyty(herbivore1.getCoordinates(), herbivore1);
                rabits.add(herbivore1);
            }
        }
    }

    public void turnActions(int turnCount, GameMap gameMap, HashSet rabits, HashSet wolfs) {
        setRandomGrass(gameMap, turnCount);
        predatorsMakeActions(gameMap, wolfs, rabits,wolfs);
        render(gameMap);
        gameMap.reMap(nextTurn(gameMap.map));

        herbivoresMakeActions(turnCount, gameMap, rabits);
    }

    public void startSimulation() {
        GameMap gameMap = new GameMap();
        HashSet<Herbivore> herbivores = new HashSet<>();
        HashSet<Predator> wolfs = new HashSet<>();

        initStartAction(gameMap, herbivores, wolfs);
        int turnCount = 0;
        int interval = 700;
        while (true) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            turnCount++;
            turnActions(turnCount, gameMap, herbivores, wolfs);
            System.out.println(turnCount);
        }
    }

    public void herbivoresMakeActions(int turnCount, GameMap gameMap, HashSet<Herbivore> herbivores) {
        Random random = new Random();
        int COLUMN = random.nextInt(MAP_SIZE) + 1;
        int ROW = random.nextInt(MAP_SIZE) + 1;
        boolean checkStartEmptyCoordinatesForSetRabit = gameMap.emptyCoordainates(new Coordinates(COLUMN, ROW));
        if (turnCount % 5 == 0 && !checkStartEmptyCoordinatesForSetRabit) {
            Herbivore newRabit = new Herbivore(new Coordinates(COLUMN, ROW));
            gameMap.setEntyty(new Coordinates(COLUMN, ROW), newRabit);
            herbivores.add(newRabit);
        }
        for (Herbivore rabit : herbivores) {
            List rabitTarget = breadthFirstSearch(rabit, gameMap);
            rabit.makeMove(rabit, rabitTarget, gameMap);
            rabit.eatGrass(rabitTarget, rabit, gameMap);
        }
    }

    public void predatorsMakeActions(GameMap gameMap, HashSet<Predator> predators, HashSet<Herbivore> rabits, HashSet<Predator> wolfs) {
        for (Predator wolf : predators) {
            List predatorTarget = breadthFirstSearch(wolf, gameMap);
            wolf.makeMove(wolf, breadthFirstSearch(wolf, gameMap), gameMap);
            wolf.eatHerbivore(predatorTarget, wolf, gameMap, rabits);
            break;
        }
    }

    public void setRandomGrass(GameMap gameMap, int turtcount) {
        Random random = new Random();
        int x = random.nextInt(MAP_SIZE) + 1;
        int y = random.nextInt(MAP_SIZE) + 1;

        int[][] nearestCootdinates = {
                {+1, +0}, {+0, -1}, {+1, +1}, {+1, -1},
                {+0, +1}, {-1, +1}, {-1, +0}, {-1, -1}
        };
        int clearCootdCount = 0;
        if (turtcount % 2 == 0) {
            for (int i = 0; i < nearestCootdinates.length; i++) {
                int newRow = nearestCootdinates[i][1];
                int newCol = nearestCootdinates[i][0];

                if (!gameMap.emptyCoordainates(new Coordinates(x + newCol, y + newRow)) &&
                       ! gameMap.emptyCoordainates(new Coordinates(x, y))) {

                    clearCootdCount++;

                } else {
                    clearCootdCount = 0;
                }
                if (clearCootdCount == 8) {
                    Grass gras = new Grass(new Coordinates(x, y));
                    gameMap.setEntyty(new Coordinates(x, y), gras);
                    clearCootdCount = 0;
                }
            }
        }

    }

    public List<Coordinates> breadthFirstSearch(Entity creature, GameMap map) {
        Map<Coordinates, Coordinates> parents = new HashMap<>();
        List<Coordinates> spisokKoord = new ArrayList<>();
        LinkedList<Coordinates> queue = new LinkedList<>();

        int[][] directions = {
                {+1, +0}, {+0, -1}, {+1, +1}, {+1, -1},
                {+0, +1}, {-1, +1}, {-1, +0}, {-1, -1}
        };

        Coordinates checkCoordinates1 = new Coordinates(creature.getCoordinates().getCOLUMN(), creature.getCoordinates().getROW());
        queue.add(checkCoordinates1);
        parents.put(checkCoordinates1, checkCoordinates1);

        HashSet<Coordinates> visited = new HashSet<>();
        Coordinates targetCoordinates = new Coordinates(0, 0);
        while (!queue.isEmpty()) {

            Coordinates nextCoordinates = queue.removeFirst();

            if (!(map.getEntyty(nextCoordinates) instanceof Stone)) {
                boolean checkBorderMap = nextCoordinates.getCOLUMN() <= MAP_SIZE && nextCoordinates.getCOLUMN() > 0 && nextCoordinates.getROW() <= MAP_SIZE && nextCoordinates.getROW() > 0;
                if (checkBorderMap) {
                    if (map.emptyCoordainates(nextCoordinates) && creature instanceof Herbivore && map.getEntyty(nextCoordinates) instanceof Grass) {
                         targetCoordinates = new Coordinates(nextCoordinates.getCOLUMN(), nextCoordinates.getROW());
                        spisokKoord = getWay(parents, targetCoordinates, checkCoordinates1);
                        break;
                    }
                    if (map.emptyCoordainates(nextCoordinates) && creature instanceof Predator && map.getEntyty(nextCoordinates) instanceof Herbivore) {
                        targetCoordinates = new Coordinates(nextCoordinates.getCOLUMN(), nextCoordinates.getROW());
                        spisokKoord = getWay(parents, targetCoordinates, checkCoordinates1);
                        break;
                    } else {
                        visited.add(nextCoordinates);

                        for (int i = 0; i < directions.length; i++) {
                            int newRow = directions[i][1];
                            int newCol = directions[i][0];
                            Coordinates neighbor = new Coordinates(nextCoordinates.getCOLUMN() + newCol, nextCoordinates.getROW() + newRow);
//                            if (queue.size() > 5) {
//                                break;
//                            }
                            if (!visited.contains(neighbor)) {
                                queue.add(neighbor);
                                visited.add(neighbor);
                                parents.put(neighbor, nextCoordinates);
                            }
                        }
                    }
                }
            }

        }
        return spisokKoord;
    }

    public List<Coordinates> getWay(Map<Coordinates, Coordinates> parents, Coordinates targetCoordinates, Coordinates startCoordinates) {
        List<Coordinates> path = new ArrayList<>();
        Coordinates current = targetCoordinates;

        while (current != null && !current.equals(startCoordinates)) {
            path.add(current);
            current = parents.get(current);
        }
        Collections.reverse(path);
        path.add(startCoordinates);
        return path;
    }

    public HashMap<Coordinates, Entity> nextTurn(Map<Coordinates, Entity> map) {
        HashMap<Coordinates, Entity> newMap = new HashMap<>();

        for (Coordinates key : map.keySet()) {
            Entity value = map.get(key);
            newMap.put(value.getCoordinates(), value);
        }
        return newMap;
    }
}
