package pl.wpam.expensesmanager.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pl.wpam.expensesmanager.R;
import pl.wpam.expensesmanager.adapter.NbpRestClientUsage;
import pl.wpam.expensesmanager.database.DatabaseHelper;
import pl.wpam.expensesmanager.presenter.ExpensePresenter;
import pl.wpam.expensesmanager.view.ExpenseView;

public class ExpenseFragment extends Fragment implements ExpenseView, View.OnClickListener {

    private NbpRestClientUsage nbpRestClientUsage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        nbpRestClientUsage = new NbpRestClientUsage();
        return inflater.inflate(R.layout.new_expense, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatabaseHelper databaseHelper = new DatabaseHelper(this.getActivity());
        ExpensePresenter expensePresenter = new ExpensePresenter(databaseHelper, this);
        expensePresenter.setExpenseTypes();
        databaseHelper.close();

        Button addExpenseButton = getActivity().findViewById(R.id.add_expense);
        Spinner currencySpinner = getActivity().findViewById(R.id.currency_type);

        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nbpRestClientUsage.getCurrentExchangeRateInfo(
                        parent.getItemAtPosition(position).toString(),
                        (TextView) getActivity().findViewById(R.id.exchange_rate)
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addExpenseButton.setOnClickListener(this);
    }

    @Override
    public String getAmount() {
        TextView view = getActivity().findViewById(R.id.amount);
        return view.getText().toString();
    }

    @Override
    public String getType() {
        Spinner spinner = getActivity().findViewById(R.id.expense_type);
        return (String) spinner.getSelectedItem();
    }

    @Override
    public String getCurrency() {
        Spinner spinner = getActivity().findViewById(R.id.currency_type);
        return (String) spinner.getSelectedItem();
    }

    @Override
    public Double getExchangeRate() {
        TextView view = getActivity().findViewById(R.id.exchange_rate);
        return Double.valueOf(view.getText().toString());
    }


    @Override
    public void renderExpenseTypes(List<String> expenseTypes) {
        Spinner spinner = getActivity().findViewById(R.id.expense_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, expenseTypes);
        spinner.setAdapter(adapter);
    }

    @Override
    public void showError() {
        TextView view = getActivity().findViewById(R.id.amount);
        view.setError(getActivity().getString(R.string.amount_empty_error));
    }

    @Override
    public void onClick(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this.getActivity());
        ExpensePresenter expensePresenter = new ExpensePresenter(databaseHelper, this);

        if (expensePresenter.addExpense()) {
            MainActivity activity = (MainActivity) getActivity();
            Toast.makeText(activity, R.string.expense_add_successfully, Toast.LENGTH_LONG).show();
            activity.onExpenseAdded();
        }

        databaseHelper.close();
    }
}
