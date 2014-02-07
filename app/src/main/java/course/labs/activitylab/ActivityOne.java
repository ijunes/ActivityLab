package course.labs.activitylab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityOne extends Activity {

	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";

	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityOne";
	
	// Lifecycle counters

	// TODO:
    int mCreate, mStart, mResume, mRestart;

	// TODO: Create variables for each of the TextViews, called
        TextView mTvCreate, mTvStart, mTvResume, mTvRestart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);
        mTvCreate = (TextView) findViewById(R.id.create);
        mTvRestart = (TextView) findViewById(R.id.restart);
        mTvResume = (TextView) findViewById(R.id.resume);
        mTvStart = (TextView) findViewById(R.id.start);

		Button launchActivityTwoButton = (Button) findViewById(R.id.bLaunchActivityTwo); 
		launchActivityTwoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
                intent.setClass(getBaseContext(), ActivityTwo.class);
                startActivity(intent);

			}
		});
		
		// Check for previously saved state
		if (savedInstanceState != null) {
            mCreate = savedInstanceState.getInt("mCreate");
            mResume = savedInstanceState.getInt("mResume");
            mRestart = savedInstanceState.getInt("mRestart");
            mStart = savedInstanceState.getInt("mStart");

		}

        Log.i(TAG, "onCreate calls: " +String.valueOf(mCreate));
        mCreate = mCreate + 1;
        displayCounts();


	}

	// Lifecycle callback overrides

	@Override
	public void onStart() {
		super.onStart();
		Log.i(TAG, "onStart calls: " + String.valueOf(mCreate));
		mStart = mStart + 1;
        displayCounts();
	}

	@Override
	public void onResume() {
		super.onResume();
        Log.i(TAG, "onResume calls: " + String.valueOf(mResume));
        mResume = mResume + 1;
        displayCounts();
	}


    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG , "onPause");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }


	@Override
	public void onRestart() {
		super.onRestart();
        mRestart = mRestart + 1;
		Log.i(TAG, "onRestart calls: " +  String.valueOf(mRestart));
        displayCounts();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
        Log.i(TAG, "onDestroy");
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("mCreate", mCreate);
        savedInstanceState.putInt("mResume", mResume);
        savedInstanceState.putInt("mRestart", mRestart);
        savedInstanceState.putInt("mStart", mStart);
	}

	public void displayCounts() {

		mTvCreate.setText("onCreate() calls: " + mCreate);
		mTvStart.setText("onStart() calls: " + mStart);
		mTvResume.setText("onResume() calls: " + mResume);
		mTvRestart.setText("onRestart() calls: " + mRestart);
	
	}
}
