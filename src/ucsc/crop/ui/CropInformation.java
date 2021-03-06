package ucsc.crop.ui;

import java.util.ArrayList;

import ucsc.crop.Crop;
import ucsc.crop.CropViewSorter;
import ucsc.crop.R;
import ucsc.crop.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class CropInformation extends Activity {
	ArrayList<Crop> cropResult;
	CropViewSorter sorter;
	GridView display;
	TextView topic;
TextView topicsortedby;
	
	Button nextButton;
	Button previousButton;
	Button toogleButton;
	
	Button homeButton;
	
	int tooglestatus = TOOGLE_STATUS_CROP;

	static int TOOGLE_STATUS_CROP = 0;
	static int TOOGLE_STATUS_LOCATION = 1;

	private void nextButtonClick() {
		sorter.next();
		topic.setText(sorter.getCurrentTopic());
		display.setAdapter(sorter);
	}

	private void prevButtonClick() {
		sorter.previous();
		topic.setText(sorter.getCurrentTopic());
		display.setAdapter(sorter);
	}
	private void homeButtonClick(View arg0) {
		Intent hom=new Intent(arg0.getContext(), LocationSelection.class);
		startActivityForResult(hom, 0);
	}
	private void toogleButtonClick() {
		if (tooglestatus == TOOGLE_STATUS_CROP) {
			sorter = new CropViewSorter(Crop.sort(Crop.SORTBY_LOCATION,
					cropResult), this,CropViewSorter.SORTED_BY_LOCATION);
			display.setAdapter(sorter);
			tooglestatus = TOOGLE_STATUS_LOCATION;
			topic.setText(sorter.getCurrentTopic());
			toogleButton.setText("Crop");
		} else {
			sorter = new CropViewSorter(
					Crop.sort(Crop.SORTBY_CROP, cropResult), this,CropViewSorter.SORTED_BY_CROP);
			tooglestatus = TOOGLE_STATUS_CROP;
			display.setAdapter(sorter);
			tooglestatus=TOOGLE_STATUS_CROP;
			topic.setText(sorter.getCurrentTopic());
			toogleButton.setText("Location");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		topic = (TextView) findViewById(R.id.topic);
		topicsortedby=(TextView)findViewById(R.id.topicsortedby);
		nextButton = (Button) findViewById(R.id.nextbutton);
		cropResult = LocationSelection.CropResult;
		sorter = new CropViewSorter(Crop.sort(Crop.SORTBY_CROP, cropResult),
				this,CropViewSorter.SORTED_BY_CROP);
		topic.setText(sorter.getCurrentTopic());
		display = (GridView) findViewById(R.id.gridtext);
		display.setAdapter(sorter);
		nextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				nextButtonClick();
			}
		});
		previousButton = (Button) findViewById(R.id.prevbutton);
		previousButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				prevButtonClick();
			}

		});
		toogleButton = (Button) findViewById(R.id.togglebutton);
		toogleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				toogleButtonClick();

			}

		});
		homeButton= (Button) findViewById(R.id.home);
		homeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				homeButtonClick(arg0);	
			}

			
		});

	}
}