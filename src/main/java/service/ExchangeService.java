package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.CurrencyEnum;
import model.FastForex;
import model.ObjectMapperHolder;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ExchangeService {

    private static final ObjectMapper mapper = ObjectMapperHolder.INSTANCE.getMapper();
    public static final String API_LINK = "https://api.fastforex.io/fetch-all?from=USD&api_key=a84da8e85a-89aa6fbae3-qvz62g";

    public static FastForex readObjectFromApi() {
        //String json = new Scanner(new URL(apiLink).openStream(), StandardCharsets.UTF_8).useDelimiter("\\A").next();
        try {
            URL url = new URL(API_LINK);
            return mapper.readValue(url, FastForex.class);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public static double getValue(String currencyName) {
        return Arrays.stream(CurrencyEnum.values())
                .filter(currency -> currencyName.toUpperCase().equals(currency.getCurrencyName()))
                .map(CurrencyEnum::getValue)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

}
