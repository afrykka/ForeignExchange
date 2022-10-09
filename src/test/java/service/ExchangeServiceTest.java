package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ObjectMapperHolder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class ExchangeServiceTest {

    private static final String RESULTS = "results";
    private static final String API_LINK = "https://api.fastforex.io/fetch-all?from=USD&api_key=a84da8e85a-89aa6fbae3-qvz62g";

    private ObjectMapper mapper;
    private Map<String, Object> readValuesFromApi;

    @Before
    public void init() throws IOException {
        mapper = ObjectMapperHolder.INSTANCE.getMapper();
        readValuesFromApi = mapper.readValue(new URL(API_LINK), new TypeReference<>() {
        });
    }

    @Test
    public void shouldReturnCorrectAmountOfObjectsInJson() {
        //when
        int objectsSize = readValuesFromApi.entrySet().size();

        //then
        assertEquals(4, objectsSize);
    }

    @Test
    public void shouldReturnCorrectAmountOfCurrenciesInJson() {
        //when
        int currenciesSize = readValuesFromApi.entrySet().stream()
                .filter(entry -> entry.getKey().equals(RESULTS))
                .map(entry -> (Map<String, Double>) entry.getValue())
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .size();

        //then
        assertEquals(150, currenciesSize);
    }

}