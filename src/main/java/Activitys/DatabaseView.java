package Activitys;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.*;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.fxml.Initializable;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

import static java.sql.DriverManager.getConnection;

public class DatabaseView implements Initializable{
        private Connection connection;

        @FXML
        public TabPane tabPane;

        @FXML
        public Tab tab;

        private String tableQuery;

        private TableView tableView;

        @FXML
        private Text date1;

        @FXML
        private Text date2;

        @FXML
        private MenuItem sum_table;

        Formulas formulas = new Formulas();
        private ObservableList<ObservableList> data;
        JdbcDao jdbcDao = new JdbcDao();
        private LocalDate localDate = null;

        @FXML
        public void initialize(URL url, ResourceBundle resourceBundle) {

                try {
                        formulas.formulas();
                } catch (ParseException e) {
                        e.printStackTrace();
                } catch (SQLException e) {
                        e.printStackTrace();
                }


                createTableValue();
                date1.setText("Start Date: "+formulas.settlementPeriod_startDate);
                date2.setText("End Date: "+formulas.settlementPeriod_endDate);

        }

        private void createTableValue(){

                try {
                        connection = DriverManager.getConnection(jdbcDao.DATABASE_URL, jdbcDao.DATABASE_USERNAME, jdbcDao.DATABASE_PASSWORD);
                } catch (SQLException e) {
                        e.printStackTrace();
                }


                //String tableQuery_bordereau = jdbcDao.SELECT_ALL_QUERY;
                //System.out.println("Table name query: \"" + tableQuery_bordereau + "\"\n");

                tableQuery = jdbcDao.SELECT_ALL_QUERY;
                System.out.println("Table name query: \"" + tableQuery + "\"\n");
                try (PreparedStatement tableQueryPS = connection.prepareStatement(tableQuery)) {
                        ResultSet rs = tableQueryPS.executeQuery();

                        //Retrieving the meta data object
                        DatabaseMetaData metaData = connection.getMetaData();
                        String[] types = {"TABLE"};
                        //Retrieving the columns in the database
                        ResultSet tableNames = metaData.getTables(null, null, "%", types);

                        while (tableNames.next()) {
                                data = FXCollections.observableArrayList();
                                System.out.println("Table name: " + tableNames.getString("TABLE_NAME") + "\n");
                                //System.out.println("Table name: " + rs.getString("ID") + "\n");
                                //ResultSetMetaData resultSetMetaData = tableNames.getMetaData();
                                //String tableName = resultSetMetaData.getTableName(1);
                                tab = new Tab(tableNames.getString("TABLE_NAME"));
                                tableView = new TableView();
                                //tabs();
                                tabPane.getTabs().add(tab);
                                tab.setContent(tableView);
                                //table(tableNames);



                                /**********************************
                                 * TABLE COLUMN ADDED DYNAMICALLY *
                                 **********************************/
                                String dataQuery = "SELECT * from " + tableNames.getString("TABLE_NAME") ;
                                ResultSet tableValues = connection.createStatement().executeQuery(dataQuery);
                                ResultSetMetaData meta = tableValues.getMetaData();

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
                                //rs.previous();

                                while(tableValues.next()){
                                        //Iterate Row
                                        ObservableList<String> row = FXCollections.observableArrayList();
                                        for(int i=1 ; i<=meta.getColumnCount(); i++){
                                                //Iterate Column
                                                //row.add(rs.getString(i));
                                                Object o = tableValues.getObject(i);
                                                row.add(o == null ? "" : o.toString());

                                        }
                                        System.out.println("Row [1] added "+row );
                                        data.add(row);

                                }
                                //FINALLY ADDED TO TableView
                                tableView.setItems(data);
                        }
                        tableNames.close();
                } catch (SQLException tableQueryException) {
                        System.err.println(tableQueryException.toString());
                }
        }


        public void createFXML() throws SQLException, IOException {
                Parent databaseView; //we need to load the layout that we want to swap

                databaseView = FXMLLoader.load(getClass().getResource("/layout/database_form.fxml"));
                //TableView tableView = new TableView();
                //jdbcDao.selectAllDatabase(tableview1);

                //Scene table = new Scene(tableView);
                Stage mainStage;


                //menuBar.useSystemMenuBarProperty().set(true);

                //mainStage = (Stage)  ((Node)event.getSource()).getScene().getWindow();
                mainStage = Main.parentWindow;
                StackPane root = new StackPane();
                //root.getChildren().addAll(tableview1);

                mainStage.setMaxWidth(2000);
                mainStage.setMaxHeight(1000);
                mainStage.getScene().setRoot(databaseView); //we dont need to change whole sceene, only set new root.
        }

        @FXML
        private void setSumTable() throws SQLException {
                TableView tableView = new TableView();
                jdbcDao.selectAllDatabase(tableView);
                final Popup popup = new Popup();
                popup.setX(300);
                popup.setY(200);
                popup.getContent().addAll(tableView);
        }

