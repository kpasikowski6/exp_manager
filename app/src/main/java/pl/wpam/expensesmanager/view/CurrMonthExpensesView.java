package pl.wpam.expensesmanager.view;

import com.echo.holographlibrary.Bar;

import java.util.List;

public interface CurrMonthExpensesView {
    void showGraph(List<Bar> points);

    void showTotalExpense(Double totalExpense);

    int getGraphColor();
}
