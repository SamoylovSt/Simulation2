import java.util.*;


public class Simulation {


    public void render(Map map) {

        for (int ROW = 20; ROW >= 1; ROW--) {
           //StringBuilder line=new StringBuilder();
            String line = "";
            for (int COLUMN = 1; COLUMN <=20 ; COLUMN++) {
                Coordinates coordinates = new Coordinates(COLUMN, ROW);
                if (!map.map.containsKey(coordinates)) {
                    line += "\uD83D\uDFEB"; //"\uD83D\uDFEB";
                  //  line.append("\uD83D\uDFEB");
                } else {
                    line += map.getEntyty(coordinates).getColor();
                   // line.append(map.getEntyty(coordinates).getColor());
                }
            }
            System.out.println(line);
        }
    }

    public void initStartAction(Map map){
        Random random = new Random();

        int COLUMN = random.nextInt(20)+1;//random.nextInt(20) + 1;
        int ROW = random.nextInt(20) + 1;
        int COLUMN2 = random.nextInt(20) + 1;
        int ROW2 = random.nextInt(20) + 1;
        int COLUMN3 = random.nextInt(20) + 1;
        int ROW3 = random.nextInt(20) + 1;
        int COLUMN4 = random.nextInt(20) + 1;
        int ROW4 = random.nextInt(20) + 1;
        int COLUMN5 = random.nextInt(20) + 1;
        int ROW5 = random.nextInt(20) + 1;

        Grass grass2 = new Grass(new Coordinates(   ROW, COLUMN));
        Grass grass3 = new Grass(new Coordinates(ROW2, COLUMN2));
       map.setEntyty(grass2.getCoordinates(), grass2);
        map.setEntyty(grass3.getCoordinates(), grass3);

        Rock rock= new Rock(new Coordinates(COLUMN3,ROW3));
        Rock rock1= new Rock(new Coordinates(COLUMN4,ROW4));
        Rock rock2= new Rock(new Coordinates(COLUMN5,ROW5));
        map.setEntyty(rock.getCoordinates(),rock);

       map.setEntyty(rock2.getCoordinates(),rock2);

    }

