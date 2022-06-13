package hcmute.spkt.group5.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.textclassifier.TextClassifierEvent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class _3x3Activity extends AppCompatActivity {

    ImageButton btnBack;    // Button use for back to ChooseOptionActivity
    TextView timeX, timeO;
    Timer timerX;
    Timer timerO;
    TimerTask timerTaskX;
    TimerTask timerTaskO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3x3);

        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_3x3Activity.this, ChooseOptionActivity.class);
                startActivity(intent);
            }
        });

        timeX = (TextView) findViewById(R.id.time_X);
        timerX = new Timer();
        timerTaskX = initializeTimerTask(timeX);

        timeO = (TextView) findViewById(R.id.time_O);
        timerO = new Timer();
        timerTaskO = initializeTimerTask(timeO);
    }

    // 0: empty; 1: O; 2: X
    int activePlayer = 2;   // X will go first
    int[][] matrix = {
            {0,0,0},
            {0,0,0},
            {0,0,0}
    };

    public void dropIn(View view){
        ImageView index = (ImageView) view;
        TextView tvLoser = (TextView) findViewById(R.id.tv_loser);
        int[] position = convertTagToPosition(index.getTag().toString());
        if(activePlayer == 2 && matrix[position[0]][position[1]] == 0){
            index.setImageResource(R.drawable.icon_x);
            activePlayer = 1;

            matrix[position[0]][position[1]] = 2;

            timerTaskO = initializeTimerTask(timeO);
            timerO.schedule(timerTaskO, 0 , 1000);
            timerTaskX.cancel();
            timeX.setText("10s");
        }
        else if(activePlayer == 1 && matrix[position[0]][position[1]] == 0) {
            index.setImageResource(R.drawable.icon_o);
            activePlayer = 2;

            matrix[position[0]][position[1]] = 1;

            timerTaskX = initializeTimerTask(timeX);
            timerX.schedule(timerTaskX, 0 , 1000);
            timerTaskO.cancel();
            timeO.setText("10s");
        }
    }

    // Function use to convert from Tag attribute to Position in matrix
    public int[] convertTagToPosition(String tag){
        int row, col;
        row = Character.getNumericValue(tag.charAt(0));
        col = Character.getNumericValue(tag.charAt(1));
        int[] position = {row, col};
        return position;
    }

    // Function use to initialize for TimerTask
    public TimerTask initializeTimerTask(TextView tv){
        return new TimerTask() {
            int sec = 10;
            int t = 1;
            @Override
            public void run() {
                if(sec>0){
                    sec--;
                    String s = String.valueOf(sec) + 's';
                    tv.setText(s);
                }
            }
        };
    }

    // Function use to show dialog notifies who is loser and play again
    public void showLosingDialog(){
        final Dialog dialog = new Dialog(_3x3Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_losing);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        Button btnCancel = dialog.findViewById(R.id.btn_cancel_dialog);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}
