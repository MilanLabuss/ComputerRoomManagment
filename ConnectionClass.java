import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class ConnectionClass {

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	static LocalDateTime now = LocalDateTime.now();

	static Statement st;
	static ResultSet rs;
	static Connection con;
	static ResultSet res;

	final static String dbUrl = "jdbc:mysql://127.0.0.1:3306/sys";
	final static String user = "root";
	final static String pass = "ClassCS2021!";
	static int Computers = 3;

	static GridPane gridPane = new GridPane();

	public static ObservableList<ObservableList> data;
	public static TableView tableview;
	public static boolean isReady = false;
	public static String Resolved = "Resolved";
	private static TableView tableView;
	private static GridPane gridpane;

	public static void connect() { // method to connect to the database

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement(); // Create a statement to view
			System.out.println("Database Connected");

		} catch (Exception e) {
			System.out.println("Error did not connect ");
		}
	}

	public static void LogFaultyTeacher() { // method for the teacher to log a fault

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement(); // Create a statement to view
			System.out.println("Database Connected for update");
			System.out.println(Main.getTName());
			String UpdateQuery = "update pc set status='" + Teacher.Description + "',loggedBy= '" + Main.getTName()
					+ "',statusDate='" + now + "'where roomNo='" + Teacher.RoomNumber + "'AND pcNo='"
					+ Teacher.ComputerNumber + "'";

			if ((Teacher.RoomNumber == 113 || Teacher.RoomNumber == 116 || Teacher.RoomNumber == 222)
					&& (Teacher.ComputerNumber <= Computers)) {
				st.executeUpdate(UpdateQuery);
				System.out.println("update successful");
			} else {
				Teacher.inputError();
			}

		} catch (Exception e) {
			System.out.println("Error did not connect the update failed");
		}

	}

	public static void LogFaultyTech() { // method to Indicate the status of the repair

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement();		 // Create a statement to view
			String UpdateQuery = "update pc set status='" + Technicians.Description + "'where roomNo='"
					+ Technicians.RoomNumber + "'AND pcNo='" + Technicians.ComputerNumber + "'";

			if ((Technicians.RoomNumber == 113 || Technicians.RoomNumber == 116 || Technicians.RoomNumber == 222)
					&& (Technicians.ComputerNumber <= Computers)) {
				st.executeUpdate(UpdateQuery);
				System.out.println("Update successful");
			} else {
				Teacher.inputError();
			}

		} catch (Exception e) {
			System.out.println("Error did not connect the update failed");
		}

	}

	public static void updatePassword() { // method to update password

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement();			 // Create a statement to view
			st.executeUpdate("update teachers set password='" + Teacher.thenewpass + "'where password='"
					+ Teacher.getOldPassword() + "'");
			System.out.println("update compltete");

		} catch (Exception e) {
			System.out.println("Error did not connect the update failed");
		}
	}

	public static void FaultsByRoom(int r) { // method to show faults by room
		int ViewRoomNo = r;
		System.out.println("Room no passed to conn class is : " + ViewRoomNo);
		tableview = new TableView();
		data = FXCollections.observableArrayList();

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			// Create a statement to view
			System.out.println("Database Connected for view");

			String viewquery = "SELECT * FROM pc WHERE loggedBy IS NOT NULL AND roomNo = '" + ViewRoomNo + "'";

			if ((ViewRoomNo == 113 || ViewRoomNo == 116 || ViewRoomNo == 222)) {
				rs = con.createStatement().executeQuery(viewquery);
			} else {
				Teacher.inputError();
			}

			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				final int x = i;
				TableColumn column = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				column.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> val) {
								return new SimpleStringProperty(val.getValue().get(x).toString());
							}
						});

				tableview.getColumns().addAll(column);

			}

			while (rs.next()) {
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getString(i));
				}
				data.add(row);

			}
			tableview.setSelectionModel(null);
			tableview.setItems(data);
			tableview.setMaxHeight(260);
			tableview.setPrefWidth(310);

		}

		catch (Exception e) {
			System.out.println("Error failed");
		}
		;

		BorderPane borderPane1 = new BorderPane();
		Scene scene = new Scene(borderPane1, 650, 400, true);
		gridPane.add(tableview, 1, 3);

		borderPane1.setCenter(tableview);
		borderPane1.setLeft(gridPane);

		Stage window4 = new Stage();

		window4.setTitle("Logs Menu");
		window4.setScene(scene);
		window4.show();

	}

	public static void insertIntoResolved() { // method to insert a record into the resolved table

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement(); // Create a statement to view
			System.out.println("Database Connected for update");
			System.out.println(Main.getTName());
			String insertQuery = "INSERT INTO resolved SELECT * FROM pc WHERE roomNo='" + Technicians.RoomNumberresolve
					+ "'AND pcNo='" + Technicians.CompNumberresolve + "'";

			if ((Technicians.RoomNumberresolve == 113 || Technicians.RoomNumberresolve == 116
					|| Technicians.RoomNumberresolve == 222) && (Technicians.CompNumberresolve <= Computers)) {
				st.executeUpdate(insertQuery);

				System.out.println("update compltete");
			} else {
				Teacher.inputError();
			}

		} catch (Exception e) {
			System.out.println("Error did not connect the update failed");
		}

	}

	public static void makeStatusResolved() { // method to make a fault resolved
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement(); // Create a statement to view

			String changeQuery = "UPDATE resolved SET status = '" + Resolved + "'WHERE roomNo='"
					+ Technicians.RoomNumberresolve + "'AND pcNo='" + Technicians.CompNumberresolve + "'";
			if ((Technicians.RoomNumberresolve == 113 || Technicians.RoomNumberresolve == 116
					|| Technicians.RoomNumberresolve == 222) && (Technicians.CompNumberresolve <= Computers)) {
				st.executeUpdate(changeQuery);
				System.out.println("update compltete");
			} else {
				Teacher.inputError();
			}

		} catch (Exception e) {
			System.out.println("Error did not connect the update failed");
		}

	}
	public static void makeStatusResolvedpc() { // method to make a fault resolved
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement(); // Create a statement to view

			String changeQuery = "UPDATE pc SET status = '" + Resolved + "'WHERE roomNo='"
					+ Technicians.RoomNumberresolve + "'AND pcNo='" + Technicians.CompNumberresolve + "'";
			if ((Technicians.RoomNumberresolve == 113 || Technicians.RoomNumberresolve == 116
					|| Technicians.RoomNumberresolve == 222) && (Technicians.CompNumberresolve <= Computers)) {
				st.executeUpdate(changeQuery);
				System.out.println("update compltete");
			} else {
				Teacher.inputError();
			}

		} catch (Exception e) {
			System.out.println("Error did not connect the update failed");
		}

	}

	public static void RemoveARoom(int r) { // method to remove a room
		int theroom = r;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement(); // Create a statement to view
			System.out.println("Database Connected for update");
			String DeleteQuery = "delete FROM rooms Where roomNo='" + theroom + "'";

		
				st.executeUpdate(DeleteQuery);
				System.out.println("update compltete");
		
			
		

		} catch (Exception e) {
			System.out.println("Error did not connect the update failed");
		}

	}

	public static void RemoveAPC(int r, int p) { // method to remove a pc
		int theroom = r;
		int thepc = p;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement(); // Create a statement to view
			System.out.println("Database Connected for update");
			String DeleteQuery = "delete FROM pc Where roomNo='" + theroom + "'AND pcNo='" + thepc + "'";

			
				st.executeUpdate(DeleteQuery);
				System.out.println("update compltete");
		

		} catch (Exception e) {
			System.out.println("Error did not connect the update failed");
		}

	}

	public static void RemoveTeacher(String enterUsername) { // method to remove a teacher
		String teach = enterUsername;

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement(); // Create a statement to view
			System.out.println("Database Connected for update");
			String DeleteQuery = "delete FROM teachers Where username='" + teach + "'";

			st.executeUpdate(DeleteQuery);
			System.out.println("delete compltete");

		} catch (Exception e) {
			System.out.println("Error did not connect the update failed");
		}

	}

	public static void RemoveTechnician(String enterUsername) { // method to remove a technician
		String tech = enterUsername;

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement(); // Create a statement to view
			System.out.println("Database Connected for update");
			String DeleteQuery = "delete FROM tecnicians Where username='" + tech + "'";

			st.executeUpdate(DeleteQuery);
			System.out.println("delete compltete");

		} catch (Exception e) {
			System.out.println("Error did not connect the update failed");
		}

	}

	public static void AddTeacher(String enterUsername, String enterpass, String enterfname) { // method to add a
																								// teacher
		String teach = enterUsername;
		String teachpass = enterpass;
		String teachfname = enterfname;

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement(); // Create a statement to view
			System.out.println("Database Connected for update");
			String insertQuery = "INSERT INTO teachers VALUES ( '" + teach + "','" + teachpass + "','" + teachfname
					+ "')";

			st.executeUpdate(insertQuery);
			System.out.println("insert compltete");

		} catch (Exception e) {
			System.out.println("did not insert");
		}

	}

	public static void AddTech(String enterUsername, String enterpass, String enterfname) { // method to add a
																							// technician
		String tech = enterUsername;
		String techpass = enterpass;
		String techfname = enterfname;

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement(); // Create a statement to view
			System.out.println("Database Connected for update");
			String insertQuery = "INSERT INTO tecnicians VALUES ( '" + tech + "','" + techpass + "','" + techfname
					+ "')";

			st.executeUpdate(insertQuery);
			System.out.println("insert compltete");

		} catch (Exception e) {

		}

	}

	public static void AddRoom(int rmnbr) {
		int roomNum = rmnbr;

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement(); // Create a statement to view
			System.out.println("Database Connected for update");
			String insertQuery = "INSERT INTO rooms VALUES ( '" + roomNum + "', '0', '0')";

			st.executeUpdate(insertQuery);
			System.out.println("insert compltete");

		} catch (Exception e) {
			System.out.println("update failed");
		}

	}

	public static void AddPC(int ser, int rm, int pcn, String mdl, String manufact) {
		int SerialNo = ser;
		int roomno = rm;
		int pcnumber = pcn;
		String Model = mdl;
		String Manufact = manufact;

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			st = con.createStatement(); // Create a statement to view
			System.out.println("Database Connected for update");
			String insertQuery = "INSERT INTO pc VALUES ( '000" + SerialNo + "','" + Model + "','" + Manufact + "','"
					+ roomno + "','" + pcnumber + "', 'OK','0000-00-00',NULL)";

			st.executeUpdate(insertQuery);
			System.out.println("insert compltete");

		} catch (Exception e) {
			System.out.println("update failed");
		}

	}

	public static void showFaultsResolved() { // method to display the entire resolved table
		tableview = new TableView();
		data = FXCollections.observableArrayList();

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			// Create a statement to view
			System.out.println("Database Connected for view");
			String viewquery = "SELECT * FROM resolved WHERE loggedBy IS NOT NULL;";
			rs = con.createStatement().executeQuery(viewquery);

			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				final int x = i;
				TableColumn column = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				column.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> val) {
								return new SimpleStringProperty(val.getValue().get(x).toString());
							}
						});

				tableview.getColumns().addAll(column);

			}

			while (rs.next()) {
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getString(i));
				}
				data.add(row);

			}
			tableview.setSelectionModel(null);
			tableview.setItems(data);
			tableview.setMaxHeight(260);
			tableview.setPrefWidth(310);

		}

		catch (Exception e) {
			System.out.println("Error did not connect thefailed");
		}

		BorderPane borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, 650, 400, true);
		gridPane.add(tableview, 1, 3);

		borderPane.setCenter(tableview);
		borderPane.setLeft(gridPane);

		Stage window3 = new Stage();

		window3.setTitle("Logs Menu");
		window3.setScene(scene);
		window3.show();

	}

	public static void showFaults() { // method to display the faults
		tableview = new TableView();
		data = FXCollections.observableArrayList();

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			// Create a statement to view
			System.out.println("Database Connected for view");
			String viewquery = "SELECT * FROM pc WHERE loggedBy IS NOT NULL;";
			rs = con.createStatement().executeQuery(viewquery);

			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				final int x = i;
				TableColumn column = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				column.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> val) {
								return new SimpleStringProperty(val.getValue().get(x).toString());
							}
						});

				tableview.getColumns().addAll(column);

			}

			while (rs.next()) {
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getString(i));
				}
				data.add(row);

			}
			tableview.setSelectionModel(null);
			tableview.setItems(data);
			tableview.setMaxHeight(260);
			tableview.setPrefWidth(310);

		}

		catch (Exception e) {
			System.out.println("Error did not connect thefailed");
		}
		// return tableview;

		BorderPane borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, 650, 400, true);
		gridPane.add(tableview, 1, 3);

		borderPane.setCenter(tableview);
		borderPane.setLeft(gridPane);

		Stage window3 = new Stage();

		window3.setTitle("Logs Menu");
		window3.setScene(scene);
		window3.show();

	}

	public static void showHighestFaults() { // method to show which room has the highest number of faults
		tableview = new TableView();
		data = FXCollections.observableArrayList();

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			// Create a statement to view
			System.out.println("Database Connected for view");
			String viewquery = "SELECT roomNo, MAX(numFaults) FROM rooms GROUP BY roomNo;";
			rs = con.createStatement().executeQuery(viewquery);

			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				final int x = i;
				TableColumn column = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				column.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> val) {
								return new SimpleStringProperty(val.getValue().get(x).toString());
							}
						});

				tableview.getColumns().addAll(column);

			}

			while (rs.next()) {
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getString(i));
				}
				data.add(row);

			}
			tableview.setSelectionModel(null);
			tableview.setItems(data);
			tableview.setMaxHeight(260);
			tableview.setPrefWidth(310);

		}

		catch (Exception e) {
			System.out.println("Error did not connect thefailed");
		}

		BorderPane borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, 300, 300, true);
		gridPane.add(tableview, 1, 3);

		borderPane.setCenter(tableview);
		borderPane.setLeft(gridPane);

		Stage window3 = new Stage();

		window3.setTitle("Logs Menu");
		window3.setScene(scene);
		window3.show();

	}

	public static void showOlderFaults() { // method to display logs older than 5 days
		tableView = new TableView();
		data = FXCollections.observableArrayList();

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "ClassCS2021!"); // Get a
																											// Connection
			// Create a statement to view
			System.out.println("Database Connected for view");
			String viewquery = "select * FROM pc WHERE loggedBy IS NOT NULL AND statusDate < NOW() - INTERVAL 5 DAY;";
			System.out.println(viewquery);
			rs = con.createStatement().executeQuery(viewquery);

			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				final int x = i;
				TableColumn column = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				column.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> val) {
								return new SimpleStringProperty(val.getValue().get(x).toString());
							}
						});

				tableView.getColumns().addAll(column);

			}

			while (rs.next()) {
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getString(i));
				}
				data.add(row);

			}
			tableView.setSelectionModel(null);
			tableView.setItems(data);
			tableView.setMaxHeight(260);
			tableView.setPrefWidth(310);

		}

		catch (Exception e) {
			System.out.println("Error did not connect thefailed");
		}

		BorderPane borderPane = new BorderPane();
		Scene oldFaults = new Scene(borderPane, 650, 400, true);
		gridPane.add(tableView, 1, 3);

		borderPane.setCenter(tableView);
		borderPane.setLeft(gridPane);

		Stage window4 = new Stage();

		window4.setTitle("Logs Menu");
		window4.setScene(oldFaults);
		window4.show();

	}

}
