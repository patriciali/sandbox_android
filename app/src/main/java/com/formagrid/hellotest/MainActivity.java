package com.formagrid.hellotest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String cellNumber = "123-456-7890";

        if (false) {
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
            sendIntent.setData(Uri.parse("sms:" + cellNumber));
            sendIntent.putExtra("exit_on_sent", true);
            startActivity(sendIntent);
        }

        if (true) {
            String uri = "tel:" + cellNumber;
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(uri));
            startActivity(intent);
        }
    }

}
