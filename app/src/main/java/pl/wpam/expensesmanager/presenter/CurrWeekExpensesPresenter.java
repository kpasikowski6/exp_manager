package pl.wpam.expensesmanager.presenter;

import pl.wpam.expensesmanager.database.DatabaseHelper;
import pl.wpam.expensesmanager.utils.ExpenseListCollectionWrapper;
import pl.wpam.expensesmanager.view.CurrWeekExpensesView;

public class CurrWeekExpensesPresenter {

    private CurrWeekExpensesView currWeekExpensesView;
    private DatabaseHelper database;
    private ExpenseListCollectionWrapper expenseListCollectionWrapper;

    public CurrWeekExpensesPresenter(DatabaseHelper database, CurrWeekExpensesView currWeekExpensesView) {
        this.database = database;
        this.currWeekExpensesView = currWeekExpensesView;
        expenseListCollectionWrapper = new ExpenseListCollectionWrapper(this.database.getCurrentWeeksExpenses());
    }

    public void renderTotalExpenses() {
        currWeekExpensesView.showTotalExpenses(expenseListCollectionWrapper.getTotalExpense());
    }

    public void renderCurrentWeeksExpenses() {
        currWeekExpensesView.showCurrentWeeksExpenses(expenseListCollectionWrapper.groupByDate());
    }
}
