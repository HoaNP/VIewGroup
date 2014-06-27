package mhst.example.groupview;

import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class ListWorkApdapter extends ArrayAdapter<Work> {
	ArrayList<Work> array;
	int resource;
	Context context;

	public ListWorkApdapter(Context context, int textViewResourceId,
			ArrayList<Work> objects) {
		
		super(context, textViewResourceId, objects);
		this.context = context;
		resource = textViewResourceId;
		array = objects;
		}
	
		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			View workView = convertView;
			if (workView == null){
				workView = new CustomViewGroup(getContext());
				
			}
			final Work work = array.get(position);
			if (work != null) {
				TextView workContent = ((CustomViewGroup)workView).workContent;
				TextView timeContent = ((CustomViewGroup)workView).timeContent;
				CheckBox checkWork = ((CustomViewGroup)workView).cb;
				checkWork.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						work.setChecked(isChecked);
						
					}
				});
				workContent.setText(work.getContent());
				timeContent.setText(work.getTime());
				checkWork.setChecked(work.isChecked());	
				
			}
			return workView;
		}
		
		
	}

