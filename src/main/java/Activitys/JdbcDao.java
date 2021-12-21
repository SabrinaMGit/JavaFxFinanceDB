package Activitys;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class JdbcDao {
    // Replace below database url, username and password with your actual database credentials
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/databasemysql?useSSL=false";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "GAmPjeb4uuzc2WGLPbVNL6"; //Z0KpF0U2fXTvcwkFI9NF
    private static final String INSERT_QUERY = "INSERT INTO registration (full_name, email_id, password) VALUES (?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM registration WHERE email_id = ? and password = ?";
    public static final String SELECT_ALL_QUERY = "SELECT * FROM contracts_and_policy_data"; //maybe failure
    private static final String SELECT_ALL_TABLE = "SELECT * FROM contracts_and_policy_data";
    public static final String SELECT_ACCOUNTING = "SELECT * FROM accounting";
    public static final String SELECT_SUM = "SELECT * FROM sum_table";
    private static final String INSERT_SUM = "INSERT INTO sum_table (Total_yearly_premium_net,Total_yearly_premium_incl_Tax,Total_commission_per_year,Mirascon_commission_per_year,Geico_gmbh_commission_per_year,Premium_incl_tax_per_instalment,ers_Production_Net_settlement_period,ers_productuin_incl_tax_settlement_period,ers_production_net_total,ers_production_incl_Tax_total,Premium_incl_Tax_paid,Premium_incl_tax_paid_YTD,Net_premium_paid_YTD,Total_payable,Total_return,Total_payable_return,Mirascon_payable,Mirascon_to_Geico_gmbh_11_payable,Disbursement_Geico_commission_Geico_UK_Agency,Disbursement_Geico_commission_Geico_Gmbh,Total_Geico_commission_YTD,Mirascon_to_Regis_payable_return,Cancellation_Regis_to_Mirascon_payable_net,Regis_to_Mirascon_payable_gross,Geico_Financial_to_Mirascon_upfront_payment_payable,Geico_Financial_to_Mirascon_upfront_payment_total_recieved,Pre_financing_tool_Mirascon_to_Geico_Financial_payable,Pre_financing_tool_Mirascon_to_Geico_Financial_paid,Pre_financing_tool_Mirascon_to_Geico_Financial_refund_MTA,Pre_financing_tool_Mirascon_to_Geico_Financial_residual_dept) VALUES (?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?)";
    private static String DELETE_SUM = "TRUNCATE my_table";

    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    private Boolean giveAccess = null;
    private ObservableList<ObservableList> data = null;


    public void insertRecord(String fullName, String emailId, String password) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, emailId);
            preparedStatement.setString(3, password);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public boolean selectRecord(String emailId, String password) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                giveAccess = false;
            }else{
                giveAccess = true;
            }
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return giveAccess;
    }

    public void selectAllDatabase(TableView tableView) throws SQLException{

        data = FXCollections.observableArrayList();
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUM)) {

            System.out.println(preparedStatement);
            rs =  preparedStatement.executeQuery();

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            ResultSetMetaData meta = rs.getMetaData();

            for(int i=0 ; i<meta.getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(meta.getColumnName(i + 1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tableView.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=meta.getColumnCount(); i++){
                    //Iterate Column
                    //row.add(rs.getString(i));
                    Object o = rs.getObject(i);
                    row.add(o == null ? "" : o.toString());

                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }
            //FINALLY ADDED TO TableView
                tableView.setItems(data);
        }
        catch (SQLException e) {
            printSQLException(e);
        }
    }

    public java.sql.Date selectColumnDateValue_WithID(int id, int columnIndex) throws SQLException, ParseException {

        java.sql.Date date = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date date_0 = new java.sql.Date(df.parse("0000-00-00").getTime());
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TABLE)) {
            //preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            //System.out.println(rs);
            while (rs.next()) {
                if(rs.getInt("ID") == id) {
                    date = rs.getDate(columnIndex);
                    if (date == date_0) {

                    }
                    System.out.println("ResultSet Date Value is found = " + date);
                }
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return date;
    }

    public void updateColumnDateValue_WithIdAndValue(int id, int columnIndex, java.sql.Date dateValue) throws SQLException {


        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TABLE, ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE)) {
            //preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            //System.out.println(rs);
            while (rs.next()) {
                if(rs.getInt("ID") == id) {
                    rs.updateDate(columnIndex, dateValue);
                    rs.updateRow();
                    System.out.println("The Date Value is updated = " + dateValue);
                }
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public Double selectColumnDoubleValue_WithID(int id, int columnIndex) throws SQLException {

        Double value = null;
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TABLE)) {
            //preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            //System.out.println(rs);
            while (rs.next()) {
                if(rs.getInt("ID") == id) {
                    value = rs.getDouble(columnIndex);
                    System.out.println("ResultSet Double Value is found = " + value);
                }
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return value;
    }

    public void updateColumnDoubleValue_WithIdAndValue(int id, int columnIndex, double doubleValue) throws SQLException {


        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TABLE, ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE)) {
            //preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery(SELECT_ALL_TABLE);
            //System.out.println(rs);
            while (rs.next()) {
                if(rs.getInt("ID") == id) {
                    rs.updateDouble(columnIndex, doubleValue);
                    rs.updateRow();
                    System.out.println("The Double Value is updated = " + doubleValue);
                }
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public String selectColumnStringValue_WithID(int id, int columnIndex) throws SQLException {

        String stringValue = null;
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TABLE)) {
            //preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            //System.out.println(rs);
            while (rs.next()) {
                if(rs.getInt("ID") == id) {
                    stringValue = rs.getString(columnIndex);
                    System.out.println("ResultSet Date Value is found = " + stringValue);
                }
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return stringValue;
    }

    public void updateColumnIntValue_WithIdAndValue(int id, int columnIndex, int intValue) throws SQLException {


        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TABLE, ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE)) {
            //preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            //System.out.println(rs);
            while (rs.next()) {
                if(rs.getInt("ID") == id) {
                    rs.updateInt(columnIndex, intValue);
                    rs.updateRow();
                    System.out.println("The Int Value is updated = " + intValue);
                }
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public java.sql.Date selectAccountingColumnDateValue_WithID(int id, int columnIndex) throws SQLException {

        java.sql.Date date = null;
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNTING)) {
            //preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery(SELECT_ACCOUNTING);
            //System.out.println(rs);
            while (rs.next()) {
                if(rs.getInt("ID") == id) {
                    date = rs.getDate(columnIndex);
                    /*if (rs.wasNull()) {
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        date = new java.sql.Date(df.parse("0000-00-00").getTime()); // set it to empty string as you desire.
                    }*/
                    System.out.println("ResultSet Accounting Date Value is found = " + date);
                }
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException((SQLException) e);
        }
        return date;
    }

    public Double selectAccountingColumnDoubleValue_WithID(int id, int columnIndex) throws SQLException {

        Double value = null;
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNTING)) {
            //preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            //System.out.println(rs);
            while (rs.next()) {
                if(rs.getInt("ID") == id) {
                    value = rs.getDouble(columnIndex);
                    System.out.println("ResultSet Accounting Double Value is found = " + value);
                }
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return value;
    }

    public int selectAccountingColumnIntValue_WithID(int id, int columnIndex) throws SQLException {

        int value = 0;
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNTING)) {
            //preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            //System.out.println(rs);
            while (rs.next()) {
                if(rs.getInt("ID") == id) {
                    value = rs.getInt(columnIndex);
                    System.out.println("ResultSet Accounting Int Value is found = " + value);
                }
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return value;
    }

    public void updateAccountingColumnStringValue_WithIdAndValue(int id, int columnIndex, String intValue) throws SQLException {


        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNTING, ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE)) {
            //preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            //System.out.println(rs);
            while (rs.next()) {
                if(rs.getInt("ID") == id) {
                    rs.updateString(columnIndex, intValue);
                    rs.updateRow();
                    System.out.println("The Accounting String Value is updated = " + intValue);
                }
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void updateAccountingColumnDoubleValue_WithIdAndValue(int id, int columnIndex, double intValue) throws SQLException {


        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNTING, ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE)) {
            //preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            //System.out.println(rs);
            while (rs.next()) {
                if(rs.getInt("ID") == id) {
                    rs.updateDouble(columnIndex, intValue);
                    rs.updateRow();
                    System.out.println("The Accounting String Value is updated = " + intValue);
                }
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void updateAccountingColumnIntValue_WithIdAndValue(int id, int columnIndex, int intValue) throws SQLException {


        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNTING, ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE)) {
            //preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            //System.out.println(rs);
            while (rs.next()) {
                if(rs.getInt("ID") == id) {
                    rs.updateInt(columnIndex, intValue);
                    rs.updateRow();
                    System.out.println("The Accounting Int Value is updated = " + intValue);
                }
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void updateAccountingColumnDateValue_WithIdAndValue(int id, int columnIndex, Date intValue) throws SQLException {


        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNTING, ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE)) {
            //preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            //System.out.println(rs);
            while (rs.next()) {
                if(rs.getInt("ID") == id) {
                    rs.updateDate(columnIndex, intValue);
                    rs.updateRow();
                    System.out.println("The Accounting Date Value is updated = " + intValue);
                }
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void setDeleteSum() throws SQLException {
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();

             statement.executeUpdate("TRUNCATE sum_table");
             } catch (SQLException e) {

                System.out.println("Could not truncate test_table " + e.getMessage());
        }
    }

        public void insertSumColumn(double sum1, double sum2, double sum3, double sum4, double sum5, double sum6,
                                double sum7, double sum8, double sum9, double sum10, double sum11, double sum12,
                                double sum13, double sum14, double sum15, double sum16, double sum17, double sum18,
                                double sum19, double sum20, double sum21, double sum22, double sum23, double sum24,
                                double sum25, double sum26, double sum27, double sum28, double sum29, double sum30) throws SQLException {
        setDeleteSum();
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUM)) {
            preparedStatement.setDouble(1, sum1);
            preparedStatement.setDouble(2, sum2);
            preparedStatement.setDouble(3, sum3);
            preparedStatement.setDouble(4, sum4);
            preparedStatement.setDouble(5, sum5);
            preparedStatement.setDouble(6, sum6);
            preparedStatement.setDouble(7, sum7);
            preparedStatement.setDouble(8, sum8);
            preparedStatement.setDouble(9, sum9);
            preparedStatement.setDouble(10, sum10);
            preparedStatement.setDouble(11, sum11);
            preparedStatement.setDouble(12, sum12);
            preparedStatement.setDouble(13, sum13);
            preparedStatement.setDouble(14, sum14);
            preparedStatement.setDouble(15, sum15);
            preparedStatement.setDouble(16, sum16);
            preparedStatement.setDouble(17, sum17);
            preparedStatement.setDouble(18, sum18);
            preparedStatement.setDouble(19, sum19);
            preparedStatement.setDouble(20, sum20);
            preparedStatement.setDouble(21, sum21);
            preparedStatement.setDouble(22, sum22);
            preparedStatement.setDouble(23, sum23);
            preparedStatement.setDouble(24, sum24);
            preparedStatement.setDouble(25, sum25);
            preparedStatement.setDouble(26, sum26);
            preparedStatement.setDouble(27, sum27);
            preparedStatement.setDouble(28, sum28);
            preparedStatement.setDouble(29, sum29);
            preparedStatement.setDouble(30, sum30);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }


    private static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