    public void startSimulation() {
        Map map = new Map();
        HashSet<Herbivore> rabits = new HashSet<>();

        Herbivore herbivore1 = new Herbivore(new Coordinates(2, 2));
        map.setEntyty(herbivore1.getCoordinates(), herbivore1);

        rabits.add(herbivore1);

        initStartAction(map);

        Predator predator = new Predator(new Coordinates(10, 10));
        map.setEntyty(predator.getCoordinates(), predator);



        int turnCount = 0;
        int interval = 500;
        boolean condition = true;

        while (condition) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            turnCount++;


            Coordinates predatorTarget = poisk(predator, map);
            predator.makeMove(predator, predatorTarget, map);
            predator.eatHerbivore(predatorTarget,predator.getCoordinates(),map,rabits);

            setRandomEntyty(map, turnCount);
            render(map);

            map.reMap(nextTurn(map.map) );
            rabbitsWalk(turnCount, map, rabits);


            System.out.println(turnCount);

        }


    }

    public void rabbitsWalk(int turnCount, Map map, HashSet<Herbivore> rabits) {
        Random random = new Random();
        int COLUMN = random.nextInt(20) + 1;
        int ROW = random.nextInt(20) + 1;

        int[][] nearestCootdinates = {
                {+1, +0}, {+0, -1}, {+1, +1}, {+1, -1},
                {+0, +1}, {-1, +1}, {-1, +0}, {-1, -1}
        };
        int clearCootdCount = 0;
        if (turnCount % 4 == 0) {
            for (int i = 0; i < nearestCootdinates.length; i++) {
                int newRow = nearestCootdinates[i][1];
                int newCol = nearestCootdinates[i][0];

                Coordinates checkEmptyCoordinates1 = new Coordinates(ROW + newCol, COLUMN + newRow);
                Coordinates checkEmptyCoordinates2 = new Coordinates(ROW, COLUMN);
                if (!map.emptyCoordainates(checkEmptyCoordinates1) &&
                        !map.emptyCoordainates(checkEmptyCoordinates2)) {

                    clearCootdCount++;

                } else {
                    clearCootdCount = 0;
                }
                if (clearCootdCount == 8) {

                    Herbivore newRabit = new Herbivore(new Coordinates(COLUMN, ROW));
                    map.setEntyty(new Coordinates(COLUMN, ROW), newRabit);
                    rabits.add(newRabit);
                    clearCootdCount = 0;
                }
            }
        }



        //зайцы из списка ходят  и едят по очереди
        for (Herbivore rabit : rabits) {
            Coordinates rabitTarget = poisk(rabit, map);

            rabit.makeMove(rabit, rabitTarget, map);
            rabit.eatGrass(rabitTarget, rabit.getCoordinates(), map);

        }
    }





    public void setRandomEntyty(Map map, int turtcount) {
        Random random = new Random();
        int x = random.nextInt(20) + 1;
        int y = random.nextInt(20) + 1;


        int[][] nearestCootdinates = {
                {+1, +0}, {+0, -1}, {+1, +1}, {+1, -1},
                {+0, +1}, {-1, +1}, {-1, +0}, {-1, -1}
        };
        int clearCootdCount = 0;
        if (turtcount % 1 == 0) {    for (int i = 0; i < nearestCootdinates.length; i++) {
            int newRow = nearestCootdinates[i][1];
            int newCol = nearestCootdinates[i][0];

            if (!map.emptyCoordainates(new Coordinates(x + newCol, y + newRow)) &&
                    !map.emptyCoordainates(new Coordinates(x , y))) {

                clearCootdCount++;

            }else {clearCootdCount=0;}
            if (clearCootdCount == 8) {
                Grass gras = new Grass(new Coordinates(x, y));
                map.setEntyty(new Coordinates(x, y), gras);
                clearCootdCount=0;
            }
        }}


    }



    public Coordinates poisk(Entyty creature, Map m) {

        // ArrayDeque<Coordinates> queue = new ArrayDeque<>();
        LinkedList<Coordinates> queue = new LinkedList<>();
        int[][] directions = {
                {+1, +0}, {+0, -1}, {+1, +1}, {+1, -1},
                {+0, +1}, {-1, +1}, {-1, +0}, {-1, -1}
        };

        for (int i = 0; i < directions.length; i++) {
            int newRow = directions[i][1];
            int newCol = directions[i][0];
            Coordinates checkCoordinates1 = new Coordinates(creature.getCoordinates().COLUMN + newCol, creature.getCoordinates().ROW + newRow);
            queue.add(checkCoordinates1);

        }

        HashSet<Coordinates> visited = new HashSet<>();
        Coordinates targetCoordinates = new Coordinates(0, 0);

        while (!queue.isEmpty()) {
            // queue.removeFirst();
            //Coordinates nextCoordinates = queue.getFirst();
            Coordinates nextCoordinates = queue.removeFirst();
            if (m.emptyCoordainates(nextCoordinates) && creature instanceof Herbivore && m.getEntyty(nextCoordinates) instanceof Grass) {
                targetCoordinates = new Coordinates(nextCoordinates.COLUMN, nextCoordinates.ROW);
                break;
            }
            if (m.emptyCoordainates(nextCoordinates) && creature instanceof Predator && m.getEntyty(nextCoordinates) instanceof Herbivore) {
                targetCoordinates = new Coordinates(nextCoordinates.COLUMN, nextCoordinates.ROW);
                break;
            } else {
                visited.add(nextCoordinates);

                for (int i = 0; i < directions.length; i++) {
                    int newRow = directions[i][1];
                    int newCol = directions[i][0];
                   Coordinates temp= new Coordinates(nextCoordinates.COLUMN + newCol, nextCoordinates.ROW + newRow);
                    if (!visited.contains(temp)) {
                       queue.add(temp);
                    }
                    //229 строка ошибка
//
//                    if (!visited.contains(new Coordinates(nextCoordinates.COLUMN + newCol, nextCoordinates.ROW + newRow))) {
//                        queue.add(new Coordinates(nextCoordinates.COLUMN + newCol, nextCoordinates.ROW + newRow));
//                        //229 строка ошибка
//                    }
                }


            }
        }

        return targetCoordinates;

    }

    public  HashMap<Coordinates,Entyty> nextTurn(HashMap<Coordinates,Entyty> map){
        HashMap<Coordinates,Entyty> newMap = new HashMap<>();

        for (Coordinates key: map.keySet()){
            Entyty value=map.get(key);
            newMap.put(value.getCoordinates(),value);
        }
        return newMap;
    }

}
