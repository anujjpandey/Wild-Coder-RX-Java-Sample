package com.locationsearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.locationsearch.R;
import com.locationsearch.entites.State;

import java.util.List;

/**
 * Created by wildcoder on 29/09/16.
 */
public class AdapterStates extends BaseAdapter {

    private List<State> states;
    private int rowLayout;
    private Context context;

    public AdapterStates(List<State> movies, int rowLayout, Context context) {
        this.states = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return states.size();
    }

    @Override
    public State getItem(int position) {
        return states.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(rowLayout, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.txtName.setText(states.get(position).getName());
        viewHolder.txtCapital.setText("Capital:" + states.get(position).getCapital());
        return convertView;
    }


    public static class ViewHolder {
        TextView txtName;
        TextView txtCapital;


        public ViewHolder(View v) {
            txtName = (TextView) v.findViewById(R.id.txt_name);
            txtCapital = (TextView) v.findViewById(R.id.txt_capital);
        }
    }


}