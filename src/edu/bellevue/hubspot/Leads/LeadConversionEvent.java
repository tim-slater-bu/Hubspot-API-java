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
public class LeadConversionEvent {

    public double ConvertDate;
    public String FormGuid;
    public int FormId;
    public String FormName;
    public FormSubmissionValue[] FormSubmissionValues;
    public String Guid;
    public int Id;
    public String LeadGuid;
    public String PageName;
    public String PageType;
    public String PageUrl;
    public int PortalId;

    LeadConversionEvent(JSONObject jsonObject) {
        try {
            ConvertDate = jsonObject.getDouble("convertDate");
            FormGuid = jsonObject.getString("formGuid");
            FormId = jsonObject.getInt("formId");
            FormName = jsonObject.getString("formName");
            // FormSubmissionValues()
            Guid = jsonObject.getString("guid");
            Id = jsonObject.getInt("id");
            LeadGuid = jsonObject.getString("leadGuid");
            PageName = jsonObject.getString("pageName");
            PageType = jsonObject.getString("pageType");
            PageUrl = jsonObject.getString("pageUrl");
            PortalId = jsonObject.getInt("portalId");
        } catch (Exception e) {
        }
    }
}
