package sg.edu.np.madpractical2_activity_s10205375;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    User u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random ran = new Random();
        Intent in = getIntent();
        int position = in.getIntExtra("row",0);
        u = ListActivity.data.get(position);

        TextView name = findViewById(R.id.name);
        name.setText(u.name);
        TextView desc = findViewById((R.id.desc));
        desc.setText(u.description);
        setFollowBtn();
    }
    private void setFollowBtn() {
        Button fBtn = findViewById(R.id.btnFollow);
        if(u.followed) {
            fBtn.setText("Unfollow");
        }
        else {
            fBtn.setText("Follow");
        }
    }
    public void onFollowClick(View v) {
        u.followed = !u.followed;
        if(u.followed){
            Toast.makeText(this, "Followed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Unfollowed", Toast.LENGTH_SHORT).show();
        }
        setFollowBtn();
    }
}