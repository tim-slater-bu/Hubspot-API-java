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
public class FormSubmissionValue {

    public String conversionGuid;
    public double convertDate;
    public String fieldLabel;
    public String fieldName;
    public String fieldValue;
    public String formGuid;
    public int formId;
    public String formName;
    public long id;
    public String leadGuid;
    public int portalId;
    
    public FormSubmissionValue(JSONObject jsonObject)
    {
        try
        {
            conversionGuid = jsonObject.getString("conversionGuid");
            convertDate = jsonObject.getLong("convertDate");
            fieldLabel = jsonObject.getString("fieldLabel");
            fieldName = jsonObject.getString("fieldName");
            fieldValue = jsonObject.getString("fieldValue");
            formGuid = jsonObject.getString("formGuid");
            formId = jsonObject.getInt("formId");
            formName = jsonObject.getString("formName");
            id = jsonObject.getLong("id");
            leadGuid = jsonObject.getString("leadGuid");
            portalId = jsonObject.getInt("portalId");
        }catch(Exception e){}
        
    }
    
}
