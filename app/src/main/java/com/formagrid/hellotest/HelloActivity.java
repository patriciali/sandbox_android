package com.formagrid.hellotest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class HelloActivity extends Activity {

    private static final String IMAGE_URL_GOOD = "http://www.desicomments.com/wp-content/uploads/LOL-With-Emoji.jpg";
    private static final String IMAGE_URL_BAD = "https://forma_app_filepicker_dev-test-staging.s3.amazonaws.com/profilePics%2FGPeekrQMepSJvusaFOng_66.png?sz=36";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        ImageView topImageView = (ImageView) findViewById(R.id.image_view_top);
        ImageView bottomImageView = (ImageView) findViewById(R.id.image_view_bottom);

        Picasso.with(this)
                .setLoggingEnabled(true);
        Picasso.with(this)
                .load(IMAGE_URL_GOOD)
                .into(topImageView);
        Picasso.with(this)
                .load(IMAGE_URL_BAD)
                .into(bottomImageView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