        @FXML
        private void startDate(){
                final Stage dialog = new Stage();
                dialog.setTitle("Change Settlement Period Start Date");
                dialog.initModality(Modality.NONE);
                Button submit = new Button("Submit");
                Button close = new Button("Close");

                // create a tile pane
                TilePane tilePane = new TilePane();

                // label to show the date
                Label label = new Label("No date is selected");

                // create a date picker
                DatePicker datePicker = new DatePicker();

                // action event
                EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e)
                        {
                                // get the date picker value
                                localDate = datePicker.getValue();

                                // get the selected date
                                label.setText("Date :" + localDate);
                        }
                };

                // show week numbers
                datePicker.setShowWeekNumbers(true);

                // when datePicker is pressed
                datePicker.setOnAction(event);

                // add button and label
                tilePane.getChildren().add(datePicker);
                tilePane.getChildren().add(label);

                HBox dialogHbox = new HBox(20);
                dialogHbox.setAlignment(Pos.CENTER);

                VBox dialogVbox1 = new VBox(20);
                dialogVbox1.setAlignment(Pos.CENTER_LEFT);

                VBox dialogVbox2 = new VBox(20);
                dialogVbox2.setAlignment(Pos.CENTER_RIGHT);

                dialogVbox1.setPrefWidth(100);
                dialogVbox2.setPrefWidth(100);
                submit.setMinWidth(dialogVbox1.getPrefWidth());
                close.setMinWidth(dialogVbox2.getPrefWidth());

                dialogVbox1.getChildren().add(submit);
                dialogVbox2.getChildren().add(close);

                submit.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent e) {
                                        Date date = Date.valueOf(localDate);
                                        formulas.setSettlementPeriod_startDate(date);
                                        // inside here you can use the minimize or close the previous stage//
                                        dialog.close();
                                }
                        });
                close.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent e) {
                                        dialog.close();
                                }
                        });



                dialogHbox.getChildren().addAll(tilePane, dialogVbox1, dialogVbox2);
                Scene dialogScene = new Scene(dialogHbox, 500, 40);
                //dialogScene.getStylesheets().add("//style sheet of your choice");
                dialog.setScene(dialogScene);
                dialog.show();
        }
        @FXML
        private void endDate(){
                //formulas.getSettlementEndDate();
                final Stage dialog = new Stage();
                dialog.setTitle("Change Settlement Period End Date");
                dialog.initModality(Modality.NONE);
                Button submit = new Button("Submit");
                Button close = new Button("Close");

                // create a tile pane
                TilePane tilePane = new TilePane();

                // label to show the date
                Label label = new Label("No date is selected");

                // create a date picker
                DatePicker datePicker = new DatePicker();

                // action event
                EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e)
                        {
                                // get the date picker value
                                localDate = datePicker.getValue();

                                // get the selected date
                                label.setText("Date :" + localDate);
                        }
                };

                // show week numbers
                datePicker.setShowWeekNumbers(true);

                // when datePicker is pressed
                datePicker.setOnAction(event);

                // add button and label
                tilePane.getChildren().add(datePicker);
                tilePane.getChildren().add(label);

                HBox dialogHbox = new HBox(20);
                dialogHbox.setAlignment(Pos.CENTER);

                VBox dialogVbox1 = new VBox(20);
                dialogVbox1.setAlignment(Pos.CENTER_LEFT);

                VBox dialogVbox2 = new VBox(20);
                dialogVbox2.setAlignment(Pos.CENTER_RIGHT);

                dialogVbox1.setPrefWidth(100);
                dialogVbox2.setPrefWidth(100);
                submit.setMinWidth(dialogVbox1.getPrefWidth());
                close.setMinWidth(dialogVbox2.getPrefWidth());

                dialogVbox1.getChildren().add(submit);
                dialogVbox2.getChildren().add(close);

                submit.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent e) {
                                        Date date = Date.valueOf(localDate);
                                        formulas.setSettlementPeriod_endDate(date);
                                        // inside here you can use the minimize or close the previous stage//
                                        dialog.close();
                                }
                        });
                close.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent e) {
                                        dialog.close();
                                }
                        });



                dialogHbox.getChildren().addAll(tilePane, dialogVbox1, dialogVbox2);
                Scene dialogScene = new Scene(dialogHbox, 500, 40);
                //dialogScene.getStylesheets().add("//style sheet of your choice");
                dialog.setScene(dialogScene);
                dialog.show();
        }


        @FXML
        private void exit() {
                new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to quit?").showAndWait();
                connection = null;
                Platform.exit();
        }
        public void exitOnClick() { exit(); }
        public void exitOnReturn(KeyEvent e) {
                if (e.getCode().equals(KeyCode.ENTER))
                        exit();
        }



        private void table(ResultSet tableNames) throws SQLException {

        }
}