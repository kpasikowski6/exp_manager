package pl.wpam.expensesmanager.entity;

import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import pl.wpam.expensesmanager.model.ExpenseCategory;

public class ExpenseTypeEntity implements BaseColumns {
    public static final String TABLE_NAME = "expense_types";
    public static final String TYPE = "type";

    public static final String CREATE_TABLE_QUERY = "create table " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TYPE + " TEXT)";
    public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    public static List<ExpenseCategory> seedData() {
        ArrayList<ExpenseCategory> expenseCategories = new ArrayList<>();
        expenseCategories.add(new ExpenseCategory("Jedzenie"));
        expenseCategories.add(new ExpenseCategory("Podróże"));
        expenseCategories.add(new ExpenseCategory("Zdrowie"));
        expenseCategories.add(new ExpenseCategory("Zakupy"));
        expenseCategories.add(new ExpenseCategory("Mieszkanie"));
        expenseCategories.add(new ExpenseCategory("Inne"));

        return expenseCategories;
    }
}
