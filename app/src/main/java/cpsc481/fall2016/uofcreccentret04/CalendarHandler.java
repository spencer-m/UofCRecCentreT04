package cpsc481.fall2016.uofcreccentret04;

import android.app.Activity;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.RectF;
import android.provider.CalendarContract;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Spencer on 11/17/2016.
 */

public class CalendarHandler {

    private List<WeekViewEvent> localevents;
    private List<WeekViewEvent> googleevents;
    private Activity activity;

    /**
     * Constructor
     * @param a the activity where it is being used
     */
    public CalendarHandler(Activity a) {

        activity = a;
        initLocalCal(); // initialize files required for local cal; only for first run
        readLocal(); // read local data and store
        readGCal(); // read google calendar data and store
    }

    /************************************
     *   Local Events File Operations   *
     ************************************/

    public boolean readLocal()  {

        localevents = new ArrayList<>();

        try {
            // get input file
            InputStream in = new FileInputStream("/data/data/cpsc481.fall2016.uofcreccentret04/files/localcal.txt");

            if (in != null) {
                // reading file
                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(tmp);
                // temporary variables
                String str;
                String[] buf;
                // read from file
                while ((str = reader.readLine()) != null) {
                    // split on tabs
                    buf = str.split("\t");
                    // get calendar object
                    Calendar startTime = Calendar.getInstance();
                    Calendar endTime = Calendar.getInstance();
                    // edit calendar instance dates
                    startTime.set(Integer.parseInt(buf[4]), Integer.parseInt(buf[2])-1, Integer.parseInt(buf[3]), Integer.parseInt(buf[5]), Integer.parseInt(buf[6]));
                    endTime.set(Integer.parseInt(buf[9]), Integer.parseInt(buf[7])-1, Integer.parseInt(buf[8]), Integer.parseInt(buf[10]), Integer.parseInt(buf[11]));
                    // create temporary WeekView object holder
                    WeekViewEvent tp = new WeekViewEvent(Long.parseLong(buf[0]), buf[1], startTime, endTime);

                    /* COLOR WILL BE AVAILABILITY
                    if (buf[12].equals("0")) {
                        tp.setColor();
                    }
                    else {
                        tp.setColor();
                    }
                    */
                    // add to "calendar database"
                    localevents.add(tp);
                }

                in.close();
            }
        }
        catch(Exception e) {
            return false;
        }
        return true;
    }

