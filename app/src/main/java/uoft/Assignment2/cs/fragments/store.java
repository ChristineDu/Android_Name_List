package uoft.Assignment2.cs.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

/**
 * Created by Christine on 16-01-23.
 */
public class store extends Fragment {
    public Map data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.store, parentViewGroup, false);
        getActivity().setTitle("Store Entered Names");
        final Button button = (android.widget.Button)(rootView.findViewById(R.id.file_button));
        final EditText name = (EditText)(rootView.findViewById(R.id.filename));
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        data = sharedPref.getAll();
        if(data.size()==0){
            Toast.makeText(getActivity(), "Nothing to  save",
                    Toast.LENGTH_LONG).show();
            if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                getActivity().finish();
            }
        }else{
            button.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View view) {
                            String filename = name.getText().toString();
                            File file = new File(getActivity().getFilesDir(), filename);
                            try {
                                FileOutputStream stream = new FileOutputStream(file, true);
                                for (Object name : data.keySet()) {
                                    stream.write((name.toString() + data.get(name) + "\n").getBytes());
                                }
                                stream.close();
                                getActivity().getSharedPreferences(getString(R.string.preference_file_key), 0)
                                        .edit().clear().commit();
                                if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                                    getActivity().finish();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
        }

        return rootView;

    }
}
