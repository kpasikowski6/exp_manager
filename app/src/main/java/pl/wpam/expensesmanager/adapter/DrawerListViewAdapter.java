package pl.wpam.expensesmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import pl.wpam.expensesmanager.R;

public class DrawerListViewAdapter extends BaseAdapter {

    private final String[] drawerElems;
    private Context context;

    public DrawerListViewAdapter(Context context) {
        this.context = context;
        drawerElems = context.getResources().getStringArray(R.array.drawer_items);
    }

    @Override
    public int getCount() {
        return drawerElems.length;
    }

    @Override
    public String getItem(int pos) {
        return drawerElems[pos];
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.drawer_list_elem, null);
        }

        TextView item = convertView.findViewById(R.id.drawer_list_item);
        item.setText(drawerElems[pos]);

        return convertView;
    }
}
