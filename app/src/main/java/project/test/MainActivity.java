package project.test;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.greatmahanta.grmframework.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import project.activity.GRMAppCompatActivity;
import project.adapter.ListViewAdapter;
import project.struct.ColorStruct;
import project.struct.SampleColorData;

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


//    requestDangerousPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, new Listeners.OnPermissionGrantListener() {
//      @Override
//      public void onGrant() {
//        createAppDirectory("main-app");
//        SQLiteDatabase database = getSqliteDatabase("mytestdbdirectory","testdb1");
////        database.execSQL("CREATE TABALE 'pouya'( 'firstName INTEGER, 'lastName' TEXT')");
//      }
//    });


    HashMap<String, String> structColors = SampleColorData.get();
    Set<String> keyset = structColors.keySet();

    ArrayList<ColorStruct> colors = new ArrayList<>();


    for (String key : keyset) {
      String value = structColors.get(key);
      Log.i(TAG, "ColorStruct: " + key + " #" + value);
      colors.add(new ColorStruct(key, value));
    }


    ListView lst_view = findViewById(R.id.lst_view);

    ListViewAdapter adapter = new ListViewAdapter(this, colors);

    lst_view.setAdapter(adapter);


  }
}


