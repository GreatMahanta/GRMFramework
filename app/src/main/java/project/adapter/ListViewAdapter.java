package project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.greatmahanta.grmframework.R;

import java.util.ArrayList;

import project.activity.viewholder.ViewHolder;
import project.struct.ColorStruct;


public class ListViewAdapter extends ArrayAdapter<ColorStruct> {

  private LayoutInflater inflater;

  public ListViewAdapter(Context context, ArrayList<ColorStruct> colors) {

    super(context, 0, colors);
    inflater = LayoutInflater.from(context);

  }


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ColorStruct item = getItem(position);

    ViewHolder viewHolder;

    if (convertView == null) {
      convertView = inflater.inflate(R.layout.activity_adapter, parent, false);

      viewHolder = new ViewHolder();
      viewHolder.txt_color = convertView.findViewById(R.id.txt_color);
      viewHolder.txt_value = convertView.findViewById(R.id.txt_value);

      convertView.setTag(viewHolder);

    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    viewHolder.txt_color.setText(item.color);
    viewHolder.txt_value.setText(item.value);

    return convertView;
  }
}
