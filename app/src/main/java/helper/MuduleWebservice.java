package helper;


import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import project.core.GRMBase;

public class MuduleWebservice {

  private static final String TAG = "Pouya";

  private String url;
  private ArrayList<NameValuePair> inputs;
  private onDataListener listener;
  private boolean enablecache;
  private String cacheDir;
  private String cacheName;
  private long cacheExpireTime;
  private int connectionTimeOut;
  private int socketTimeOut;
  private int statusCode;


  public interface onDataListener {
    void onData(String data, int statusCode);

    void onFail(String error);
  }


  public MuduleWebservice url(String value) {
    url = value;
    return this;
  }


  public MuduleWebservice inputArguments(ArrayList<NameValuePair> value) {
    inputs = value;
    return this;
  }


  public MuduleWebservice listener(onDataListener value) {
    listener = value;
    return this;
  }


  public MuduleWebservice enablecache(boolean value) {
    enablecache = value;
    return this;
  }


  public MuduleWebservice cacheDir(String value) {
    cacheDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + value + "/";
    return this;
  }


  public MuduleWebservice cacheName(String value) {
    cacheName = value;
    return this;
  }


  public MuduleWebservice cacheExpireTime(long value) {
    cacheExpireTime = value;
    return this;
  }


  public MuduleWebservice connectionTimeOut(int value) {
    connectionTimeOut = value;
    return this;
  }


  public MuduleWebservice socketTimeOut(int value) {
    socketTimeOut = value;
    return this;
  }
  
  GRMBase base = new GRMBase();


  public void read() {

    String data = null;

    File file = new File(cacheDir + cacheName);
    if (!file.exists()) {
      readFromNet();
    } else {
      file.delete();
    }
    data = readFromCache();

    if (data == null) {
      readFromNet();
    }

    if (listener != null) {
      listener.onData(data, statusCode);
    }

  }


  private void readFromNet() {

    new Thread(new Runnable() {

      @Override
      public void run() {

        try {

          HttpParams params = new BasicHttpParams();
          HttpConnectionParams.setConnectionTimeout(params, connectionTimeOut);
          HttpConnectionParams.setSoTimeout(params, socketTimeOut);
          HttpClient client = new DefaultHttpClient(params);
          HttpPost post = new HttpPost(url);
          post.setEntity(new UrlEncodedFormEntity(inputs));
          HttpResponse response = client.execute(post);
          statusCode = response.getStatusLine().getStatusCode();
          InputStream stream = response.getEntity().getContent();
          String result = convertStreamToString(stream);

          if (listener != null) {
            listener.onData(result, statusCode);
          }

          saveToCache(result);

          SharedPreferences.Editor myPreferences = base.putPreferences();
          myPreferences.putLong("SAVE_TIME", System.currentTimeMillis());
          myPreferences.apply();

        } catch (IOException e) {
          if (listener != null) {
            listener.onFail("IOException... and StatusCode is :" + statusCode);
          }
        }

        if (cacheExpireTime != 0) {
          try {
            Thread.sleep(cacheExpireTime * 1100);

            boolean b = new File(cacheDir + cacheName).delete();
            Log.i(TAG, "ThreadDelete: " + b);


          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();
  }


  private String convertStreamToString(InputStream stream) {

    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    StringBuilder builder = new StringBuilder();

    try {

      String line;

      while ((line = reader.readLine()) != null) {
        builder.append(line);
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        stream.close();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return builder.toString();

  }


  private void saveToCache(String data) {

    File file = new File(cacheDir);
    if (!file.exists()) {
      file.mkdirs();
    }


    try {
      FileOutputStream fileOutputStream = new FileOutputStream(cacheDir + cacheName);
      fileOutputStream.write(data.getBytes(), 0, data.length());

      if (fileOutputStream != null) {
        fileOutputStream.close();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public String readFromCache() {

    try {

      if ((System.currentTimeMillis() - base.getPreferences().getLong("SAVE_TIME", 0) > (cacheExpireTime * 1000))) {
        boolean b = new File(cacheDir + cacheName).delete();
        Log.i(TAG, "readfromCACHEdelete: " + b);
      } else {

        Log.i(TAG, "readfromCACHEdelete: " + false);
        return convertStreamToString(new FileInputStream(cacheDir + cacheName));
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }
    Log.i(TAG, "readfromCACHEdelete: " + false);
    return null;
  }
}
