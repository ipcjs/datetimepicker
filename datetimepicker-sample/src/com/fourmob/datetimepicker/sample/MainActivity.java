package com.fourmob.datetimepicker.sample;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

public class MainActivity extends FragmentActivity implements com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    public static final String DATEPICKER_TAG = "datepicker";
    public static final String TIMEPICKER_TAG = "timepicker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Calendar calendar = Calendar.getInstance();

        findViewById(R.id.dateButton).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(MainActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), isVibrate());
                datePickerDialog.setVibrate(isVibrate());
                datePickerDialog.setYearRange(1985, 2028);
                datePickerDialog.setCloseOnSingleTapDay(isCloseOnSingleTapDay());
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
            }
        });

        findViewById(R.id.timeButton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean is24 = ((CheckBox) findViewById(R.id.is_24)).isChecked();
                final TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(MainActivity.this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), is24, false);
                timePickerDialog.setVibrate(isVibrate());
                timePickerDialog.setCloseOnSingleTapMinute(isCloseOnSingleTapMinute());
//                timePickerDialog.set
                timePickerDialog.show(getSupportFragmentManager(), TIMEPICKER_TAG);
            }
        });

        if (savedInstanceState != null) {
            DatePickerDialog dpd = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag(DATEPICKER_TAG);
            if (dpd != null) {
                dpd.setOnDateSetListener(this);
            }

            TimePickerDialog tpd = (TimePickerDialog) getSupportFragmentManager().findFragmentByTag(TIMEPICKER_TAG);
            if (tpd != null) {
                tpd.setOnTimeSetListener(this);
            }
        }
    }

    private boolean isVibrate() {
        return ((CheckBox) findViewById(R.id.checkBoxVibrate)).isChecked();
    }

    private boolean isCloseOnSingleTapDay() {
        return ((CheckBox) findViewById(R.id.checkBoxCloseOnSingleTapDay)).isChecked();
    }

    private boolean isCloseOnSingleTapMinute() {
        return ((CheckBox) findViewById(R.id.checkBoxCloseOnSingleTapMinute)).isChecked();
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        Toast.makeText(MainActivity.this, "new date:" + year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        Toast.makeText(MainActivity.this, "new time:" + hourOfDay + "-" + minute, Toast.LENGTH_LONG).show();
    }

    @SuppressLint("NewApi")
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.android_date:
            android.app.DatePickerDialog dpd = new android.app.DatePickerDialog(this, new OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                }
            }, 2014, 11, 11);
            dpd.show();
            break;
        case R.id.android_time:
            android.app.TimePickerDialog tpd = new android.app.TimePickerDialog(this, new OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                }
            }, 1, 1, true);
            tpd.show();
            break;
        case R.id.android_number:
            LinearLayout container = (LinearLayout) findViewById(R.id.container);
            NumberPicker np = new NumberPicker(this);
            String[] strs = new String[]{"第1个人","第2个人","第3个人","第4个人","第5个人","第6个人","第7个人","第8个人","第9个人"};
            np.setMinValue(0);
            np.setMaxValue(strs.length - 1);
            np.setDisplayedValues(strs);
            np.setWrapSelectorWheel(true);
            container.addView(np, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
            break;
        }

    }
}
