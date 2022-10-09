package pl.kurs;

import interfaces.ExchangeService;
import service.ExchangeServiceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ExchangeService exchangeService = new ExchangeServiceImpl();
        //System.out.println(exchangeService.getValue("aoA"));
        System.out.println(exchangeService.processExchange(5, "aoa"));

    }
}
