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

    public boolean allViewsImported;
    public double countsModifiedAt;
    public double firstVisitAt;
    public String id;
    public double lastPageViewAt;
    public double lastVisitAt;
    public String leadGuid;
    public int leadId;
    public int pageViewCount;
    public int portalId;
    public String userToken;
    public int visitCount;

    public AnalyticsDetails(JSONObject jsonObject) {
        try {
            allViewsImported = jsonObject.getBoolean("allViewsImported");
            countsModifiedAt = jsonObject.getDouble("countsModifiedAt");
            firstVisitAt = jsonObject.getDouble("firstVisitAt");
            id = jsonObject.getString("id");
            lastPageViewAt = jsonObject.getDouble("lastPageViewAt");
            lastVisitAt = jsonObject.getDouble("lastVisitAt");
            leadGuid = jsonObject.getString("leadGuid");
            leadId = jsonObject.getInt("leadId");
            pageViewCount = jsonObject.getInt("pageViewCount");
            portalId = jsonObject.getInt("portalId");
            userToken = jsonObject.getString("userToken");
            visitCount = jsonObject.getInt("visitCount");

        } catch (Exception e) {
        }
    }
}
