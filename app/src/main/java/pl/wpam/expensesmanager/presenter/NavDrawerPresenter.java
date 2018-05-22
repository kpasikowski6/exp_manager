package pl.wpam.expensesmanager.presenter;

import pl.wpam.expensesmanager.activity.CurrMonthStatisticsFragment;
import pl.wpam.expensesmanager.activity.CurrWeekExpensesFragment;
import pl.wpam.expensesmanager.view.NavDrawerElemsView;

public class NavDrawerPresenter {

  public static final String THIS_WEEK = "Bieżący tydzień";
  public static final String THIS_MONTH = "Statystyki";
  public static final String HOME = "Początek";
  private NavDrawerElemsView navDrawerElemsView;

  public NavDrawerPresenter(NavDrawerElemsView navDrawerElemsView) {
    this.navDrawerElemsView = navDrawerElemsView;
  }

  public void onItemSelected(String drawerItem) {
    switch (drawerItem){
      case THIS_WEEK:
        navDrawerElemsView.render(new CurrWeekExpensesFragment());
        break;
      case THIS_MONTH:
        navDrawerElemsView.render(new CurrMonthStatisticsFragment());
        break;
      case HOME:
        navDrawerElemsView.goToHome();
        break;
    }
  }
}