    public boolean writeLocal () {

        try {
            // writing file prep
            FileOutputStream fOut = new FileOutputStream("/data/data/cpsc481.fall2016.uofcreccentret04/files/localcal.txt");
            OutputStreamWriter out = new OutputStreamWriter(fOut);
            String str, sstr, estr;
            Calendar st;
            Calendar et;

            for (WeekViewEvent e : localevents) {
                // formatting start time
                st = e.getStartTime();
                sstr = (st.get(st.MONTH)+1) + "\t" + st.get(st.DATE) + "\t" + st.get(st.YEAR) + "\t" + st.get(st.HOUR_OF_DAY) + "\t" + st.get(st.MINUTE);
                // formatting end time
                et = e.getEndTime();
                estr = (et.get(et.MONTH)+1) + "\t" + et.get(et.DATE) + "\t" + et.get(et.YEAR) + "\t" + et.get(et.HOUR_OF_DAY) + "\t" + et.get(et.MINUTE);

                /* COLOR WILL BE AVAILABILITY
                String tavail;

                if (e.getColor() == 1234) {
                    tavail = "0";
                }
                else {
                    tavail = "1"
                }
                 */
                // formatting output
                str = Long.toString(e.getId()) + "\t" + e.getName() + "\t" + sstr + "\t" + estr + "\n";
                // write output
                out.write(str);
            }
            out.close();

        }
        catch(Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Init localcal.txt
     * Check for existence
     * If does not exist, copy contents of sampleEvents.txt to localcal.txt
     */
    public boolean initLocalCal() {

        try {
            File file = activity.getBaseContext().getFileStreamPath("localcal.txt");
            if (!file.exists()) {
                OutputStreamWriter out = new OutputStreamWriter(activity.openFileOutput("localcal.txt", 0));
                AssetManager am = activity.getApplicationContext().getAssets();
                InputStream in = am.open("sampleEvents");

                if (in != null) {
                    InputStreamReader tmp = new InputStreamReader(in);
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    while ((str = reader.readLine()) != null) {
                        out.write(str + "\n");
                    }
                }
                in.close();
                out.close();
                Toast.makeText(activity, "Local Calendar Created!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(activity, "Local Calendar Present!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(activity, "Somethings is wrong " + e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    /**********************************
     *   Google Calendar Operations   *
     **********************************/

    public boolean readGCal() {

        googleevents = new ArrayList<>();

        final String[] EVENT_PROJECTION = new String[] {
                CalendarContract.Events._ID,
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DTSTART,
                CalendarContract.Events.DTEND
        };

        final int ID_INDEX = 0, TITLE_INDEX = 1, START_INDEX = 2, END_INDEX = 3;

        String selection = "(" + CalendarContract.Events.CALENDAR_ID + " = ?)";
        String[] selectionArgs = new String[] {"2"}; // 2 because it is the second calendar apart from the 1 which is local

        // Get a Cursor over the Events Provider.
        Cursor cursor = activity.getContentResolver().query(CalendarContract.Events.CONTENT_URI, EVENT_PROJECTION, selection, selectionArgs, null);

        // Iterate over the result Cursor.
        while (cursor.moveToNext()) {

            // Extract the ID.
            long id = cursor.getLong(ID_INDEX);
            // Extract the Title
            String title = cursor.getString(TITLE_INDEX);
            // Extract the Start Time
            Calendar startTime = Calendar.getInstance();
            startTime.setTimeInMillis(cursor.getLong(START_INDEX));
            // Extract the End Time
            Calendar endTime = Calendar.getInstance();
            endTime.setTimeInMillis(cursor.getLong(END_INDEX));

            WeekViewEvent tp = new WeekViewEvent(id, title, startTime, endTime);

            /*
            COLOR WILL BE AVAILABILITY
            if (buf[12].equals("0")) {
                tp.setColor();
            }
            else {
                tp.setColor();
            }
            */

            googleevents.add(tp);
        }
        // Close the Cursor.
        cursor.close();

        if (googleevents.size() == 0) {
            return false;
        }
        else {
            return true;
        }
    }


    /******************
     *   Listeners    *
     ******************/

    public MonthLoader.MonthChangeListener theMonthChangeListener() {

        MonthLoader.MonthChangeListener r = new MonthLoader.MonthChangeListener() {

            @Override
            public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

                List<WeekViewEvent> allevents = new ArrayList<>(localevents);
                allevents.addAll(googleevents);

                // Return only the events that matches newYear and newMonth.
                List<WeekViewEvent> matchedEvents = new ArrayList<WeekViewEvent>();

                for (WeekViewEvent event : allevents) {
                    if (eventMatches(event, newYear, newMonth)) {
                        matchedEvents.add(event);
                    }
                }

                return matchedEvents;
            }

        };
        return r;
    }

    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

    public WeekView.EventClickListener theEventClickListener() {

        WeekView.EventClickListener r = new WeekView.EventClickListener() {
            @Override
            public void onEventClick(WeekViewEvent event, RectF eventRect) {

            }
        };
        return  r;
    }

    public WeekView.EventLongPressListener theEventLongPressListener() {

        WeekView.EventLongPressListener r = new WeekView.EventLongPressListener() {
            @Override
            public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

            }
        };
        return r;
    }

    /**
     * DEBUGGERS
     */

    public String whatIsLocalData() {

        String result = "";

        String str, sstr, estr;
        Calendar st;
        Calendar et;

        for (WeekViewEvent e : localevents) {

            // extract start time
            st = e.getStartTime();
            sstr = st.get(st.MONTH) + "\t" + st.get(st.DATE) + "\t" + st.get(st.YEAR) + "\t" + st.get(st.HOUR_OF_DAY) + "\t" + st.get(st.MINUTE);
            // extract end time
            et = e.getEndTime();
            estr = et.get(et.MONTH) + "\t" + et.get(et.DATE) + "\t" + et.get(et.YEAR) + "\t" + et.get(et.HOUR_OF_DAY) + "\t" + et.get(et.MINUTE);
            // combine with id and name
            str = Long.toString(e.getId()) + "\t" + e.getName() + "\t" + sstr + "\t" + estr + "\n";
            result = result + str;
        }

        return result;

    }

    public String whatIsGoogleData() {

        String result = "";

        String str, sstr, estr;
        Calendar st;
        Calendar et;

        for (WeekViewEvent e : googleevents) {

            // extract start time
            st = e.getStartTime();
            sstr = st.get(st.MONTH) + "\t" + st.get(st.DATE) + "\t" + st.get(st.YEAR) + "\t" + st.get(st.HOUR_OF_DAY) + "\t" + st.get(st.MINUTE);
            // extract end time
            et = e.getEndTime();
            estr = et.get(et.MONTH) + "\t" + et.get(et.DATE) + "\t" + et.get(et.YEAR) + "\t" + et.get(et.HOUR_OF_DAY) + "\t" + et.get(et.MINUTE);
            // combine with id and name
            str = Long.toString(e.getId()) + "\t" + e.getName() + "\t" + sstr + "\t" + estr + "\n";
            result = result + str;
        }

        return result;
    }

    /*
    public boolean theCalendars_parser() {

        googleevents = new ArrayList<>();

        String[] projection = new String[]{
                CalendarContract.Calendars._ID,
                CalendarContract.Calendars.NAME,
                CalendarContract.Calendars.ACCOUNT_NAME,
                CalendarContract.Calendars.ACCOUNT_TYPE
        };

        Cursor calCursor = activity.getContentResolver().query(CalendarContract.Calendars.CONTENT_URI,
                projection,
                CalendarContract.Calendars.VISIBLE + " = 1",
                null,
                CalendarContract.Calendars._ID + " ASC");


        theCalendars = "";
        if (calCursor.moveToFirst()) {
            do {

                String id = calCursor.getString(0);
                String displayName = calCursor.getString(1);
                String accName = calCursor.getString(2);
                String accType = calCursor.getString(3);
                theCalendars = theCalendars + "\nID: " + id + " DN: " + displayName + " ACCN: " + accName + " ACCT: " + accType;

            } while (calCursor.moveToNext());
        }

        if (googleevents.size() == 0) {
            return false;
        }
        else {
            return true;
        }
    }
    */

}
