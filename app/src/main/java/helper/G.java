package helper;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;

public class G extends Application {
//  static Context context;
  static SharedPreferences preferences;
  public static final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
  public static Handler handler = new Handler();


  @Override
  public void onCreate() {
    super.onCreate();
//    G.context = getApplicationContext();
    preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

  }

}
