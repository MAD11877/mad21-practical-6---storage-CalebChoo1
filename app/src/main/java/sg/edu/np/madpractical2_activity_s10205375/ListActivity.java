package sg.edu.np.madpractical2_activity_s10205375;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    User u;
    static ArrayList<User> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        data = new ArrayList<User>();
        for(int i=0; i<20; i++) {
            Random ran = new Random();
            String ranInt = "" + String.valueOf(ran.nextInt());
            Random rand = new Random();
            String randInt = " " + String.valueOf(rand.nextInt());
            u = new User();
            u.setName("Name" + ranInt);
            u.setDesc("Description" + randInt);
            u.setFollow(ran.nextBoolean());
            data.add(u);
        }
        RecyclerView rv = findViewById(R.id.rv);
        UsersAdapter adapter = new UsersAdapter(this,data);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }
}