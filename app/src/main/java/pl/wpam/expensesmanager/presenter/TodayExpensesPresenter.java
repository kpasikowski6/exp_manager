package pl.wpam.expensesmanager.presenter;

import java.util.List;

import pl.wpam.expensesmanager.database.DatabaseHelper;
import pl.wpam.expensesmanager.model.Expense;
import pl.wpam.expensesmanager.view.TodayExpensesView;

public class TodayExpensesPresenter {

    private TodayExpensesView todayExpensesView;
    private final List<Expense> expenses;

    public TodayExpensesPresenter(TodayExpensesView todayExpensesView, DatabaseHelper databaseHelper) {
        this.todayExpensesView = todayExpensesView;
        expenses = databaseHelper.getTodaysExpenses();
    }

    public void renderTotalExpense() {
        Double totalExpense = 0.0;
        for (Expense expense : expenses)
            totalExpense += expense.getAmount();

        todayExpensesView.showTotalExpense(totalExpense);
    }

    public void renderTodaysExpenses() {
        todayExpensesView.showTodaysExpenses(expenses);
    }
}
