package project.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import project.core.GRMBase;

public class MySQLiteDatabaseHelper extends SQLiteOpenHelper {

  private static final String TAG = "Pouya";

  private static final int DB_VERSION = 1;

  public MySQLiteDatabaseHelper(Context context, String appDirectoryName, String databaseDirectoryName, String databaseName) {
    super(context, GRMBase.SDCARD + "/" + appDirectoryName + "/" + databaseDirectoryName + "/" + databaseName + ".sqlite", null, 1);
//    super(context, GRMBase.SDCARD + "/" + appDirectoryName + "/" + databaseDirectoryName + "/" + databaseName + ".sqlite", null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    try {

      //database.execSQL("CREAT TABLE person 'firstname' TEXT, 'lastName' TEXT");

    } catch (Exception e) {
      Log.i(TAG, "Syntax Error !!!");
    }


  }

  @Override
  public void onUpgrade(android.database.sqlite.SQLiteDatabase database, int oldVersion, int newVersion) {

  }
}
