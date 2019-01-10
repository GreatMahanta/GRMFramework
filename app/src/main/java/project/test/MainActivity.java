package project.test;

import android.Manifest;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import project.activity.GRMAppCompatActivity;
import project.listener.Listeners;

public class MainActivity extends GRMAppCompatActivity {

  private static final String TAG = "Pouya";

  String[] myPermissions = {
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.CAMERA,
    Manifest.permission.READ_SMS,
    Manifest.permission.ACCESS_COARSE_LOCATION,
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


    requestDangerousPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, new Listeners.OnPermissionGrantListener() {
      @Override
      public void onGrant() {
        createAppDirectory("main-app");
        SQLiteDatabase database = getSqliteDatabase("mytestdbdirectory","testdb1");
//        database.execSQL("CREATE TABALE 'pouya'( 'firstName INTEGER, 'lastName' TEXT')");
      }
    });
  }


}


