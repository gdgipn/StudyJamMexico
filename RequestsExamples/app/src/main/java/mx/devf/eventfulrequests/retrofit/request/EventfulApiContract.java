package mx.devf.eventfulrequests.retrofit.request;


import mx.devf.eventfulrequests.EventfulApiKeys;
import retrofit.http.GET;
import retrofit.http.Query;

public interface EventfulApiContract {

    @GET(EventfulApiKeys.URL_EVENTS_SEARCH)
    public void findEvents(@Query(EventfulApiKeys.PARAM_APP_KEY) String appKey ,
                           @Query(EventfulApiKeys.PARAM_DATE) String date,
                           @Query(EventfulApiKeys.PARAM_LOCATION) String location,
                               retrofit.Callback<EventsRequestModel.EventsModelResponse> eventsCallback);
}
