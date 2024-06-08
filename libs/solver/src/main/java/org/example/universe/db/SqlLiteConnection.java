package org.example.universe.db;

import static org.slf4j.LoggerFactory.getLogger;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

public class SqlLiteConnection {

  private static final Logger LOGGER = getLogger(SqlLiteConnection.class);

  private final String SELECT_ALL_ROUTES = "SELECT * FROM routes";

  private final ObjectMapper objectMapper = new ObjectMapper();

  public List<Route> retrieveAllRoutes(String pathToDb) {
    List<Route> routes = List.of();
    Connection connection = null;
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite::resource:" + pathToDb);
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(SELECT_ALL_ROUTES);

      routes = map(resultSet);

      resultSet.close();
      statement.close();
    } catch (ClassNotFoundException | SQLException e) {
      LOGGER.error("Can't read universe from db " + e.getMessage(), e);
      return routes;
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        LOGGER.error("Can't read universe from db " + e.getMessage(), e);
      }
    }
    return routes;
  }

  private List<Route> map(ResultSet resultSet) throws SQLException {
    List<Route> rows = new ArrayList<>();
    int columnCount = resultSet.getMetaData().getColumnCount();

    while (resultSet.next()) {
      Map<String, Object> row = new HashMap<>();
      for (int i = 1; i <= columnCount; i++) {
        String columnName = resultSet.getMetaData().getColumnName(i);
        Object columnValue = resultSet.getObject(i);
        row.put(columnName, columnValue);
      }
      rows.add(objectMapper.convertValue(row, Route.class));
    }

    return rows;
  }
}
