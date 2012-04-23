/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bellevue.hubspot.Events;

import org.json.JSONObject;

/**
 *
 * @author TSLATER
 */
public class Event {

    public long createDate;
    public String description;
    public String eventType;
    public int portalId;
    public String url;
    public String createdBy;
    private boolean createable;

    public Event(JSONObject jsonObject) {
        try {
            this.createDate = jsonObject.getLong("createDate");
            this.description = jsonObject.getString("description");
            this.eventType = jsonObject.getString("eventType");
            this.portalId = jsonObject.getInt("portalId");
            this.url = jsonObject.getString("url");
            this.createdBy = jsonObject.getString("createdBy");
            this.createable = false;
        }catch (Exception e){}
    }
    
    public Event(String description, String eventURL, String eventType)
    {
        this(description,System.currentTimeMillis(),eventURL,eventType);
    }
    public Event(String description, long createDate, String eventURL, String eventType)
    {
        this.description = description;
        this.url = eventURL;
        this.eventType = eventType;
        this.createDate = createDate;
        this.createable = true;
    }
    
    public boolean isCreateable()
    {
        return createable;
    }
}
