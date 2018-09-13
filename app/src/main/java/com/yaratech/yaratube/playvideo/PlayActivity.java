package com.yaratech.yaratube.playvideo;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.yaratech.yaratube.R;

public class PlayActivity extends AppCompatActivity {

    private String mPlayVideoUrl = "";
    private Context mContext;
    private PlayerView mPlayerView;
    private SimpleExoPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mContext = getApplicationContext();
        mPlayVideoUrl = getIntent().getExtras().getString("url");

        // 1. Create a default TrackSelector
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        // 2. Create the player
        mPlayer =
                ExoPlayerFactory.newSimpleInstance(mContext, trackSelector);

        // Bind the player to the view.
        mPlayerView = findViewById(R.id.playerView);
        mPlayerView.setPlayer(mPlayer);

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(
                mContext, Util.getUserAgent(mContext, "name"));
        HlsMediaSource videoSource = new HlsMediaSource
                .Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(mPlayVideoUrl));

        mPlayer.prepare(videoSource);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.release();
    }
}
