import java.security.SecureRandom;
import java.util.ArrayList;

public class Race {

    private static SecureRandom random = new SecureRandom();
    private static final int TOTAL_RACING_PATHS = 70;

    private static Contenders hare;
    private static Contenders tortoise;

    private static String[] race_route;

    private static ArrayList<Integer> tortoise_race_route_cells = new ArrayList<>();
    private static ArrayList<Integer> hare_race_route_cells = new ArrayList<>();

    private static final String collision = "OUCH!";

    public static void initialize () {

        race_route = new String[TOTAL_RACING_PATHS];

        hare = new Contenders("🐇","Hare","HB");
        tortoise = new Contenders("🐢","Tortoise","TB");
    }

    public static void contendersRacing () {

        int hare_random_choice, tortoise_random_choice, hare_next_position, tortoise_next_position;

        tortoise_race_route_cells.add(0);
        hare_race_route_cells.add(0);


        while (race_route[race_route.length - 1] == null) {

           hare_random_choice = 1 + random.nextInt(10);
           tortoise_random_choice = 1 + random.nextInt(10);

           hare_next_position = getContenderNextPosition (hare, hare_random_choice);
           tortoise_next_position = getContenderNextPosition (tortoise, tortoise_random_choice);

            hare.updateContenderPath (hare_next_position);
            tortoise.updateContenderPath (tortoise_next_position);

//            if (hare.getLastContenderPosition() > 69) {
//                System.out.println("Hare out bounds "+hare.getLastContenderPosition()+" last index in route cells "+hare_race_route_cells.get(hare_race_route_cells.size() - 1));
//            }
//
//            if (tortoise.getLastContenderPosition() > 69) {
//                System.out.println("Hare out bounds "+tortoise.getLastContenderPosition());
//            }

            updateRace_route();
        }

        checkForWinner ();
    }

    private static int getContenderNextPosition (Contenders contender, int random_choice) {

        int next_position = 0;

        if (contender == hare) {
            next_position = getHareNextPosition (random_choice);
        }
        else if (contender == tortoise) {
            next_position = getTortoiseNextPosition (random_choice);
        }

        return next_position;
    }

    private static int getHareNextPosition (int random_choice) {

        int hare_next_position = 0;

        if (1 <= random_choice && random_choice <= 2) {
            hare_next_position = 0;
        }
        else if (3 <= random_choice && random_choice <= 4) {
            hare_next_position = 9;
        }
        else if (random_choice == 5) {
            hare_next_position = -12;
        }
        else if (6 <= random_choice && random_choice <= 8) {
            hare_next_position = 1;
        }
        else if (9 <= random_choice && random_choice <= 10) {
            hare_next_position = -2;
        }

        return hare_next_position;
    }

    private static int getTortoiseNextPosition (int random_choice) {

        int tortoise_next_position = 0;

        if (1 <= random_choice && random_choice <= 5) {
            tortoise_next_position = 3;
        }
        else if (6 <= random_choice && random_choice <= 7) {
            tortoise_next_position = -6;
        }
        else if (8 <= random_choice && random_choice <= 10) {
            tortoise_next_position = 1;
        }

        return tortoise_next_position;
    }

    private static void updateRace_route () {

        int hare_curr_position, tortoise_curr_position;

        hare_curr_position = hare.getLastContenderPosition();
        tortoise_curr_position = tortoise.getLastContenderPosition();

        updateContenderRouteCellsArray(hare_curr_position, tortoise_curr_position);

        if (hare_curr_position == tortoise_curr_position) {
            contenderCollision(hare_curr_position);
        }
        else {
            updateContenderPath (hare, hare_curr_position);
            updateContenderPath (tortoise, tortoise_curr_position);
        }

        printRacingRoutePath ();
    }

    private static void updateContenderRouteCellsArray (int hare_curr_position, int tortoise_curr_position) {

        hare_race_route_cells.add(hare_curr_position);
        tortoise_race_route_cells.add(tortoise_curr_position);

        if (hare_curr_position == 0) {
            makeEmptyContenderRouteCellsArray (hare_race_route_cells, hare);
        }
        else if (hare_race_route_cells.size() > 1 && hare_race_route_cells.get(hare_race_route_cells.size() - 2) > hare_race_route_cells.get(hare_race_route_cells.size() - 1)) {
            updateRouteCellsAndRaceRoutePath(hare_race_route_cells, tortoise, hare);
        }

        if (tortoise_curr_position == 0) {
            makeEmptyContenderRouteCellsArray (tortoise_race_route_cells, tortoise);
        }
        else if (tortoise_race_route_cells.size() > 1 && tortoise_race_route_cells.get(tortoise_race_route_cells.size() - 2) > tortoise_race_route_cells.get(tortoise_race_route_cells.size() - 1)) {
            updateRouteCellsAndRaceRoutePath(tortoise_race_route_cells, hare, tortoise);
        }
    }

    private static void makeEmptyContenderRouteCellsArray (ArrayList<Integer> contender_route_cells, Contenders contender) {

        for (int i = 0; i < contender_route_cells.size(); i++) {
            race_route[contender_route_cells.get(i)] = contender.geContender_slip();
        }
        contender_route_cells.clear();
        contender_route_cells.add(0);
    }

    private static void updateRouteCellsAndRaceRoutePath (ArrayList<Integer> contender_route_cells, Contenders cont_competing, Contenders slipped_cont)
    {
        int index, last_index;
        String cont_competing_symbol, slipped_cont_symbol;

        cont_competing_symbol = cont_competing.geContender_symbol();
        slipped_cont_symbol = slipped_cont.geContender_slip();

        index = contender_route_cells.size() - 2;
        last_index = contender_route_cells.size() - 1;

        while (contender_route_cells.get(index) >= contender_route_cells.get(last_index)) {

            race_route[contender_route_cells.get(index)] = (race_route[contender_route_cells.get(index)] == collision) ? cont_competing_symbol : slipped_cont_symbol;

            contender_route_cells.remove(index);

            index = contender_route_cells.size() - 2;
            last_index = contender_route_cells.size() - 1;
        }
    }

    private static void contenderCollision (int collide_position) {
        race_route[collide_position] = collision;
    }

    private static void updateContenderPath (Contenders contender, int position) {
        race_route[position] = contender.geContender_symbol();
    }

    private static void printRacingRoutePath () {

        for (String curr_pos_situation : race_route) {

            if (curr_pos_situation != null) {
                System.out.print(curr_pos_situation +"    ");
            }

        }
    }

    private static void checkForWinner () {

        System.out.println("\n");

        System.out.println("Hare's position: "+hare_race_route_cells.get(hare_race_route_cells.size() - 1));

        System.out.println("Hare's path\n---------------------------\n");

        getPrintedContenderPaths (hare_race_route_cells);

        System.out.println("Tortoise's position: "+tortoise_race_route_cells.get(tortoise_race_route_cells.size() - 1));

        System.out.println("Hare's path\n---------------------------\n");

        getPrintedContenderPaths (tortoise_race_route_cells);


        if (race_route[race_route.length - 1] == collision) {
            System.out.println("It's a tie");
        }
        else if (race_route[race_route.length - 1] == hare.geContender_symbol()) {
            System.out.println("Hare is the champion");
        }
        else if (race_route[race_route.length - 1] == tortoise.geContender_symbol()) {
            System.out.println("Tortoise is the champion");
        }
    }

    public static void getPrintedContenderPaths (ArrayList<Integer> contender_paths) {
        for (int i = contender_paths.size() - 1; i >= 0 ; i--) {
            System.out.println("Paths: "+contender_paths.get(i));
        }
        System.out.println();
    }
}
