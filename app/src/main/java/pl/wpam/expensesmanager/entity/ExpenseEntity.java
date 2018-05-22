package pl.wpam.expensesmanager.entity;

import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class ExpenseEntity implements BaseColumns {
    public static final String TABLE_NAME = "expenses";
    public static final String AMOUNT = "amount";
    public static final String TYPE = "type";
    public static final String DATE = "date";
    public static final String CURRENCY = "currency";
    public static final String EXCHANGE_RATE = "exchange_rate";

    public static final String CREATE_TABLE_QUERY = "create table " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            AMOUNT + " INTEGER," +
            TYPE + " TEXT, " +
            DATE + " TEXT, " +
            CURRENCY + " TEXT, " +
            EXCHANGE_RATE + " REAL)";

    public static final String SELECT_ALL = "SELECT " + _ID + ", date, type, (amount*exchange_rate) as amount, currency, exchange_rate FROM " + TABLE_NAME + " ORDER BY " + _ID + " DESC";

    public static final String SELECT_ALL_GROUP_BY_CATEGORY = "SELECT " + _ID + ", date, type, sum(amount*exchange_rate) as amount, currency, exchange_rate FROM " + TABLE_NAME + " GROUP BY type";

    public static String getExpensesForDate(String date) {
        return "SELECT " + _ID + ", date, type, (amount*exchange_rate) as amount, currency, exchange_rate FROM " + TABLE_NAME + " WHERE date like '" + date + "%' ORDER BY " + _ID + " DESC";
    }

    public static String getConsolidatedExpensesForDates(List<String> dates) {
        StringBuilder dateLike = new StringBuilder();
        for (String date : dates) {
            dateLike.append("date like '").append(date).append("%' ").append(dates.get(dates.size() - 1).equals(date) ? "" : "or ");
        }

        return "SELECT " + _ID + ", date, type, sum(amount*exchange_rate) as amount, currency, exchange_rate FROM " + TABLE_NAME + " WHERE " + dateLike + " GROUP BY date, type";
    }

    public static String getExpenseForCurrentMonth(String currentMonthOfYear) {
        String currentMonthsExpenses = "(SELECT " + _ID + ", date, type, (amount*exchange_rate) as amount, currency, exchange_rate FROM " +
                TABLE_NAME + " WHERE date like '%-" + currentMonthOfYear + "')";

        return "SELECT " + _ID + ", date, type, sum(amount) as amount, currency, exchange_rate FROM " +
                currentMonthsExpenses + " GROUP BY type";
    }
}
