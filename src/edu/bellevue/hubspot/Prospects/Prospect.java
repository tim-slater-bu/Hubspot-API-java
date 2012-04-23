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
public class Prospect {
    public String slug;
    public String organization;
    public int page_views;
    public int visitors;
    public long timestamp;
    public String city;
    public String region;
    public String country;
    public String url;
    public int leads;
    public double longitude;
    public double latitude;
    public String ip_address;
    public Touch[] touches;
    
    public Prospect(JSONObject jsonObject)
    {
        try
        {
            slug = jsonObject.getString("slug");
            organization = jsonObject.getString("organization");
            page_views = jsonObject.getInt("page-views");
            visitors = jsonObject.getInt("visitors");
            timestamp = jsonObject.getLong("timestamp");
            city = jsonObject.getString("city");
            region = jsonObject.getString("region");
            country = jsonObject.getString("country");
            url = jsonObject.getString("url");
            leads = jsonObject.getInt("leads");
            longitude = jsonObject.getDouble("longitude");
            latitude = jsonObject.getDouble("latitude");
            ip_address = jsonObject.getString("ip-address");
            
            JSONArray touchArray = jsonObject.getJSONArray("touches");
            touches = new Touch[touchArray.length()];
            for (int x = 0; x < touchArray.length();x++)
            {
                touches[x] = new Touch(touchArray.getJSONObject(x));
            }
            
            
        }catch (Exception e){}
    }
    
}
