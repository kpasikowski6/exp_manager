package pl.wpam.expensesmanager.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import pl.wpam.expensesmanager.activity.CurrMonthStatisticsFragment;
import pl.wpam.expensesmanager.activity.CurrWeekExpensesFragment;
import pl.wpam.expensesmanager.view.NavDrawerElemsView;

import static pl.wpam.expensesmanager.presenter.NavDrawerPresenter.HOME;
import static pl.wpam.expensesmanager.presenter.NavDrawerPresenter.THIS_MONTH;
import static pl.wpam.expensesmanager.presenter.NavDrawerPresenter.THIS_WEEK;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NavDrawerPresenterTest {

    private NavDrawerElemsView view;
    private NavDrawerPresenter presenter;

    @Before
    public void setUp() throws Exception {
        view = mock(NavDrawerElemsView.class);
        presenter = new NavDrawerPresenter(view);
    }

    @Test
    public void shouldRenderCurrentWeeksFragment() throws Exception {
        presenter.onItemSelected(THIS_WEEK);
        ArgumentCaptor<CurrWeekExpensesFragment> fragmentCaptor = ArgumentCaptor.forClass(CurrWeekExpensesFragment.class);
        verify(view).render(fragmentCaptor.capture());

        CurrWeekExpensesFragment fragment = fragmentCaptor.getValue();
        assertTrue(fragment.getClass().equals(CurrWeekExpensesFragment.class));
    }

    @Test
    public void shouldRenderCurrentMonthsFragment() throws Exception {
        presenter.onItemSelected(THIS_MONTH);
        ArgumentCaptor<CurrMonthStatisticsFragment> fragmentCaptor = ArgumentCaptor.forClass(CurrMonthStatisticsFragment.class);
        verify(view).render(fragmentCaptor.capture());

        CurrMonthStatisticsFragment fragment = fragmentCaptor.getValue();
        assertTrue(fragment.getClass().equals(CurrMonthStatisticsFragment.class));
    }

    @Test
    public void shouldRenderHomeFragment() throws Exception {
        presenter.onItemSelected(HOME);
        verify(view).goToHome();
    }
}