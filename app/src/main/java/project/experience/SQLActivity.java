package project.experience;

import android.Manifest;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.greatmahanta.grmframework.R;

import project.activity.GRMAppCompatActivity;
import project.listener.Listeners;

public class SQLActivity extends GRMAppCompatActivity {

  private static final String TAG = "Pouya";
  private SQLiteDatabase sqLiteDatabase;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sql);


    requestDangerousPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, new Listeners.OnPermissionGrantListener() {
      @Override
      public void onGrant() {

        sqLiteDatabase = getSqliteDatabase("SqliteApp", "SqliteDirectory", "test2");
        //createAppDirectory("test-test");
        Log.i(TAG, "Ummm ");

      }
    });
  }
}
