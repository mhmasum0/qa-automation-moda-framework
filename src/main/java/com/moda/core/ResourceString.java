package com.moda.core;

public class ResourceString {

    private ResourceString() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final  String LOGIN_ERROR_TEXT = "Sorry, that password was incorrect. Remember, your password cannot contain special characters and must be between 6 to 15 characters in length.";

    public static final  String WELCOME_MESSAGE = "welcome to your Member Dashboard";
    public static final  String PCP_360_ELIGIBILITY = "PCP360";
    protected static final String[] BH_360_GROUPS = {
            "OEBB",
            "CPH",
            "SPH",
            "Leatherman",
            "Trailblazer"
    };
    protected static final String[] WITHOUT_BH_360_GROUPS = {
            "PEBB",
            "Salem Health",
            "OHSU"
    };
    protected static final String[] BH360ProgramList = {
            "Charlie Health",
            "Cyti Psychological",
            "Equip", "NOCD",
            "Behavioral Health Champions",
            "Behavioral Health 360 Care Coordination",
            "Child skill development with Gemiini",
            "Addiction care with Hazelden Betty Ford",
            "Mental health support with Spring Health",
            "Mobile therapy with Meru Health"
    };

    public static final  String ORAL_HEALTH_PDF_CONTENT = "Health through Oral Wellness";

    public static final String HAZELDEN_BETTY_FORD = "Hope. Health. Healing.";

    public static final String CARE_REMINDERS = "Care Reminders";
    protected static String firstCareReminder;
    protected static String snoozedCareReminder;

    public static void setSnoozedCareReminder(String snoozed){
        snoozedCareReminder = snoozed;
    }

    public static String getSnoozedCareReminder(){
        return snoozedCareReminder;
    }

    public static void setFirstCareReminder(String first){
        firstCareReminder = first;
    }

    public static String getFirstCareReminder(){
        return firstCareReminder;
    }

    public static String[] getBHGroups(){
        return BH_360_GROUPS;
    }

    public static String[] getBH360ProgramList(){
        return BH360ProgramList;
    }

    public static String[] getWithoutBh360Groups(){
        return WITHOUT_BH_360_GROUPS;
    }
}
