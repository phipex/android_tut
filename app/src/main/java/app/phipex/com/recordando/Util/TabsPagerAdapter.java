package app.phipex.com.recordando.Util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.support.v13.app.FragmentPagerAdapter;

import app.phipex.com.recordando.CrearContactoFragment;
import app.phipex.com.recordando.ListaContactosFragment;

/**
 * Created by sony vaio on 21/12/2015.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new CrearContactoFragment();
            case 1: return new ListaContactosFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;//solo hay 2 fragmanetos
    }
}
