package pl.wpam.expensesmanager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import pl.wpam.expensesmanager.activity.ExpenseFragment;
import pl.wpam.expensesmanager.activity.TodayExpensesFragment;

public class HomeViewAdapter extends FragmentStatePagerAdapter {

    public HomeViewAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return new ExpenseFragment();
            case 1:
                return new TodayExpensesFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
