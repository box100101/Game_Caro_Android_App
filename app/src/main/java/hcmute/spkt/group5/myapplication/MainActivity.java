package hcmute.spkt.group5.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.app.Dialog;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btnPlayTwoPeople;         // Button use for move to Activity choose options
    Button btnPlayWithAI;            // Button use for move to 8x8 Activity AI
    Button btnPlayOnline;            // Button use for move to 8x8 Activity AI
    ImageView btnMusic;              // Button use for turn off or turn on music in game
    ImageView btnSound;              // Button use for turn off or turn on sound in game
    boolean statusMusicBtn = true;   // Variable use for check state of music button
    boolean statusSoundBtn = true;   // Variable use for check state of sound button
    LinearLayout userProfile;        // Variable use for showing layout_dialog_user_profile

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle event click to show profile of user
        userProfile = (LinearLayout) findViewById(R.id.user_profile);
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserProfile();
            }
        });

        //  Handle event move form Main Activity to ChooseOptionActivity
        btnPlayTwoPeople = (Button) findViewById(R.id.btn_play_two_people);
        btnPlayTwoPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChooseOptionActivity.class);
                startActivity(intent);
            }
        });

        //  Handle event click to Play with AI
        btnPlayWithAI = (Button) findViewById(R.id.btn_play_with_AI);
        btnPlayWithAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, _8x8ActivityAI.class);
                startActivity(intent);
            }
        });

        //  Handle event click to Play online
        btnPlayOnline = (Button) findViewById(R.id.btn_play_online);
        btnPlayOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, _8x8ActivityOnline.class);
                startActivity(intent);
            }
        });
    }

    // Function use to show the detail profile of user
    private void openUserProfile(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_user_profile);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        ImageView btnCancel = dialog.findViewById(R.id.btn_cancel_dialog);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button btnSave = dialog.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dialog.show();
    }
}