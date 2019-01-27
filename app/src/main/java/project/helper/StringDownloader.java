package project.helper;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import project.listener.Listeners;

public class StringDownloader {

  private static final String TAG = "Pouya";

  private String url;
  private String method;
  private ArrayList<BasicNameValuePair> inputArguments;
  private Listeners.OnHttpDownloadListener listener;
  private boolean enableCache;
  private long cacheExpireTime;
  private int connectionTimeOut;
  private int socketTimeOut;


  public StringDownloader url(String url) {
    this.url = url;
    return this;
  }

  public StringDownloader method(String method) {
    this.method = method;
    return this;
  }

  public StringDownloader inputArgument(String key, String value) {
    this.inputArguments.add(new BasicNameValuePair(key, value));
    return this;
  }

  public StringDownloader listener(Listeners.OnHttpDownloadListener listener) {
    this.listener = listener;
    return this;
  }

  public StringDownloader enableCache(boolean enableCache) {
    this.enableCache = enableCache;
    return this;
  }

  public StringDownloader cacheExpireTime(long cacheExpireTime) {
    this.cacheExpireTime = cacheExpireTime * 1000;
    return this;
  }

  public StringDownloader connectionTimeOut(int connectionTimeOut) {
    this.connectionTimeOut = connectionTimeOut * 1000;
    return this;
  }

  public StringDownloader socketTimeOut(int socketTimeOut) {
    this.socketTimeOut = socketTimeOut * 1000;
    return this;
  }


  public void download() {

    new Thread(new Runnable() {
      @Override
      public void run() {

        try {

          URL path = new URL(url);
          HttpURLConnection connection = (HttpURLConnection) path.openConnection();
          connection.setRequestMethod(method);

          if (method.equals("POST")) {
            connection.setDoOutput(true);
          }

          HttpParams params = new BasicHttpParams();
          HttpConnectionParams.setConnectionTimeout(params, connectionTimeOut);
          HttpConnectionParams.setSoTimeout(params, socketTimeOut);
//          Log.i(TAG, "run: " + connection.getDoInput());
//          params.
//          connection.setConnectTimeout(connectionTimeOut);
//          connection.setReadTimeout(socketTimeOut);
//          connection.setDoInput(true);
          connection.connect();
          Log.e(TAG, "Errorrun: " + connection.getResponseCode());

          InputStream errorStream = connection.getErrorStream();
          InputStream stream = connection.getInputStream();
//          String result = GRMBase.convertStreamToString(stream);
//          String errorResult = GRMBase.convertStreamToString(errorStream);
          String result = EntityUtils.toString((HttpEntity) stream, "UTF-8");

          Log.i(TAG, "run: " + result);

          if (listener != null) {
            listener.onHttpDownload(result);
          }

//          stream.close();
//          connection.disconnect();

        } catch (MalformedURLException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

}
