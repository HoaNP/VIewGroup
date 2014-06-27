package mhst.example.groupview;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Build;

public class MainActivity extends Activity {
	private static final int DELETE_WORK = Menu.FIRST;
	private static final int ABOUT = Menu.FIRST + 2;
	ArrayList<Work> array;
	ListWorkApdapter arrayAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		array = new ArrayList<Work>();
		arrayAdapter = new ListWorkApdapter(this, R.layout.list, array);
		final EditText workEnter = (EditText) findViewById(R.id.work_enter);
		final EditText hourEdit = (EditText) findViewById(R.id.hour_edit);
		final EditText minuteEdit = (EditText) findViewById(R.id.minute_edit);
		final Button button = (Button) findViewById(R.id.button);
		final ListView list = (ListView) findViewById(R.id.list);
		list.setAdapter(arrayAdapter);
		OnClickListener add = new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (workEnter.getText().toString().equals("")
						|| hourEdit.getText().toString().equals("")
						|| minuteEdit.getText().toString().equals("")) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							MainActivity.this);
					builder.setTitle("Info missing");
					builder.setMessage("Please enter all information of the work");
					builder.setPositiveButton("Continue",
							new DialogInterface.OnClickListener() {

								// @Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub

								}
							});
					builder.show();
					// TODO Auto-generated method stub
				} else {
					String workContent = workEnter.getText().toString();
					String timeContent = hourEdit.getText().toString() + ":"
							+ minuteEdit.getText().toString();
					Work work = new Work(workContent, timeContent);
					array.add(0, work);
					arrayAdapter.notifyDataSetChanged();
					workEnter.setText("");
					hourEdit.setText("");
					minuteEdit.setText("");
				}
			}
		};
		button.setOnClickListener(add);
	}

	// @Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		menu.add(0, DELETE_WORK, 0, "delete").setIcon(
				android.R.drawable.ic_delete);
		menu.add(0, ABOUT, 0, "About").setIcon(
				android.R.drawable.ic_menu_info_details);
		return true;
	}

	// @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case DELETE_WORK: {
			deleteCheckedWork();
			break;
		}
		case ABOUT: {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("VietAndroid");
			builder.setMessage("Author:" + "\n" + "Nguyen Phuong Hoa" + "\n");
			builder.setPositiveButton("Close",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
						}
					});
			builder.setIcon(android.R.drawable.ic_dialog_info);
			builder.show();
			break;
		}
		}
		return true;

	}

	private void deleteCheckedWork() {

		if (array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				if (i > array.size()) {
					break;
				}
				if (array.get(i).isChecked()) {
					array.remove(i);
					arrayAdapter.notifyDataSetChanged();
					continue;
				}
			}
		}
	}

}