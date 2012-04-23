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

    public String Address;
    public AnalyticsDetails AnalyticsDetails;
    public String City;
    public double ClosedAt;
    public String Company;
    public String Country;
    public CrmDetails CrmDetails;
    public double CustomerStatusModifiedAt;
    public boolean EligibleForEmail;
    public String Email;
    public boolean EmailBounced;
    public boolean EmailOptOut;
    public String Fax;
    public String FirstCampaign;
    public String FirstName;
    public String FirstRefDomain;
    public String FirstReferrer;
    public String FirstURL;
    public int FirstVisitSetAt;
    public String FoundVia;
    public String FullFoundViaString;
    public String Guid;
    public String Id;
    public boolean Imported;
    public String Industry;
    public double InsertedAt;
    public String IpAddress;
    public boolean IsCustomer;
    public boolean IsDeleted;
    public String JobTitle;
    public double LastConvertedAt;
    public double LastModifiedAt;
    public String LastName;
    public LeadConversionEvent[] LeadConversionEvents;
    public LogEntry[] LeadCustomerStatusModifyLogs;
    public LogEntry[] LeadDeleteLogs;
    public int LeadId;
    public String LeadJsonLink;
    public String LeadLink;
    public boolean LeadNurturingActive;
    public int LeadNurturingCampaignId;
    public String Message;
    public int NumConversionEvents;
    public String Phone;
    public int PortalId;
    public String PublicLeadLink;
    public int RawScore;
    public String Salutation;
    public int Score;
    public int SourceId;
    public String SourceValue1;
    public String SourceValue2;
    public double SourceValueModifiedAt;
    public String State;
    public String TwitterHandle;
    public String UserToken;
    public String Website;
    public String Zip;

    Lead(JSONObject jsonObject) {
        try {
            Address = jsonObject.getString("address");
            AnalyticsDetails = new AnalyticsDetails(jsonObject.getJSONObject("analyticsDetails"));
            City = jsonObject.getString("city");
            ClosedAt = jsonObject.getDouble("closedAt");
            Company = jsonObject.getString("company");
            Country = jsonObject.getString("country");
            this.CrmDetails = new CrmDetails(jsonObject.getJSONObject("crmDetails"));
            CustomerStatusModifiedAt = jsonObject.getDouble("customerStatusModifiedAt");

            EligibleForEmail = jsonObject.getBoolean("eligibleForEmail");
            Email = jsonObject.getString("email");
            EmailBounced = jsonObject.getBoolean("emailBounced");
            EmailOptOut = jsonObject.getBoolean("emailOptOut");
            Fax = jsonObject.getString("fax");
            FirstCampaign = jsonObject.getString("firstCampaign");
            FirstName = jsonObject.getString("firstName");
            FirstRefDomain = jsonObject.getString("firstRefDomain");
            FirstReferrer = jsonObject.getString("firstReferrer");
            FirstURL = jsonObject.getString("firstURL");
            FirstVisitSetAt = jsonObject.getInt("firstVisitSetAt");
            FoundVia = jsonObject.getString("foundVia");
            FullFoundViaString = jsonObject.getString("fullFoundViaString");
            Guid = jsonObject.getString("guid");
            Id = jsonObject.getString("id");
            Imported = jsonObject.getBoolean("imported");
            Industry = jsonObject.getString("industry");
            InsertedAt = jsonObject.getDouble("insertedAt");
            IpAddress = jsonObject.getString("ipAddress");
            IsCustomer = jsonObject.getBoolean("isCustomer");
            IsDeleted = jsonObject.getBoolean("isDeleted");
            JobTitle = jsonObject.getString("jobTitle");
            LastConvertedAt = jsonObject.getDouble("lastConvertedAt");
            LastModifiedAt = jsonObject.getDouble("lastModifiedAt");
            LastName = jsonObject.getString("lastName");
            
            JSONArray tempJsonArray = jsonObject.getJSONArray("leadConversionEvents");
            LeadConversionEvents = new LeadConversionEvent[tempJsonArray.length()];
            for (int x = 0; x < tempJsonArray.length();x++)
            {
                LeadConversionEvents[x] = new LeadConversionEvent(tempJsonArray.getJSONObject(x));
                
            }
            
            tempJsonArray = jsonObject.getJSONArray("leadCustomerStatusModifyLogs");
            LeadCustomerStatusModifyLogs = new LogEntry[tempJsonArray.length()];
            for (int x = 0; x < tempJsonArray.length();x++)
            {
                LeadCustomerStatusModifyLogs[x] = new LogEntry(tempJsonArray.getJSONObject(x));
                
            }
            
            tempJsonArray = jsonObject.getJSONArray("leadDeleteLogs");
            LeadDeleteLogs = new LogEntry[tempJsonArray.length()];
            for (int x = 0; x < tempJsonArray.length();x++)
            {
                LeadDeleteLogs[x] = new LogEntry(tempJsonArray.getJSONObject(x));
                
            }
            LeadId = jsonObject.getInt("leadId");
            LeadJsonLink = jsonObject.getString("leadJsonLink");
            LeadLink = jsonObject.getString("leadLink");
            LeadNurturingActive = jsonObject.getBoolean("leadNurturingActive");
            LeadNurturingCampaignId = jsonObject.getInt("leadNurturingCampaignId");
            Message = jsonObject.getString("message");
            NumConversionEvents = jsonObject.getInt("numConversionEvents");
            Phone = jsonObject.getString("phone");
            PortalId = jsonObject.getInt("portalId");
            PublicLeadLink = jsonObject.getString("publicLeadLink");
            RawScore = jsonObject.getInt("rawScore");
            Salutation = jsonObject.getString("salutation");
            Score = jsonObject.getInt("score");
            SourceId = jsonObject.getInt("sourceId");
            SourceValue1 = jsonObject.getString("sourceValue1");
            SourceValue2 = jsonObject.getString("sourceValue2");
            SourceValueModifiedAt = jsonObject.getDouble("sourceValueModifiedAt");
            State = jsonObject.getString("state");
            TwitterHandle = jsonObject.getString("twitterHandle");
            UserToken = jsonObject.getString("userToken");
            Website = jsonObject.getString("website");
            Zip = jsonObject.getString("zip");
            
            
        } catch (Exception e) {
        }
    }
}
