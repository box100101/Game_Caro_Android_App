package hcmute.spkt.group5.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChooseOptionActivity extends AppCompatActivity {

    ImageButton btnBack;    // Button use for back to home
    Button btn3x3;          // Button use for move to chessboard 3x3
    Button btn6x6;          // Button use for move to chessboard 6x6
    Button btn8x8;          // Button use for move to chessboard 8x8
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_options);

        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _intent(ChooseOptionActivity.this, MainActivity.class);
            }
        });

        btn3x3 = (Button) findViewById(R.id.btn_3x3);
        btn3x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _intent(ChooseOptionActivity.this, _3x3Activity.class);
            }
        });

        btn6x6 = (Button) findViewById(R.id.btn_6x6);
        btn6x6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _intent(ChooseOptionActivity.this, _6x6Activity.class);
            }
        });

        btn8x8 = (Button) findViewById(R.id.btn_8x8);
        btn8x8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _intent(ChooseOptionActivity.this, _8x8Activity.class);
            }
        });
    }

    //  Function use to move on between the activities
    private void _intent(Activity a, Class b){
        Intent intent = new Intent(a, b);
        startActivity(intent);
    }
}
