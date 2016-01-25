package uoft.Assignment2.cs.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Christine on 16-01-23.
 */
public class Load_file extends ListFragment{
    DataPassListener mCallback;
    public interface DataPassListener{
        void passData(String data);
    }
    public ArrayList<String> filenames = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Load Existing File");
        View rootView = inflater.inflate(R.layout.load_file, parentViewGroup, false);
        try {
            mCallback = (DataPassListener)getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement DataPassListener");
        }
        File file = new File(getActivity().getFilesDir().getAbsolutePath());
        File[] files = file.listFiles();
        for(File tmp : files){
            filenames.add(tmp.getName());
        }
        if(filenames.size()==0){
            TextView text = (TextView)(rootView.findViewById(R.id.choose_file));
            text.setText("No files currently stored");
        }else {
            TextView text = (TextView) (rootView.findViewById(R.id.choose_file));
            text.setText("Choose one file to load");
            setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, filenames));
        }
        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCallback.passData(filenames.get(position).toString());
    }
}
