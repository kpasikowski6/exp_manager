package pl.wpam.expensesmanager.view;

import java.util.List;

import pl.wpam.expensesmanager.model.Expense;

public interface TodayExpensesView {

    void showTotalExpense(Double totalExpense);

    void showTodaysExpenses(List<Expense> expenses);

}
