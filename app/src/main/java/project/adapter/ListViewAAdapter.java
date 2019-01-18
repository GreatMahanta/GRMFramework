package project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.greatmahanta.grmframework.R;

import java.util.ArrayList;

import project.viewholder.ColorViewHolder;


public abstract class ListViewAAdapter<T> extends ArrayAdapter<T> {

  private LayoutInflater inflater;

  public ListViewAAdapter(Context context, ArrayList<T> colors) {

    super(context, 0, colors);
    inflater = LayoutInflater.from(context);

  }

  public abstract ColorViewHolder assign(View convertView);
  public abstract void fill(ColorViewHolder viewHolder, T item);

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    T item = getItem(position);

    ColorViewHolder viewHolder;

    if (convertView == null) {

      convertView = inflater.inflate(R.layout.activity_adapter, parent, false);

      viewHolder = assign(convertView);

      convertView.setTag(viewHolder);

    } else {
      viewHolder = (ColorViewHolder) convertView.getTag();
    }

    fill(viewHolder, item);

    return convertView;
  }
}


