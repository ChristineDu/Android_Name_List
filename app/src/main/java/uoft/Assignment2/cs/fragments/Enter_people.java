package uoft.Assignment2.cs.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
/**
 * Created by Christine on 16-01-21.
 */
public class Enter_people extends Fragment {
    String selected="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_people, parentViewGroup, false);
        getActivity().setTitle("Enter Names");
        Context context = getActivity();
        final SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);


        final Button button = (android.widget.Button)(rootView.findViewById(R.id.button));
        final EditText name = (EditText)(rootView.findViewById(R.id.name));
        final EditText age = (EditText)(rootView.findViewById(R.id.age));
        final Spinner spinner = (Spinner)(rootView.findViewById(R.id.drop_down));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.drop_down_movies, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selected = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View view) {
                        String get_name = name.getText().toString();
                        String get_age = age.getText().toString();
                        if (selected.equals("Select favourite movie")|| get_name.equals("") || get_age.equals("")) {
                            Toast.makeText(getActivity(), "Please enter all information",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(get_name, "\n" + get_age + "\n" + selected );
                            editor.commit();
                            Toast.makeText(getActivity(), get_name,
                                    Toast.LENGTH_LONG).show();
                            name.setText("");
                            age.setText("");
                            spinner.setSelection(0);
                            get_name = "";
                            get_age = "";
                            selected = "";


                        }

                    }
                });


        return rootView;

    }
}
