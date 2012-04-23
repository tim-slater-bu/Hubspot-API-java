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
public class Campaign {
    public String name;
    public String guid;
    
    public Campaign(JSONObject jsonObject)
    {
        try
        {
        this.name = jsonObject.getString("name");
        this.guid = jsonObject.getString("guid");
        }catch (Exception e){}
    }
    
}
