/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bellevue.hubspot.Keywords;

import edu.bellevue.hubspot.BaseClient;

/**
 *
 * @author TSLATER
 */
public class KeywordsAPI extends BaseClient{
    public KeywordsAPI(String apiKey) {
        super(apiKey, "Default Agent String");
        API_PATH = "keywords";
        API_VERSION = "v1";
    }

    public KeywordsAPI(String apiKey, String agentString) {
        super(apiKey, agentString);
        API_PATH = "keywords";
        API_VERSION = "v1";
    }
}
