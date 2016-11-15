package cpsc481.fall2016.uofcreccentret04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pauline on 2016-11-14.
 *
 * Class contains list of current bookings
 *
 */


public class CurrentBookingsListDataPump {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> currentBookingsListDetail = new HashMap<String, List<String>>();

        List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa");

        currentBookingsListDetail.put("Current Bookings", cricket);
        return currentBookingsListDetail;
    }
}
