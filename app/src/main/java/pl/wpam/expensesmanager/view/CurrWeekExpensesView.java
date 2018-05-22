package pl.wpam.expensesmanager.view;

import java.util.List;
import java.util.Map;

import pl.wpam.expensesmanager.model.Expense;

public interface CurrWeekExpensesView {
    void showCurrentWeeksExpenses(Map<String, List<Expense>> expensesByDate);

    void showTotalExpenses(Double totalExpense);
}
