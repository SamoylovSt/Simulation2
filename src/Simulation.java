import java.util.*;

public class Simulation {
    public final int MAP_SIZE = 15;

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

        int COLUMN = random.nextInt(MAP_SIZE) + 1;
        int ROW = random.nextInt(MAP_SIZE) + 1;
        int COLUMN2 = random.nextInt(MAP_SIZE) + 1;
        int ROW2 = random.nextInt(MAP_SIZE) + 1;
        int COLUMN3 = random.nextInt(MAP_SIZE) + 1;
        int ROW3 = random.nextInt(MAP_SIZE) + 1;
        int COLUMN4 = random.nextInt(MAP_SIZE) + 1;
        int ROW4 = random.nextInt(MAP_SIZE) + 1;
        int COLUMN5 = random.nextInt(MAP_SIZE) + 1;
        int ROW5 = random.nextInt(MAP_SIZE) + 1;
        int COLUMN6 = random.nextInt(MAP_SIZE) + 1;
        int ROW6 = random.nextInt(MAP_SIZE) + 1;
        int COLUMN7 = random.nextInt(MAP_SIZE) + 1;
        int ROW7 = random.nextInt(MAP_SIZE) + 1;


        Grass grass2 = new Grass(new Coordinates(ROW, COLUMN));
        Grass grass3 = new Grass(new Coordinates(ROW2, COLUMN2));
        gameMap.setEntyty(grass2.getCoordinates(), grass2);
        gameMap.setEntyty(grass3.getCoordinates(), grass3);

        Stone rock = new Stone(new Coordinates(COLUMN3, ROW3));
        Stone rock1 = new Stone(new Coordinates(COLUMN4, ROW4));
        Stone rock2 = new Stone(new Coordinates(COLUMN5, ROW5));
        gameMap.setEntyty(rock.getCoordinates(), rock);
        gameMap.setEntyty(rock1.getCoordinates(), rock1);
        gameMap.setEntyty(rock2.getCoordinates(), rock2);

        Herbivore herbivore1 = new Herbivore(new Coordinates(COLUMN6, ROW6));
        gameMap.setEntyty(herbivore1.getCoordinates(), herbivore1);
        rabits.add(herbivore1);

        Predator predator = new Predator(new Coordinates(COLUMN7, ROW7));
        gameMap.setEntyty(predator.getCoordinates(), predator);
        wolfs.add(predator);
    }

    public void turnActions(int turnCount, GameMap gameMap, HashSet rabits, HashSet wolfs) {
        setRandomEntity(gameMap, turnCount);
        render(gameMap);
        predatorsMakeActions(gameMap, wolfs, rabits);
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

        int[][] nearestCootdinates = {
                {+1, +0}, {+0, -1}, {+1, +1}, {+1, -1},
                {+0, +1}, {-1, +1}, {-1, +0}, {-1, -1}
        };
        int clearCootdCount = 0;
        if (turnCount % 3 == 0) {
            for (int i = 0; i < nearestCootdinates.length; i++) {
                int newRow = nearestCootdinates[i][1];
                int newCol = nearestCootdinates[i][0];

                Coordinates checkEmptyCoordinates1 = new Coordinates(ROW + newCol, COLUMN + newRow);
                Coordinates checkEmptyCoordinates2 = new Coordinates(ROW, COLUMN);
                if (!gameMap.emptyCoordainates(checkEmptyCoordinates1) &&
                        !gameMap.emptyCoordainates(checkEmptyCoordinates2)) {
                    clearCootdCount++;
                } else {
                    clearCootdCount = 0;
                }
                if (clearCootdCount == 8) {
                    Herbivore newRabit = new Herbivore(new Coordinates(COLUMN, ROW));
                    gameMap.setEntyty(new Coordinates(COLUMN, ROW), newRabit);
                    herbivores.add(newRabit);
                    clearCootdCount = 0;
                }
            }
        }
        for (Herbivore rabit : herbivores) {
            Coordinates rabitTarget = breadthFirstSearch(rabit, gameMap);
            rabit.makeMove(rabit, rabitTarget, gameMap);
            rabit.eatGrass(rabitTarget, rabit.getCoordinates(), gameMap);
        }
    }

    public void predatorsMakeActions(GameMap gameMap, HashSet<Predator> predators, HashSet<Herbivore> rabits) {
        for (Predator wolf : predators) {
            Coordinates predatorTarget = breadthFirstSearch(wolf, gameMap);
            wolf.makeMove(wolf, predatorTarget, gameMap);
            wolf.eatHerbivore(predatorTarget, wolf.getCoordinates(), gameMap, rabits);
        }
    }

    public void setRandomEntity(GameMap gameMap, int turtcount) {
        Random random = new Random();
        int x = random.nextInt(MAP_SIZE) + 1;
        int y = random.nextInt(MAP_SIZE) + 1;

        int[][] nearestCootdinates = {
                {+1, +0}, {+0, -1}, {+1, +1}, {+1, -1},
                {+0, +1}, {-1, +1}, {-1, +0}, {-1, -1}
        };
        int clearCootdCount = 0;
        if (turtcount % 2  == 0) {
            for (int i = 0; i < nearestCootdinates.length; i++) {
                int newRow = nearestCootdinates[i][1];
                int newCol = nearestCootdinates[i][0];

                if (!gameMap.emptyCoordainates(new Coordinates(x + newCol, y + newRow)) &&
                        !gameMap.emptyCoordainates(new Coordinates(x, y))) {

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

    public Coordinates breadthFirstSearch(Entity creature, GameMap map) {
        Map<Coordinates,Coordinates> hui= new HashMap<>();

        LinkedList<Coordinates> queue = new LinkedList<>();
        int[][] directions = {
                {+1, +0}, {+0, -1}, {+1, +1}, {+1, -1},
                {+0, +1}, {-1, +1}, {-1, +0}, {-1, -1}
        };

        queue.add(new Coordinates(creature.getCoordinates().getCOLUMN(), creature.getCoordinates().getROW()));

        HashSet<Coordinates> visited = new HashSet<>();
        Coordinates targetCoordinates = new Coordinates(0, 0);
        while (!queue.isEmpty()) {

            Coordinates nextCoordinates = queue.removeFirst();

            boolean checkBorderMap = nextCoordinates.getCOLUMN() < 15 && nextCoordinates.getCOLUMN() > 0 && nextCoordinates.getROW() < 15 && nextCoordinates.getROW() > 0;
            if (checkBorderMap) {
                if (map.emptyCoordainates(nextCoordinates) && creature instanceof Herbivore && map.getEntyty(nextCoordinates) instanceof Grass) {
                    targetCoordinates = new Coordinates(nextCoordinates.getCOLUMN(), nextCoordinates.getROW());
                    break;
                }
                if (map.emptyCoordainates(nextCoordinates) && creature instanceof Predator && map.getEntyty(nextCoordinates) instanceof Herbivore) {
                    targetCoordinates = new Coordinates(nextCoordinates.getCOLUMN(), nextCoordinates.getROW());
                    break;
                } else {
                    visited.add(nextCoordinates);

                    for (int i = 0; i < directions.length; i++) {
                        int newRow = directions[i][1];
                        int newCol = directions[i][0];
                        Coordinates temp = new Coordinates(nextCoordinates.getCOLUMN() + newCol, nextCoordinates.getROW() + newRow);
                        if (!visited.contains(temp)) {
                            queue.add(temp);
                        }
                    }
                }
            }
        }
        return targetCoordinates;
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
