package se.gu.smart.exception;

import se.gu.smart.model.Timesheet;

public class TimesheetNotFoundException extends RuntimeException {

    public TimesheetNotFoundException() {
        super(String.format("Timesheet was not found!"));
    }
}
