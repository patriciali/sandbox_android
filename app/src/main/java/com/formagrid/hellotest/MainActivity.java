package com.formagrid.hellotest;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text_view);

        String json = "20018-07-03T00:00:00.000Z";
        GregorianCalendar cal = new GregorianCalendar(20015, 0, 3);
        Date date = cal.getTime();
        SimpleDateFormat mJsonDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat mCardDatetimeFormat = new SimpleDateFormat("LLL' 'd', 'yyyy', 'h':'mm' 'a");

//        try {
//            mTextView.setText(mCardDatetimeFormat.format(mJsonDateFormat.parse(json)));
//            //mTextView.setText(mCardDatetimeFormat.format(date));
//        } catch (ParseException e) {
//            mTextView.setText(e.toString());
//        }
        mTextView.setText(mCardDatetimeFormat.format(null));
    }

}
