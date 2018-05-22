package pl.wpam.expensesmanager.presenter;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import pl.wpam.expensesmanager.database.DatabaseHelper;
import pl.wpam.expensesmanager.model.Expense;
import pl.wpam.expensesmanager.view.TodayExpensesView;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodayExpensesPresenterTest {

    private DatabaseHelper database;
    private TodayExpensesView view;
    private TodayExpensesPresenter presenter;
    private List<Expense> expenses;

    @Before
    public void setUp() throws Exception {
        database = mock(DatabaseHelper.class);
        view = mock(TodayExpensesView.class);

        Expense expense1 = new Expense(90.0, "Food", "03-09-2015", "PLN", 1.0);
        Expense expense2 = new Expense(100.0, "Travel", "31-08-2015", "PLN", 1.0);
        expenses = asList(expense1, expense2);
        when(database.getTodaysExpenses()).thenReturn(expenses);

        presenter = new TodayExpensesPresenter(view, database);
    }

    @Test
    public void shouldRenderTodaysExpenses() throws Exception {
        presenter.renderTodaysExpenses();
        verify(view).showTodaysExpenses(expenses);
    }

    @Test
    public void shouldRenderTodaysTotalExpenses() throws Exception {
        presenter.renderTotalExpense();
        verify(view).showTotalExpense(190.0);
    }
}