package org.example.infrastructure;

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
import org.example.infrastructure.model.Route;

public class SqlLiteConnection {

  private final String SELECT_ALL_ROUTES = "SELECT * FROM routes";

  private final ObjectMapper objectMapper = new ObjectMapper();

  public static void main(String[] args) {
    var a = new SqlLiteConnection();
    var result = a.retrieveAllRoutes("universe.db");
    System.out.println("result = " + result);
  }

  public List<Route> retrieveAllRoutes(String pathToDb) {
    Connection connection = null;
    List<Route> routes = List.of();
    var path = SqlLiteConnection.class.getClassLoader().getResource(pathToDb).getFile();
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:" + pathToDb);
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(SELECT_ALL_ROUTES);

      routes = getRoutes(resultSet);

      resultSet.close();
      statement.close();
    } catch (ClassNotFoundException | SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException ex) {
        System.out.println(ex.getMessage());
      }
    }
    return routes;
  }

  private List<Route> getRoutes(ResultSet resultSet) throws SQLException {
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
