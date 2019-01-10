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

public class DownloadHelper {

  private String urlPath;
  private String fileName;
  private String fileDir;
  private float totalDownloadFileSizeMB;
  private float currentDownloadFileSizeMB;
  private int percent;

  public DownloadHelper url(String value) {
    urlPath = value;
    return this;
  }

  public DownloadHelper fileName(String value) {
    fileName = value;
    return this;
  }

  public DownloadHelper fileDir(String value) {
    fileDir = value;
    return this;
  }

  GRMBase base = new GRMBase();
  
  public void download() {

    new Thread(new Runnable() {
      @Override
      public void run() {

        try {

          URL url = new URL(urlPath);

          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          connection.setRequestMethod("GET");
//          connection.setDoOutput(true);
//          connection.setDoInput(true);
          connection.setConnectTimeout(15000);
          connection.setReadTimeout(500000);
          connection.connect();


          InputStream inputStream = connection.getInputStream();

          totalDownloadFileSizeMB = convertToMB((float) connection.getContentLength());
          final String type = connection.getContentType();

//          Log.i("Pouya", "Volume: " + String.format(downloadFileSize,"%.1f") + " MB");


          if (fileDir != null) {
            File file = new File(base.SDCARD + "/" + fileDir);
            if (!file.exists()) {
              file.mkdirs();
            }
          } else {
            File file = new File(base.SDCARD + "/default-my-filedownloader-program/");
            if (!file.exists()) {
              file.mkdirs();
            }
          }

          byte[] buffer = new byte[8 * 1024];
          int length;

//          FileOutputStream fileOutputStream = new FileOutputStream(DIR_APP + fileName + "." +  type.substring(type.length()-3));
          FileOutputStream fileOutputStream;
          if (fileDir != null) {
            fileOutputStream = new FileOutputStream(base.SDCARD + "/" + fileDir + "/" + fileName);
          } else {
            fileOutputStream = new FileOutputStream(base.SDCARD + "/default-my-filedownloader-program/" + fileName);
          }





            Log.i("Pouya", "run: "+ getTotalDownloadFileSize());





          float currentDownloadFileSize = 0;
          while ((length = inputStream.read(buffer)) != -1) {

            fileOutputStream.write(buffer, 0, length);
            currentDownloadFileSize += length;
            currentDownloadFileSizeMB = convertToMB(currentDownloadFileSize);
            percent = (int) (currentDownloadFileSizeMB * 100 / totalDownloadFileSizeMB);
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

    }).start();
  }

  private float convertToMB(float volume) {
    return (volume / 1024 / 1024);
  }

  public String getTotalDownloadFileSize() {
    Log.i("Pouya", "getTotalDownloadFileSize: "+ totalDownloadFileSizeMB);
    return String.format(Locale.getDefault(),"%.1f",totalDownloadFileSizeMB);
  }

  public String getCurrentDownloadFileSize() {
    return String.format(Locale.getDefault(),"%.1f",currentDownloadFileSizeMB);
  }

  public int getPercent() {
    return percent;
  }
}
