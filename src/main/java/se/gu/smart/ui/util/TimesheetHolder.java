package se.gu.smart.ui.util;

import se.gu.smart.model.Timesheet;
import se.gu.smart.model.TimesheetEntry;

public final class TimesheetHolder {

    private TimesheetEntry timesheetEntry;
    private Timesheet timesheet;

    private final static TimesheetHolder TIMESHEET_INSTANCE = new TimesheetHolder();

    private TimesheetHolder() {

    }

    public static TimesheetHolder getInstance() {
        return TIMESHEET_INSTANCE;
    }

    public Timesheet getTimesheet() {
        return timesheet;
    }

    public TimesheetEntry getTimesheetEntry() {
        return timesheetEntry;
    }

    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
    }

    public void setTimesheetEntry(TimesheetEntry timesheetEntry) {
        this.timesheetEntry = timesheetEntry;
    }
}
