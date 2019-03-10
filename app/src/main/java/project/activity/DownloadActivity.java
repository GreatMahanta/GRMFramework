package project.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.greatmahanta.grmframework.R;

import project.helper.StringDownloader;
import project.listener.Listeners;

public class DownloadActivity extends AppCompatActivity {

  private static final String TAG = "Pouya";
  public String method;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_download);

    final EditText edt_url = findViewById(R.id.edt_url);
    final EditText edt_method = findViewById(R.id.edt_method);
    final TextView txt_resault = findViewById(R.id.txt_resault);
    Button btn_connect = findViewById(R.id.btn_connect);

    btn_connect.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        method = edt_method.getText().toString();
        txt_resault.setText("");

        StringDownloader downloader = new StringDownloader();
        downloader.url(edt_url.getText().toString())
          .listener(new Listeners.OnHttpDownloadListener() {

          @Override
          public void onHttpDownload(final String data) {

            runOnUiThread(new Runnable() {
              @Override
              public void run() {
                txt_resault.setText(data);

              }
            });
            Log.i(TAG, "onHttpDownload: " + data);

          }
        })
          .download();
      }
    });
  }
}
