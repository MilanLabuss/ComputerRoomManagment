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

public class Technicians {
	static int RoomNumber;
	public static int ComputerNumber;
	public static String Description;
	public static int RoomNumberresolve; // for resolve
	public static int CompNumberresolve;
	// for resolve

	public static void display() {

		VBox v = new VBox();
		HBox bottom = new HBox();
		HBox bottomvr = new HBox();
		VBox vr = new VBox();
		BorderPane borderpane2 = new BorderPane();
		VBox f = new VBox();
		HBox bottomf = new HBox();
		BorderPane borderpane3 = new BorderPane();

		HBox bottomre = new HBox();
		VBox re = new VBox();
		BorderPane borderpane4 = new BorderPane();

		Stage window2 = new Stage();
		window2.setTitle("Technicians Menu");
		window2.initModality(Modality.APPLICATION_MODAL);

		Label Techlbl = new Label();
		Techlbl.setText("Technicians Menu");
		Techlbl.setFont(new Font("Arial", 20));
		Techlbl.setPadding(new Insets(50, 10, 10, 10));

		Button viewLogs = new Button("View all Logs"); // Button to view all Faults
		viewLogs.setOnAction(e -> {
			ConnectionClass.showFaults();
		});
		viewLogs.setPrefWidth(150);

		Button olderLogs = new Button("View Older Logs"); // Button to view Faults Older than 5 days
		olderLogs.setOnAction(e -> {
			ConnectionClass.showOlderFaults();
		});
		olderLogs.setPrefWidth(150);

		Button mostlogs = new Button("Show Faults Report"); // Button to enter the room with the most faults
		mostlogs.setOnAction(e -> {
			ConnectionClass.showHighestFaults();
		});
		mostlogs.setPrefWidth(150);

		Button help = new Button("Help");
		help.setOnAction(e -> {
			getHelp();
		});

		Button close = new Button("Close");
		close.setOnAction(e -> {
			window2.close();
		});

//for the log a fault menu
		Label LogAFault = new Label();
		LogAFault.setText("Status");
		LogAFault.setFont(new Font(20));
		LogAFault.setPadding(new Insets(30, 10, 10, 10));

		Label Roomnumb = new Label();
		Roomnumb.setText("Enter Room Number");
		Roomnumb.setFont(new Font(14));

		TextField RoomNumb = new TextField();
		RoomNumb.setMaxWidth(250);

		Label Compnumb = new Label();
		Compnumb.setText("Enter Comp Number");
		Compnumb.setFont(new Font(14));

		TextField CompNo = new TextField();
		CompNo.setMaxWidth(250);

		Label description = new Label();
		description.setText("Update Repair Status");
		description.setFont(new Font(14));

		TextField describe = new TextField();
		describe.setMaxWidth(250);

//For resolving a fault Menu
		Label ResolveFault = new Label();
		ResolveFault.setText("Resolve a Fault");
		ResolveFault.setFont(new Font(20));
		ResolveFault.setPadding(new Insets(30, 10, 10, 10));

		Label roomnumbres = new Label();
		roomnumbres.setText("Enter Room Number");
		roomnumbres.setFont(new Font(14));

		TextField roomNumberres = new TextField();
		roomNumberres.setMaxWidth(250);

		Label Compnumberres = new Label();
		Compnumberres.setText("Enter PC Number");
		Compnumberres.setFont(new Font(14));

		TextField CompNumberres = new TextField();
		CompNumberres.setMaxWidth(250);

		Button resolveThisFault = new Button("Resolve");
		resolveThisFault.setMaxWidth(200);
		resolveThisFault.setOnAction(e -> {

			if (Teacher.isInt(roomNumberres, CompNumberres)) {

				RoomNumberresolve = Integer.parseInt(roomNumberres.getText());
				CompNumberresolve = Integer.parseInt(CompNumberres.getText());
				ConnectionClass.insertIntoResolved();
				ConnectionClass.makeStatusResolved();
				ConnectionClass.makeStatusResolvedpc();
				// ConnectionClass.RemoveResolvedFault();

			} else
				inputError();
		});

		// For the by room view Menu
		Label Byroom = new Label();
		Byroom.setText("View Faults By Room");
		Byroom.setFont(new Font("Arial", 20));
		Byroom.setPadding(new Insets(50, 10, 10, 10));

		TextField EnterRoom = new TextField();
		EnterRoom.setPromptText("Enter Room Number");
		EnterRoom.setMaxWidth(250);

		Button viewFaults = new Button(); // button to view faults by room
		viewFaults.setText("View Faults");
		viewFaults.setMaxWidth(150);
		viewFaults.setOnAction(e -> {
			if (isInt(EnterRoom)) {
				RoomNumber = Integer.parseInt(EnterRoom.getText());
				ConnectionClass.FaultsByRoom(RoomNumber);
			} else {
				inputError();
			}
		});

		Insets in = new Insets(10);

		borderpane2.setTop(Byroom);
		BorderPane.setAlignment(Byroom, Pos.CENTER);
		borderpane2.setCenter(vr);
		BorderPane.setMargin(vr, in);
		borderpane2.setBottom(bottomvr);
		borderpane2.setPadding(new Insets(6));
		Scene ViewbyRoom = new Scene(borderpane2, 360, 360);
		ViewbyRoom.getStylesheets().add("style.css");

//For the log fault menu

		Button helplog = new Button("Help");
		helplog.setOnAction(e -> {
			getLogHelp();
		});
		// for the view resolved faults
		Button viewresolved = new Button("View Resolved Faults");
		viewresolved.setMaxWidth(150);
		viewresolved.setOnAction(e -> {
			ConnectionClass.showFaultsResolved();
		});
//scene for log fault menu

		Button LogFault = new Button("Complete");
		LogFault.setPrefWidth(200);
		LogFault.setAlignment(Pos.BOTTOM_CENTER);
		LogFault.setOnAction(e -> {

			if (Teacher.isInt(RoomNumb, CompNo)) {
				RoomNumber = Integer.parseInt(RoomNumb.getText());
				ComputerNumber = Integer.parseInt(CompNo.getText());
				Description = describe.getText();
				ConnectionClass.LogFaultyTech();
			} else
				inputError();
		});

		Insets ints = new Insets(10);

		borderpane3.setTop(LogAFault);
		BorderPane.setAlignment(LogAFault, Pos.CENTER);
		borderpane3.setCenter(f);
		borderpane3.setBottom(bottomf);
		BorderPane.setMargin(f, ints);
		borderpane3.setPadding(new Insets(6));

		Scene LogedFaults = new Scene(borderpane3, 500, 500);
		LogedFaults.getStylesheets().add("style.css");

//view faults by room
		Button byRoom = new Button("View By Room");
		byRoom.setOnAction(e -> {
			window2.setScene(ViewbyRoom);

		});
		byRoom.setPrefWidth(150);

//Main Tech Menu
		Insets insets = new Insets(10);

		BorderPane borderpane = new BorderPane();
		borderpane.setTop(Techlbl);
		BorderPane.setAlignment(Techlbl, Pos.CENTER);
		borderpane.setCenter(v);
		BorderPane.setMargin(v, insets);
		borderpane.setBottom(bottom);
		borderpane.setPadding(new Insets(6));

		Scene techScene = new Scene(borderpane, 500, 500);
		techScene.getStylesheets().add("style.css");

//For the log fault Menu
		Button closelog = new Button("Close");
		closelog.setOnAction(e -> {
			window2.setScene(techScene);
		});

		Button logFault = new Button("Update Repair Status"); // button to go to the logged faults menu
		logFault.setOnAction(e -> {
			window2.setScene(LogedFaults);
		});
		logFault.setPrefWidth(150);

//for log fault
		f.getChildren().addAll(Roomnumb, RoomNumb, Compnumb, CompNo, description, describe, LogFault);
		f.setAlignment(Pos.CENTER);
		f.setPadding(new Insets(10, 10, 10, 10));
		f.setSpacing(15);

		bottomf.getChildren().addAll(closelog, helplog);
		bottomf.setAlignment(Pos.BOTTOM_LEFT);
		bottomf.setSpacing(8);

		Insets inse = new Insets(10);

//for the resolve screen menu
		Button closeResolve = new Button("Exit");
		closeResolve.setMaxWidth(200);
		closeResolve.setOnAction(e -> {

			window2.setScene(techScene);
		});

		re.getChildren().addAll(roomnumbres, roomNumberres, Compnumberres, CompNumberres, resolveThisFault,
				closeResolve);
		re.setAlignment(Pos.CENTER);
		re.setPadding(new Insets(10, 10, 10, 10));
		re.setSpacing(15);

		Button Exitresolve = new Button();
		Exitresolve.setText("Cancel");
		Exitresolve.setMaxWidth(150);
		Exitresolve.setOnAction(e -> {
			window2.setScene(techScene);

		});

		borderpane4.setTop(ResolveFault);
		BorderPane.setAlignment(ResolveFault, Pos.CENTER);
		borderpane4.setCenter(re);
		BorderPane.setMargin(re, inse);
		borderpane4.setBottom(bottomre);
		borderpane4.setPadding(new Insets(6));

		Scene resolve = new Scene(borderpane4, 360, 360); // Scene for the resolve screen menu
		resolve.getStylesheets().add("style.css");

		Button ResolveLog = new Button("Resolve Log"); // button to go to the resolve screen
		ResolveLog.setMaxWidth(150);
		ResolveLog.setOnAction(e -> {
			window2.setScene(resolve);
		});

//for the view by room menu
		Button ExitByroom = new Button();
		ExitByroom.setText("Cancel");
		ExitByroom.setMaxWidth(150);
		ExitByroom.setOnAction(e -> {
			window2.setScene(techScene);

		});

		Button getRoomHelp = new Button("Help");
		getRoomHelp.setMaxWidth(150);
		getRoomHelp.setOnAction(e -> {
			RoomHelp();

		});

		bottomvr.getChildren().addAll(ExitByroom, getRoomHelp);
		bottomvr.setAlignment(Pos.BOTTOM_LEFT);
		bottomvr.setSpacing(6);

		vr.getChildren().addAll(EnterRoom, viewFaults);
		vr.setAlignment(Pos.CENTER);
		vr.setPadding(new Insets(10, 10, 10, 10));
		vr.setSpacing(15);

		v.getChildren().addAll(byRoom, logFault, ResolveLog, viewLogs, viewresolved, olderLogs, mostlogs);
		v.setAlignment(Pos.CENTER);
		v.setPadding(new Insets(10, 10, 10, 10));
		v.setSpacing(15);

		bottom.getChildren().addAll(close, help);
		bottom.setAlignment(Pos.BOTTOM_LEFT);
		bottom.setSpacing(6);

		window2.setTitle("Tech Menu");
		window2.setScene(techScene);
		window2.show();

	}

