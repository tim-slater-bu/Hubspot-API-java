/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bellevue.hubspot.Prospects;

import edu.bellevue.hubspot.BaseClient;
import java.net.URLEncoder;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author TSLATER
 */
public class ProspectsAPI extends BaseClient {

    private String timelineOrgOffset;
    private String timelineTimeOffset;
    private boolean timelineHasMore = true;
    private String searchOrgOffset;
    private String searchTimeOffset;
    private boolean searchHasMore = true;
    public static final String SEARCH_TYPE_CITY = "city";
    public static final String SEARCH_TYPE_REGION = "region";
    public static final String SEARCH_TYPE_COUNTRY = "country";

    // returns if the last get_timeline() call had more results
    public boolean hasMoreTimeLineResults() {
        return timelineHasMore;
    }

    // returns if the last search_prospects() call had more results
    public boolean hasMoreSearchResults() {
        return searchHasMore;
    }

    public ProspectsAPI(String apiKey) {
        super(apiKey, "Default Agent String");
        API_PATH = "prospects";
        API_VERSION = "v1";
    }

    public ProspectsAPI(String apiKey, String agentString) {
        super(apiKey, agentString);
        API_PATH = "prospects";
        API_VERSION = "v1";
    }

    // reset for calls to get_timeline(), this discards the state of previous
    // calls in regards to if there are more results.
    public void reset_timeline() {
        timelineOrgOffset = null;
        timelineTimeOffset = null;
        timelineHasMore = true;
    }

    // reset for calls to search_prospects(), this discards the state of previous
    // calls in regards to if there are more results.
    public void reset_prospect_search() {
        searchOrgOffset = null;
        searchTimeOffset = null;
        searchHasMore = true;
    }

    // returns an array holding the 20 most recent prospects. Subsequent calls return
    // the next 'page' of data if there were more than 20 results. Calling reset_timeline()
    // causes the advancing of pages to reset.
    public Prospect[] get_timeline() {
        HashMap params = new HashMap();
        Prospect[] returnedProspects = null;
        if (timelineOrgOffset != null && timelineTimeOffset != null && timelineHasMore) {
            params.put("timeOffset", timelineTimeOffset);
            params.put("orgOffset", timelineOrgOffset);
        }
        try {
            String jsonResponse = execute_get_request(get_request_url("timeline", params));
            JSONObject response = new JSONObject(jsonResponse);

            timelineOrgOffset = response.optString("org-offset");
            timelineTimeOffset = Long.toString(response.getLong("time-offset"));
            timelineHasMore = response.getBoolean("has-more");

            JSONArray prospects = response.getJSONArray("prospects");
            returnedProspects = new Prospect[prospects.length()];

            for (int x = 0; x < prospects.length(); x++) {
                returnedProspects[x] = new Prospect(prospects.getJSONObject(x));
            }
        } catch (Exception e) {
        }
        return returnedProspects;
    }

    // Get the details of a specific organization. 
    // @param organizationName - name of the organization, generally gotten from
    //                           a Prospect object. (the organization field)
    public OrganizationDetails get_organization_details(String organizationName) {
        OrganizationDetails details = null;
        organizationName = organizationName.replaceAll(" ", "-");
        organizationName = organizationName.replaceAll("[^a-zA-Z0-9\\-]", "");

        try {
            String jsonResponse = execute_get_request(get_request_url("timeline/" + organizationName));
            details = new OrganizationDetails(new JSONObject(jsonResponse));

        } catch (Exception e) {
        }
        return details;
    }

    // Gets a TypeAheadResponse that can be used in assisting the user with what they
    // are typing.
    public TypeAheadResponse get_typeahead(String query) {
        TypeAheadResponse response = null;
        try {
            HashMap params = new HashMap();
            params.put("q", query);

            String jsonResponse = execute_get_request(get_request_url("typeahead", params));
            response = new TypeAheadResponse(new JSONObject(jsonResponse));
        } catch (Exception e) {
        }
        return response;
    }

    // returns an array holding the 20 prospects matching the criteria. Subsequent calls return
    // the next 'page' of data if there were more than 20 results. Calling reset_prospect_search()
    // causes the advancing of pages to reset.
    public Prospect[] search_prospects(String searchType, String query) {
        Prospect[] returnedProspects = null;
        try {
            HashMap params = new HashMap();

            if (searchOrgOffset != null && searchTimeOffset != null && searchHasMore) {
                params.put("timeOffset", searchTimeOffset);
                params.put("orgOffset", searchOrgOffset);
            }

            params.put("q", query);

            String jsonResponse = execute_get_request(get_request_url("search/" + searchType, params));
            JSONObject response = new JSONObject(jsonResponse);

            searchOrgOffset = response.optString("org-offset");
            searchTimeOffset = Long.toString(response.getLong("time-offset"));
            searchHasMore = response.getBoolean("has-more");

            JSONArray prospects = response.getJSONArray("prospects");
            returnedProspects = new Prospect[prospects.length()];

            for (int x = 0; x < prospects.length(); x++) {
                returnedProspects[x] = new Prospect(prospects.getJSONObject(x));
            }
        } catch (Exception e) {
        }
        return returnedProspects;
    }

    // makes a prospect not show up in the Hubspot Site, used for spam or ISPs.
    // @param p - The prospect to hide
    public void hide_prospect(Prospect p) {
        hide_prospect(p.organization);
    }

    // makes a prospect not show up in the Hubspot Site, used for spam or ISPs.
    // @param organizationName - name of the organization to hide.
    public void hide_prospect(String organizationName) {
        try {
            String response = execute_post_request(get_request_url("filters"), "organization=" + URLEncoder.encode(organizationName, "UTF-8"), true);
        } catch (Exception e) {
        }
    }

    // Makes a hidden prospect visible again
    // @param organizationName - name of the organization to unhide.
    public void unhide_prospect(String organizationName) {
        HashMap params = new HashMap();

        try {
            params.put("organization", URLEncoder.encode(organizationName, "UTF-8"));
            execute_delete_request(get_request_url("filters", params), "");
        } catch (Exception e) {
        }
    }

    // Gets an array of Hidden Prospects
    public HiddenProspect[] get_hidden_prospects() {
        HiddenProspect[] prospects = null;
        try {
            String response = execute_get_request(get_request_url("filters"));
            JSONArray array = new JSONArray(response);
            
            prospects = new HiddenProspect[array.length()];
            
            for (int x = 0; x < array.length(); x++)
            {
                prospects[x] = new HiddenProspect(array.getJSONObject(x));
            }
            
        } catch (Exception e) {
        }
        return prospects;
    }
}
