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

    public int AnnualRevenue;
    public int CloseProbability;
    public int CrmType;
    public int EstimatedRevenue;
    public String Id;
    public String NextAction;
    public int NextActionAssignedTo;
    public String NextActionAssignedToEmail;
    public int NextActionDue;
    public int NumEmployees;
    public String Status;
    
    public CrmDetails (JSONObject jsonObject)
    {
        try
        {
        AnnualRevenue = jsonObject.getInt("annualRevenue");
        CloseProbability = jsonObject.getInt("closeProbability");
        CrmType = jsonObject.getInt("crmType");
        EstimatedRevenue = jsonObject.getInt("estimatedRevenue");
        Id = jsonObject.getString("id");
        NextAction = jsonObject.getString("nextAction");
        NextActionAssignedTo = jsonObject.getInt("nextActionAssignedTo");
        NextActionAssignedToEmail = jsonObject.getString("nextActionAssignedToEmail");
        NextActionDue = jsonObject.getInt("nextActionDue");
        NumEmployees = jsonObject.getInt("numEmployees");
        Status = jsonObject.getString("status");
        }
        catch(Exception e)
        {}     
    }   
}
