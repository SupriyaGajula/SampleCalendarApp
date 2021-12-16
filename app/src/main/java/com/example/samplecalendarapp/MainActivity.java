package com.example.samplecalendarapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import java.util.Calendar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView myDate;
    Button dispBtn;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CalendarView) findViewById(R.id.calenderview);
        myDate = (TextView) findViewById(R.id.myDate);
        Button dispBtn = (Button) findViewById(R.id.dispbut);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 +1) + "/" + i2 +"/"+ i;
                myDate.setText(date);

            }
        });
        dispBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    public void disp() {
        Calendar startTime = Calendar.getInstance();
        startTime.set(2010, 2, 21, 9, 9);
        Uri uri = Uri.parse("content://com.android.calendar/time/"
                + String.valueOf(startTime.getTimeInMillis()));

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
// The calendar app will be open with appropriate date displayed
        startActivity(intent);
    }
    public void insert() {
        Intent intent = new Intent(Intent.ACTION_INSERT,
                CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.Events.TITLE, "Final Presentation!");
        intent.putExtra(CalendarContract.Events.DESCRIPTION,
                "Prepare for the Presentation");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION,
                "UMKC.com");
        Calendar startTime = Calendar.getInstance();
        startTime.set(2021, 12, 14);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                startTime.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}