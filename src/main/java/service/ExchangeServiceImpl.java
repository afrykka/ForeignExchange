package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.ExchangeService;
import model.ObjectMapperHolder;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class ExchangeServiceImpl implements ExchangeService {

    private static final ObjectMapper mapper = ObjectMapperHolder.INSTANCE.getMapper();
    private static final String API_LINK = "https://api.fastforex.io/fetch-all?from=USD&api_key=a84da8e85a-89aa6fbae3-qvz62g";
    public static final String RESULTS = "results";

    public double processExchange(double usdAmountToExchange, String currencyToExchangeFor) throws IOException {
        return usdAmountToExchange * getValue(currencyToExchangeFor);
    }

    public double getValue(String shortCurrencyName) throws IOException {
        Map<String, Object> readValuesFromApi = mapper.readValue(new URL(API_LINK), new TypeReference<>() {
        });

        return readValuesFromApi.entrySet().stream()
                .filter(entry -> RESULTS.equals(entry.getKey()))
                .map(entry -> (Map<String, Double>) entry.getValue())
                .map(s -> s.get(shortCurrencyName.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
