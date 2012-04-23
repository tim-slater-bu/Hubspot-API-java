/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bellevue.hubspot.Prospects;

import org.json.JSONObject;

/**
 *
 * @author TSLATER
 */
public class HiddenProspect {
    public String organization;
    public long createdAt;
    
    public HiddenProspect(JSONObject jsonObject)
    {
        try
        {
            organization = jsonObject.optString("organization");
            createdAt = jsonObject.optLong("createdAt");
        }catch(Exception e){}
    }
    
}
