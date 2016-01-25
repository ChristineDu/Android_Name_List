package uoft.Assignment2.cs.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Christine on 16-01-23.
 */
public class Show_filecontent extends ListFragment {
    final static String DATA_RECEIVE = "data_receive";
    public ArrayList file_content= new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.view_people, parentViewGroup, false);
        getActivity().setTitle("Load Existing File");
        Bundle args = getArguments();
        String filename = args.getString(DATA_RECEIVE);

        BufferedReader input = null;
        File file = null;
        try {
            file = new File(getActivity().getFilesDir(), filename);

            input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            String tmp ="";
            int i = 1;
            while ((line = input.readLine()) != null) {
                if(i % 3==0){
                    tmp=tmp+line;
                    file_content.add(tmp);
                    tmp = "";
                    i=1;

                }else{
                    tmp=tmp+line+"\n";
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file_content.isEmpty()){
            Toast.makeText(getActivity(), "Nothing in this file",
                    Toast.LENGTH_LONG).show();
        }else{
            setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, file_content));

        }
        return rootView;
    }

}
