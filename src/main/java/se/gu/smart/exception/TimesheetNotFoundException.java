package se.gu.smart.exception;

public class TimesheetNotFoundException extends RuntimeException {

    public TimesheetNotFoundException() {
        super(String.format("Timesheet was not found!"));
    }
}
