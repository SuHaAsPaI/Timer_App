package com.example.timer;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener
{
	private static final String tag = "Main";
	private MalibuCountDownTimer countDownTimer;
	private long timeElapsed;
	private boolean timerHasStarted = false;
	private Button startB;
	private TextView text;
	private TextView timeElapsedView;
	private EditText einterval;
	private EditText etime;

	private long startTime = 0;
	private long interval = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			startB = (Button) this.findViewById(R.id.button1);
			startB.setOnClickListener(this);

			text = (TextView) this.findViewById(R.id.textView2);
			timeElapsedView = (TextView) this.findViewById(R.id.textView1);
			
			einterval=(EditText)this.findViewById(R.id.interval1);
			etime=(EditText)this.findViewById(R.id.count1);
			text.setText(text.getText() + String.valueOf(startTime));
		}

	@Override
	public void onClick(View v){
		
		String a=etime.getText().toString();
		String b=einterval.getText().toString();
		Float data1 = new Float(a);
		Float data2 = new Float(b);
		startTime=data1.longValue();
		interval=data2.longValue();
		
		countDownTimer = new MalibuCountDownTimer(startTime,interval);
		if (!timerHasStarted)
				{
					countDownTimer.start();
					timerHasStarted = true;
					startB.setText("Start");
				}
			else
				{

					countDownTimer.cancel();
					timerHasStarted = false;
					startB.setText("RESET");
				}
		}

	// CountDownTimer class
	public class MalibuCountDownTimer extends CountDownTimer{

			public MalibuCountDownTimer(long startTime, long interval)
				{
					super(startTime, interval);
				}

			@Override
			public void onFinish()
				{
					text.setText("Time's up!");
					timeElapsedView.setText("Time Elapsed: " + String.valueOf(startTime));
				}

			@Override
			public void onTick(long millisUntilFinished)
				{
					text.setText("Time remain:" + millisUntilFinished);
					timeElapsed = startTime - millisUntilFinished;
					timeElapsedView.setText("Time Elapsed: " + String.valueOf(timeElapsed));
				}
		}
}