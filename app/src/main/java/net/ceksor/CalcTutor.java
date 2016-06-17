package net.ceksor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.*;

import android.widget.ImageView;
import android.widget.TextView;

public class CalcTutor extends AppCompatActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link FragmentPagerAdapter} derivative, which will keep every
	 * loaded fragment in memory. If this becomes too memory intensive, it
	 * may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	private SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	public ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calc_tutor);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);


		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				//		.setAction("Action", null).show();
				onBackPressed();
			}
		});

	}
	@Override
	public void onBackPressed() {
		SharedPreferences p = getSharedPreferences(getPackageName(),MODE_PRIVATE);
		p.edit().putBoolean("firstrun",false).commit();
		overridePendingTransition(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
		super.onBackPressed();
		return;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.menu_calc_tutor, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		//if (id == R.id.action_settings) { return true; }
		return super.onOptionsItemSelected(item);
	}


	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class below).
			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section
		 * number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		                         Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_calc_tutor, container, false);
			TextView textView = (TextView) rootView.findViewById(R.id.section_label);
			ImageView img = (ImageView) rootView.findViewById(R.id.imageView);
			img.setBackgroundColor(0);
			//img.setVisibility(View.INVISIBLE);
			switch(getArguments().getInt(ARG_SECTION_NUMBER))
			{
				case 1:
					//img.setBackgroundColor(0xffffffff);
					textView.setText("ÇekSor | Çek hesaplama");
					img.setImageResource(getResources().getIdentifier("net.ceksor:drawable/logo2", null, null));
					break;
				case 2:
					textView.setText("çek bilgilerini girip, giriş tuşuna basın");
					img.setImageResource(getResources().getIdentifier("net.ceksor:drawable/pic1", null, null));
					break;
				case 3:
					textView.setText("çekinizin degerini ögrenin!\nçıkmak için x'e basın.");
					img.setImageResource(getResources().getIdentifier("net.ceksor:drawable/pic2", null, null));
					((TextView)rootView.findViewById(R.id.textView)).setText("çıkış için >>");
					break;
				case 4:
					getActivity().onBackPressed();
					textView.setText("kurulum tamamlandı");
					img.setVisibility(View.INVISIBLE);
					getActivity().finish();
					try { finalize(); } catch(Throwable t) { t.printStackTrace(System.err); }
					break;
				default:
					textView.setText("error");
			}
			return rootView;
		}
	}
}
