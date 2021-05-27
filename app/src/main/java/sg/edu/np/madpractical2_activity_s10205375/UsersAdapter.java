package sg.edu.np.madpractical2_activity_s10205375;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {
    Context context;
    ArrayList<User> data;

    public UsersAdapter(Context c, ArrayList<User> d) {
        context = c;
        data = d;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        if(viewType == 0) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_user7, parent, false);
        }
        else
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_user, parent, false);
        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User u = data.get(position);

        holder.name.setText(u.getName());
        holder.desc.setText(u.getDesc());
        ImageView iv = holder.imgView;
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Profile");
                builder.setMessage(u.name);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Random ran = new Random();
                        String ranInt = " " + String.valueOf(ran.nextInt());
                        Intent in = new Intent(context, MainActivity.class);
                        in.putExtra("row", position);
                        context.startActivity(in);
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position).getName().endsWith("7"))
            return 0;
        return 1;
    }
}
