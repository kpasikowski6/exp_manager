package pl.wpam.expensesmanager.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import pl.wpam.expensesmanager.R;
import pl.wpam.expensesmanager.adapter.TodayExpensesListViewAdapter;
import pl.wpam.expensesmanager.database.DatabaseHelper;
import pl.wpam.expensesmanager.model.Expense;
import pl.wpam.expensesmanager.presenter.TodayExpensesPresenter;
import pl.wpam.expensesmanager.view.TodayExpensesView;

public class TodayExpensesFragment extends Fragment implements TodayExpensesView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.today_expenses, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DatabaseHelper databaseHelper = new DatabaseHelper(this.getActivity());
        TodayExpensesPresenter todayExpensesPresenter = new TodayExpensesPresenter(this, databaseHelper);

        todayExpensesPresenter.renderTodaysExpenses();
        todayExpensesPresenter.renderTotalExpense();
        databaseHelper.close();
    }

    @Override
    public void showTotalExpense(final Double totalExpense) {
        TextView totalExpenseTextBox = getActivity().findViewById(R.id.total_expense);
        totalExpenseTextBox.setText(getActivity().getString(R.string.total_expense) + " " + totalExpense.toString() + " " + getActivity().getString(R.string.rupee_sym));
    }

    @Override
    public void showTodaysExpenses(final List<Expense> expenses) {
        ListView listView = getActivity().findViewById(R.id.todays_expenses_list);
        listView.setAdapter(new TodayExpensesListViewAdapter(expenses, getActivity(), android.R.layout.simple_list_item_1));
    }
}
