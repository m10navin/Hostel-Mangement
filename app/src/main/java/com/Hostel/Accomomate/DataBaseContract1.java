package com.Hostel.Accomomate;

import android.provider.BaseColumns;

public final class DataBaseContract1 {
    private DataBaseContract1() {}

    public static class FeeEntry implements BaseColumns {
        public static final String TABLE_NAME = "fee_table";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_REGISTER_NUMBER = "register_number";
        public static final String COLUMN_NAME_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_ROOM_NUMBER = "room_number";
        public static final String COLUMN_NAME_YEAR1 = "year";
        public static final String COLUMN_NAME_AMOUNT = "amount";
    }
}
