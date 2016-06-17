package net.ceksor;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.style.TtsSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Date;

public class CalcAct extends AppCompatActivity {
	public final static String RESSTR = "net.ceksor.RESULT";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calc);
		SharedPreferences p = getSharedPreferences(getPackageName(),MODE_PRIVATE);
		if(p.getBoolean("firstrun",true))
			startActivity(new Intent(this,CalcTutor.class));
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					Intent intent = new Intent(CalcAct.this, CalcResult.class);
					intent.putExtra(RESSTR,calculate());
					//intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					overridePendingTransition(android.R.anim.slide_in_left,
							android.R.anim.slide_out_right);
					startActivity(intent);
				} catch(Exception e) {
					e.printStackTrace(System.err);
					Snackbar.make(view, "Tüm Kutuları Doldurmalısınız!", Snackbar.LENGTH_LONG)
							/*.setAction("OK", null)*/.show();
				}
			}
		});
		fab.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {
				startActivity(new Intent(CalcAct.this,TestAct.class));
				return false;
			}
		});
		((DatePicker)findViewById(R.id.datePicker)).setMinDate((24000*3600) + System.currentTimeMillis() - (System.currentTimeMillis()%(24000*3600)));
		((EditText)findViewById(R.id.editText1)).requestFocus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_calc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		startActivity(new Intent(this,CalcTutor.class));
		if (id == R.id.action_settings) { return true; }
		return super.onOptionsItemSelected(item);
	}

	public double calculate() throws Exception
	{
		long cdate = System.currentTimeMillis(), date=0;
		double inp,per,val,com,out,def,pro;
		inp = Double.parseDouble(((TextView)findViewById(R.id.editText1)).getText().toString());
		per = Double.parseDouble(((TextView)findViewById(R.id.editText2)).getText().toString());
		pro = Double.parseDouble(((TextView)findViewById(R.id.editText3)).getText().toString());
		com = Double.parseDouble(((TextView)findViewById(R.id.editText4)).getText().toString());
		int y = ((DatePicker)findViewById(R.id.datePicker)).getYear();
		int m = ((DatePicker)findViewById(R.id.datePicker)).getMonth();
		int d = ((DatePicker)findViewById(R.id.datePicker)).getDayOfMonth();
		date = Date.parse(y+"/"+m+"/"+d); val=0;
		DatePicker p = ((DatePicker)findViewById(R.id.datePicker));
		if( ((RadioButton)findViewById(R.id.radioButton0)).isSelected() )
			val = 0;
		if( ((RadioButton)findViewById(R.id.radioButton1)).isSelected() )
			val = 1;
		if( ((RadioButton)findViewById(R.id.radioButton2)).isSelected() )
			val = 2;
		if( ((RadioButton)findViewById(R.id.radioButton3)).isSelected() )
			val = 3;
		def = 36000.0;
		if(per < 9.0) per = per*12.0;
		out = ((inp*per) * (Math.abs(cdate-date) / (24000*3600) + val) / def);
		out = (com*inp/100.0 + pro) + out;
		out = ( inp - ((5.0*out/100.0)+out) );
		/*
		System.out.println(inp);
		System.out.println(per);
		System.out.println(pro);
		System.out.println(com);
		System.out.println(date);
		System.out.println(cdate);
		System.out.println(val);
		System.out.println(out);
		System.out.println(def);
		*/
		return out;
		/*
		runOnUiThread(new Runnable() {
			@Override
			public void run() {

				if (!isFinishing()){
					new AlertDialog.Builder(CalcAct.this)
						.setTitle("Sonuç:")
						.setMessage(""+r)
						.setCancelable(false)
						.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) { return; }
						}).create().show();
				}
			}
		});
		*/
	}
}
