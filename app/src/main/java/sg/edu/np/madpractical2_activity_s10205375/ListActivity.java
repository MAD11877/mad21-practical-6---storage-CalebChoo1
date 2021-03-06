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
    static ArrayList<User> userList;
    DBHandler dbHandler = new DBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        if (dbHandler.checkDB()) {
            for(int i=0; i<20; i++) {
                User u = new User();
                u.setName("Name" + new Random().nextInt());
                u.setDesc("Description " + new Random().nextInt());
                u.setFollow(new Random().nextBoolean());
                u.setId(i);
                dbHandler.addUser(u);
            }
        }

        userList = dbHandler.getUsers();
        RecyclerView rv = findViewById(R.id.rv);
        UsersAdapter adapter = new UsersAdapter(this,userList);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }
}