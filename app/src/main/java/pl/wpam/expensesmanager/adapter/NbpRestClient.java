package pl.wpam.expensesmanager.adapter;

import com.loopj.android.http.*;
public class NbpRestClient {
    private static final String BASE_URL = "https://api.nbp.pl/api/exchangerates/rates/A/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String currency, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(currency), params, responseHandler);
    }

    public static void post(String currency, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(currency), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl + "/?format=json";
    }
}
