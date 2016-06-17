package net.ceksor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalcResult extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calc_result);
		Intent i = getIntent();
		if(i.getExtras().isEmpty())
		{
			onBackPressed();
			return;
		}
		((TextView)findViewById(R.id.textView2)).setText(""+i.getDoubleExtra(CalcAct.RESSTR,0));
		//findViewById(R.id.button1).setVisibility(View.INVISIBLE);
		findViewById(R.id.button2).setVisibility(View.INVISIBLE);
		((EditText)findViewById(R.id.editText)).requestFocus();
	}
	@Override
	public void onBackPressed() {
		overridePendingTransition(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
		super.onBackPressed();
		return;
	}
}
