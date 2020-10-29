package com.catata.audioyvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import java.net.URI;

public class Video extends AppCompatActivity {

    VideoView video;
    ImageButton pause, play, stop;

    String videourl = "rtsp://r2---sn-5hne6ns6.googlevideo.com/Cj0LENy73wIaNAlVF4L9GilIoBMYESARFC01IppfMOCoAUIASARgwcrB0-C0x8lfigELS1ZjYXNXMHJJc1UM/883FAADC0A1E5DD4F9E5EA52938E6B191BA44C2D.2ACF26756248B9621796E9BF51147D710E5690F2/yt8/1/video.3gp";

    private static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        play = (ImageButton) findViewById(R.id.btnReproducirVideo);
        pause = (ImageButton) findViewById(R.id.btnPauseVideo);
        stop = (ImageButton) findViewById(R.id.btnStopVideo);


        video = (VideoView) findViewById(R.id.videoView);


        //Mostramos el dialogo
        progressDialog = ProgressDialog.show(Video.this, "", "Cargando video...", true);
        progressDialog.setCancelable(true);

        PlayVideo();





        //Para coger video de raw
        //video.setVideoURI(Uri.parse("android.resource://"+ getPackageName() + "/" + R.raw.video));





        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video.pause();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //libera la isntancia de mediaplayer
                video.stopPlayback();
            }
        });
    }

    private void PlayVideo() {
        try {


            //Saca sus propios controles
            //getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController = new MediaController(Video.this);
            mediaController.setAnchorView(video);

            Uri videoUri = Uri.parse(videourl);
            video.setMediaController(mediaController);
            video.setVideoURI(videoUri);
            video.requestFocus();

            video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
                    progressDialog.dismiss();
                    video.start();
                }
            });

        }catch(Exception e)
        {
            progressDialog.dismiss();
            System.out.println("Video Play Error :"+e.toString());
            finish();
        }
    }

    public void irYoutube(View view) {
        Intent i = new Intent(this, Youtube.class);
        startActivity(i);

    }
}