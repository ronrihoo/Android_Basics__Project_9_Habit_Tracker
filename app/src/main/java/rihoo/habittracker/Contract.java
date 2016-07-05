package rihoo.habittracker;

import android.content.Context;
import android.provider.BaseColumns;


public class Contract  {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "crud.db";

    public Contract(Context context) {

    }

    public class MedicationTable implements BaseColumns {
        public static final String TABLE_name = "drugs";
        public static final String KEY_ID = "id";
        public static final String KEY_name = "name";
        public static final String KEY_dose = "dose";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_name + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + KEY_name + " TEXT, "
                + KEY_dose + " INTEGER )";
    }

}