package com.Hostel.Accomomate;

import android.provider.BaseColumns;

public class DataBaseContract {
    private DataBaseContract() {
    }

    public static class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "students";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_FATHER_NAME = "father_name";
        public static final String COLUMN_NAME_FATHER_PHONE = "father_phone";
        public static final String COLUMN_NAME_MOTHER_NAME = "mother_name";
        public static final String COLUMN_NAME_MOTHER_PHONE = "mother_phone";
        public static final String COLUMN_NAME_REGISTER_NUMBER = "register_number";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_DEPARTMENT = "department";
    }
    }

