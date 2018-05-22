package pl.wpam.expensesmanager.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import pl.wpam.expensesmanager.model.Expense;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ExpenseListCollectionWrapperTest {

  private Expense food;
  private Expense travel;
  private ExpenseListCollectionWrapper expenseListCollectionWrapper;

  @Before
  public void setUp() throws Exception {
    food = new Expense(100l, "Food", "31-09-2015", currency, exchangeRate);
    travel = new Expense(150l, "Travel", "02-10-2015", currency, exchangeRate);
    Expense movie = new Expense(100l, "Movie", "02-10-2015", currency, exchangeRate);
    expenseListCollectionWrapper = new ExpenseListCollectionWrapper(asList(food, movie, travel));
  }

  @Test
  public void shouldReturnTotalExpense() throws Exception {
    assertThat(expenseListCollectionWrapper.getTotalExpense(), is(350l));
  }

  @Test
  public void shouldReturnExpensesGroupedByDate() throws Exception {
    Map<String, List<Expense>> expenses = expenseListCollectionWrapper.groupByDate();

    assertThat(expenses.keySet().size(), is(2));
    assertThat(expenses.get(travel.getDate()).size(), is(2));
    assertThat(expenses.get(food.getDate()).size(), is(1));
  }
}