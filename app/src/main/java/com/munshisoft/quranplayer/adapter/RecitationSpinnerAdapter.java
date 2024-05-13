package com.munshisoft.quranplayer.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.munshisoft.quranplayer.model.RecitationResponse;

import java.util.List;

public class RecitationSpinnerAdapter extends ArrayAdapter<RecitationResponse.Recitation> {
    private List<RecitationResponse.Recitation> recitations;

    public RecitationSpinnerAdapter(Context context, int resource, List<RecitationResponse.Recitation> recitations) {
        super(context, resource, recitations);
        this.recitations = recitations;
    }

    @Override
    public int getCount() {
        return recitations.size();
    }

    @Override
    public RecitationResponse.Recitation getItem(int position) {
        return recitations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return recitations.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);

        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(recitations.get(position).getReciterName());

        return view;
    }
}
