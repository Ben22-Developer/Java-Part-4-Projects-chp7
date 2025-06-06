import java.util.ArrayList;
import java.security.SecureRandom;

public class Polling {

    private static ArrayList<String> topics = new ArrayList<>();
    private static int[][] survey_results;

    private static SecureRandom randomize = new SecureRandom();

    public static void setup () {

        setToDefault();
        getTopics();

        if (!topics.isEmpty()) {
            getUsersInvolved();
        }
    }

    private static void setToDefault () {

        if (!topics.isEmpty()) {
            topics.clear();
        }

        if (survey_results != null) {
            survey_results = null;
        }
    }

    private static void getTopics () {

        String prompt_topics_involved, a_topic;

        prompt_topics_involved = "Enter the topic name to be discussed\nIf all the topics are entered, don't enter anything just press 'Enter'";

        a_topic = UI.inputStringMessageDialog(prompt_topics_involved);

        while (!a_topic.isEmpty()) {
            topics.add(a_topic);
            a_topic = UI.inputStringMessageDialog(prompt_topics_involved);
        }
    }

    private static void getUsersInvolved () {

        String prompt_users_involved;
        int users_involved_nbr;

//        prompt_users_involved = "Enter the total users involved in this survey";
//        users_involved_nbr = UI.inputIntegerMessageDialog(prompt_users_involved);

        users_involved_nbr = 10 + randomize.nextInt(11);

        survey_results = new int[topics.size()][users_involved_nbr];
    }

    public static void survey_Controller () {

        if (!topics.isEmpty()) {
            collectData();
            displayData();
        }
    }


    private static void collectData () {

        String a_topic, display_for_each, error;
        int rate;

        error = "The rate is out of range\nRate is in range 0 - 10";

        for (int i = 0; i < survey_results.length; i++) {

            a_topic = "Enter your opinion for topic: "+topics.get(i) +"\n";
            for (int j = 0; j < survey_results[i].length; j++) {

//                display_for_each = a_topic+"Person "+(j+1) +"\nRate is out of 10, Example 9/10 = 90%\n";
//                rate = UI.inputIntegerMessageDialog(display_for_each);

//                while (rate < 0 || rate > 10) {
//                    UI.showMessageDialog(error);
//                    rate = UI.inputIntegerMessageDialog(display_for_each);
//                }

                rate = randomize.nextInt(11);

                survey_results[i][j] = rate;
            }
        }
    }

    private static void displayData () {

        double highest_point, lowest_point, average;
        int highest_point_index, lowest_point_index;
        String display_title, full_data, analysis, average_str;

        display_title = "The survey results\n----------------------------------\n";

        full_data = "";
        highest_point = 0;
        lowest_point = 0;
        highest_point_index = 0;
        lowest_point_index = 0;

        for (int i = 0; i < survey_results.length; i ++) {

            full_data += topics.get(i) + "       ";
            average = 0;

            for (int j = 0; j < survey_results[i].length; j++) {

                full_data += (survey_results[i][j] + "       ");
                average += survey_results[i][j];
            }

            average /= survey_results[i].length;
            average_str = String.format("%.2f",average);
            full_data += "average:"+average_str+"\n";

            if (i == 0) {
                highest_point = average;
                lowest_point = average;
                highest_point_index = i;
                lowest_point_index = i;
            }
            else {

                if (average > highest_point) {
                    highest_point = average;
                    highest_point_index = i;
                }

                if (average < lowest_point) {
                    lowest_point = average;
                    lowest_point_index = i;
                }
            }
        }

        analysis = "The highest point issue: "+topics.get(highest_point_index)+", Points: "+String.format("%.2f",highest_point)+"\nThe lowest point issue:  "+topics.get(lowest_point_index)+", Points: "+String.format("%.2f",lowest_point);

        UI.showMessageDialog(display_title + full_data +analysis);
    }
}
