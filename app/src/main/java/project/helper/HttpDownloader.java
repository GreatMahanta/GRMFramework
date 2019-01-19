package project.helper;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import project.activity.DownloadActivity;
import project.core.GRMBase;
import project.listener.Listeners;

public class HttpDownloader {

  private static final String TAG = "Pouya";


  public static void download(final String url, final Listeners.OnHttpDownloadListener listener) {

    new Thread(new Runnable() {
      @Override
      public void run() {

        try {

          URL path = new URL(url);
          HttpURLConnection connection = (HttpURLConnection) path.openConnection();
          connection.setRequestMethod(DownloadActivity.method);
          connection.setDoInput(true);
          InputStream stream = connection.getInputStream();
          String result = GRMBase.convertStreamToString(stream);

          if (listener != null) {
            listener.onHttpDownload(result);
          }

          stream.close();
          connection.disconnect();

        } catch (MalformedURLException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }

      }
    }).start();
  }

  public static void downloadHttpClient(final String url, final Listeners.OnHttpDownloadListener listener) {

    new Thread(new Runnable() {
      @Override
      public void run() {

        try {

          HttpClient client = new DefaultHttpClient();
          HttpPost getMethod = new HttpPost(url);
          HttpResponse response = client.execute(getMethod);
          InputStream stream = response.getEntity().getContent();
          String resault = GRMBase.convertStreamToString(stream);
          int code = response.getStatusLine().getStatusCode();

          Log.i(TAG, "code " + code);

          if (listener != null) {
            listener.onHttpDownload(resault);
          }

        } catch (IOException e) {
          e.printStackTrace();
        }

      }
    }).start();
  }
}
