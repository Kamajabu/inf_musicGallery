package com.kamajabu.infmusicgallery.musicmanager;

import android.app.DialogFragment;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.kamajabu.infmusicgallery.R;
import com.kamajabu.infmusicgallery.model.Image;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Kamil Buczel on 09.04.2017.
 */

public abstract class MusicPlayerControls extends DialogFragment {

    protected ArrayList<Image> images;
    protected ViewPager viewPager;
    protected MyViewPagerAdapter myViewPagerAdapter;
    protected int selectedPosition = 0;

    //music player fields
    protected static final String RES_PREFIX = "android.resource://com.kamajabu.infmusicgallery/";

    // Media Player
    protected MediaPlayer mp;
    // Handler to update UI timer, progress bar etc,.
    protected Handler mHandler = new Handler();

    protected SongsManager songManager;
    protected Utilities utils;
    protected int seekForwardTime = 5000; // 5000 milliseconds
    protected int seekBackwardTime = 5000; // 5000 milliseconds
    protected int currentSongIndex = 0;
    protected boolean isShuffle = false;
    protected boolean isRepeat = false;
    protected ArrayList<HashMap<String, String>> songsList = new ArrayList<>();


    @BindView(R.id.btnPlay)
    ImageButton btnPlay;
    @BindView(R.id.btnForward)
    ImageButton btnForward;
    @BindView(R.id.btnBackward)
    ImageButton btnBackward;
    @BindView(R.id.btnNext)
    ImageButton btnNext;
    @BindView(R.id.btnPrevious)
    ImageButton btnPrevious;
    @BindView(R.id.btnPlaylist)
    ImageButton btnPlaylist;
    @BindView(R.id.btnRepeat)
    ImageButton btnRepeat;
    @BindView(R.id.btnShuffle)
    ImageButton btnShuffle;
    @BindView(R.id.songProgressBar)
    SeekBar songProgressBar;
    @BindView(R.id.songTitle)
    TextView songTitleLabel;
    @BindView(R.id.songCurrentDurationLabel)
    TextView songCurrentDurationLabel;
    @BindView(R.id.songTotalDurationLabel)
    TextView songTotalDurationLabel;
    private ImageView musicPicture;

    @OnClick(R.id.btnPlay)
    public void playButtonWasClicked() {
        // check for already playing
        if (mp.isPlaying()) {
            if (mp != null) {
                mp.pause();
                // Changing button image to play button
                btnPlay.setImageResource(R.drawable.btn_play);
            }
        } else {
            // Resume song
            if (mp != null) {
                mp.start();
                // Changing button image to pause button
                btnPlay.setImageResource(R.drawable.btn_pause);
            }
        }

    }

    @OnClick(R.id.btnForward)
    public void buttonForwardWasClicked() {

        // get current song position
        int currentPosition = mp.getCurrentPosition();
        // check if seekForward time is lesser than song duration
        if (currentPosition + seekForwardTime <= mp.getDuration()) {
            // forward song
            mp.seekTo(currentPosition + seekForwardTime);
        } else {
            // forward to end position
            mp.seekTo(mp.getDuration());
        }
    }

    @OnClick(R.id.btnBackward)
    public void buttonBackwardWasClicked() {
        // get current song position
        int currentPosition = mp.getCurrentPosition();
        // check if seekBackward time is greater than 0 sec
        if (currentPosition - seekBackwardTime >= 0) {
            // forward song
            mp.seekTo(currentPosition - seekBackwardTime);
        } else {
            // backward to starting position
            mp.seekTo(0);
        }

    }




}
