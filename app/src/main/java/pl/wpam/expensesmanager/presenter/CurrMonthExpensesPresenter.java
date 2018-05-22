package pl.wpam.expensesmanager.presenter;

import com.echo.holographlibrary.Bar;

import java.util.ArrayList;
import java.util.List;

import pl.wpam.expensesmanager.database.DatabaseHelper;
import pl.wpam.expensesmanager.model.Expense;
import pl.wpam.expensesmanager.utils.ExpenseListCollectionWrapper;
import pl.wpam.expensesmanager.view.CurrMonthExpensesView;


public class CurrMonthExpensesPresenter {
    private final CurrMonthExpensesView view;
    private final ExpenseListCollectionWrapper expenseListCollectionWrapper;

    public CurrMonthExpensesPresenter(CurrMonthExpensesView currMonthExpensesView, DatabaseHelper database) {
        this.view = currMonthExpensesView;
        List<Expense> expenses = database.getExpensesForCurrentMonthGroupByCategory();
        expenseListCollectionWrapper = new ExpenseListCollectionWrapper(expenses);
    }

    public void plotGraph() {
        List<Bar> points = new ArrayList<Bar>();

        for (Expense expense : expenseListCollectionWrapper.withoutMoneyTransfer()) {
            Bar bar = new Bar();
            bar.setColor(view.getGraphColor());
            bar.setName(expense.getType());
            bar.setValue(expense.getAmount().floatValue());
            points.add(bar);
        }

        view.showGraph(points);
    }

    public void showTotalExpense() {
        view.showTotalExpense(expenseListCollectionWrapper.getTotalExpense());
    }
}
