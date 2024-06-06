package org.example.infrastructure;

import org.example.infrastructure.model.Route;
import org.example.model.Edge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class SqlLiteConnectionTest {
    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private ResultSet resultSet;

    @Mock
    private ResultSetMetaData resultSetMetaData;

    SqlLiteConnection sqlLiteConnection;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);

        when(resultSet.getMetaData()).thenReturn(resultSetMetaData);

        when(resultSetMetaData.getColumnCount()).thenReturn(3);
        when(resultSetMetaData.getColumnName(1)).thenReturn("origin");
        when(resultSetMetaData.getColumnName(2)).thenReturn("destination");
        when(resultSetMetaData.getColumnName(3)).thenReturn("travel_time");

        when(resultSet.getObject(1)).thenReturn("Tatouine", "Dagobah");
        when(resultSet.getObject(2)).thenReturn("Dagobah","Tatouine");
        when(resultSet.getObject(3)).thenReturn(2, 2);
        when(resultSet.next()).thenReturn(true, true, false);
        sqlLiteConnection = new SqlLiteConnection();
    }

    @Test
    @DisplayName("Should retrieve edges from database")
    void shouldRetrieveEdgesFromDatabase() throws SQLException {
        // Given

        mockStatic(DriverManager.class);
        when(DriverManager.getConnection(anyString())).thenReturn(connection);

        // When
        var actual = sqlLiteConnection.retrieveAllRoutes("dummy-path");

        // Then
        assertThat(actual)
                .containsExactlyInAnyOrder(
                        new Route("Tatouine", "Dagobah", 2),
                        new Route("Dagobah", "Tatouine", 2)
                );
    }
}