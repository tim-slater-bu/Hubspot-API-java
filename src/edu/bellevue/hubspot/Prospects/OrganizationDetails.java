/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bellevue.hubspot.Prospects;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author TSLATER
 */
public class OrganizationDetails {

    public Prospect summary;
    public JSONArray activites;

    public OrganizationDetails(JSONObject jsonObject) {
        try {
            summary = new Prospect(jsonObject.getJSONObject("summary"));
            activites = jsonObject.optJSONArray("activities");
        } catch (Exception e) {
        }
    }
}
