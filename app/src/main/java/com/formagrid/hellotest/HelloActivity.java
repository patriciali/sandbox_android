package com.formagrid.hellotest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

public class HelloActivity extends Activity {

    private static final String VIDEO_RESOURCE_URI =
            //"android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.raw.overview_video;
            "android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.raw.sample_video;

    private MediaPlayer mSoundMediaPlayer;
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSoundMediaPlayer = MediaPlayer.create(this, R.raw.wings);
        mSoundMediaPlayer.setLooping(false);

        mVideoView = (VideoView) findViewById(R.id.video_view);
        Uri videoUri = Uri.parse(VIDEO_RESOURCE_URI);
        mVideoView.setVideoURI(videoUri);
        mVideoView.setMediaController(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSoundMediaPlayer.release();
        mSoundMediaPlayer = null;

        mVideoView = null;
    }

    @Override
    protected void onPause() {
        super.onPause();

        mSoundMediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSoundMediaPlayer.seekTo(0);
        mSoundMediaPlayer.start();
        mVideoView.start();
    }

    public void onClickHint(View view) {
        Log.d("patricia", "click");
    }

}
