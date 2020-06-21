package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    Button btn;
    TextView textView;
    CountDownTimer countDownTimer;
    Boolean counterActive=false;

    public void start(View view){

        if(counterActive){

            countDownTimer.cancel();
            seekBar.setProgress(30);
            seekBar.setEnabled(true);
            btn.setText("start");
            textView.setText("00:30");
            counterActive=false;

        }
        else {
            btn.setText("reset");
            seekBar.setEnabled(false);
            counterActive=true;

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);
                    seekBar.setProgress((int)millisUntilFinished/1000);

                }

                @Override
                public void onFinish() {

                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ring);
                    mediaPlayer.start();

                }
            }.start();
        }

    }

    public void updateTimer(int secondsLeft){


        int min=secondsLeft/60;
        int sec=secondsLeft- min*60;
        String minutes=Integer.toString(min);
        String seconds=Integer.toString(sec);
        if(min<10){
            minutes="0"+minutes;
        }
        if(sec<=9)
        {
            seconds="0"+seconds;
        }
        textView.setText(minutes+":"+seconds);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=(SeekBar)findViewById(R.id.seekBar);
        btn=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.textView);

        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
