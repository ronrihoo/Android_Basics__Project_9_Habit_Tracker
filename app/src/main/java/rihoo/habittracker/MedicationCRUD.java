package rihoo.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;


public class MedicationCRUD {

    private DatabaseHelper dbHelper;

    public MedicationCRUD(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public int insert(Medication medication) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Contract.MedicationTable.KEY_name, medication.name);
        values.put(Contract.MedicationTable.KEY_dose, medication.dose);

        long medication_Id = db.insert(Contract.MedicationTable.TABLE_name, null, values);
        db.close();
        return (int) medication_Id;
    }

    public void delete(int medication_Id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(Contract.MedicationTable.TABLE_name, Contract.MedicationTable.KEY_ID + "= ?",
                new String[]{String.valueOf(medication_Id)});
        db.close();
    }

    public void deleteAllRows(String tableName) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(tableName, null, null);
        db.close();
    }

    public void update(Medication medication) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Contract.MedicationTable.KEY_name, medication.name);
        values.put(Contract.MedicationTable.KEY_dose, medication.dose);

        db.update(Contract.MedicationTable.TABLE_name, values, Contract.MedicationTable.KEY_ID + "= ?",
                new String[]{String.valueOf(medication.drug_ID)});
        db.close();
    }

    public ArrayList<HashMap<String, String>> getMedicationList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<HashMap<String, String>> medicationList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT  " +
                Contract.MedicationTable.KEY_ID + "," +
                Contract.MedicationTable.KEY_name + "," +
                Contract.MedicationTable.KEY_dose +
                " FROM " + Contract.MedicationTable.TABLE_name;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> medication = new HashMap<String, String>();
                medication.put("id", cursor.getString(cursor.getColumnIndex(
                        Contract.MedicationTable.KEY_ID)));
                medication.put("name", cursor.getString(cursor.getColumnIndex(
                        Contract.MedicationTable.KEY_name)));
                medication.put("dose", cursor.getString(cursor.getColumnIndex(
                        Contract.MedicationTable.KEY_dose)));
                medicationList.add(medication);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return medicationList;
    }

    public Medication getMedicationById(int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT  " +
                Contract.MedicationTable.KEY_ID + "," +
                Contract.MedicationTable.KEY_name + "," +
                Contract.MedicationTable.KEY_dose +
                " FROM " + Contract.MedicationTable.TABLE_name
                + " WHERE " + Contract.MedicationTable.KEY_ID + "=?";

        Medication medication = new Medication();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});

        if (cursor.moveToFirst()) {
            do {
                medication.drug_ID = cursor.getInt(cursor.getColumnIndex(
                        Contract.MedicationTable.KEY_ID));
                medication.name = cursor.getString(cursor.getColumnIndex(
                        Contract.MedicationTable.KEY_name));
                medication.dose = cursor.getInt(cursor.getColumnIndex(
                        Contract.MedicationTable.KEY_dose));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return medication;
    }

}