	public static Alert getLogHelp() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText("Enter a valid Room number in the Textfield\n" + "1. Enter a valid Computer number\n"
				+ "2. Enter your repair status\n" + "3. Enter the complete button\n"
				+ "4. To go back press the close button\n"

		);
		alert.showAndWait();
		return alert;

	}

	public static Alert RoomHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText("Enter a valid Room number in the Textfield\n" + "1. Press the view faults button\n"
				+ "2. To exit press the cancel button\n"

		);
		alert.showAndWait();

		return alert;
	}

	public static Alert getHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText("To view Faults by room press the View By Room Menu\n"
				+ "1. To change the status of the repair press the update repair status button\n"
				+ "2. To resolve a fault press the Resolve Log button\n"
				+ "3. To view all Logs press the View All Logs button\n"
				+ "4. To view all Logs that have been resolved press the View Resolved Faults Button\n"
				+ "5. To view all Logs that have been resolved press the View Resolved Faults Button\n"
				+ "6. To view faults older than 5 days press the View Older Logs Button\n"
				+ "7. To view which room has the most faults press the show Faults Report Button\n");
		alert.showAndWait();

		return alert;
	}

	public static boolean isInt(TextField textone) {
		try {
			int RoomNo = Integer.parseInt(textone.getText());

			return true;
		} catch (NumberFormatException e) {

		}
		return false;

	}

	public static Alert inputError() {
		// Alert displaying that your account Balance is under 2 Euro
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText("Error");
		alert.setContentText("The input is not correct!");
		alert.showAndWait();
		return alert;
	}

}
