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

public class Teacher{


	private static String OldPassword;
	static String thenewpass;
	public static int RoomNumber;
	public static int ComputerNumber;
	public static Scene teacherScene;
	public static String Description;


	public static String getOldPassword() {
		return OldPassword;
	}

	
	public static void setOldPassword(String oldPassword) {
		OldPassword = oldPassword;
	}


	public static void display () {
		Insets insets = new Insets(10);
		VBox p = new VBox();
		HBox bottomp = new HBox();
		BorderPane borderpane2 = new BorderPane();
		VBox f = new VBox();
		HBox bottomf = new HBox();
		BorderPane borderpane1 = new BorderPane();
		VBox v = new VBox();
		BorderPane borderpane3 = new BorderPane();
		HBox bottom = new HBox();
		BorderPane borderpane4 = new BorderPane();
		HBox bottomvr = new HBox();
		VBox vr = new VBox();
		


		


		System.out.println(OldPassword);

		Stage window1 = new Stage();
		window1.setTitle("Teachers Menu");
		window1.initModality(Modality.APPLICATION_MODAL);

		Label Teacherlbl = new Label();
		Teacherlbl.setText("Teachers Menu");
		Teacherlbl.setFont(new Font("Arial", 20));
		Teacherlbl.setPadding(new Insets(50,10,10,10));


		//for the password change menu
		Label passwordchange = new Label();
		passwordchange.setText("Password Change");
		Label EnterOldPass = new Label();
		EnterOldPass.setFont(new Font(20));
		EnterOldPass.setText("Enter your current password: "); 

		TextField oldpass = new TextField();
		oldpass.setPromptText("Current Password");
		oldpass.setMaxWidth(250);

		Label EnterNewPass = new Label();
		EnterNewPass.setFont(new Font(14));
		EnterNewPass.setText("Enter your new password: "); 

		TextField newPass = new TextField();
		newPass.setPromptText("new Password");
		newPass.setMaxWidth(250);


		Label confirmNewPassword = new Label();
		confirmNewPassword.setFont(new Font(14));
		confirmNewPassword.setText("Confirm your new password: "); 

		TextField confirmPass = new TextField();
		confirmPass.setPromptText("Confirm new Password");
		confirmPass.setMaxWidth(250);



		Button Confirm = new Button();
		Confirm.setText("Confirm");
		Confirm.setMaxWidth(150);
		Confirm.setOnAction( e-> {

			if(newPass.getText().equals(confirmPass.getText()) && oldpass.getText().equals(OldPassword)   ) {
				System.out.println("Wow it works amazing!");
				thenewpass= newPass.getText();
				ConnectionClass.updatePassword();
			}else {
				inputError();
			}

		});

		Button helppass = new Button("Help");
		helppass.setOnAction(e -> {
			getPassHelp();
		});
		Button closep = new Button("Close");
		closep.setOnAction(e -> {
			window1.close();

		});

		//for the log a fault menu
		Label LogAFault = new Label();
		LogAFault.setText("Fault Log");
		LogAFault.setFont(new Font("Arial", 20));

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
		description.setText("Enter the fault Description");
		description.setFont(new Font(14));
		
		TextField describe = new TextField();
		describe.setMaxWidth(250);
		

	

		Button LogFault = new Button("Complete");
		LogFault.setPrefWidth(200);
		LogFault.setAlignment(Pos.BOTTOM_CENTER);
		LogFault.setOnAction(e -> {

			if(isInt(RoomNumb,CompNo)) {
				RoomNumber= Integer.parseInt( RoomNumb.getText());
				ComputerNumber = Integer.parseInt(CompNo.getText());
				Description = describe.getText();
				ConnectionClass.LogFaultyTeacher();
			}else 
				inputError(); 
		});

		Button viewLogs = new Button("View all Logs");
		viewLogs.setMaxWidth(200);
		viewLogs.setOnAction(e -> {
			ConnectionClass.showFaults();
		});
		


		Button help = new Button("Help");
		help.setOnAction(e -> {
			getHelp();
		});

		Button close = new Button("Close");
		close.setOnAction(e -> {
			window1.close();
		});
		
	

		//For the Teachers Menu
		Scene teacherScene = new Scene( borderpane1, 500, 500);
		teacherScene.getStylesheets().add("style.css");
		window1.setTitle("Teacher Menu");
		window1.setScene(teacherScene);
		window1.show();
		Button closePass = new Button("Close");
		closePass.setOnAction(e -> {
			window1.setScene(teacherScene);
		});


		//For the change password menu
	
		p.getChildren().addAll(EnterOldPass,oldpass,EnterNewPass,newPass,confirmNewPassword,confirmPass,Confirm);
		p.setAlignment(Pos.CENTER);
		p.setPadding(new Insets(10,10,10,10));
		p.setSpacing(15);

		
		bottomp.getChildren().addAll(closep,helppass);
		bottomp.setAlignment(Pos.BOTTOM_LEFT);
		bottomp.setSpacing(6);


		
		borderpane2.setTop(passwordchange);
		BorderPane.setAlignment(passwordchange, Pos.CENTER);
		borderpane2.setCenter(p);
		BorderPane.setMargin(p, insets);
		borderpane2.setBottom(bottomp);
		borderpane2.setPadding(new Insets(6));

		Scene passchange = new Scene(borderpane2,500,500);
		passchange.getStylesheets().add("style.css");

		Button changePass = new Button("Change Password");
		changePass.setMaxWidth(200);
		changePass.setOnAction(e -> {
			window1.setScene(passchange);

		});
		changePass.setPrefWidth(150);

		//For the log fault menu
		Button helplog = new Button("Help");
		helplog.setOnAction(e -> {
			getLogHelp();
		});

		Button closelog = new Button("Close");
		closelog.setOnAction(e -> {
			window1.setScene(teacherScene);
		});

		//scene for log fault menu
		
		f.getChildren().addAll(Roomnumb,RoomNumb,Compnumb,CompNo, description, describe, LogFault);
		f.setAlignment(Pos.CENTER);
		f.setPadding(new Insets(10,10,10,10));
		f.setSpacing(15);
		
	
		bottomf.getChildren().addAll(helplog,closelog);
		bottomf.setAlignment(Pos.BOTTOM_LEFT);
		bottomf.setSpacing(8);

		
		borderpane3.setTop(LogAFault);
		BorderPane.setAlignment(LogAFault, Pos.CENTER);
		borderpane3.setCenter(f);
		borderpane3.setBottom(bottomf);
		BorderPane.setMargin(f, insets);
		borderpane3.setPadding(new Insets(6));

		Scene LogedFaults = new Scene(borderpane3,500,500);
		LogedFaults.getStylesheets().add("style.css");

		//for the logfault  menu
		Button logFault = new Button("Log a Fault");
		logFault.setMaxWidth(200);
		logFault.setOnAction(e -> {
			window1.setScene(LogedFaults);
		});
		
		//for the view resolved faults
		Button viewresolved = new Button("View Resolved Faults");
		viewresolved.setMaxWidth(200);
		viewresolved.setOnAction(e -> {
			ConnectionClass.showFaultsResolved();
		});
		
		//for the view by room menu
		Label Byroom = new Label();
		Byroom.setText("View Faults By Room");
		Byroom.setFont(new Font(20));
		Byroom.setPadding(new Insets(50,10,10,10));
		
		TextField EnterRoom = new TextField();
		EnterRoom.setPromptText("Enter Room Number");
		EnterRoom.setMaxWidth(250);
			
		Button viewFaults = new Button();		//button to view faults by room
		viewFaults.setText("View Faults");
		viewFaults.setMaxWidth(150);
		viewFaults.setOnAction( e-> {
			if(Technicians.isInt(EnterRoom)) {
			RoomNumber = Integer.parseInt( EnterRoom.getText());
			ConnectionClass.FaultsByRoom(RoomNumber);
			}
			else {
				inputError();
			}
		});
		
		Button ExitByroom = new Button("Close");
		ExitByroom.setMaxWidth(200);
		ExitByroom.setOnAction(e -> {
			window1.setScene(teacherScene);
		});
		
		Button helpbyroom = new Button("Help");
		helpbyroom.setMaxWidth(200);
		helpbyroom.setOnAction(e -> {
			getroomHelp();
		});
			
	
		bottomvr.getChildren().addAll(ExitByroom,helpbyroom);
		bottomvr.setAlignment(Pos.BOTTOM_LEFT);
		bottomvr.setSpacing(6);
		

		vr.getChildren().addAll(EnterRoom,viewFaults);
		vr.setAlignment(Pos.CENTER);
		vr.setPadding(new Insets(10,10,10,10));
		vr.setSpacing(15);
			
		
		
		borderpane4.setTop(Byroom);
		BorderPane.setAlignment(Byroom, Pos.CENTER);
		borderpane4.setCenter(vr);
		BorderPane.setMargin(vr, insets);
		borderpane4.setBottom(bottomvr);
		borderpane4.setPadding(new Insets(6));
		
		Scene ViewbyRoomsc = new Scene(borderpane4,360,360);
		ViewbyRoomsc.getStylesheets().add("style.css");
		
		Button viewFaultsRoom = new Button("ViewbyRoom");  //Button to view faults by room
		viewFaultsRoom.setMaxWidth(200);
		viewFaultsRoom.setOnAction( e -> {
			window1.setScene(ViewbyRoomsc);
		});
		
		

		//For the Teachers Menu	
		v.getChildren().addAll(changePass,logFault,viewLogs,viewFaultsRoom,viewresolved);
		v.setAlignment(Pos.CENTER);
		v.setPadding(new Insets(10,10,10,10));
		v.setSpacing(15);

	
		bottom.getChildren().addAll(close,help);
		bottom.setAlignment(Pos.BOTTOM_LEFT);
		bottom.setSpacing(6);



		borderpane1.setTop(Teacherlbl);
		BorderPane.setAlignment(Teacherlbl, Pos.CENTER);
		borderpane1.setCenter(v);
		BorderPane.setMargin(v, insets);
		borderpane1.setBottom(bottom);
		borderpane1.setPadding(new Insets(6));


	}
	


