package project.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.greatmahanta.grmframework.R;

import java.util.ArrayList;

import project.struct.ColorStruct;
import project.viewholder.ColorViewHolder;

public class ColorListViewAdapter extends ListViewAdapter<ColorStruct> {


  public ColorListViewAdapter(Context context, ArrayList<ColorStruct> colors) {
    super(context, colors);
  }

  @Override
    public ColorViewHolder assign(View convertView) {

      ColorViewHolder viewHolder = new ColorViewHolder();
      viewHolder.txt_color = convertView.findViewById(R.id.txt_color);
      viewHolder.txt_value = convertView.findViewById(R.id.txt_value);
      viewHolder.lay_horiz = convertView.findViewById(R.id.lay_horiz);
      return viewHolder;

    }

    @Override
    public void fill(ColorViewHolder viewHolder, ColorStruct item) {

      viewHolder.txt_color.setText(item.color);
      viewHolder.txt_value.setText(item.value);
      viewHolder.lay_horiz.setBackgroundColor(Color.parseColor("#" + item.value));

    }
  };


