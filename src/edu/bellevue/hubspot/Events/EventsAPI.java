/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bellevue.hubspot.Events;

import edu.bellevue.hubspot.BaseClient;
import java.net.URLEncoder;
import java.util.HashMap;
import org.json.JSONArray;

/**
 *
 * @author TSLATER
 * This class exposes the Marketing Events API for Hubspot
 * Requires the use of an API Key (no OAUTH support).
 */
public class EventsAPI extends BaseClient {

    public EventsAPI(String apiKey) {
        super(apiKey, "Default Agent String");
        API_PATH = "events";
        API_VERSION = "v1";
    }

    public EventsAPI(String apiKey, String agentString) {
        super(apiKey, agentString);
        API_PATH = "events";
        API_VERSION = "v1";
    }

    // default return count is 20
    public Event[] get_events() {
        return get_events(20);
    }

    // return some events from the API
    // @param max - The maximum number of events to return
    // @return - returns and array of Event objects.
    public Event[] get_events(int max) {
        HashMap params = new HashMap();
        if (max > 100) {
            max = 100;
        }
        if (max < 0) {
            max = 20;
        }

        params.put("max", Integer.toString(max).trim());
        String jsonResponse = execute_get_request(get_request_url("events", params));

        JSONArray events = null;
        Event[] returnedEvents = null;
        try {
            events = new JSONArray(jsonResponse);
            returnedEvents = new Event[events.length()];
            for (int x = 0; x < events.length(); x++) {
                returnedEvents[x] = new Event(events.getJSONObject(x));
            }
        } catch (Exception e) {
        }

        return returnedEvents;
    }

    // Creates an event within the hubspot portal
    // @param event - the event to create.
    public void create_event(Event event) {
        if (event.isCreateable() == false) {
            return;
        }
        try {
            HashMap map = new HashMap();
            map.put("description", URLEncoder.encode(event.description, "UTF-8"));
            map.put("createDate",Long.toString(event.createDate).trim());
            map.put("url",URLEncoder.encode(event.url,"UTF-8"));
            map.put("eventType",URLEncoder.encode(event.eventType,"UTF-8"));
        execute_post_request(get_request_url("events", null), mapToParamList(map), true);
        } catch (Exception e) {
        }
    }
}
