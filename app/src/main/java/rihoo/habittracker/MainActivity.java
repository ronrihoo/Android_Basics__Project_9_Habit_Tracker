package rihoo.habittracker;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testGetAll();

        /*
        testInsertion("Naproxen", 500);
        testGetAll();
        /**/

        /*
        int id = 14;
        testUpdating(id, "Motrin", 800);
        testReading(id);
        /**/


        /*
        testReading(1);
        testGetAll();
        /**/

        /*
        testDelete(1);
        testGetAll();
        /**/

        /*
        testDeleteAllRows();
        testGetAll();
        /**/
    }

    public void testInsertion(String name, int dose) {
        MedicationCRUD entry = new MedicationCRUD(this);
        Medication medication = new Medication();

        medication.name = name;
        medication.dose = dose;

        entry.insert(medication);
    }

    public void testUpdating(int id, String name, int dose) {
        MedicationCRUD entry = new MedicationCRUD(this);
        Medication medication = new Medication();

        medication.drug_ID = id; // _Medication_Id;
        medication.name = name;
        medication.dose = dose;

        entry.update(medication);
    }

    public void testReading(int med_Id) {
        MedicationCRUD entry = new MedicationCRUD(this);

        Medication medication = new Medication();

        medication = entry.getMedicationById(med_Id);

        Log.e("Id: ", toString().valueOf(medication.drug_ID));
        Log.e("Name: ", medication.name);
        Log.e("Dose: ", toString().valueOf(medication.dose));
    }

    public void testGetAll() {
        MedicationCRUD entry = new MedicationCRUD(this);

        ArrayList<HashMap<String, String>> medicationList = entry.getMedicationList();

        if (medicationList.size() != 0) {
            for (HashMap<String, String> medication : medicationList) {
                Log.e("Id: ", medication.get("id"));
                Log.e("Name: ", medication.get("name"));
                Log.e("Dose: ", medication.get("dose"));
            }
        } else {
            Log.e("Get_All: ", "No medication items to list.");
        }
    }

    public void testDelete(int id) {
        MedicationCRUD entry = new MedicationCRUD(this);

        entry.delete(id);
    }

    public void testDeleteAllRows() {
        MedicationCRUD entry = new MedicationCRUD(this);

        entry.deleteAllRows(Contract.MedicationTable.TABLE_name);
    }
}
