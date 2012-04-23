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

    public boolean hasMoreTimeLineResults() {
        return timelineHasMore;
    }

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

    public void reset_timeline() {
        timelineOrgOffset = null;
        timelineTimeOffset = null;
        timelineHasMore = true;
    }

    public void reset_prospect_search() {
        searchOrgOffset = null;
        searchTimeOffset = null;
        searchHasMore = true;
    }

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

    public void hide_prospect(Prospect p) {
        hide_prospect(p.organization);
    }

    public void hide_prospect(String organizationName) {
        try {
            String response = execute_post_request(get_request_url("filters"), "organization=" + URLEncoder.encode(organizationName, "UTF-8"), true);
        } catch (Exception e) {
        }
    }

    public void unhide_prospect(String organizationName) {
        HashMap params = new HashMap();

        try {
            params.put("organization", URLEncoder.encode(organizationName, "UTF-8"));
            execute_delete_request(get_request_url("filters", params), "");
        } catch (Exception e) {
        }
    }

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
