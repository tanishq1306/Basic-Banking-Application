package com.example.basicbankingapp.DB;

import android.provider.BaseColumns;

public final class UserContract {
    private UserContract() {}

    public static final class UserEntry implements BaseColumns {
        /** Name of database table for pets */
        public final static String TABLE_NAME = "user";

        /** Table Fields */
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_USER_NAME ="name";
        public final static String COLUMN_USER_ACCOUNT_NUMBER ="accountNo";
        public final static String COLUMN_USER_EMAIL ="email";
        public final static String COLUMN_USER_IFSC_CODE ="ifscCode";
        public final static String COLUMN_USER_PHONE_NO ="phoneNo";
        public final static String COLUMN_USER_ACCOUNT_BALANCE ="balance";
    }
}
