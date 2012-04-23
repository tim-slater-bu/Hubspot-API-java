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
public class Touch {

    public String domain;
    public String child_id;
    public String keyword;
    public String keyword_engine;

    public Touch(JSONObject jsonObject) {
        try {
            domain = jsonObject.optString("domain");
            child_id = jsonObject.optString("child-id");
            keyword = jsonObject.optString("keyword");
            keyword_engine = jsonObject.optString("keyword_engine");
        } catch (Exception e) {
        }
    }
}
