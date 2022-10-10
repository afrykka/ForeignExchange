package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.ExchangeService;
import model.ObjectMapperHolder;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;

public class ExchangeServiceImpl implements ExchangeService {

    private static final String API_LINK = "https://api.fastforex.io/fetch-all?from=USD&api_key=a84da8e85a-89aa6fbae3-qvz62g";
    private static final String RESULTS = "results";
    private static final ObjectMapper mapper = ObjectMapperHolder.INSTANCE.getMapper();

    public double processExchange(double usdAmountToExchange, String currencyToExchangeFor) throws IOException, SQLException {
        TransactionService dao = new TransactionService();
        double amountToReceive = usdAmountToExchange * getValue(currencyToExchangeFor);
        dao.processDatabaseTransaction(usdAmountToExchange, currencyToExchangeFor, amountToReceive);
        System.out.println("Saved transaction of " + usdAmountToExchange + " " + currencyToExchangeFor.toUpperCase() + " to database");
        return amountToReceive;
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
