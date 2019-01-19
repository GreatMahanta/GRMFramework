package project.listener;

public class Listeners {

  public interface OnPermissionGrantListener {
    void onGrant();
  }

  public interface OnHttpDownloadListener {
    void onHttpDownload(String data);
  }


}
