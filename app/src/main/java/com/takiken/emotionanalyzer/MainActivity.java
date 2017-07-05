package com.takiken.emotionanalyzer;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.io.File;
import java.io.FileWriter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

import android.view.Window;

import com.takiken.emotionanalyzer.R;


public class MainActivity extends AppCompatActivity {

    //ファイル名
    private String fileName = "Emotion.csv";

    private Timer timer = new Timer(true);
    private ScheduledExecutorService mScheduledExecutor;
    private TextView tapScreenTextView;
    private boolean isDisp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tapScreenTextView = (TextView) findViewById(R.id.textView2);

        init();
    }

    private void init() {

        final android.os.Handler handler = new android.os.Handler();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        handler.post( new Runnable(){
                            public void run(){
                                isDisp = !isDisp;

                                //TextViewの表示を切り替える
                                if (isDisp) {
                                    tapScreenTextView.setText("電話対応が終わる度に焦りレベルを選択してください");
                                } else {
                                    tapScreenTextView.setText("");
                                }
                            }
                        });
                    }
                }
                , 600000, 500   //開始遅延(何ミリ秒後に開始するか)と、周期(何ミリ秒ごとに実行するか)
        );
    }

    private void stopAndRun() {

        timer.cancel();
        timer = new Timer(true);
        init();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void changeLabel1(View view) {

        String msg;

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int ms = calendar.get(Calendar.MILLISECOND);

        msg = String.valueOf(year + "/" + (month + 1) + "/" + day + "/" + " " +
                hour + ":" + minute + ":" + second + "." + ms) + ",1" + "\n";


        TextView tv  = (TextView)findViewById(R.id.textView1);

        tv.setText(msg);

        writeSensorData(fileName, msg);

        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#EF5FA7"));

        stopAndRun();


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void changeLabel2(View view) {

        String msg;

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int ms = calendar.get(Calendar.MILLISECOND);

        msg = String.valueOf(year + "/" + (month + 1) + "/" + day + "/" + " " +
                hour + ":" + minute + ":" + second + "." + ms) + ",2" + "\n";


        TextView tv  = (TextView)findViewById(R.id.textView1);

        tv.setText(msg);

        writeSensorData(fileName, msg);

        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FAE232"));

        stopAndRun();


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void changeLabel3(View view) {

        String msg;

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int ms = calendar.get(Calendar.MILLISECOND);

        msg = String.valueOf(year + "/" + (month + 1) + "/" + day + "/" + " " +
                hour + ":" + minute + ":" + second + "." + ms) + ",3" + "\n";


        TextView tv  = (TextView)findViewById(R.id.textView1);

        tv.setText(msg);

        writeSensorData(fileName, msg);

        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#61D836"));

        stopAndRun();


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void changeLabel4(View view) {

        String msg;

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int ms = calendar.get(Calendar.MILLISECOND);

        msg = String.valueOf(year + "/" + (month + 1) + "/" + day + "/" + " " +
                hour + ":" + minute + ":" + second + "." + ms) + ",4" + "\n";


        TextView tv  = (TextView)findViewById(R.id.textView1);

        tv.setText(msg);

        writeSensorData(fileName, msg);

        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#00A2FF"));

        stopAndRun();


    }



    private void writeSensorData(String fileName, String message) {
        String filePath = this.getExternalFilesDir(null).getAbsolutePath() + "/" + fileName;
        System.out.println(filePath);
        // String filePath = Environment.getDataDirectory() + "/" + fileName;
        // Log.d(TAG, filePath);
        try {
            File outputFile = new File(filePath);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(outputFile, true);
            fileWriter.write(message);
            fileWriter.close();
        } catch (IOException e) {
        }
    }

}
