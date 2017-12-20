package lenovo.example.com.myjdapp.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLitePersonDBOpenHelper extends SQLiteOpenHelper {

	public SQLitePersonDBOpenHelper(Context context) {
		
		super(context, "person.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("create table person (_id Integer primary key autoincrement,name varchar(20))");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
