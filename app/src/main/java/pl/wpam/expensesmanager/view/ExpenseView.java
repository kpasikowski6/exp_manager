package pl.wpam.expensesmanager.view;

import java.util.List;

public interface ExpenseView {
    String getAmount();

    String getType();

    String getCurrency();

    Double getExchangeRate();

    void renderExpenseTypes(List<String> expenseTypes);

    void showError();
}
