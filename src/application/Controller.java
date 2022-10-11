// AUTHOR: LINYUN LIU
// DATE: MARCH 15th, 2021

package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {
	private String strName;
	private String  strDetails;
	private String priorityLevel;
	private LocalDate date = LocalDate.now();
	private ArrayList<Task> taskList = new ArrayList<Task>(); 
	private Spinner<String> spinnerA, spinnerB;
	private Spinner<String> spinnerC;

	private String fileName = "/Users/yunliu/MyApplications/Task/src/task.txt";
	private File file = new File(fileName);
	private File sound1 = new File("/Users/yunliu/MyApplications/Task/src/Bottle Cork.wav");
	private File sound2 = new File("/Users/yunliu/MyApplications/Task/src/Dit Hit.wav");
	private File sound3 = new File("/Users/yunliu/MyApplications/Task/src/Whistle.wav");
	private File sound4 = new File("/Users/yunliu/MyApplications/Task/src/Empty Trash.wav");
	private File sound5 = new File("/Users/yunliu/MyApplications/Task/src/Check.wav");
	private String background1 = "/Users/yunliu/MyApplications/Task/src/background1.jpg";
	private String background2 = "/Users/yunliu/MyApplications/Task/src/background2.jpg";
	
	@FXML
    private Button ButtonDelete;
	@FXML
	private Button ButtonDeleteAll;
    @FXML
    private Button ButtonNew;
    @FXML
    private Button ButtonUpdate;
    @FXML
    private Button ButtonSaveAs;
    @FXML
	private TextArea textarea;
    @FXML
	private ListView<String> listview;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private MenuItem MenuDueDate;
    @FXML
    private MenuItem MenuPriority;
    @FXML
    private MenuItem MenuStatus;
    @FXML
    private CheckBox checkbox;
    
    //-----------------------------------------------------
    // This is part is a animation that shows quotes 
    // and it initilize some methods
    //-----------------------------------------------------
    @FXML
    private Text textQuote;
    private Quotes quote = new Quotes();
    FadeTransition fade = new FadeTransition(Duration.seconds(4));
    KeyFrame frame = new KeyFrame(Duration.seconds(10), event -> {
    	fade.play();
    	textQuote.setText(quote.getQuotes());
    	});
    Timeline timeline = new Timeline(frame);
    public void initialize() throws FileNotFoundException{
    	loadFile();
    	textQuote.setText(quote.getQuotes());
    	fade.setFromValue(0);
    	fade.setToValue(100);
    	fade.setNode(textQuote);
    	timeline.setCycleCount(Animation.INDEFINITE);
    	timeline.play();
    }



    
    
    //---------------------------------------------------------------
    // methods that load file and write file
    //---------------------------------------------------------------
    public void loadFile() throws FileNotFoundException {
		taskList.clear();
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String list[];
			list = scan.nextLine().split(",");
			Task task = new Task(list[0], list[1], list[2], list[3].replace("NEXTLINE", "\n").replace("ACOMMA", ","), list[4], Boolean.parseBoolean(list[5]));
			taskList.add(task);	
		}
		listview.getItems().clear();
		for (int i=0; i<taskList.size(); i++) {
			listview.getItems().add(taskList.get(i).getName()+ "        " +taskList.get(i).getDueDate());
			listview.setStyle("-fx-font-size: 14px; -fx-font-family: 'Comic Sans MS';");
		}
		scan.close();
    }
    public void writeFile() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(fileName);
		for(Task task: taskList) {
			writer.println(task.toString());
		}
		writer.close();
    }

    
    
    
    //---------------------------------------------------------------
    // those are the Buttons methods
    //---------------------------------------------------------------
    public void ProcessNew(ActionEvent event) throws Exception {
    	SoundEffects.playSound(sound1);
		HBox boxH = new HBox();
		boxH.setSpacing(20);
		boxH.setAlignment(Pos.CENTER);
		VBox boxV = new VBox();
		boxV.setSpacing(20);
		boxV.setAlignment(Pos.CENTER);
		Scene scene = new Scene(boxV,800,400);
		
		// this is for creating the name
		Text hintName = new Text("Task Name:");
		hintName.setFont(new Font("Comic Sans MS", 14));
		TextField textName = new TextField();
		textName.setFont(new Font("Comic Sans MS", 14));
		textName.setPrefWidth(180);
		VBox boxVname = new VBox(hintName, textName);
		boxVname.setSpacing(5);
		//*******************************
		
		// this is for picking a date
		Text hintpicker = new Text("Pick a Date");
		hintpicker.setFont(new Font("Comic Sans MS", 14));
		DatePicker datepicker = new DatePicker(LocalDate.now());
		datepicker.setMaxWidth(120);
		VBox boxVpicker = new VBox(hintpicker, datepicker);
		boxVpicker.setSpacing(5);
		//*******************************
		
		//this is for picking an hour
		Text hintspinnerHour = new Text("Hour");
		hintspinnerHour.setFont(new Font("Comic Sans MS", 14));
		ObservableList<String> listA = FXCollections.observableArrayList();
        listA.addAll("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        spinnerA = new Spinner<String>(listA);
        spinnerA.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerA.setMaxWidth(80);
        spinnerA.getValueFactory().setWrapAround(true);
        spinnerA.getValueFactory().setValue(LocalTime.now().toString().substring(0, 2));
        VBox boxVspinnerHour = new VBox(hintspinnerHour, spinnerA);
		boxVspinnerHour.setSpacing(5);
        //*******************************
        
		//this is for picking a minutes
		Text hintspinnerMinute = new Text("Minute");
		hintspinnerMinute.setFont(new Font("Comic Sans MS", 14));
        ObservableList<String> listB = FXCollections.observableArrayList();
        listB.addAll("00","01","02","03","04","05","06","07","08","09");
        for (int i = 10; i<=59; i++) {
        	listB.add(i+"");
        }
        spinnerB = new Spinner<String>(listB);
        spinnerB.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerB.setMaxWidth(80);
        spinnerB.getValueFactory().setWrapAround(true);
        spinnerB.getValueFactory().setValue(LocalTime.now().toString().substring(3, 5));
        VBox boxVspinnerMinute = new VBox(hintspinnerMinute, spinnerB);
		boxVspinnerMinute.setSpacing(5);
        //*******************************
         
		//this is for picking a Priority Level
		Text hintspinnerPriority = new Text("Priority Level");
		hintspinnerPriority.setFont(new Font("Comic Sans MS", 14));
        ObservableList<String> listC = FXCollections.observableArrayList();
        listC.addAll("None", "Low", "Medium", "High");
        spinnerC = new Spinner<String>(listC);
        spinnerC.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerC.setMaxWidth(110);
        spinnerC.getValueFactory().setWrapAround(true);
        VBox boxVspinnerPriority = new VBox(hintspinnerPriority, spinnerC);
        boxVspinnerPriority.setSpacing(5);
        //*******************************

        // this is a textArea
		TextArea textDetails = new TextArea();
		textDetails.setMaxWidth(650);
		textDetails.setFont(new Font("Comic Sans MS", 14));
		textDetails.setPromptText("Details");
		//*******************************
		
		// these are two buttons
		Button buttonCancel = new Button("Cancel");
		buttonCancel.setFont(new Font("Comic Sans MS", 14));
		Button buttonAdd = new Button("Add");	
		HBox boxH2 = new HBox(buttonCancel, buttonAdd);
		buttonAdd.setFont(new Font("Comic Sans MS", 14));
		buttonAdd.setStyle("-fx-text-fill: #6cb562; -fx-background-radius: 30px");
		boxH2.setSpacing(550);
		boxH2.setAlignment(Pos.CENTER);
		//*******************************
		
		
		boxH.getChildren().addAll(boxVname, boxVpicker, boxVspinnerHour, boxVspinnerMinute, boxVspinnerPriority);
		boxV.getChildren().addAll(boxH, textDetails, boxH2);
		boxV.setBackground(BackgroundImages.getBackground(background1));
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("New Task");
		window.setScene(scene);
		window.setResizable(false);
		window.show();

		buttonCancel.setOnAction(e -> {
			window.close();
		});
		
		buttonAdd.setOnAction(e -> {
			// play the sound when the button is clicked
			try {
				SoundEffects.playSound(sound1);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			// get the value from user
			strName = textName.getText();
			date = datepicker.getValue();
			strDetails = textDetails.getText();
			priorityLevel = spinnerC.getValue();
			
			// create a time string for add to the list view
			// create a time string with only integers
			String time = spinnerA.getValue() + ":"+ spinnerB.getValue() + " " +date.getDayOfWeek()+" "+date.getMonth()+" "+date.getDayOfMonth();
			String timeInt = date.toString().replace("-", "")+spinnerA.getValue()+spinnerB.getValue();
			// create a task object and add it to the taskList
			Task task = new Task(strName, time, timeInt, strDetails, priorityLevel, false);
			taskList.add(task);
			// add every task in taskList to the list view
			listview.getItems().clear();
			for (int i=0; i<taskList.size(); i++) {
				listview.getItems().add(taskList.get(i).getName()+ "        " +taskList.get(i).getDueDate());
				listview.setStyle("-fx-font-size: 14px; -fx-font-family: 'Comic Sans MS';");
			}
			// write the task in the file 
			try {
				writeFile();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			window.close();
		});
    	
   
    }
    @FXML
    public void ProcessUpdate(ActionEvent event) throws Exception {
    	SoundEffects.playSound(sound1);
    	if (listview.getSelectionModel().isEmpty() == false) {
    		// get the indext of listed item
    		int num = listview.getSelectionModel().getSelectedIndex();
    	
    		HBox boxH = new HBox();
    		boxH.setSpacing(20);
    		boxH.setAlignment(Pos.CENTER);
    		VBox boxV = new VBox();
    		boxV.setSpacing(20);
    		boxV.setAlignment(Pos.CENTER);
    		Scene scene = new Scene(boxV,800,400);
    		
    		// this is for creating the name
    		Text hintName = new Text("Task Name:");
    		hintName.setFont(new Font("Comic Sans MS", 14));
    		TextField textName = new TextField();
    		textName.setFont(new Font("Comic Sans MS", 14));
    		textName.setText(taskList.get(num).getName());
    		textName.setPrefWidth(180);
    		VBox boxVname = new VBox(hintName, textName);
    		boxVname.setSpacing(5);
    		//*******************************
    		
    		// this is for picking a date
    		Text hintpicker = new Text("Pick a Date");
    		hintpicker.setFont(new Font("Comic Sans MS", 14));
    		DatePicker datepicker = new DatePicker(LocalDate.now());
    		datepicker.setValue(LocalDate.of(ParseTimeIntDateY(
    				      taskList.get(num)),ParseTimeIntDateM(taskList.get(num)), ParseTimeIntDateD(taskList.get(num)) ));
    		datepicker.setMaxWidth(120);
    		VBox boxVpicker = new VBox(hintpicker, datepicker);
    		boxVpicker.setSpacing(5);
    		//*******************************
    		
    		//this is for picking an hour
    		Text hintspinnerHour = new Text("Hour");
    		hintspinnerHour.setFont(new Font("Comic Sans MS", 14));
    		ObservableList<String> listA = FXCollections.observableArrayList();
            listA.addAll("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
            spinnerA = new Spinner<String>(listA);
            spinnerA.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
            spinnerA.setMaxWidth(80);
            spinnerA.getValueFactory().setValue(ParseTimeIntTimeH(taskList.get(num)));
            spinnerA.getValueFactory().setWrapAround(true);
            VBox boxVspinnerHour = new VBox(hintspinnerHour, spinnerA);
    		boxVspinnerHour.setSpacing(5);
            //*******************************
            
    		//this is for picking a minutes
    		Text hintspinnerMinute = new Text("Minute");
    		hintspinnerMinute.setFont(new Font("Comic Sans MS", 14));
            ObservableList<String> listB = FXCollections.observableArrayList();
            listB.addAll("00","01","02","03","04","05","06","07","08","09");
            for (int i = 10; i<=59; i++) {
            	listB.add(i+"");
            }
            spinnerB = new Spinner<String>(listB);
            spinnerB.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
            spinnerB.setMaxWidth(80);
            spinnerB.getValueFactory().setValue(ParseTimeIntTimeM(taskList.get(num)));
            spinnerB.getValueFactory().setWrapAround(true);
            VBox boxVspinnerMinute = new VBox(hintspinnerMinute, spinnerB);
    		boxVspinnerMinute.setSpacing(5);
            //*******************************
             
    		//this is for picking a Priority Level
    		Text hintspinnerPriority = new Text("Priority Level");
    		hintspinnerPriority.setFont(new Font("Comic Sans MS", 14));
            ObservableList<String> listC = FXCollections.observableArrayList();
            listC.addAll("None", "Low", "Medium", "High");
            spinnerC = new Spinner<String>(listC);
            spinnerC.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
            spinnerC.setMaxWidth(110);
            spinnerC.getValueFactory().setValue(taskList.get(num).getPriority());
            spinnerC.getValueFactory().setWrapAround(true);
            VBox boxVspinnerPriority = new VBox(hintspinnerPriority, spinnerC);
            boxVspinnerPriority.setSpacing(5);
            //*******************************

            // this is a textArea
    		TextArea textDetails = new TextArea();
    		textDetails.setMaxWidth(650);
    		textDetails.setFont(new Font("Comic Sans MS", 14));
    		textDetails.setText(taskList.get(num).getDetails());
    		//*******************************
    		
    		// these are two buttons
    		Button buttonCancel = new Button("Cancel");
    		buttonCancel.setFont(new Font("Comic Sans MS", 14));
    		Button buttonUpdate = new Button("Update");	
    		HBox boxH2 = new HBox(buttonCancel, buttonUpdate);
    		buttonUpdate.setFont(new Font("Comic Sans MS", 14));
    		buttonUpdate.setStyle("-fx-text-fill: #e8a035; -fx-background-radius: 30px");
    		boxH2.setSpacing(530);
    		boxH2.setAlignment(Pos.CENTER);
    		//*******************************
    		
    		
    		boxH.getChildren().addAll(boxVname, boxVpicker, boxVspinnerHour, boxVspinnerMinute, boxVspinnerPriority);
    		boxV.getChildren().addAll(boxH, textDetails, boxH2);
    		boxV.setBackground(BackgroundImages.getBackground(background2));
    		
    		Stage window = new Stage();
    		window.initModality(Modality.APPLICATION_MODAL);
    		window.setTitle("Update");
    		window.setScene(scene);
    		window.setResizable(false);
    		window.show();
    	
    		buttonCancel.setOnAction(e -> {
    			window.close();
    		});
    		
    		buttonUpdate.setOnAction(e -> {
    			try {
    				SoundEffects.playSound(sound1);
    			} catch (Exception e2) {
    				e2.printStackTrace();
    			}
    			// to get the updated values
    			strName = textName.getText();
    			date = datepicker.getValue();
    			strDetails = textDetails.getText();
    			priorityLevel = spinnerC.getValue();

    			String time = spinnerA.getValue() + ":"+ spinnerB.getValue() + " " +date.getDayOfWeek()+" "+date.getMonth()+" "+date.getDayOfMonth();
    			String timeInt =date.toString().replace("-", "")+spinnerA.getValue()+spinnerB.getValue();
    			Task task = new Task(strName, time, timeInt, strDetails, priorityLevel, taskList.get(num).getCompStatus());
    			// this is to update the task 
    			taskList.add(num, task);
    			taskList.remove(num+1);
    			// this is for updating the listview
    			listview.getItems().clear();
    			for (int i=0; i<taskList.size(); i++) {
    				listview.getItems().add(taskList.get(i).getName()+ "        " +taskList.get(i).getDueDate());
    				listview.setStyle("-fx-font-size: 14px; -fx-font-family: 'Comic Sans MS';");
    			}
    			// write the file subsequently 
    			try {
    				writeFile();
    			} catch (FileNotFoundException e1) {
    				e1.printStackTrace();
    			}
    			window.close();
    		});
    	}
    	
    }
    @FXML
    public void ProcessSaveAs(ActionEvent event) throws Exception {
    	SoundEffects.playSound(sound1);
    	if (listview.getItems().isEmpty() == false) {
	    	try {
	    	Stage window = new Stage();
	    	FileChooser chooser = new FileChooser();
	    	File file = chooser.showSaveDialog(window);
	    	String str = file.getAbsolutePath() + ".txt";
	    	PrintWriter writer = new PrintWriter(str);
	    	String compStatus = "";
	    	for (int i=0; i<taskList.size(); i++) {
	    		if (taskList.get(i).getCompStatus() == true) {
	    			compStatus = "Completed!";
	    		}
	    		else {
	    			compStatus = "Not Completed";
	    		}
	    		writer.println((i+1)+": " + taskList.get(i).getName()+" ("+compStatus+")"+"\n"
	    					 +"Due Date: "+ taskList.get(i).getDueDate()+"\n"
	    					 +"Priority Level: " + taskList.get(i).getPriority()+"\n"
	    					 +"Details:\n"+ taskList.get(i).getDetails()+"\n");
	    	}
	    	writer.close();}
	    	catch(Exception e3) {
	    		System.out.println("Error");
	    	}
    	}
    }
 
    //---------------------------------------------------------------
    // Other Methods
    //---------------------------------------------------------------
    @FXML
    public void ProcessMouseClick(MouseEvent event) throws Exception {
    	SoundEffects.playSound(sound2);
    	if (listview.getSelectionModel().isEmpty() == false) {
    		// get the index of listed task
    		// the index is the same with the task index in taskList
	    	int num = listview.getSelectionModel().getSelectedIndex();
	    	// this is for showing the task detail in the text area
	    	strDetails = taskList.get(num).getDetails();
	    	textarea.setText(strDetails);
	    	textarea.setFont(new Font("Comic Sans MS", 14));
	    	// this is for showing the priotity level of the task
	    	label2.setText("Priority Level:  " + taskList.get(num).getPriority());
	    	// this is foe showing the completion status
	    	if (taskList.get(num).getCompStatus() == false) {
	    		checkbox.setSelected(false);
	    		checkbox.setText("Not Completed");
	    		checkbox.setStyle("-fx-text-fill: black");
	    	}
	    		else {
	    			checkbox.setSelected(true);
	    			checkbox.setText("Completed !");
	    			checkbox.setStyle("-fx-text-fill: lightgreen");
	    	}
    		
    	}
    }
    @FXML
    public void ProcessCheckBox(ActionEvent event) throws Exception {
    	SoundEffects.playSound(sound2);
    	if (listview.getItems().isEmpty() == false) {
    		int num = listview.getSelectionModel().getSelectedIndex();
    		if (checkbox.isSelected()) {
    			SoundEffects.playSound(sound5);
    			taskList.get(num).setCompStatus(true);
    			checkbox.setText("Completed !");
    			checkbox.setStyle("-fx-text-fill: lightgreen");
    		}
    			else {
    				taskList.get(num).setCompStatus(false);
    				checkbox.setText("Not Completed");
    				checkbox.setStyle("-fx-text-fill: black;");
    			}
    		}
    	// write the file because the completion status is updated
    	try {
			writeFile();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
    }
        
    //---------------------------------------------------------------
    // those are two delete buttons
    //---------------------------------------------------------------
    @FXML
    public void ProcessDelete(ActionEvent event) throws Exception {
    	SoundEffects.playSound(sound1);
    	if (listview.getItems().isEmpty() == false && listview.getSelectionModel().isEmpty() == false) {
    		// creating an alert window when every dete button is clicked
    		Alert alert  = new Alert(AlertType.CONFIRMATION);
    		alert.setHeaderText("Are you Sure?");
    		alert.setHeight(50);
    		alert.setWidth(100);
    		alert.setResizable(false);
    		Optional <ButtonType> choice = alert.showAndWait();
    		// if statement to make sure the deletion won't happen until the OK button is clicked
    		if (choice.get() == ButtonType.OK) {
    			SoundEffects.playSound(sound4);
    			int num = listview.getSelectionModel().getSelectedIndex();
    			// to remove the selected task from the list
    			// and remove the task from taskList as well
    			listview.getItems().remove(num);
    			taskList.remove(num);
    			textarea.setText("");
    			// update the file as well
    			try {
    				writeFile();
    			} catch (FileNotFoundException e1) {
    				e1.printStackTrace();
    			}
    			// set the checkbox and label to default
    			checkbox.setText("Not Completed");
    			checkbox.setStyle("-fx-text-fill: black");
    			checkbox.setSelected(false);
    			label2.setText("Priority Level: ----  ");
    		}
    	}
		
    }
    @FXML
    public void ProcessDeleteAll(ActionEvent event) throws Exception {
    	SoundEffects.playSound(sound1);
    	if (listview.getItems().isEmpty() == false) {
    		Alert alert  = new Alert(AlertType.CONFIRMATION);
    		alert.setHeaderText("You are going to delete every tasks");
    		alert.setHeight(50);
    		alert.setWidth(100);
    		alert.setResizable(false);
    		Optional <ButtonType> choice = alert.showAndWait();
    		if (choice.get() == ButtonType.OK) {
    			SoundEffects.playSound(sound4);
    			// remove everything in taskList and listView
    			listview.getItems().clear();
    			taskList.clear();
    			textarea.setText("");
    			// clear the file
    			try {
    				writeFile();
    			} catch (FileNotFoundException e1) {
    				e1.printStackTrace();
    			}
    			// set the checkbox and label to default 
    			checkbox.setText("Not Completed");
    			checkbox.setStyle("-fx-text-fill: black");
    			checkbox.setSelected(false);
    			label2.setText("Priority Level: ----  ");
    		}
    	}	
    }
 
    //---------------------------------------------------------------
    // Those are functions that sort the task
    //---------------------------------------------------------------
    @FXML
    public void DueDateSort(ActionEvent event) throws Exception {
    	SoundEffects.playSound(sound3);
    	listview.getItems().clear();
    	for (int j=0; j<taskList.size(); j++) {
    		for (int i = 0; i<taskList.size()-1; i++) {
    			if (Long.parseLong(taskList.get(i).getTimeLong()) 
    				> Long.parseLong(taskList.get(i+1).getTimeLong())) {
    				Collections.swap(taskList, i, i+1);
    			}
    		}
    	}
		for (int i=0; i<taskList.size(); i++) {
			listview.getItems().add(taskList.get(i).getName()+ "        " +taskList.get(i).getDueDate());
		}

    }
    @FXML
    public void PrioritySort(ActionEvent event) throws Exception {
    	SoundEffects.playSound(sound3);
    	ArrayList<Task> sort = new ArrayList<Task>();
    	listview.getItems().clear();
    	for (int i = 0; i<taskList.size(); i++) {
    		if (taskList.get(i).getPriority().equals("High")) {
    			sort.add(taskList.get(i));
    		}
    	}
    	for (int i = 0; i<taskList.size(); i++) {
    		if (taskList.get(i).getPriority().equals("Medium")) {
    			sort.add(taskList.get(i));
    		}
    	}
    	for (int i = 0; i<taskList.size(); i++) {
    		if (taskList.get(i).getPriority().equals("Low")) {
    			sort.add(taskList.get(i));
    		}
    	}
    	for (int i = 0; i<taskList.size(); i++) {
    		if (taskList.get(i).getPriority().equals("None")) {
    			sort.add(taskList.get(i));
    		}
    	}
    	taskList = sort;
    	for (int i=0; i<taskList.size(); i++) {
			listview.getItems().add(taskList.get(i).getName()+ "        " +taskList.get(i).getDueDate());
		}
    	
    	
    	
    }
    @FXML
    public void StatusSort(ActionEvent event) throws Exception {
    	SoundEffects.playSound(sound3);
    	ArrayList<Task> sort = new ArrayList<Task>();
    	// clear the listview inorder to add the taskes in taskList again with a new order
    	listview.getItems().clear();
    	for (int i = 0; i<taskList.size(); i++) {
    		if (taskList.get(i).getCompStatus() == false) {
    			sort.add(taskList.get(i));
    		}
    	}
    	for (int i = 0; i<taskList.size(); i++) {
    		if (taskList.get(i).getCompStatus() == true) {
    			sort.add(taskList.get(i));
    		}
    	}
    	// this is to override the taskList
    	taskList = sort;
    	
    	for (int i=0; i<taskList.size(); i++) {
			listview.getItems().add(taskList.get(i).getName()+ "        " +taskList.get(i).getDueDate());
		}

    }

    //---------------------------------------------------------------
    // those are the methods that parse the stored time value
    //---------------------------------------------------------------
	public int ParseTimeIntDateY(Task t) {
		int dateY = Integer.parseInt(t.getTimeLong().substring(0,4));
		return dateY;
	}
	public int ParseTimeIntDateM(Task t) {
		int dateM = Integer.parseInt(t.getTimeLong().substring(4, 6));
		return dateM;
	}
	public int ParseTimeIntDateD(Task t) {
		int dateD = Integer.parseInt(t.getTimeLong().substring(6, 8));
		return dateD;
	}
	public String ParseTimeIntTimeH(Task t) {
		String TimeH = t.getTimeLong().substring(8, 10);
		return TimeH;
	}
	public String ParseTimeIntTimeM(Task t) {
		String TimeH = t.getTimeLong().substring(10, 12);
		return TimeH;
	}}
