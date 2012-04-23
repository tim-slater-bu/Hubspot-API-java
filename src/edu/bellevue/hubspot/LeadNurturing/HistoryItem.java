/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bellevue.hubspot.LeadNurturing;

import org.json.JSONObject;

/**
 *
 * @author TSLATER
 */
public class HistoryItem {
    
    public String leadGuid;
    public String campaignGuid;
    public String status;

    public HistoryItem(JSONObject jsonObject) {
        try {
            leadGuid = jsonObject.getString("leadGuid");
            campaignGuid = jsonObject.getString("campaignGuid");
            status = jsonObject.getString("status");
        } catch (Exception e) {
        }
    }
}
