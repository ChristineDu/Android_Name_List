package uoft.Assignment2.cs.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class FragmentLayout extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_layout);
	}

	public static class DetailsActivity extends Activity implements Load_file.DataPassListener {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
//            File dir = getFilesDir();
//            File file = new File(dir, "first");
//            file.delete();

			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				finish();
				return;
			}

			if (savedInstanceState == null) {

                int index = getIntent().getIntExtra("index",0);
                switch (index){
                    case 0:
                        Enter_people enter_information = new Enter_people();
                        getFragmentManager().beginTransaction()
                                .add(android.R.id.content, enter_information).commit();
                        break;
                    case 1:
                        View_temp view_temp = new View_temp();
                        getFragmentManager().beginTransaction()
                                .add(android.R.id.content, view_temp).commit();
                        break;
                    case 2:
                        store storeToFile = new store();
                        getFragmentManager().beginTransaction()
                                .add(android.R.id.content, storeToFile).commit();
                        break;
                    case 3:
                        Load_file load_file = new Load_file();
                        getFragmentManager().beginTransaction()
                                .add(android.R.id.content, load_file).commit();
                        break;
                    case 4:
                        this.finish();
                        System.exit(0);
                 }
			}
		}
        @Override
        public void passData(String data) {
            Show_filecontent show_file = new Show_filecontent();
            Bundle args = new Bundle();
            args.putString(show_file.DATA_RECEIVE, data);
            show_file.setArguments(args);
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, show_file )
                    .commit();
        }
    }


	public static class TitlesFragment extends ListFragment {
		int mCurCheckPosition = 0;
		final String[] start_list =
				{
                        "EnterNames",
						"View",
						"Store",
						"Load",
						"Exit"
				};

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
            getActivity().setTitle("Welcome");
			setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, start_list));
			//View detailsFrame = getActivity().findViewById(R.id.details);

			if (savedInstanceState != null) {
				// Restore last state for checked position.
				mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
                showDetails(mCurCheckPosition);
			}


		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			outState.putInt("curChoice", mCurCheckPosition);
		}


		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			showDetails(position);
		}


		void showDetails(int index) {
			mCurCheckPosition = index;
            Intent intent = new Intent();
            intent.setClass(getActivity(), DetailsActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);

		}
	}

}
