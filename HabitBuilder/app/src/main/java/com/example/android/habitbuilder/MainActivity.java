package com.example.android.habitbuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper databaseHelper = new DBHelper(this);
        System.out.println("database created in" + databaseHelper.getDb().getPath());

        // add some habits to the database
        databaseHelper.insertHabit("Waking Up Early", "21");
        databaseHelper.insertHabit("Run for 30 mins", "21");
        databaseHelper.insertHabit("Watch a TED talk daily", "10");
        System.out.println(databaseHelper.getHabits().getCount() + "Habits created");

        // edit the first habit
        databaseHelper.updateHabit("Waking Up Early", "11");

        // get all habits
        databaseHelper.getHabits();

        // delete all habits
        databaseHelper.deleteAllHabits();
        System.out.println(databaseHelper.getHabits().getCount() + " Habits found after deleting all");

    }
}
