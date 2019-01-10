package project.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.greatmahanta.grmframework.R;

import java.io.File;

import project.core.GRMBase;
import project.helper.SQLiteDatabaseHelper;
import project.listener.Listeners;

@SuppressLint("Registered")
public class GRMAppCompatActivity extends AppCompatActivity {

  private static final String TAG = "Pouya";

  private Activity activity;
  private String permission;
  private Listeners.OnPermissionGrantListener onPermissionGrantListener;
  private int requestCode = 100;

  private String appDirectoryName;
  private String databaseDirectoryName;
  private String databaseName;
  private SQLiteDatabase sqliteDatabase;

  protected void requestDangerousPermissions(final Activity activity, final String permission, Listeners.OnPermissionGrantListener onPermissionGrantListener) {
    this.activity = activity;
    this.permission = permission;
    this.onPermissionGrantListener = onPermissionGrantListener;

    if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
      onPermissionGrantListener.onGrant();
    } else {
      ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
    }
  }

  @Override
  public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
      onPermissionGrantListener.onGrant();
    } else {

      if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

        new AlertDialog.Builder(this)
          .setTitle("Permission Required")
          .setMessage("Permission " + permission.replace("android.permission.", "") + " is Needed to Work This Application Properly")
          .setIcon(R.drawable.ic_warning_black_24dp)
          .setCancelable(false)
          .setPositiveButton("Ask Me Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);

            }
          })
          .create()
          .show();

      } else {
        ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
      }
    }
  }

  protected void createAppDirectory(String appDirectoryName) {

    this.appDirectoryName = appDirectoryName;

    File appFile;
    if (appDirectoryName != null) {
      appFile = new File(GRMBase.SDCARD + "/" + appDirectoryName + "/");
    } else {
      appFile = new File(GRMBase.SDCARD + "/GRM-programs-directoy/");
    }
    if (!appFile.exists()) {
      boolean created = appFile.mkdirs();

      if (created) {
        Toast.makeText(activity, "Directory Created Successfully", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(activity, "Access Denied!!!", Toast.LENGTH_SHORT).show();
      }
    } else {
      Toast.makeText(activity, "Directory Already Exists", Toast.LENGTH_SHORT).show();
    }
  }


  protected SQLiteDatabase getSqliteDatabase(String databaseDirectoryName, String databaseName) {

    this.databaseDirectoryName = databaseDirectoryName;
    this.databaseName = databaseName;

    SQLiteDatabaseHelper databaseHelper = new SQLiteDatabaseHelper(this, appDirectoryName, databaseDirectoryName, databaseName);

    if (databaseHelper != null) {
      sqliteDatabase = databaseHelper.getWritableDatabase();
      Toast.makeText(activity, "Database Created Successfully", Toast.LENGTH_SHORT).show();
    }

    return sqliteDatabase;
  }

}
