package sg.edu.np.madpractical2_activity_s10205375;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(@Nullable Context context) {
        super(context, "USERS_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DESCRIPTION TEXT, FOLLOWED BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        onCreate(db);
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put("NAME",user.getName());
        values.put("DESCRIPTION", user.getDesc());
        values.put("FOLLOWED", user.getFollow());

        SQLiteDatabase db = getWritableDatabase();
        db.insert("USERS", null, values);
        db.close();
    }

    public void updateUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        int follow_value;
        if(user.followed) {
            follow_value = 1;
        }
        else follow_value = 0;
        Cursor cursor = db.rawQuery("update USERS set FOLLOWED = \"" + follow_value + "\" where NAME = \"" + user.name + "\"",null);
        User u = null;

        while(cursor.moveToNext()) {
            u = new User();
            u.setFollow(cursor.getInt(3) != 0);
        }
        cursor.close();
        db.close();
    }

    public ArrayList<User> getUser(String name) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERS",null);
        User u = null;
        ArrayList<User> list = new ArrayList<>();

        while(cursor.moveToNext()) {
            u = new User();
            u.setName(cursor.getString(1));
            u.setDesc(cursor.getString(2));
            u.setFollow(cursor.getInt(3) != 0);

            list.add(u);
        }
        cursor.close();
        db.close();

        return list;
    }

    public boolean checkDB() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM USERS", null);
        boolean rowExists;
        if (mCursor.getCount() == 0)
        {
            rowExists = true;

        } else
        {
            rowExists = false;
        }
        mCursor.close();
        db.close();
        return rowExists;
    }
}
