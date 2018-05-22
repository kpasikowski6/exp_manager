package pl.wpam.expensesmanager.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import pl.wpam.expensesmanager.model.Expense;

public class TodayExpensesListViewAdapter extends ArrayAdapter {
    private final List<Expense> expenses;

    public TodayExpensesListViewAdapter(List<Expense> expenses, Context context, int resource) {
        super(context, resource);
        this.expenses = expenses;
    }

    @Override
    public int getCount() {
        return expenses.size();
    }

    @Override
    public Object getItem(int pos) {
        Expense expense = expenses.get(pos);
        return expense.getType() + " - " + expense.getAmount();
    }

    @Override
    public long getItemId(int pos) {
        return expenses.get(pos).getId();
    }
}
