package com.sequenia.mvc.objects;

import java.util.Locale;

/**
 * Информация, отображаемая на главном экране.
 *
 * Created by chybakut2004 on 14.07.16.
 */

public class Info {

    private String firstName;
    private String lastName;

    public Info(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullInfo() {
        return String.format(
                Locale.US,
                "%s %s",
                firstName == null ? "" : firstName, lastName == null ? "" : lastName
        );
    }
}
