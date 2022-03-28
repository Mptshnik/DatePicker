package com.example.datepickerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    private TextView _textViewTime;
    private Button _btnEditTime;
    private Button _btnEditDate;
    private Calendar _dateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _textViewTime = findViewById(R.id.textViewTime);
        _btnEditTime = findViewById(R.id.btnEditTime);
        _btnEditDate = findViewById(R.id.btnEditDate);

        _dateTime = Calendar.getInstance();


        _textViewTime.setText(DateUtils.formatDateTime(this,
                _dateTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_ABBREV_MONTH |
                DateUtils.FORMAT_SHOW_TIME));

        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                _dateTime.set(Calendar.YEAR, year);
                _dateTime.set(Calendar.MONTH, month);
                _dateTime.set(Calendar.DAY_OF_MONTH, day);

                _textViewTime.setText(DateUtils.formatDateTime(getApplicationContext(), _dateTime.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME));
            }
        };
        _btnEditDate.setOnClickListener(view -> new DatePickerDialog(MainActivity.this,d,
                _dateTime.get(Calendar.YEAR),
                _dateTime.get(Calendar.MONTH),
                _dateTime.get(Calendar.DAY_OF_MONTH))
                .show()
        );

        TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                _dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                _dateTime.set(Calendar.MINUTE, minute);

                _textViewTime.setText(DateUtils.formatDateTime(getApplicationContext(), _dateTime.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME));
            }
        };

        _btnEditTime.setOnClickListener(view -> new TimePickerDialog(MainActivity.this, t,
                _dateTime.get(Calendar.HOUR_OF_DAY), _dateTime.get(Calendar.MINUTE), true).show()
        );


    }
}