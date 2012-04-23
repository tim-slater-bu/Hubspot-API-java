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

import org.json.JSONObject;

/**
 *
 * @author TSLATER
 */
public class AnalyticsDetails {

    public boolean AllViewsImported;
    public double CountsModifiedAt;
    public double FirstVisitAt;
    public String Id;
    public double LastPageViewAt;
    public double LastVisitAt;
    public String LeadGuid;
    public int LeadId;
    public int PageViewCount;
    public int PortalId;
    public String UserToken;
    public int VisitCount;

    public AnalyticsDetails(JSONObject jsonObject) {
        try {
            AllViewsImported = jsonObject.getBoolean("allViewsImported");
            CountsModifiedAt = jsonObject.getDouble("countsModifiedAt");
            FirstVisitAt = jsonObject.getDouble("firstVisitAt");
            Id = jsonObject.getString("id");
            LastPageViewAt = jsonObject.getDouble("lastPageViewAt");
            LastVisitAt = jsonObject.getDouble("lastVisitAt");
            LeadGuid = jsonObject.getString("leadGuid");
            LeadId = jsonObject.getInt("leadId");
            PageViewCount = jsonObject.getInt("pageViewCount");
            PortalId = jsonObject.getInt("portalId");
            UserToken = jsonObject.getString("userToken");
            VisitCount = jsonObject.getInt("visitCount");

        } catch (Exception e) {
        }
    }
}
