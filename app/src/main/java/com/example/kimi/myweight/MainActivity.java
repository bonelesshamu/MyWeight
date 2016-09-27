package com.example.kimi.myweight;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.math.BigDecimal;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private BigDecimal mWeight = new BigDecimal("50.0");
    private Calendar mCal = Calendar.getInstance();

    private void showData() {
        Button weight = (Button)findViewById(R.id.weight);
        weight.setText(String.valueOf(mWeight));
        String today = mCal.get(Calendar.YEAR) + "/" + (mCal.get(Calendar.MONTH) + 1) + "/" + (mCal.get(Calendar.DATE));
        Button date = (Button)findViewById(R.id.date);
        date.setText(today);
    }

    private void setCalendar(int year, int month, int day){
        mCal.set(year, month, day);
        showData();
    }

    public static class MainDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("保存処理");
            builder.setMessage("保存します");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which){

                }
            });
            return builder.create();
        }
    }

    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    ((MainActivity) getActivity()).setCalendar(year, monthOfYear, dayOfMonth);
                }
            }, year, month, day);
        }
    }

    public void onSaveButtonTapped(View view){
        DialogFragment newFragment = new MainDialogFragment();
        newFragment.show(getSupportFragmentManager(), "MainDialogFragment");
    }

    public void onDateButtonTapped(View view){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "DatePickerFragment");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
