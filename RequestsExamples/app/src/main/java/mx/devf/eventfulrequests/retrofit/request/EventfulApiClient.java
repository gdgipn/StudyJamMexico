package mx.devf.eventfulrequests.retrofit.request;


import mx.devf.eventfulrequests.EventfulApiKeys;
import retrofit.RestAdapter;

public class EventfulApiClient {

    private EventfulApiContract apiContract;

    public EventfulApiClient() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setEndpoint(EventfulApiKeys.BASE_URL)
                .build();

        apiContract = restAdapter.create(EventfulApiContract.class);
    }

    public EventfulApiContract getApiContract() {
        return apiContract;
    }
}
