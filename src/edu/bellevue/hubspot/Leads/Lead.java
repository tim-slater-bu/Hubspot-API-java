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

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author TSLATER
 */
public class Lead {

    public String address;
    public AnalyticsDetails analyticsDetails;
    public String city;
    public double closedAt;
    public String company;
    public String country;
    public CrmDetails crmDetails;
    public double customerStatusModifiedAt;
    public boolean eligibleForEmail;
    public String email;
    public boolean emailBounced;
    public boolean emailOptOut;
    public String fax;
    public String firstCampaign;
    public String firstName;
    public String firstRefDomain;
    public String firstReferrer;
    public String firstURL;
    public int firstVisitSetAt;
    public String foundVia;
    public String fullFoundViaString;
    public String guid;
    public String id;
    public boolean imported;
    public String industry;
    public double insertedAt;
    public String ipAddress;
    public boolean isCustomer;
    public boolean isDeleted;
    public String jobTitle;
    public double lastConvertedAt;
    public double lastModifiedAt;
    public String lastName;
    public LeadConversionEvent[] leadConversionEvents;
    public LogEntry[] leadCustomerStatusModifyLogs;
    public LogEntry[] leadDeleteLogs;
    public int leadId;
    public String leadJsonLink;
    public String leadLink;
    public boolean leadNurturingActive;
    public int leadNurturingCampaignId;
    public String message;
    public int numConversionEvents;
    public String phone;
    public int portalId;
    public String publicLeadLink;
    public int rawScore;
    public String salutation;
    public int score;
    public int sourceId;
    public String sourceValue1;
    public String sourceValue2;
    public double sourceValueModifiedAt;
    public String state;
    public String twitterHandle;
    public String userToken;
    public String website;
    public String zip;

    Lead(JSONObject jsonObject) {
        try {
            address = jsonObject.getString("address");
            analyticsDetails = new AnalyticsDetails(jsonObject.getJSONObject("analyticsDetails"));
            city = jsonObject.getString("city");
            closedAt = jsonObject.getDouble("closedAt");
            company = jsonObject.getString("company");
            country = jsonObject.getString("country");
            this.crmDetails = new CrmDetails(jsonObject.getJSONObject("crmDetails"));
            customerStatusModifiedAt = jsonObject.getDouble("customerStatusModifiedAt");

            eligibleForEmail = jsonObject.getBoolean("eligibleForEmail");
            email = jsonObject.getString("email");
            emailBounced = jsonObject.getBoolean("emailBounced");
            emailOptOut = jsonObject.getBoolean("emailOptOut");
            fax = jsonObject.getString("fax");
            firstCampaign = jsonObject.getString("firstCampaign");
            firstName = jsonObject.getString("firstName");
            firstRefDomain = jsonObject.getString("firstRefDomain");
            firstReferrer = jsonObject.getString("firstReferrer");
            firstURL = jsonObject.getString("firstURL");
            firstVisitSetAt = jsonObject.getInt("firstVisitSetAt");
            foundVia = jsonObject.getString("foundVia");
            fullFoundViaString = jsonObject.getString("fullFoundViaString");
            guid = jsonObject.getString("guid");
            id = jsonObject.getString("id");
            imported = jsonObject.getBoolean("imported");
            industry = jsonObject.getString("industry");
            insertedAt = jsonObject.getDouble("insertedAt");
            ipAddress = jsonObject.getString("ipAddress");
            isCustomer = jsonObject.getBoolean("isCustomer");
            isDeleted = jsonObject.getBoolean("isDeleted");
            jobTitle = jsonObject.getString("jobTitle");
            lastConvertedAt = jsonObject.getDouble("lastConvertedAt");
            lastModifiedAt = jsonObject.getDouble("lastModifiedAt");
            lastName = jsonObject.getString("lastName");
            
            JSONArray tempJsonArray = jsonObject.getJSONArray("leadConversionEvents");
            leadConversionEvents = new LeadConversionEvent[tempJsonArray.length()];
            for (int x = 0; x < tempJsonArray.length();x++)
            {
                leadConversionEvents[x] = new LeadConversionEvent(tempJsonArray.getJSONObject(x));
                
            }
            
            tempJsonArray = jsonObject.getJSONArray("leadCustomerStatusModifyLogs");
            leadCustomerStatusModifyLogs = new LogEntry[tempJsonArray.length()];
            for (int x = 0; x < tempJsonArray.length();x++)
            {
                leadCustomerStatusModifyLogs[x] = new LogEntry(tempJsonArray.getJSONObject(x));
                
            }
            
            tempJsonArray = jsonObject.getJSONArray("leadDeleteLogs");
            leadDeleteLogs = new LogEntry[tempJsonArray.length()];
            for (int x = 0; x < tempJsonArray.length();x++)
            {
                leadDeleteLogs[x] = new LogEntry(tempJsonArray.getJSONObject(x));
                
            }
            leadId = jsonObject.getInt("leadId");
            leadJsonLink = jsonObject.getString("leadJsonLink");
            leadLink = jsonObject.getString("leadLink");
            leadNurturingActive = jsonObject.getBoolean("leadNurturingActive");
            leadNurturingCampaignId = jsonObject.getInt("leadNurturingCampaignId");
            message = jsonObject.getString("message");
            numConversionEvents = jsonObject.getInt("numConversionEvents");
            phone = jsonObject.getString("phone");
            portalId = jsonObject.getInt("portalId");
            publicLeadLink = jsonObject.getString("publicLeadLink");
            rawScore = jsonObject.getInt("rawScore");
            salutation = jsonObject.getString("salutation");
            score = jsonObject.getInt("score");
            sourceId = jsonObject.getInt("sourceId");
            sourceValue1 = jsonObject.getString("sourceValue1");
            sourceValue2 = jsonObject.getString("sourceValue2");
            sourceValueModifiedAt = jsonObject.getDouble("sourceValueModifiedAt");
            state = jsonObject.getString("state");
            twitterHandle = jsonObject.getString("twitterHandle");
            userToken = jsonObject.getString("userToken");
            website = jsonObject.getString("website");
            zip = jsonObject.getString("zip");
            
            
        } catch (Exception e) {
        }
    }
}
