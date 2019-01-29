package project.experience;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.greatmahanta.grmframework.R;

import project.adapter.MyPagerAdapter;

public class PagerActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pager);


    TabLayout lay_tab = findViewById(R.id.lay_tab);
    ViewPager viw_pager = findViewById(R.id.viw_pager);
    MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

    pagerAdapter.addFragment(new FragmentCalls());
    pagerAdapter.addFragment(new FragmentContacts());
    pagerAdapter.addFragment(new FragmentMessages());

    viw_pager.setAdapter(pagerAdapter);

    lay_tab.setupWithViewPager(viw_pager);

    lay_tab.setSelectedTabIndicatorColor(Color.BLUE);

    lay_tab.getTabAt(0).setText("Calls");
    lay_tab.getTabAt(0).setIcon(R.drawable.ic_call_black_24dp);

    lay_tab.getTabAt(1).setText("Contacts");
    lay_tab.getTabAt(1).setIcon(R.drawable.ic_contacts_black_24dp);

    lay_tab.getTabAt(2).setText("Messages");
    lay_tab.getTabAt(2).setIcon(R.drawable.ic_message_black_24dp);

  }



}