	public static Alert getHelp() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText("To change your password press the Change Password Button\n"
				+ "1. To report a fault press the log a fault button\n"
				+ "2. To view all faults press the View all logs button\n"
				+ "3. To view Faults by room press the view by room button\n"
				+ "4. To view all the faults that have been resolved press the view resolved faults\n"
				+ "5. To go back press the close button\n"
				);	
		alert.showAndWait();

		return alert;
		
	}


	public static Alert getPassHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText("In the first box enter your current password\n"
				+ "1. in the second box enter your new password\n"
				+ "2. in the third box reenter your new password\n"
				+ "3. When all textboxes are full press the confirm box\n"
				+ "4. To go back press the close button\n"	
				);	
		alert.showAndWait();

		return alert;
	}
	
	public static Alert getLogHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText("Please Enter the room number and pc number \n"
				+ "1. Once you have entered that into the textfield then press complete\n"
				+ "2. If you wish to exit the window press cancel\n"
				
				);	
		alert.showAndWait();

		return alert;
	}
	
	public static Alert getroomHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText("Please Enter the room number in the textbox \n"
				+ "1. Then press the view button\n"
				+ "2. If you wish to exit the window press close\n"
				
				);	
		alert.showAndWait();

		return alert;
	}


	public static Alert inputError() {
		//Alert displaying that your account Balance is under 2 Euro
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText("Error");
		alert.setContentText("The input is not correct!");	
		alert.showAndWait();
		return alert;
	}

	public static boolean isInt(TextField textone, TextField texttwo){
		try {
			int Room =  Integer.parseInt(textone.getText());
			int COmp =  Integer.parseInt(texttwo.getText());
			return true;
		}
		catch(NumberFormatException e) {

		}
		return false;


	}










}
