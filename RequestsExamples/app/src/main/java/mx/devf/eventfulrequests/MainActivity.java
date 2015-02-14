package mx.devf.eventfulrequests;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

import java.util.ArrayList;

import mx.devf.eventfulrequests.async.AsyncTaskRequest;
import mx.devf.eventfulrequests.model.Event;
import mx.devf.eventfulrequests.retrofit.request.EventfulApiClient;
import mx.devf.eventfulrequests.retrofit.request.EventsRequestModel;
import mx.devf.eventfulrequests.volley.VolleySingleton;
import retrofit.Callback;
import retrofit.RetrofitError;

import static android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity implements OnClickListener,AsyncTaskRequest.AsyncRespose{

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    EditText etxLocationForEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAsync = (Button) findViewById(R.id.btn_asynctask);
        Button btnVolley = (Button) findViewById(R.id.btn_volley);
        Button btnRetrofit = (Button) findViewById(R.id.btn_retrofit);

        etxLocationForEvents = (EditText) findViewById(R.id.etx_place);

        btnAsync.setOnClickListener(this);
        btnVolley.setOnClickListener(this);
        btnRetrofit.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View viewClicked) {
        switch (viewClicked.getId()){
            case R.id.btn_asynctask:
                String location = etxLocationForEvents.getText()
                        .toString();

                if(!location.isEmpty()){
                    new AsyncTaskRequest(this)
                            .execute();
                }

                break;

            case R.id.btn_volley:
                location = etxLocationForEvents.getText()
                        .toString();

                if(!location.isEmpty()){
                    volleyEventfulRequest(location);
                }
                break;

            case R.id.btn_retrofit:
                location = etxLocationForEvents.getText()
                        .toString();

                if(!location.isEmpty()){
                    retrofitEventfulRequest(location);
                }
                break;

            default:
                break;
        }
    }

    private void retrofitEventfulRequest(String location) {
        EventfulApiClient retrofitClient = new EventfulApiClient();

        retrofitClient.getApiContract().findEvents(EventfulApiKeys.APP_KEY , EventfulApiKeys.VALUE_THIS_WEEK , location,
            new Callback<EventsRequestModel.EventsModelResponse>() {
                @Override
                public void success(EventsRequestModel.EventsModelResponse eventsModelResponse,
                                    retrofit.client.Response response) {

                    EventsRequestModel.EventGson randomEvent = eventsModelResponse.getListEvents().get(0);
                    changeEventUI(randomEvent.getTitle(), randomEvent.getDate(), randomEvent.getDescription());
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
    }

    public void volleyEventfulRequest(String location) {

        StringRequest eventfulRequest = new StringRequest(Request.Method.GET, EventfulApiKeys.getSearchEventsUrl(location),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Event randomEvent = EventfulApiKeys.parseEventsFromJson(response).get(0);
                            changeEventUI(randomEvent.getTitle(), randomEvent.getDate(), randomEvent.getDescription());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(LOG_TAG, error.toString());
                    }
                });

        VolleySingleton.getInstance(this)
                .addToRequestQueue(eventfulRequest);
    }

    @Override
    public void onResponse(ArrayList<Event> events) {
        Event randomEvent = events.get(0);
        changeEventUI(randomEvent.getTitle(), randomEvent.getDate(), randomEvent.getDescription());
    }

    @Override
    public void onError() {

    }

    public void changeEventUI(String title, String date, String description) {
        TextView txtTitle = (TextView) findViewById(R.id.event_title);
        TextView txtDate = (TextView) findViewById(R.id.event_date);
        TextView txtDescription = (TextView) findViewById(R.id.event_description);

        txtTitle.setText(title);
        txtDate.setText(date);
        txtDescription.setText(description);
    }
}
