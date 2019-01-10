package helper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class AskUserForPermissionAndCreateFiles extends ContextWrapper {

  public AskUserForPermissionAndCreateFiles(Context base) {
    super(base);
     if (ActivityCompat.checkSelfPermission(base, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
       ActivityCompat.requestPermissions((Activity) base, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
     }
  }
}
