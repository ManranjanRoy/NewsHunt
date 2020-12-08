package com.manoranjan.newshunt1.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.manoranjan.newshunt1.Adaptor.BrowseSingleNewsAdaptor;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.StaticData.CatagoryList;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class SingleNewsActivity extends AppCompatActivity {
    PlayerView videoFullScreenPlayer;
    SimpleExoPlayer player;
    private ProgressBar progressBar;
    private String filePath = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_news);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        BrowseSingleNewsAdaptor adapter = new BrowseSingleNewsAdaptor(getApplicationContext(), CatagoryList.newsListModels);
        viewPager.setAdapter(adapter);
        // viewPager.setCurrentItem(StaticData.img_postion);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position > 1) {
                    if (position % 2 == 0) {
                        showalert1(position);
                    } else {
                        System.out.println("Given number is not divisible by 3");
                    }
                }
                // Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }

    private void showalert1(final int position) {

        //  final EditText input = new EditText(ProductDetailsActivity.this);
        //   input.setHint("    Enter Pincode");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.SheetDialog1)
                .setView(R.layout.dialoglayout1)
                .create();

        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // setUp();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {
                final ImageView cancel = dialog.findViewById(R.id.cancel);
                final TextView coundcown = dialog.findViewById(R.id.countdown);
                ImageView adsimage = dialog.findViewById(R.id.adsimage);
                videoFullScreenPlayer = dialog.findViewById(R.id.videoFullScreenPlayer);
                progressBar = dialog.findViewById(R.id.progressBar);
                VideoView simpleVideoView = dialog.findViewById(R.id.adsvideo);
                if (position == 4) {
                    progressBar.setVisibility(View.VISIBLE);
                    adsimage.setVisibility(View.GONE);
                    videoFullScreenPlayer.setVisibility(View.VISIBLE);
                   /* simpleVideoView.setVisibility(View.VISIBLE);
                    simpleVideoView.setVideoURI(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
                    simpleVideoView.start();*/
                    setUp();
                } else {
                    progressBar.setVisibility(View.GONE);
                    adsimage.setVisibility(View.VISIBLE);
                    videoFullScreenPlayer.setVisibility(View.GONE);
                    simpleVideoView.setVisibility(View.GONE);
                }
                new CountDownTimer(10000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        coundcown.setText((String.valueOf(millisUntilFinished / 1000)));
                    }

                    public void onFinish() {
                        coundcown.setVisibility(View.GONE);
                        cancel.setVisibility(View.VISIBLE);
                        coundcown.setText("done!");
                    }
                }.start();

                cancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something

                        //Dismiss once everything is OK.
                        dialog.dismiss();
                    }
                });
            }
        });
        dialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    // finish();
                    //  dialog.dismiss();
                }
                return true;
            }
        });
        dialog.show();
    }

    private void setUp() {
        initializePlayer();
        if (filePath == null) {
            return;
        }
        buildMediaSource(Uri.parse(filePath));
    }

    private void initializePlayer() {
        if (player == null) {

            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
            // 1. Create a default TrackSelector
            //DefaultLoadControl loadControl = new DefaultLoadControl.Builder().setBufferDurationsMs(32*1024, 64*1024, 1024, 1024).createDefaultLoadControl();
            // 2. Create the player
            player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            videoFullScreenPlayer.setPlayer(player);
        }
    }

    private void buildMediaSource(Uri mUri) {
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getString(R.string.app_name)), bandwidthMeter);
        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(mUri);
        // Prepare the player with the source.
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
        player.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                switch (playbackState) {
                    case Player.STATE_BUFFERING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case Player.STATE_ENDED:
                        // Activate the force enable
                        break;
                    case Player.STATE_IDLE:
                        break;
                    case Player.STATE_READY:
                        progressBar.setVisibility(View.GONE);
                        break;
                    default:
                        // status = PlaybackStatus.IDLE;
                        break;
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    private void pausePlayer() {
        if (player != null) {
            player.setPlayWhenReady(false);
            player.getPlaybackState();
        }
    }

    private void resumePlayer() {
        if (player != null) {
            player.setPlayWhenReady(true);
            player.getPlaybackState();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pausePlayer();
       /* if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }*/
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        resumePlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }


}
