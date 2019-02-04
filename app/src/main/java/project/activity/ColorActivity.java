package project.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.greatmahanta.grmframework.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import project.adapter.ColorListViewAdapter;
import project.struct.ColorStruct;
import project.struct.SampleColorData;

public class ColorActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_color);


    HashMap<String, String> structColors = SampleColorData.get();
    Set<String> keyset = structColors.keySet();
    ArrayList<ColorStruct> colors = new ArrayList<>();

    for (String key : keyset) {
      String value = structColors.get(key);
      colors.add(new ColorStruct(key, value));
    }

    ListView lst_view = findViewById(R.id.lst_view);

    ColorListViewAdapter adapter = new ColorListViewAdapter(this, colors);

    lst_view.setAdapter(adapter);
  }
}
