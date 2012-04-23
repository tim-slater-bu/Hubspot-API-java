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
public class TypeAheadResponse {

    public String[] COUNTRY;
    public String[] CITY;
    public String[] REGION;
    public String[] ORGANIZATION;

    public TypeAheadResponse(JSONObject jsonObject) {
        try {
            JSONArray array = jsonObject.getJSONObject("COUNTRY").getJSONArray("values");
            COUNTRY = new String[array.length()];
            for (int x = 0; x < array.length(); x++) {
                COUNTRY[x] = array.getString(x);
            }

            array = jsonObject.getJSONObject("CITY").getJSONArray("values");
            CITY = new String[array.length()];
            for (int x = 0; x < array.length(); x++) {
                CITY[x] = array.getString(x);
            }

            array = jsonObject.getJSONObject("REGION").getJSONArray("values");
            REGION = new String[array.length()];
            for (int x = 0; x < array.length(); x++) {
                REGION[x] = array.getString(x);
            }

            array = jsonObject.getJSONObject("ORGANIZATION").getJSONArray("values");
            ORGANIZATION = new String[array.length()];
            for (int x = 0; x < array.length(); x++) {
                ORGANIZATION[x] = array.getString(x);
            }
        } catch (Exception e) {
        }

    }
}
