package sg.edu.np.madpractical2_activity_s10205375;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersDB.db";
    public static final String USERS_TABLE = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FOLLOWED = "followed";
    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + USERS_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_FOLLOWED + " INTEGER" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
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
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", user.getName());
        values.put("DESCRIPTION", user.getDesc());
        values.put("FOLLOWED", user.getFollow());

        db.update(USERS_TABLE, values, COLUMN_ID + " = ?", new String[] {String.valueOf(user.getId())});
        db.close();
    }

    public ArrayList<User> getUsers() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users",null);
        ArrayList<User> list = new ArrayList<>();

        while(cursor.moveToNext()) {
            User u = new User();
            u.setId(Integer.parseInt(cursor.getString(0)));
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
        Cursor mCursor = db.rawQuery("SELECT * FROM users", null);
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
