package uoft.Assignment2.cs.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Christine on 16-01-22.
 */
public class View_temp extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.view_people, parentViewGroup, false);
        getActivity().setTitle("View Last Entered Names");
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        Map data = sharedPref.getAll();
        ArrayList inform_list = new ArrayList();
        for(Object name : data.keySet()){
            inform_list.add(name.toString()+data.get(name));
        }
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, inform_list));

        return rootView;
    }
}
