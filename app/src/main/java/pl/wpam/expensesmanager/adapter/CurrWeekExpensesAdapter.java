package pl.wpam.expensesmanager.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import pl.wpam.expensesmanager.R;
import pl.wpam.expensesmanager.model.Expense;
import pl.wpam.expensesmanager.utils.ExpenseListCollectionWrapper;

import static pl.wpam.expensesmanager.utils.DateHelper.getDayName;

public class CurrWeekExpensesAdapter implements ExpandableListAdapter {
    private Context context;
    private final Map<String, List<Expense>> expensesMap;

    public CurrWeekExpensesAdapter(Context context, Map<String, List<Expense>> expensesMap) {
        this.context = context;
        this.expensesMap = expensesMap;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getGroupCount() {
        return expensesMap.keySet().size();
    }

    @Override
    public int getChildrenCount(int position) {
        return expensesMap.get(expensesMap.keySet().toArray()[position]).size();
    }

    @Override
    public Object getGroup(int position) {
        String date = (String) this.expensesMap.keySet().toArray()[position];
        Double totalExpense = new ExpenseListCollectionWrapper(this.expensesMap.get(date)).getTotalExpense();

        return date + " (" + getDayName(date) + ") - " + totalExpense + " " + context.getString(R.string.rupee_sym);
    }

    @Override
    public Object getChild(int parent, int child) {
        Expense expense = expensesMap.get(expensesMap.keySet().toArray()[parent]).get(child);
        return expense.getType() + " - " + expense.getAmount();
    }

    @Override
    public long getGroupId(int position) {
        return position;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View converterView, ViewGroup viewGroup) {
        String parentText = (String) getGroup(parent);

        if (converterView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converterView = inflater.inflate(R.layout.expense_header_text_box, viewGroup, false);
        }

        TextView textBox = converterView.findViewById(R.id.expense_header_text_box);
        textBox.setText(parentText);

        return converterView;
    }

    @Override
    public View getChildView(int parent, int child, boolean lastChild, View converterView, ViewGroup viewGroup) {
        String childText = (String) getChild(parent, child);

        if (converterView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converterView = inflater.inflate(R.layout.expense_text_box, viewGroup, false);
        }

        TextView textBox = converterView.findViewById(R.id.expense_text_box);
        textBox.setText(childText);

        return converterView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }
}