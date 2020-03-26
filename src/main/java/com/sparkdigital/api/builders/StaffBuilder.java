package com.sparkdigital.api.builders;

import com.sparkdigital.api.domain.Staff;

/**
 * Builds a STAFF progressively.
 * The design, as well as the one for movie, goes like this because we preview to have
 * several more attributes added to the REST service. If that happens, we want to maintain the API's
 * forward and backward compatibility.
 */
public class StaffBuilder {
    private Staff staff;

    public Staff buildRawStaffInstance(String name) {
        staff = new Staff(name);
        return staff;
    }

    /**
     * Takes the old Staff record found, and merges the changes with it
     * updating the node.
     */
    public Staff mergedStaff(Staff foundStaff, Staff staff) {
        foundStaff.setBorn(staff.getBorn() != null?staff.getBorn():staff.getBorn());
        foundStaff.setName(staff.getName() != null?staff.getName():staff.getName());

        return foundStaff; //I return the found and modified staff, so we preserve the ID.
    }
}
