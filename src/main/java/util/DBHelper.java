package util;

import models.CareerResponseDetails;
import models.DBDetails;

import java.sql.*;

public class DBHelper {
    private static final String SQL_ERROR = "SQL Error";
    private String dbUrl;
    private String userName;
    private String password;

    public DBHelper(String dbUrl, String dbUserName, String dbPassword) {
        this.dbUrl = dbUrl;
        this.userName = dbUserName;
        this.password = dbPassword;
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbUrl, userName, password);
        } catch (Exception e) {
            LoggerUtil.logERROR("DB Connection error", e);
        }
        return DriverManager.getConnection(dbUrl, userName, password);
    }

    public void executeQuery(String query) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LoggerUtil.logERROR(SQL_ERROR, e);
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LoggerUtil.logERROR(SQL_ERROR, e);
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    LoggerUtil.logERROR(SQL_ERROR, e);
                }
        }
    }

    public void executeDBPostStepsWithLoggers(ResultSet resultSet, Connection connection, PreparedStatement preparedStatement) {
        if (resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException e) {
                LoggerUtil.logERROR(SQL_ERROR, e);
            }
        if (preparedStatement != null)
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LoggerUtil.logERROR(SQL_ERROR, e);
            }
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                LoggerUtil.logERROR(SQL_ERROR, e);
            }
    }

/*    public DBDetails updateDetails(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        DBDetails dbDetails = new DBDetails();

        String query = "update table set name='example' where id=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LoggerUtil.logERROR(SQL_ERROR, e);
        } finally {
            executeDBPostStepsWithLoggers(connection, preparedStatement);
        }
        return dbDetails;
    }*/

    public DBDetails getDetails(int id) {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        DBDetails dbDetails = new DBDetails();

        String query = "select name,address,age from personalDetails where id=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                dbDetails.setName(resultSet.getString("name"));
                dbDetails.setAddress(resultSet.getString("address"));
                dbDetails.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            LoggerUtil.logERROR(SQL_ERROR, e);
        } finally {
            executeDBPostStepsWithLoggers(resultSet, connection, preparedStatement);
        }
        return dbDetails;
    }
}