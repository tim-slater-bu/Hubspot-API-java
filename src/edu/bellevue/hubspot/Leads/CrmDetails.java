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
public class CrmDetails {

    public int annualRevenue;
    public int closeProbability;
    public int crmType;
    public int estimatedRevenue;
    public String id;
    public String nextAction;
    public int nextActionAssignedTo;
    public String nextActionAssignedToEmail;
    public int nextActionDue;
    public int numEmployees;
    public String status;
    
    public CrmDetails (JSONObject jsonObject)
    {
        try
        {
        annualRevenue = jsonObject.getInt("annualRevenue");
        closeProbability = jsonObject.getInt("closeProbability");
        crmType = jsonObject.getInt("crmType");
        estimatedRevenue = jsonObject.getInt("estimatedRevenue");
        id = jsonObject.getString("id");
        nextAction = jsonObject.getString("nextAction");
        nextActionAssignedTo = jsonObject.getInt("nextActionAssignedTo");
        nextActionAssignedToEmail = jsonObject.getString("nextActionAssignedToEmail");
        nextActionDue = jsonObject.getInt("nextActionDue");
        numEmployees = jsonObject.getInt("numEmployees");
        status = jsonObject.getString("status");
        }
        catch(Exception e)
        {}     
    }   
}
