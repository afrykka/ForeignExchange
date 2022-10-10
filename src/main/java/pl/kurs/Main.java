package pl.kurs;

import interfaces.ExchangeService;
import service.ExchangeServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        ExchangeService exchangeService = new ExchangeServiceImpl();

        System.out.println(exchangeService.processExchange(5, "aoa"));
        System.out.println(exchangeService.processExchange(8, "CDF"));
        System.out.println(exchangeService.processExchange(15, "Mop"));

    }
}
