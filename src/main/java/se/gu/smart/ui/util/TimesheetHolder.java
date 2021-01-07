package se.gu.smart.ui.util;

import se.gu.smart.model.project.timesheet.Timesheet;

import java.util.Optional;

public final class TimesheetHolder {

    private Optional<Timesheet> timesheet;

    private final static TimesheetHolder TIMESHEET_INSTANCE = new TimesheetHolder();

    private TimesheetHolder() {

    }

    public static TimesheetHolder getInstance() {
        return TIMESHEET_INSTANCE;
    }

    public Optional<Timesheet> getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(Optional<Timesheet> timesheet) {
        this.timesheet = timesheet;
    }
}
