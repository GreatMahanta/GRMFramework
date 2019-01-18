package helper;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import project.core.GRMBase;

public class MyFileDownloader {

  private String urlPath;
  private String fileName;
  private String fileDir;
  private OnFileDownloadListener listener;
  private float currentDownloadFileSize = 0.0F;
  private float currentDownloadFileSizeMB = 0.0F;
  private int percent = 0;

  private static final String TAG = "Pouya";

  public interface OnFileDownloadListener {
    void onFileDownload(String type, String totalSize, String currentSize, int percent);
  }

  public MyFileDownloader url(String value) {
    urlPath = value;
    return this;
  }

  public MyFileDownloader fileName(String value) {
    fileName = value;
    return this;
  }

  public MyFileDownloader fileDir(String value) {
    fileDir = value;
    return this;
  }

  public MyFileDownloader listener(OnFileDownloadListener value) {
    listener = value;
    return this;
  }

  public void download() {

    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {

        try {

          URL url = new URL(urlPath);

          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          connection.setRequestMethod("GET");
          connection.setConnectTimeout(15000);
          connection.setReadTimeout(500000);
          connection.connect();

          InputStream inputStream = connection.getInputStream();

          float totalDownloadFileSizeMB = (float) connection.getContentLength() / 1024 / 1024;
          String type = connection.getContentType();

          if (fileDir != null) {
            File file = new File(GRMBase.SDCARD + "/" + fileDir);
            if (!file.exists()) {
              file.mkdirs();
            }
          } else {
            File file = new File(GRMBase.SDCARD + "/default-GRM-filedownloader-program/");
            if (!file.exists()) {
              file.mkdirs();
            }
          }

          byte[] buffer = new byte[8 * 1024];
          int length;

          FileOutputStream fileOutputStream;

          String filePath;
          if (fileDir != null) {
            filePath = GRMBase.SDCARD + "/" + fileDir + "/" + fileName;
          } else {
            filePath = GRMBase.SDCARD + "/default-GRM-filedownloader-program/" + fileName;
          }
          fileOutputStream = new FileOutputStream(filePath);

          while ((length = inputStream.read(buffer)) != -1) {

            fileOutputStream.write(buffer, 0, length);
            currentDownloadFileSize = currentDownloadFileSize + length;
            currentDownloadFileSizeMB = currentDownloadFileSize / 1024 / 1024;
            percent = (int) (currentDownloadFileSizeMB * 100 / totalDownloadFileSizeMB);
            Log.i(TAG, "run: " + percent);
            if (listener != null) {

              listener.onFileDownload(type, String.format(Locale.getDefault(), "%.2f", totalDownloadFileSizeMB), String.format(Locale.getDefault(), "%.2f", currentDownloadFileSizeMB), percent);
            }
          }

          if (inputStream != null) {
            inputStream.close();
          }

          if (fileOutputStream != null) {
            fileOutputStream.close();
          }

        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    thread.start();
  }

}
