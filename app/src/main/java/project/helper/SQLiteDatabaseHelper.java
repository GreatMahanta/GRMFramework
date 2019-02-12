package project.helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import project.core.GRMBase;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

  private static final String TAG = "Pouya";

  private static final int DB_VERSION = 1;

  public SQLiteDatabaseHelper(Context context, String appDirectoryName, String databaseDirectoryName, String databaseName) {
    super(context, GRMBase.SDCARD + "/" + appDirectoryName + "/" + databaseDirectoryName + "/" + databaseName + ".sqlite", null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    try {

      database.execSQL("CREATE TABLE person ()");

    } catch (SQLException e) {
      Log.i(TAG, "Syntax Error !!!");
    }


  }

  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

  }
}
