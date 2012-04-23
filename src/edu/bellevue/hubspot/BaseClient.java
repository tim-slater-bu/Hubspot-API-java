/**
 * Copyright 2012 Bellevue University.
 * 
* Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
* http://www.apache.org/licenses/LICENSE-2.0
 * 
* Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package edu.bellevue.hubspot;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author TSLATER
 */
public class BaseClient {

    
    // HAPIKey, API_PATH, API_VERSION are used by subclasses
    protected String HAPIKey = "";
    protected String API_PATH = "";
    protected String API_VERSION = "";
    
    // should we use the QA domain
    protected boolean isTest = false;
    protected String PATH_DIV = "/";
    protected String KEY_PARAM = "?hapikey=";
    protected String PROD_DOMAIN = "https://api.hubapi.com";
    protected String QA_DOMAIN = "https://hubapiqa.com";
    protected String userAgent = "";

    public BaseClient(String apiKey) {
        this(apiKey, "Default Agent String");
    }

    public BaseClient(String apiKey, String agentString) {
        this.HAPIKey = apiKey;
        this.userAgent = agentString;
    }

    protected String get_api() {
        return API_PATH;
    }

    protected String get_api_version() {
        return API_VERSION;
    }

    protected void set_is_test(boolean newValue) {
        this.isTest = newValue;
    }

    protected String get_domain() {
        if (this.isTest == true) {
            return QA_DOMAIN;
        } else {
            return PROD_DOMAIN;
        }
    }

    protected String get_request_url(String endpoint, HashMap values) {
        //$paramstring = $this->array_to_params($params);

        StringBuilder sb = new StringBuilder();
        sb.append(this.get_domain());
        sb.append(PATH_DIV);
        sb.append(this.get_api());
        sb.append(PATH_DIV);
        sb.append(this.get_api_version());
        sb.append(PATH_DIV);
        sb.append(endpoint);
        sb.append(KEY_PARAM);
        sb.append(HAPIKey);
        if (values != null) {
            sb.append(mapToParamList(values));
        }

        return sb.toString();
    }

    protected String get_request_url(String endpoint) {
        return get_request_url(endpoint, null);
    }

    protected String execute_get_request(String url) {
        String returnValue = null;
        try {
            URL theUrl = new URL(url);
            URLConnection conn = theUrl.openConnection();
            conn.setRequestProperty("User-Agent", this.userAgent);
            InputStream is = conn.getInputStream();
            returnValue = convertStreamToString(is);
            is.close();

        } catch (Exception e) {
            returnValue = "An exception has occured accessing: " + url;
            returnValue += "\n " + e.getMessage();
        }
        return returnValue;
    }

    protected String execute_post_request(String urlPath, String data, boolean formenc) {
        String response = "";
        try {
            // Construct data
            // String data = KEY_PARAM + HAPIKey + mapToParamList(values);
            // Send data
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            conn.setRequestProperty("User-Agent", this.userAgent);
            conn.setRequestProperty("Content-Type","text/html;charset=UTF-8");
            if (formenc) {
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            }
            
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();
            // Get the response
            response = convertStreamToString(conn.getInputStream());

            wr.close();
        } catch (Exception e) {
            int x = 1;
            x--;
        }
        return response;
    }

    protected String execute_xml_post_request(String urlPath, String body) {
        String response = "";
        try {
            // Send data
            URL url = new URL(urlPath);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("User-Agent", this.userAgent);
            conn.setRequestProperty("Content-Type", "application/atom+xml");
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(body);
            wr.flush();
            // Get the response
            response = convertStreamToString(conn.getInputStream());

            wr.close();
        } catch (Exception e) {
        }
        return response;
    }

    protected String execute_put_request(String urlPath, String body) {
        String response = "";
        try {
            // Send data
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("User-Agent", this.userAgent);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(body);
            wr.flush();
            // Get the response
            response = convertStreamToString(conn.getInputStream());

            wr.close();
        } catch (Exception e) {
            int x = 1;
            x--;
        }
        return response;


    }

    protected String execute_xml_put_request(String urlPath, String body) {
        String response = "";
        try {
            // Send data
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("User-Agent", this.userAgent);
            conn.setRequestProperty("Content-Type", "application/atom+xml");
            conn.setRequestProperty("Content-Length", Integer.toString(body.length()).trim());

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(body);
            wr.flush();
            // Get the response
            response = convertStreamToString(conn.getInputStream());

            wr.close();
        } catch (Exception e) {
        }
        return response;
    }

    protected String execute_delete_request(String urlPath, String body) {
        String response = "";
        try {
            // Send data
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("User-Agent", this.userAgent);
            if (body != null) {
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Content-Length", Integer.toString(body.length()).trim());
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(body);
                wr.flush();
                wr.close();
            }

            // Get the response
            response = convertStreamToString(conn.getInputStream());

        } catch (Exception e) {
            int x = 1;
            x--;
        }
        return response;
    }

    // Used to take the http response stream and convert to a string.
    protected static String convertStreamToString(java.io.InputStream is) {
        try {
            return new java.util.Scanner(is).useDelimiter("\\A").next();
        } catch (java.util.NoSuchElementException e) {
            return "";
        }
    }

    // Converts a map of values into a query string used for GET/POST
    protected String mapToParamList(Map values) {
        StringBuilder sb = new StringBuilder();
        Set entries = values.entrySet();
        Iterator i = entries.iterator();
        while (i.hasNext()) {
            Entry e = ((Entry) i.next());
            sb.append("&");
            sb.append(e.getKey());
            sb.append("=");
            sb.append(e.getValue());
        }
        return sb.toString();
    }
}
