import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Admin {

	public static void display() {

		BorderPane borderpane = new BorderPane();
		HBox bottom = new HBox();
		VBox v = new VBox();

		VBox vbr = new VBox(); // for the view by room scene
		HBox bottomvbr = new HBox();
		BorderPane borderpane1 = new BorderPane();

		Insets insets = new Insets(10);

		Stage window3 = new Stage();
		window3.setTitle("Admin Menu");
		window3.initModality(Modality.APPLICATION_MODAL);

		Label Adminlbl = new Label();
		Adminlbl.setText("Admin Menu");
		Adminlbl.setFont(new Font(20));
		Adminlbl.setPadding(new Insets(50, 10, 10, 10));

		Button viewLogs = new Button("View all Logs");
		viewLogs.setPrefWidth(150);
		viewLogs.setOnAction(e -> {
			ConnectionClass.showFaults();
		});

		Button modRoom = new Button("Modify Room Details");
		modRoom.setMaxWidth(150);
		modRoom.setOnAction(e -> {
			ModRoom.dispay();
		});

		Button modComputer = new Button("Modify Computer Details");
		modComputer.setOnAction(e -> {
			modComp.dispay();
		});
		modComputer.setPrefWidth(150);

		Button modUsers = new Button("Modify User Details");
		modUsers.setOnAction(e -> {
			ModifyUser.display();
		});
		modUsers.setPrefWidth(150);

		Button help = new Button("Help");
		help.setOnAction(e -> {
			getHelp();
		});

		Button close = new Button("Close");
		close.setOnAction(e -> {
			window3.close();
		});

		// For the view by Room Menu

		Label ByRoom = new Label();
		ByRoom.setText("View Faults By Room");
		ByRoom.setFont(new Font(20));
		ByRoom.setPadding(new Insets(50, 10, 10, 10));

		TextField RoomNo = new TextField();
		RoomNo.setMaxWidth(250);

		Button viewRoom = new Button("View by room");
		viewRoom.setMaxWidth(150);
		viewRoom.setOnAction(e -> {
			if (Technicians.isInt(RoomNo)) {
				int RmNumber = Integer.parseInt(RoomNo.getText());
				ConnectionClass.FaultsByRoom(RmNumber);
			} else {
				Technicians.inputError();
			}
		});

		Scene viewbr = new Scene(borderpane1, 350, 350);
		viewbr.getStylesheets().add("style.css");

		Button viewLogsRoom = new Button("View by Room"); // Button to view logs by room
		viewLogsRoom.setPrefWidth(150);
		viewLogsRoom.setOnAction(e -> {

			window3.setScene(viewbr);
		});

		// for the view resolved faults
		Button viewresolved = new Button("View Resolved Faults");
		viewresolved.setMaxWidth(150);
		viewresolved.setOnAction(e -> {
			ConnectionClass.showFaultsResolved();
		});

		// For the Admin Menu
		v.getChildren().addAll(viewLogs, viewLogsRoom, viewresolved, modRoom, modComputer, modUsers);
		v.setAlignment(Pos.CENTER);
		v.setPadding(new Insets(10, 10, 10, 10));
		v.setSpacing(15);

		bottom.getChildren().addAll(close, help);
		bottom.setAlignment(Pos.BOTTOM_LEFT);
		bottom.setSpacing(6);

		borderpane.setTop(Adminlbl);
		BorderPane.setAlignment(Adminlbl, Pos.CENTER);
		borderpane.setCenter(v);
		BorderPane.setMargin(v, insets);
		borderpane.setBottom(bottom);
		borderpane.setPadding(new Insets(6));

		Scene AdminScene = new Scene(borderpane, 500, 500);
		AdminScene.getStylesheets().add("style.css");
		window3.setTitle("Admin Menu");

		Button closevbr = new Button("Cancel");
		closevbr.setMaxWidth(150);
		closevbr.setOnAction(e -> {

			window3.setScene(AdminScene);
		});

		Button helpvbr = new Button("Help");
		helpvbr.setMaxWidth(150);
		helpvbr.setOnAction(e -> {
			getHelpvbr();
		});

		vbr.getChildren().addAll(RoomNo, viewRoom);
		vbr.setAlignment(Pos.CENTER);
		vbr.setPadding(new Insets(10, 10, 10, 10));
		vbr.setSpacing(20);

		bottomvbr.getChildren().addAll(closevbr, helpvbr);
		bottomvbr.setAlignment(Pos.BOTTOM_LEFT);
		bottomvbr.setSpacing(6);

		borderpane1.setTop(ByRoom);
		BorderPane.setAlignment(ByRoom, Pos.CENTER);
		borderpane1.setCenter(vbr);
		BorderPane.setMargin(vbr, insets);
		borderpane1.setBottom(bottomvbr);
		borderpane1.setPadding(new Insets(6));

		window3.setScene(AdminScene);
		window3.show();

	}

	public static Alert getHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText("To View all faults logged press the view all logs button\n"
				+ "1. To view faults by room press the View by Room button\n"
				+ "2. To view all of the faults that have been resolved press the view Resolved faults button\n"
				+ "3. To modify the rooms table press the Modify Room Detials button\n"
				+ "4. To modify the pc table press the Modify Computer Detials button\n"
				+ "5. To add or remove a user press the Modify User Details button\n"
				+ "6. To go back press the close button\n");
		alert.showAndWait();

		return alert;
	}

	public static Alert getHelpvbr() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText("Enter the room number in the textfield\n"
				+ "1. press the view by room button to see the faults for that room\n" + "2. To go back press cancel\n"

		);
		alert.showAndWait();

		return alert;
	}

}
