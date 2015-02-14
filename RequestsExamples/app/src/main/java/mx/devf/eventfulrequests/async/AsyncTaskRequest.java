package mx.devf.eventfulrequests.async;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import mx.devf.eventfulrequests.EventfulApiKeys;
import mx.devf.eventfulrequests.model.Event;

public class AsyncTaskRequest extends AsyncTask<String, Void, ArrayList<Event>> {

    public static final String LOG_TAG = AsyncTaskRequest.class.getSimpleName();

    private AsyncRespose responseListener;

    public AsyncTaskRequest(AsyncRespose responseListener) {
        this.responseListener = responseListener;
    }

    @Override
    protected ArrayList<Event> doInBackground(String... params) {

        if (params.length == 0) {
            responseListener.onError();
            return null;
        }

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String location = params[0];
        String eventsResponse;

        try {
            URL url = new URL(EventfulApiKeys.getSearchEventsUrl(location));

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line)
                        .append("\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                responseListener.onError();
                return null;
            }
            eventsResponse = buffer.toString();
        }catch (IOException e){
            responseListener.onError();
            return null;
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    responseListener.onError();
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try{
           return EventfulApiKeys.parseEventsFromJson(eventsResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Event> events) {
        super.onPostExecute(events);

        if(events == null){
            responseListener.onError();
            return;
        }

        responseListener.onResponse(events);
    }

    public interface AsyncRespose {
        public void onResponse(ArrayList<Event> events);
        public void onError();
    }
}
