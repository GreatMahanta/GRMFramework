package project.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GRMBase extends Application {

  private static final String TAG = "Pouya";

  public static final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
  private Context applicationContext;
  private Activity activity;
  private LayoutInflater layoutInflater;
  private static Handler handler;
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

  public static Handler getHandler() {
    return handler;
  }

  public SharedPreferences getPreferences() {
    return preferences;
  }

  public SharedPreferences.Editor putPreferences() {
    return editor;
  }

  public static String convertStreamToString(InputStream stream) {

    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      StringBuilder builder = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        builder.append(line);
      }
      return builder.toString();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return "Error!!!";
  }


}
