package cpsc481.fall2016.uofcreccentret04;

/**
 * Created by nicol on 11/15/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class fc_pager_adapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public fc_pager_adapter (FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                activity_fitness_centre_t_tab1_overview tab1 = new activity_fitness_centre_t_tab1_overview();
                return tab1;
            case 1:
                activity_fitness_centre_t_tab2_traffic tab2 = new activity_fitness_centre_t_tab2_traffic();
                return tab2;
            case 2:
                activity_fitness_centre_t_tab3_programs tab3 = new activity_fitness_centre_t_tab3_programs();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
