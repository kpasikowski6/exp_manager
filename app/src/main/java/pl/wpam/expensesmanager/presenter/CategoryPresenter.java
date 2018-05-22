package pl.wpam.expensesmanager.presenter;

import pl.wpam.expensesmanager.database.DatabaseHelper;
import pl.wpam.expensesmanager.model.ExpenseCategory;
import pl.wpam.expensesmanager.view.NewCategoryView;

public class CategoryPresenter {
    private final NewCategoryView view;
    private final DatabaseHelper database;

    public CategoryPresenter(NewCategoryView view, DatabaseHelper database) {
        this.view = view;
        this.database = database;
    }

    public boolean addCategory() {
        String category = view.getCategory();
        if (category.isEmpty()) {
            view.showError();
            return false;
        }

        database.addExpenseType(new ExpenseCategory(category));
        return true;
    }
}
