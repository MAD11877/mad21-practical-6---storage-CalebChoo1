package sg.edu.np.madpractical2_activity_s10205375;

import android.media.audiofx.AudioEffect;

public class User {
    String name;
    public void setName(String n)
    {
        name = n;
    }
    public String getName()
    {
        return name;
    }

    String description;
    public void setDesc(String d)
    {
        description = d;
    }
    public String getDesc()
    {
        return description;
    }

    int id;
    public void setId(int id1)
    {
        id = id1;
    }
    public int getId()
    {
        return id;
    }

    boolean followed;
    public void setFollow(boolean follow)
    {
        followed = follow;
    }
    public boolean getFollow()
    {
        return followed;
    }
}




