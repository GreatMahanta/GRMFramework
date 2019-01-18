package project.experience;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.greatmahanta.grmframework.R;

import project.activity.GRMAppCompatActivity;
import project.color_listview_test.ColorActivity;

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
    setContentView(R.layout.activity_main);

    Button btn_listview = findViewById(R.id.btn_listview);
    btn_listview.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, ColorActivity.class));
      }
    });


//    requestDangerousPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, new Listeners.OnPermissionGrantListener() {
//      @Override
//      public void onGrant() {
//        createAppDirectory("main-app");
//        SQLiteDatabase database = getSqliteDatabase("mytestdbdirectory","testdb1");
////        database.execSQL("CREATE TABALE 'pouya'( 'firstName INTEGER, 'lastName' TEXT')");
//      }
//    });


  }
}


