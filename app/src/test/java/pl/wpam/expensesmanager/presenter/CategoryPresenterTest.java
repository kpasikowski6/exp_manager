package pl.wpam.expensesmanager.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import pl.wpam.expensesmanager.database.DatabaseHelper;
import pl.wpam.expensesmanager.model.ExpenseCategory;
import pl.wpam.expensesmanager.view.NewCategoryView;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class CategoryPresenterTest {

  private DatabaseHelper database;
  private NewCategoryView view;
  private CategoryPresenter presenter;

  @Before
  public void setUp() throws Exception {
    database = mock(DatabaseHelper.class);
    view = mock(NewCategoryView.class);
    presenter = new CategoryPresenter(view, database);
  }

  @Test
  public void shouldAddCategoryToTheDatabaseAndNotify() throws Exception {
    when(view.getCategory()).thenReturn("Movie");

    ArgumentCaptor<ExpenseCategory> expenseTypeCaptor = ArgumentCaptor.forClass(ExpenseCategory.class);

    assertTrue(presenter.addCategory());
    verify(database).addExpenseType(expenseTypeCaptor.capture());

    ExpenseCategory expenseCategory = expenseTypeCaptor.getValue();
    assertThat(expenseCategory.getCategory(), is("Movie"));
  }

  @Test
  public void shouldNotAddCategoryAndNotifyViewWhenCategoryInputIsEmpty() throws Exception {
    when(view.getCategory()).thenReturn("");

    assertFalse(presenter.addCategory());
    verifyNoMoreInteractions(database);
    verify(view).showError();
  }
}