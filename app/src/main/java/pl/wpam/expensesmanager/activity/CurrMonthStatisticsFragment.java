package pl.wpam.expensesmanager.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;

import java.util.ArrayList;
import java.util.List;

import pl.wpam.expensesmanager.R;
import pl.wpam.expensesmanager.database.DatabaseHelper;
import pl.wpam.expensesmanager.presenter.CurrMonthExpensesPresenter;
import pl.wpam.expensesmanager.view.CurrMonthExpensesView;

public class CurrMonthStatisticsFragment extends Fragment implements CurrMonthExpensesView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.expense_graph, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        CurrMonthExpensesPresenter presenter = new CurrMonthExpensesPresenter(this, databaseHelper);

        presenter.plotGraph();
        presenter.showTotalExpense();
        databaseHelper.close();
    }

    @Override
    public void showGraph(List<Bar> points) {
        BarGraph graph = (BarGraph) getActivity().findViewById(R.id.graph);
        graph.setBars((ArrayList<Bar>) points);
    }

    @Override
    public void showTotalExpense(Double totalExpense) {
        TextView totalExpenseTextBox = getActivity().findViewById(R.id.current_months_total_expense);
        totalExpenseTextBox.setText(getString(R.string.total_expense) + " " + totalExpense + " " + getString(R.string.rupee_sym));
    }

    @Override
    public int getGraphColor() {
        return getActivity().getResources().getColor(R.color.light_blue);
    }
}
