/**
* Copyright 2012 Bellevue University.
*
*   Licensed under the Apache License, Version 2.0 (the 
* "License"); you may not use this file except in compliance 
* with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, 
* software distributed under the License is distributed on an 
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
* either express or implied.  See the License for the specific 
* language governing permissions and limitations under the 
* License.
*/
package edu.bellevue.hubspot.Leads;

import edu.bellevue.hubspot.BaseClient;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author TSLATER
 */
public class LeadsAPI extends BaseClient {
    //protected String API_PATH = "leads";
    //protected String API_VERSION = "v1";

    public LeadsAPI(String apiKey) {
        super(apiKey, "Default User Agent");
        API_PATH = "leads";
        API_VERSION = "v1";
    }

    public LeadsAPI(String apiKey, String agent) {
        super(apiKey, agent);
        API_PATH = "leads";
        API_VERSION = "v1";
    }

    public Lead[] get_leads(LeadSearchCriteria criteria) {
        Lead[] requestedLeads = null;
        String jsonResult = execute_get_request(get_request_url("list", criteria.toHashMap()));
        try {
            JSONArray array = new JSONArray(jsonResult);
            requestedLeads = new Lead[array.length()];

            for (int x = 0; x < array.length(); x++) {
                requestedLeads[x] = new Lead(array.getJSONObject(x));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        jsonResult = null;
        return requestedLeads;
    }

    public Lead get_lead(String leadGuid) {
        Lead returnedLead = null;
        String jsonResult = execute_get_request(get_request_url("lead/" + leadGuid, new HashMap()));
        try {
            JSONObject leadObject = new JSONObject(jsonResult);
            returnedLead = new Lead(leadObject);
        } catch (Exception e) {
        }
        return returnedLead;
    }

    public void update_lead(String leadGuid, HashMap data) {
        String endpoint = "lead/" + leadGuid;

        // clear any values not able to be updated
        String[] badValues = new String[]{"guid", "id", "leadId", "isCustomer", "lastConvertedAt", "lastModifiedAt", "leadJsonLink", "leadLink", "LeadNurturingActive", "numConversionEvents", "portalId", "publicLeadLink", "sourceValueModifiedAt", "analyticsDetails", "crmDetails", "leadConversionEvents"};
        List badList = Arrays.asList(badValues);
        Iterator keys = data.keySet().iterator();
        while (keys.hasNext()) {
            if (badList.contains((String) keys.next())) {
                keys.remove();
            }
        }
        JSONObject a = new JSONObject(data);

        execute_put_request(get_request_url(endpoint, null), a.toString());
    }

    public void close_lead(String leadGuid, long closeDate) {
        String endpoint = "lead/" + leadGuid;
        try {
            HashMap newValues = new HashMap();
            newValues.put("id", leadGuid);
            newValues.put("closedAt", Long.toString(closeDate).trim());
            newValues.put("isCustomer", "true");
            String body = (new JSONObject(newValues)).toString();

            execute_put_request(get_request_url(endpoint, null), body);
        } catch (Exception e) {
        }
    }

    public void add_lead(String formUrl, HashMap formValues) {
        execute_post_request(formUrl, mapToParamList(formValues), false);
    }

    public WebHook[] get_webhooks() {
        String endpoint = "callback-url";
        WebHook[] hooks = null;
        try {
            String jsonResult = execute_get_request(get_request_url(endpoint, null));
            JSONObject jsonObject = new JSONObject(jsonResult);
            hooks = new WebHook[jsonObject.length()];
            Iterator i = jsonObject.keys();
            int x = 0;
            while (i.hasNext()) {
                String key = i.next().toString();
                String value = jsonObject.getString(key);
                hooks[x] = new WebHook(key, value);
                x++;
            }
        } catch (Exception e) {
        }
        return hooks;
    }

    public void register_webhook(String callbackUrl) {
        String endpoint = "callback-url";
        // need to URL Encode
        try {
            execute_post_request(get_request_url(endpoint, null), "url=" + URLEncoder.encode(callbackUrl, "UTF-8"), true);
        } catch (Exception e) {
        }

    }

    public void delete_webhook(String hookGuid) {
        String endpoint = "callback-url/" + hookGuid;
        execute_delete_request(get_request_url(endpoint, null), null);
    }
}
