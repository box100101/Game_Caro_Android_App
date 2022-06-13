package hcmute.spkt.group5.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class _8x8ActivityOnline extends AppCompatActivity {
    ImageButton btnBack;    // Button use for back to ChooseOptionActivity
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8x8_online);

        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_8x8ActivityOnline.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    // 0: empty; 1: O; 2: X
    int activePlayer = 2; // Show who player will play next

    // Matrix 8x8 use to store the moves of the pieces
    //  Array initialization equal 0 with 8 columns and 8 rows
    int [][] matrix = {
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}
    };

    public void dropIn(View view){
        ImageView index = (ImageView) view;
        int[] position = convertTagToPosition(index.getTag().toString());
        if(activePlayer == 2 && matrix[position[0]][position[1]] == 0){
            index.setImageResource(R.drawable.icon_x);
            activePlayer = 1;

            matrix[position[0]][position[1]] = 2;
        }else if(activePlayer == 1 && matrix[position[0]][position[1]] == 0) {
            index.setImageResource(R.drawable.icon_o);
            activePlayer = 2;

            matrix[position[0]][position[1]] = 1;
        }
    }

    // Function use to convert from Tag attribute to Position in matrix
    public int[] convertTagToPosition(String tag) {
        int row, col;
        row = Character.getNumericValue(tag.charAt(0));
        col = Character.getNumericValue(tag.charAt(1));
        int[] position = {row, col};
        return position;
    }
}
