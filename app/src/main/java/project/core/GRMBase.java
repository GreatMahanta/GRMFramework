package project.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;

public class GRMBase extends Application {

  private static final String TAG = "Pouya";

  public static final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
  private Context applicationContext;
  private Activity activity;
  private LayoutInflater layoutInflater;
  private Handler handler;
  private SharedPreferences preferences;
  private SharedPreferences.Editor editor;

  public GRMBase() {

  }

  public GRMBase(Activity activity) {
    this.activity = activity;
  }

  @Override
  public void onCreate() {
    super.onCreate();

    applicationContext = getApplicationContext();
    layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
    handler = new Handler();
    preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
    editor = preferences.edit();

  }

  public Context getContext() {
    if (activity != null) {
      return activity;
    }
    return applicationContext;
  }

  public LayoutInflater getLayoutInflater() {
    return layoutInflater;
  }

  public Handler getHandler() {
    return handler;
  }

  public SharedPreferences getPreferences() {
    return preferences;
  }

  public SharedPreferences.Editor putPreferences() {
    return editor;
  }

}
