package pl.wpam.expensesmanager.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import pl.wpam.expensesmanager.R;
import pl.wpam.expensesmanager.adapter.CurrWeekExpensesAdapter;
import pl.wpam.expensesmanager.database.DatabaseHelper;
import pl.wpam.expensesmanager.model.Expense;
import pl.wpam.expensesmanager.presenter.CurrWeekExpensesPresenter;
import pl.wpam.expensesmanager.view.CurrWeekExpensesView;

public class CurrWeekExpensesFragment extends Fragment implements CurrWeekExpensesView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.curr_week_expenses, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        CurrWeekExpensesPresenter currWeekExpensesPresenter = new CurrWeekExpensesPresenter(databaseHelper, this);
        currWeekExpensesPresenter.renderTotalExpenses();
        currWeekExpensesPresenter.renderCurrentWeeksExpenses();
        databaseHelper.close();
    }

    @Override
    public void showCurrentWeeksExpenses(Map<String, List<Expense>> expensesByDate) {
        ExpandableListView listView = getActivity().findViewById(R.id.current_week_expenses_list);
        listView.setAdapter(new CurrWeekExpensesAdapter(getActivity(), expensesByDate));
    }

    @Override
    public void showTotalExpenses(Double total) {
        TextView totalExpenseTextBox = getActivity().findViewById(R.id.current_week_expense);
        totalExpenseTextBox.setText(getString(R.string.total_expense) + " " + total + " " + getString(R.string.rupee_sym));
    }
}
