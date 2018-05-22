package pl.wpam.expensesmanager.adapter;

import android.widget.TextView;

import org.json.*;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class NbpRestClientUsage {

    public void getCurrentExchangeRateInfo(final String currency, final TextView rateTextView) {
        if("PLN".equals(currency)) {
            rateTextView.setText(Integer.toString(1));
            return;
        }

        NbpRestClient.get(currency, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray ratesArray = response.getJSONArray("rates");
                    JSONObject firstElem = ratesArray.getJSONObject(0);
                    rateTextView.setText(String.valueOf(firstElem.getDouble("mid")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {

            }
        });
    }
}
