package com.catata.audioyvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    final int DELAY_TIME = 750;
    MediaPlayer mediaPlayer;
    ImageButton play, pause,stop;
    SeekBar seekBar;

    Handler handler;

    int playPosition = 0;
    Boolean playing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (ImageButton) findViewById(R.id.btnReproducirAudio);
        pause = (ImageButton) findViewById(R.id.btnPause);
        stop = (ImageButton) findViewById(R.id.btnStop);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        handler = new Handler();
        moverBarra();



        mediaPlayer = MediaPlayer.create(this,R.raw.musica);

        mediaPlayer.setVolume(10,10);


        int millis = mediaPlayer.getDuration();

        seekBar.setMax(millis);



        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playing = true;
                mediaPlayer.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing){
                    playing = false;

                }
                mediaPlayer.pause();

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing){
                    playing = false;
                }
                seekBar.setProgress(0);
                mediaPlayer.seekTo(0);
                mediaPlayer.pause();

            }
        });




        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(i);
                if(playing)
                    mediaPlayer.start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void irVideo(View view) {
        Intent i = new Intent(this,Video.class);
        mediaPlayer.stop();
        playing = false;
        startActivity(i);
    }

    private void moverBarra(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,DELAY_TIME);
            }
        },DELAY_TIME);
    }
}