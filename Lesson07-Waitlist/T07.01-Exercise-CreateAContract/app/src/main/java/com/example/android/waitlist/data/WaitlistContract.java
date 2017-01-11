package com.example.android.waitlist.data;

import android.provider.BaseColumns;

public class WaitlistContract {


    // TODO (1) Create an inner class named WaitlistEntry class that implements the BaseColumns interface
    private final class WaitlistEntry implements BaseColumns {
        private static final String TABLE_NAME = "waitlist";
        private static final String COLUMN_GUEST_NAME = "guestName";
        private static final String COLUMN_PARTY_SIZE = "partySize";
        private static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
