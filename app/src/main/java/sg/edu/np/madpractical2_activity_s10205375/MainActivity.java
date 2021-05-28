package sg.edu.np.madpractical2_activity_s10205375;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    User targetUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent in = getIntent();
        int position = in.getIntExtra("id",0);
        targetUser = ListActivity.userList.get(position);

        TextView name = findViewById(R.id.txtName);
        name.setText(targetUser.name);
        TextView desc = findViewById((R.id.desc));
        desc.setText(targetUser.description);
        setFollowBtn();
    }
    private void setFollowBtn() {
        Button fBtn = findViewById(R.id.btnFollow);
        if(targetUser.followed) {
            fBtn.setText("Unfollow");
        }
        else {
            fBtn.setText("Follow");
        }
    }
    public void onFollowClick(View v) {
        DBHandler dbHandler = new DBHandler(this);
        targetUser.followed = !targetUser.followed;
        if(targetUser.followed){
            Toast.makeText(this, "Followed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Unfollowed", Toast.LENGTH_SHORT).show();
        }
        dbHandler.updateUser(targetUser);
        setFollowBtn();
    }
}