package pl.wpam.expensesmanager.presenter;

import com.echo.holographlibrary.Bar;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import pl.wpam.expensesmanager.database.DatabaseHelper;
import pl.wpam.expensesmanager.model.Expense;
import pl.wpam.expensesmanager.view.CurrMonthExpensesView;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CurrMonthExpensesPresenterTest {

  private CurrMonthExpensesView view;
  private DatabaseHelper database;

  @Before
  public void setUp() throws Exception {
    view = mock(CurrMonthExpensesView.class);
    database = mock(DatabaseHelper.class);
    DateTimeUtils.setCurrentMillisFixed(new DateTime("2015-10-01").getMillis());
  }

  @After
  public void tearDown() throws Exception {
    DateTimeUtils.setCurrentMillisSystem();
  }

  @Test
  public void shouldRenderTotalExpenses() throws Exception {
    Expense food = new Expense(100l, "Food", "01-10-2015", currency, exchangeRate);
    Expense travel = new Expense(150l, "Travel", "02-10-2015", currency, exchangeRate);
    when(database.getExpensesForCurrentMonthGroupByCategory()).thenReturn(asList(food, travel));

    CurrMonthExpensesPresenter presenter = new CurrMonthExpensesPresenter(view, database);

    presenter.showTotalExpense();
    verify(view).showTotalExpense(250l);
  }

  @Test
  public void shouldRenderTheGraphWithRightPoints() throws Exception {
    Expense food = new Expense(100l, "Food", "01-10-2015", currency, exchangeRate);
    when(database.getExpensesForCurrentMonthGroupByCategory()).thenReturn(asList(food));

    CurrMonthExpensesPresenter presenter = new CurrMonthExpensesPresenter(view, database);
    ArgumentCaptor<List> pointsCaptor = ArgumentCaptor.forClass(List.class);

    presenter.plotGraph();
    verify(view).getGraphColor();
    verify(view).showGraph(pointsCaptor.capture());

    List points = pointsCaptor.getValue();
    assertThat(points.size(), is(1));
    assertThat(((Bar)points.get(0)).getName(), is(food.getType()));
    assertThat(((Bar)points.get(0)).getValue(), is(food.getAmount().floatValue()));
  }
}