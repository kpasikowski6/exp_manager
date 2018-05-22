package pl.wpam.expensesmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import pl.wpam.expensesmanager.model.Expense;
import pl.wpam.expensesmanager.model.ExpenseCategory;
import pl.wpam.expensesmanager.entity.ExpenseEntity;
import pl.wpam.expensesmanager.entity.ExpenseTypeEntity;
import pl.wpam.expensesmanager.utils.DateHelper;

import static pl.wpam.expensesmanager.utils.DateHelper.getCurrentDate;
import static pl.wpam.expensesmanager.utils.DateHelper.getCurrentWeekDates;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String EXPENSE_DB = "expense";

    public DatabaseHelper(Context context) {
        super(context, EXPENSE_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDb) {
        sqLiteDb.execSQL(ExpenseEntity.CREATE_TABLE_QUERY);
        sqLiteDb.execSQL(ExpenseTypeEntity.CREATE_TABLE_QUERY);
        seedExpenseTypes(sqLiteDb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public List<String> getExpenseTypes() {
        ArrayList<String> expenseTypes = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(ExpenseTypeEntity.SELECT_ALL, null);

        if (isCursorPopulated(cursor)) {
            do {
                String type = cursor.getString(cursor.getColumnIndex(ExpenseTypeEntity.TYPE));
                expenseTypes.add(type);
            } while (cursor.moveToNext());
        }

        return expenseTypes;
    }

    public void deleteAll() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(ExpenseTypeEntity.TABLE_NAME, "", new String[]{});
        database.delete(ExpenseEntity.TABLE_NAME, "", new String[]{});
        database.close();
    }

    public void addExpense(Expense expense) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ExpenseEntity.AMOUNT, expense.getAmount());
        values.put(ExpenseEntity.TYPE, expense.getType());
        values.put(ExpenseEntity.DATE, expense.getDate());
        values.put(ExpenseEntity.CURRENCY, expense.getCurrency());
        values.put(ExpenseEntity.EXCHANGE_RATE, expense.getExchangeRate());

        database.insert(ExpenseEntity.TABLE_NAME, null, values);
    }

    public List<Expense> getExpenses() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(ExpenseEntity.SELECT_ALL, null);

        return buildExpenses(cursor);
    }

    public List<Expense> getTodaysExpenses() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(ExpenseEntity.getExpensesForDate(getCurrentDate()), null);

        return buildExpenses(cursor);
    }

    public List<Expense> getCurrentWeeksExpenses() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(ExpenseEntity.getConsolidatedExpensesForDates(getCurrentWeekDates()), null);
        return buildExpenses(cursor);
    }

    public List<Expense> getExpensesForCurrentMonthGroupByCategory() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(ExpenseEntity.getExpenseForCurrentMonth(DateHelper.getCurrentMonthOfYear()), null);
        return buildExpenses(cursor);
    }

    public void addExpenseType(ExpenseCategory type) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ExpenseEntity.TYPE, type.getCategory());

        database.insert(ExpenseTypeEntity.TABLE_NAME, null, values);
    }

    public void truncate(String tableName) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from " + tableName);
    }

    private List<Expense> buildExpenses(Cursor cursor) {
        List<Expense> expenses = new ArrayList<>();
        if (isCursorPopulated(cursor)) {
            do {
                String type = cursor.getString(cursor.getColumnIndex(ExpenseEntity.TYPE));
                String amount = cursor.getString(cursor.getColumnIndex(ExpenseEntity.AMOUNT));
                String date = cursor.getString(cursor.getColumnIndex(ExpenseEntity.DATE));
                String id = cursor.getString(cursor.getColumnIndex(ExpenseEntity._ID));
                String currency = cursor.getString(cursor.getColumnIndex(ExpenseEntity.CURRENCY));
                Double exchangeRate = cursor.getDouble(cursor.getColumnIndex(ExpenseEntity.EXCHANGE_RATE));


                Expense expense = id == null ? new Expense(Double.parseDouble(amount), type, date, currency, exchangeRate)
                        : new Expense(parseInt(id), Double.parseDouble(amount), type, date, currency, exchangeRate);
                expenses.add(expense);
            } while (cursor.moveToNext());
        }

        return expenses;
    }

    private boolean isCursorPopulated(Cursor cursor) {
        return cursor != null && cursor.moveToFirst();
    }

    private void seedExpenseTypes(SQLiteDatabase sqLiteDatabase) {
        List<ExpenseCategory> expenseCategories = ExpenseTypeEntity.seedData();
        for (ExpenseCategory expenseCategory : expenseCategories) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ExpenseTypeEntity.TYPE, expenseCategory.getCategory());

            sqLiteDatabase.insert(ExpenseTypeEntity.TABLE_NAME, null, contentValues);
        }
    }
}
