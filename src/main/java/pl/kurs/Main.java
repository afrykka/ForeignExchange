package pl.kurs;

import service.ExchangeService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
            https://api.fastforex.io/fetch-all?from=USD&api_key=a84da8e85a-89aa6fbae3-qvz62g
            Napisz aplikacje do zamiany walut opartej na stroni https://www.fastforex.io/.
            Wykorzystaj ObjectMaperHoldera, napisz ładne interefejsy i constansy. Aplikacja moze dzialac
            z uzyciem scanera i np switch case'a.

            Zbuduj jara i uruchom apke przez cmd'ka

            Napisz testy dla aplikacji, np sprawdzajace poprawnosc pobranego jsona.

            Praca domowa po zajeciach z JDBC: wszystkie transakcje zapisywac do bazy danych

            Praca domowa po zajeciach ze springa i htmla: dorzuc wpisywanie danych przez przeglądarke
         */

        System.out.println(ExchangeService.readObjectFromApi());

        System.out.println(ExchangeService.getValue("usd"));

    }
}
