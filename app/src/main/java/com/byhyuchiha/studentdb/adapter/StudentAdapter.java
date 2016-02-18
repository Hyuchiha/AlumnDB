package com.byhyuchiha.studentdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.byhyuchiha.studentdb.Model.Alumno;
import com.byhyuchiha.studentdb.R;
import com.byhyuchiha.studentdb.viewHolders.viewHolderStudent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byhyuchiha on 2/02/16.
 */
public class StudentAdapter extends ArrayAdapter<Alumno> {
    private Context context;
    private List<Alumno> alumnos;

    public StudentAdapter(Context context, ArrayList<Alumno> objects) {
        super(context, R.layout.detalle, objects);
        this.context = context;
        this.alumnos = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View  currentView = convertView;
        viewHolderStudent holder;

        if(currentView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            currentView = inflater.inflate(R.layout.detalle, parent,false);
            holder = new viewHolderStudent();
            holder.name = (TextView) currentView.findViewById(R.id.StudentName);
            currentView.setTag(holder);
        }

        holder = (viewHolderStudent) currentView.getTag();
        Alumno alumno = alumnos.get(position);
        holder.name.setText(alumno.getFirstName());
        return currentView;
    }
}
