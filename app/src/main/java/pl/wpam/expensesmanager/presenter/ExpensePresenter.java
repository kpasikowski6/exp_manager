package pl.wpam.expensesmanager.presenter;

import pl.wpam.expensesmanager.database.DatabaseHelper;
import pl.wpam.expensesmanager.model.Expense;
import pl.wpam.expensesmanager.view.ExpenseView;

import static pl.wpam.expensesmanager.utils.DateHelper.getCurrentDate;

public class ExpensePresenter {

    private DatabaseHelper databaseHelper;
    private ExpenseView view;

    public ExpensePresenter(DatabaseHelper databaseHelper, ExpenseView view) {
        this.databaseHelper = databaseHelper;
        this.view = view;
    }

    public boolean addExpense() {
        String amount = view.getAmount();

        if (amount.isEmpty()) {
            view.showError();
            return false;
        }

        Expense expense = new Expense(Double.parseDouble(amount), view.getType(), getCurrentDate(), view.getCurrency(),
                view.getExchangeRate());
        databaseHelper.addExpense(expense);
        return true;
    }

    public void setExpenseTypes() {
        view.renderExpenseTypes(databaseHelper.getExpenseTypes());
    }
}
