package data.api;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;
import utils.Pair;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Used for retrieving
 */
public class BitcoinApi {

    private String getPrivateApiKey() throws IOException {
        return Files.asCharSource(new File("/Users/anmihaila/bitcoin-prices/app/src/main/resources/private-api-key"),
                Charsets.UTF_8).readFirstLine();
    }

    /**
     * Gets the rate of BTC to USD for a given date
     *
     * @param time string of a date in ISO-8601 format
     */
    private int getBitcoinRate(String time) {
        OkHttpClient client = new OkHttpClient();

        Request request = null;
        try {
            request = new Request.Builder()
                    .url("https://rest.coinapi.io/v1/exchangerate/BTC/USD?time=" + time)
                    .addHeader("X-CoinAPI-Key", getPrivateApiKey())
                    .build();
            Response response = client.newCall(request).execute();
            return (int) new JSONObject(response.body().string()).getDouble("rate");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Retrieves a list of bitcoin to usd rates for the past 7 days
     *
     * @return
     *      a list of pairs (time in milliseconds, bitcoin to usd rate)
     */
    public List<Pair<Integer, Integer>> getRatesForPastWeek() {

        List<Pair<Integer, Integer>> result = new ArrayList<>();

        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo =  today.minusDays(7);
        LocalDate dateInputForApi = today;

        while (dateInputForApi.isAfter(sevenDaysAgo)) {

            result.add(new Pair<>((int) dateInputForApi.toEpochDay(), getBitcoinRate(dateInputForApi.toString())));

            dateInputForApi = dateInputForApi.minusDays(1);
        }

        return result;
    }
}
