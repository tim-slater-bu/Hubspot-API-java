/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bellevue.hubspot.testing;

import edu.bellevue.hubspot.Events.Event;
import edu.bellevue.hubspot.Events.EventsAPI;
import edu.bellevue.hubspot.LeadNurturing.Campaign;
import edu.bellevue.hubspot.LeadNurturing.HistoryItem;
import edu.bellevue.hubspot.LeadNurturing.NurtureLead;
import edu.bellevue.hubspot.LeadNurturing.NutureAPI;
import edu.bellevue.hubspot.Leads.*;
import edu.bellevue.hubspot.Prospects.*;
import java.util.HashMap;

/**
 *
 * @author TSLATER
 */
public class Main {

    public static void main(String[] args) {

        if (true){
            performLeadTests();
        }

        if (false) {
            performEventTests();
        }

        if (false) {
            performNutureTests();
        }

        if (false) {
            performProspectTests();
        }
    }

    private static void performProspectTests() {
        ProspectsAPI client = new ProspectsAPI("demo");
        Prospect[] result = client.get_timeline();
        for (Prospect p : result) {
            OrganizationDetails details = client.get_organization_details(p.organization);

        }

        TypeAheadResponse resp = client.get_typeahead("cam");
        
        Prospect[] results = client.search_prospects(ProspectsAPI.SEARCH_TYPE_CITY, "Omaha");
        results = client.search_prospects(ProspectsAPI.SEARCH_TYPE_CITY, "Omaha");
        
        client.hide_prospect("jimmyJohns");
        
        HiddenProspect[] hidden = client.get_hidden_prospects();
        
        client.unhide_prospect("jimmyJohns");
    }

    
    
    private static void performNutureTests() {
        NutureAPI client = new NutureAPI("demo");

        Campaign[] campaigns = client.get_campaigns();

        for (int x = 0; x < 2; x++) {
            NurtureLead[] leads = client.get_leads_for_campaign(campaigns[x]);
            System.out.println("This campaign has " + leads.length + " leads in it.");
            System.out.println("Deleting the first lead.");
            client.remove_lead_from_campaign(leads[0].leadGuid, campaigns[x].guid);
            leads = client.get_leads_for_campaign(campaigns[x]);
            System.out.println("This campaign now has " + leads.length + " leads in it.");

            HistoryItem[] items = client.get_lead_campaign_history(leads[x]);
            System.out.println("This lead has " + items.length + " history items.");
        }


    }

    private static void performEventTests() {
        EventsAPI marketingClient = new EventsAPI("demo");
        Event[] events =
                marketingClient.get_events(100);

        Event foo = new Event("description", "http://www.google.com", "eventType");
        marketingClient.create_event(foo);
    }

    private static void performLeadTests() {
        // Testing out lead searching  
        LeadsAPI client = new LeadsAPI("demo");
        LeadSearchCriteria criteria = new LeadSearchCriteria();
        criteria.pageSize = 100;
        criteria.sortField = "lastName";
        criteria.direction = SortDirection.descending;
        criteria.keyword = "Fallon";
        Lead[] leads = client.get_leads(criteria);
        
        Lead l1 = client.get_lead(leads[0].guid);
        Lead l2 = client.get_lead_by_user_token(leads[0].userToken);
        Lead l3 = client.get_lead_by_email(leads[0].email);
        Lead l4 = null;
        if (leads[0].leadConversionEvents != null && leads[0].leadConversionEvents.length > 0)
        {
            l4 = client.get_lead_by_conversion_event_guid(leads[0].leadConversionEvents[0].guid);
        }
        
        
        System.out.println(leads[0].guid);

        // Testing retrival of a specific lead
        Lead specificLead = client.get_lead(leads[3].guid);
        System.out.println(specificLead.lastName + ", " + specificLead.firstName);

        HashMap newValues = new HashMap();
        newValues.put("lastName", "Fallon");
        newValues.put("firstName", "Jimmy");
        client.update_lead(leads[3].guid, newValues);

        //Testing web hook retrieval
        WebHook[] s = client.get_webhooks();

        client.register_webhook("https://www3.bellevue.edu/forms/HubspotWebHookDemo/demo.cgi");

        client.delete_webhook(s[0].guid);
    }
}
