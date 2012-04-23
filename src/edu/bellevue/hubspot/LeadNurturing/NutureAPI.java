/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bellevue.hubspot.LeadNurturing;

import edu.bellevue.hubspot.BaseClient;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author TSLATER
 */
public class NutureAPI extends BaseClient {

    public NutureAPI(String apiKey) {
        super(apiKey, "Default Agent String");
        API_PATH = "nurture";
        API_VERSION = "v1";
    }

    public NutureAPI(String apiKey, String agentString) {
        super(apiKey, agentString);
        API_PATH = "nurture";
        API_VERSION = "v1";
    }

    public Campaign[] get_campaigns() {
        return get_campaigns(false);
    }

    public Campaign[] get_campaigns(boolean includeInactive) {
        HashMap params = new HashMap();
        Campaign[] returnedCampaigns = null;
        if (includeInactive) {
            params.put("excludeInactive", "1");
        }
        String jsonResponse = execute_get_request(get_request_url("campaigns", params));
        try {
            JSONArray campaigns = new JSONArray(jsonResponse);
            returnedCampaigns = new Campaign[campaigns.length()];

            for (int x = 0; x < campaigns.length(); x++) {
                returnedCampaigns[x] = new Campaign(campaigns.getJSONObject(x));
            }
        } catch (Exception e) {
        }
        return returnedCampaigns;
    }

    public NurtureLead[] get_leads_for_campaign(Campaign c) {
        return get_leads_for_campaign(c.guid);
    }

    public NurtureLead[] get_leads_for_campaign(String campaignGuid) {
        NurtureLead[] returnedLeads = null;
        String jsonResponse = execute_get_request(get_request_url("campaign/" + campaignGuid + "/list"));
        try {
            JSONArray leads = new JSONArray(jsonResponse);
            returnedLeads = new NurtureLead[leads.length()];

            for (int x = 0; x < leads.length(); x++) {
                returnedLeads[x] = new NurtureLead(leads.getJSONObject(x));
            }
        } catch (Exception e) {
        }
        return returnedLeads;
    }

    public HistoryItem[] get_lead_campaign_history(NurtureLead l) {
        return get_lead_campaign_history(l.leadGuid);
    }

    public HistoryItem[] get_lead_campaign_history(String leadGuid) {
        HistoryItem[] returnedHistory = null;
        String jsonResponse = execute_get_request(get_request_url("lead/" + leadGuid));
        try {
            JSONArray leads = new JSONArray(jsonResponse);
            returnedHistory = new HistoryItem[leads.length()];

            for (int x = 0; x < leads.length(); x++) {
                returnedHistory[x] = new HistoryItem(leads.getJSONObject(x));
            }
        } catch (Exception e) {
        }
        return returnedHistory;
    }
    
    public void add_lead_to_campaign(String leadGuid, String campaignGuid)
    {
        execute_post_request(get_request_url("campaign/" + campaignGuid + "/add"),leadGuid, false);
    }
    
    public void remove_lead_from_campaign(String leadGuid, String campaignGuid)
    {
        execute_post_request(get_request_url("campaign/" + campaignGuid + "/remove"),leadGuid, false);
    }
    
    
}
