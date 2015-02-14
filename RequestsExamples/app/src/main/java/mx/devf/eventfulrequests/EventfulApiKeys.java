package mx.devf.eventfulrequests;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.devf.eventfulrequests.model.Event;

public class EventfulApiKeys {
    public static final String BASE_URL = "http://www.eventful.com";
    public static final String PATH_JSON = "json";
    public static final String PATH_EVENTS = "events";
    public static final String PATH_SEARCH = "search";

    public static final String URL_EVENTS_SEARCH = "/" + PATH_JSON + "/" + PATH_EVENTS + "/" + PATH_SEARCH;

    public static final String PARAM_LOCATION = "location";
    public static final String PARAM_APP_KEY = "app_key";
    public static final String PARAM_DATE = "date";

    public static final String APP_KEY = "4Q7hC5bJXwHMZ99t";
    public static final String VALUE_THIS_WEEK = "This Week";

    public static final String JSON_KEY_EVENTS = "events";
    public static final String JSON_KEY_PACKAGE_EVENTS = "tabular";
    public static final String JSON_KEY_DATE_EVENT = "rf_start_time";
    public static final String JSON_KEY_DESCRIPTION_EVENT = "description";
    public static final String JSON_KEY_TITLE_EVENT = "title";

    public static String getSearchEventsUrl (String location){
        Uri eventUrl = Uri.parse(EventfulApiKeys.BASE_URL).buildUpon()
                .appendPath(EventfulApiKeys.PATH_JSON)
                .appendPath(EventfulApiKeys.PATH_EVENTS)
                .appendPath(EventfulApiKeys.PATH_SEARCH)
                .appendQueryParameter(EventfulApiKeys.PARAM_APP_KEY, EventfulApiKeys.APP_KEY)
                .appendQueryParameter(EventfulApiKeys.PARAM_LOCATION, location)
                .appendQueryParameter(EventfulApiKeys.PARAM_DATE, VALUE_THIS_WEEK)
                .build();

        return eventUrl.toString();
    }

    public static final ArrayList<Event> parseEventsFromJson(String eventfulResponse) throws JSONException {

        JSONObject jsonResponse = new JSONObject(eventfulResponse);
        JSONObject eventsMain = jsonResponse.getJSONObject(EventfulApiKeys.JSON_KEY_EVENTS);
        JSONObject eventsPackage = eventsMain.getJSONObject(EventfulApiKeys.JSON_KEY_PACKAGE_EVENTS);
        JSONArray events = eventsPackage.getJSONArray(EventfulApiKeys.JSON_KEY_EVENTS);

        ArrayList<Event> eventsList = new ArrayList<>();

        for (int i = 0; i < events.length(); i++) {
            JSONObject currentEvent = events.getJSONObject(i);

            String date = currentEvent.getString(JSON_KEY_DATE_EVENT);
            String description = currentEvent.getString(JSON_KEY_DESCRIPTION_EVENT);
            String title = currentEvent.getString(JSON_KEY_TITLE_EVENT);

            Event eventFound = new Event(title, description, date);
            eventsList.add(eventFound);
        }

        return eventsList;
    }
}
