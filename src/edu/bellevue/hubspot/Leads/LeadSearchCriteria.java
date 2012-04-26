/**
* Copyright 2012 Bellevue University.
*
*   Licensed under the Apache License, Version 2.0 (the 
* "License"); you may not use this file except in compliance 
* with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, 
* software distributed under the License is distributed on an 
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
* either express or implied.  See the License for the specific 
* language governing permissions and limitations under the 
* License.
*/
package edu.bellevue.hubspot.Leads;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author TSLATER
 */
public class LeadSearchCriteria {

    public String Keyword;
    public String SortField;
    public SortDirection Direction;
    public int PageSize;
    public int PageNumber;
    public Calendar StartDateUtc;
    public Calendar StopDateUtc;
    public TimePivot StartStopDateType;
    public Boolean ExcludeConversionEvents;
    public Boolean IncludeOptOutLeads;
    public Boolean IncludeOnlyEmailEligibleLeads;
    public Boolean IncludeOnlyBouncedLeads;
    public Boolean IncludeOnlyNonImportedLeads;
    public Boolean IncludeDeletedLeads;
    public List<String> SpecificLeadGuids;

    public LeadSearchCriteria() {
        Keyword = "";
        SortField = "";
        PageSize = 100;
        PageNumber = 1;
        Direction = SortDirection.NotSet;
        StartDateUtc = Calendar.getInstance();
        StartDateUtc.set(1970, 1, 1, 0, 0, 0);
        StopDateUtc = Calendar.getInstance();
        StartStopDateType = TimePivot.NotSet;
        SpecificLeadGuids = new ArrayList<String>();
        IncludeDeletedLeads = false;
    }

    public HashMap toHashMap() {
        HashMap map = new HashMap();

        if (keyword.trim().length() > 0) {
            map.put("search", keyword);
        }

        if (sortField.trim().length() > 0) {
            map.put("sort", sortField);
            if (direction == SortDirection.ascending) {
                map.put("dir", "asc");
            }
            if (direction == SortDirection.descending) {
                map.put("dir", "desc");
            }
        }

        if (this.pageSize >= 1) {
            map.put("max", Integer.toString(pageSize));
            if (pageNumber > 1) {
                int offset = pageNumber - 1 * pageSize;
                map.put("offset", Integer.toString(offset));
            }
        }

        if (this.startStopDateType != TimePivot.notSet) {
            double start = startDateUtc.getTimeInMillis();
            double stop = stopDateUtc.getTimeInMillis();
            map.put("startTime", Double.toString(start));
            map.put("stopTime", Double.toString(stop));

            map.put("timePivot", startStopDateType.getValue());
        }

        if (excludeConversionEvents != null) {
            map.put("excludeConversionEvents", excludeConversionEvents.toString().toLowerCase());
        }

        if (includeOptOutLeads != null) {
            map.put("optout", includeOptOutLeads.toString().toLowerCase());
        }
        if (includeOnlyEmailEligibleLeads != null) {
            map.put("eligibleForEmail", includeOnlyEmailEligibleLeads.toString().toLowerCase());
        }
        if (includeOnlyBouncedLeads != null) {
            map.put("bounced", includeOnlyBouncedLeads.toString().toLowerCase());
        }
        if (includeOnlyNonImportedLeads != null) {
            map.put("isNotImported", includeOnlyNonImportedLeads.toString().toLowerCase());
        }
        if (IncludeDeletedLeads)
        {
            map.put("includeDeleted","true");
        }

        int counter = 0;
        for (String s : this.specificLeadGuids) {
            map.put("guids[" + Integer.toString(counter).trim() + "]", s);
            counter++;
        }
        
        return map;
    }
}
