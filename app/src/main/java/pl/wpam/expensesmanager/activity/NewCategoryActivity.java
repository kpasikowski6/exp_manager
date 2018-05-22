package pl.wpam.expensesmanager.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import pl.wpam.expensesmanager.R;
import pl.wpam.expensesmanager.database.DatabaseHelper;
import pl.wpam.expensesmanager.presenter.CategoryPresenter;
import pl.wpam.expensesmanager.view.NewCategoryView;

import static pl.wpam.expensesmanager.activity.MainActivity.ADD_NEW_CAT;


public class NewCategoryActivity extends FragmentActivity implements NewCategoryView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_category);
    }

    public void addCategory(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        CategoryPresenter categoryPresenter = new CategoryPresenter(this, databaseHelper);

        if (categoryPresenter.addCategory())
            Toast.makeText(this, getString(R.string.add_category_success), Toast.LENGTH_LONG).show();

        databaseHelper.close();
        finishActivity(ADD_NEW_CAT);
    }

    @Override
    public String getCategory() {
        TextView categoryInput = findViewById(R.id.category);
        return categoryInput.getText().toString();
    }

    @Override
    public void showError() {
        TextView view = this.findViewById(R.id.category);
        view.setError(this.getString(R.string.category_empty_error));
    }
}
