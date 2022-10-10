package interfaces;

import java.io.IOException;
import java.sql.SQLException;

public interface ExchangeService {
    double getValue(String shortCurrencyName) throws IOException;

    double processExchange(double usdAmountToExchange, String currencyToExchangeFor) throws IOException, SQLException;
}
