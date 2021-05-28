package sg.edu.np.madpractical2_activity_s10205375;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView desc;
    public ImageView imgView;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.nameDisplay);
        desc = itemView.findViewById(R.id.descDisplay);
        imgView = itemView.findViewById(R.id.img_profile);
    }
}

