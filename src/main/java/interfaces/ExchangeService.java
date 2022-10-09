package interfaces;

import java.io.IOException;

public interface ExchangeService {
    double getValue(String shortCurrencyName) throws IOException;

    double processExchange(double usdAmountToExchange, String currencyToExchangeFor) throws IOException;
}
