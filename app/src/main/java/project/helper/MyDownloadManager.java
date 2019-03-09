package project.helper;

import android.app.DownloadManager;
import android.net.Uri;

public class MyDownloadManager {

  String filePath = "Http://";
  Uri uri = Uri.parse(filePath);
  DownloadManager.Request request = new DownloadManager.Request(uri);


}
