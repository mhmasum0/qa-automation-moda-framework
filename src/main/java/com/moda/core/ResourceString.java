package com.moda.core;

public class ResourceString {
    private ResourceString() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    // Login Page
    public static final  String LOGIN_ERROR_TEXT = "Sorry, that password was incorrect. Remember, your password cannot contain special characters and must be between 6 to 15 characters in length.";

    // Dashboard Page
    public static final  String WELCOME_MESSAGE = "welcome to your Member Dashboard";
    public static final  String PCP_360_ELIGIBILITY = "PCP360";
    public static final String[] BH_360_GROUPS = {"OEBB", "CPH", "SPH", "Leatherman", "Trailblazers"};
    public static final String[] WITHOUT_BH_360_GROUPS = { "PEBB", "Salem Health", "OHSU"};

    // Moda 360 Programs
    public static final  String ORAL_HEALTH_PDF_CONTENT = "Health through Oral Wellness";

    // Addiction care with Hazelden Betty Ford
    public static final String HAZELDEN_BETTY_FORD = "Hope. Health. Healing.";

    // Care Reminders Page
    public static final String CARE_REMINDERS = "Care Reminders";
    public static final String YEARLY_DENTAL_EXAM_AND_CLEANING_SNOOZED = "Yearly Dental Exam and Cleaning";
    private static String firstCareReminder;

    public static void setFirstCareReminder(String first){
        firstCareReminder = first;
    }

    public static String getFirstCareReminder(){
        return firstCareReminder;
    }
}
