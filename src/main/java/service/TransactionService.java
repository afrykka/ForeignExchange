package service;

import db_connector.MySqlConnector;
import org.jooq.CreateTableElementListStep;
import org.jooq.DSLContext;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.sql.SQLException;

public class TransactionService {

    private static final String FOREIGN_EXCHANGE_TRANSACTIONS = "foreign_exchange_transactions";
    public static final String TRANSACTION = "transaction";

    private MySqlConnector mySqlConnector;
    private DSLContext mySql;

    public void processDatabaseTransaction(double amountToExchange, String currency, double amountReceived) throws SQLException {
        createDatabaseIfNotExists(amountToExchange, currency, amountReceived);
    }

    private void createDatabaseIfNotExists(double amountToExchange, String currency, double amountReceived) throws SQLException {
        connectToMySql();
        mySql.createDatabaseIfNotExists(FOREIGN_EXCHANGE_TRANSACTIONS).execute();
        selectDatabase(FOREIGN_EXCHANGE_TRANSACTIONS);
        if (tableIsNotExisting()) {
            createSchema();
        }
        insertIntoTable(amountToExchange, currency, amountReceived);
    }

    private void createSchema() {
        CreateTableElementListStep table = mySql.createTable(TRANSACTION);
        table.column("id", SQLDataType.BIGINT).primaryKey("id")
                .column("amountToExchange", SQLDataType.DOUBLE)
                .column("currency", SQLDataType.VARCHAR)
                .column("amountReceived", SQLDataType.DOUBLE)
                .execute();
        mySql.execute("alter table " + TRANSACTION + " modify column id bigint auto_increment");
    }

    private boolean tableIsNotExisting() {
        return DSL.using(mySqlConnector.getConnection()).meta().getTables(TRANSACTION).size() != 1;
    }

    private void insertIntoTable(double amountToExchange, String currency, double amountReceived) {
        Name name = DSL.name(TRANSACTION);
        Table<? extends Record> table = new TableImpl<>(name);

        mySql.insertInto(table)
                .values(null, amountToExchange, currency.toUpperCase(), amountReceived)
                .execute();
    }

    private void connectToMySql() throws SQLException {
        mySqlConnector = MySqlConnector.connectToMySql();
        mySql = DSL.using(mySqlConnector.getConnection(), SQLDialect.MYSQL);
    }

    private void selectDatabase(String databaseName) throws SQLException {
        mySqlConnector = MySqlConnector.useDatabase(databaseName);
        mySql = DSL.using(mySqlConnector.getConnection(), SQLDialect.MYSQL);
    }

}
