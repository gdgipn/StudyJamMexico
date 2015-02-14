package mx.devf.eventfulrequests.retrofit.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import mx.devf.eventfulrequests.EventfulApiKeys;

public class EventsRequestModel {

    public class EventsModelResponse {
        @SerializedName(EventfulApiKeys.JSON_KEY_EVENTS)
        EventsMainSection eventsMainSection;

        public List<EventGson> getListEvents (){
            return eventsMainSection.eventsPackage.listEvents;
        }
    }

    private class EventsMainSection {
        @SerializedName(EventfulApiKeys.JSON_KEY_PACKAGE_EVENTS)
        EventsPackage eventsPackage;
    }

    private class EventsPackage {
        @SerializedName(EventfulApiKeys.JSON_KEY_EVENTS)
        List<EventGson> listEvents;
    }

    public class EventGson {
        @SerializedName(EventfulApiKeys.JSON_KEY_TITLE_EVENT)
        String title;

        @SerializedName(EventfulApiKeys.JSON_KEY_DATE_EVENT)
        String date;

        @SerializedName(EventfulApiKeys.JSON_KEY_DESCRIPTION_EVENT)
        String description;

        public String getTitle() {
            return title;
        }

        public String getDate() {
            return date;
        }

        public String getDescription() {
            return description;
        }
    }
}
