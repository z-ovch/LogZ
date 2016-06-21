package ru.gum.ats.logzocfx.logzocctrl;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.gum.ats.logzocfx.modellogzoc.FilterData;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
/**
 * Created by chupiraov on 17.05.2016.
 */
public class FilterCtrl {
    @FXML
    private CheckBox cbFlagAll;
    @FXML
    private CheckBox cbFlagOne;
    @FXML
    private CheckBox cbFlagRange;
    @FXML
    private DatePicker datePicControl;
    @FXML
    private DatePicker datePicStart;
    @FXML
    private DatePicker datePicEnd;
    @FXML
    private CheckBox cbFlagNumber;
    @FXML
    private TextField tfNumber;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnClearAll;
    @FXML
    private CheckBox cbFlagExternal;
    @FXML
    private CheckBox cbFlagRoute;
    @FXML
    private ComboBox<String> routeCombo;
    @FXML
    private CheckBox cbFlagLim;
    @FXML
    private ComboBox<String> limCombo;
    @FXML
    private CheckBox cbFlagNonZero;
    @FXML
    private Label selectionMsg;
    @FXML
    private Label dateMsg;
    @FXML
    private Label numberMsg;
    @FXML
    private Label routeMsg;
    @FXML
    private Label limMsg;
    @FXML
    private Label nonzeroMsg;


    FilterData filterData;

    private boolean bAll;
    private boolean bCtrl;
    private boolean bRng;
    private LocalDate ctrlDate;
    private LocalDate stDate;
    private LocalDate enDate;
    private String number;
    private boolean bNum;
    private Set<String> numbers = new HashSet<>();
    private boolean bExt;
    private boolean bRou;
    private boolean bLim;
    private boolean bNonZ;
    private String route;
    private String lim;


    public FilterCtrl(){
        this.filterData = new FilterData();

    }
    public FilterData getFilterData(){return this.filterData;}

    //<CheckBox fx:id="cbFlagAll" ... onAction="#actionAllDate"
    public void actionAllDate() {
        BooleanProperty bAllProp = cbFlagAll.selectedProperty();
        bAllProp.addListener((ov, oldValue, newValue) -> {
            if(newValue != null && newValue && !oldValue)
            {
                printMessage("Your Selection: All Date");
                cbFlagOne.setDisable(true);
                bAll =newValue;
                cbFlagRange.setDisable(true);
                bCtrl=false;
                bRng= false;
                printDate("All");
                datePicControl.setDisable(true);
                datePicStart.setDisable(true);
                datePicEnd.setDisable(true);
            }else if(oldValue==true && newValue == false){
                printMessage("You deselect all dates");
                cbFlagOne.setDisable(false);
                cbFlagRange.setDisable(false);
                datePicControl.setDisable(false);
                datePicStart.setDisable(false);
                datePicEnd.setDisable(false);
            }
        });
    }


    //<CheckBox fx:id="cbFlagOne" ... onAction="#actionOneDate"
    public void actionOneDate() {
        BooleanProperty bOne = cbFlagOne.selectedProperty();
        bOne.addListener((ov, oldValue, newValue) -> {
            if(newValue != null && newValue && !oldValue){
                printMessage("Your Selection: One Date");
                cbFlagAll.setDisable(true);
                bAll=false;
                bRng=false;
                bCtrl=newValue;
                cbFlagRange.setDisable(true);
                datePicStart.setDisable(true);
                datePicEnd.setDisable(true);
                printDate("All");
            }else if(oldValue==true && newValue == false){
                printMessage("You deselect One Date");
                cbFlagAll.setDisable(false);
                cbFlagRange.setDisable(false);
                datePicStart.setDisable(false);
                datePicEnd.setDisable(false);
            }
        });
    }
    // <CheckBox fx:id="cbFlagRange" ... onAction="#actionRangeDates"
    public void actionRangeDates() {
        BooleanProperty bRangeProp = cbFlagRange.selectedProperty();
        bRangeProp.addListener((ov, oldValue, newValue) -> {
            if(newValue != null && newValue && !oldValue)
            {
                printMessage("Your Selection: Range Date");
                cbFlagAll.setSelected(false);
                cbFlagOne.setSelected(false);
                bAll=false;
                bCtrl=false;
                bRng=newValue;
                datePicControl.setDisable(true);
            }else if(oldValue==true && newValue == false){
                printMessage("You deselect Range Date");
                filterData.setRangeDates(newValue);
                datePicControl.setDisable(false);
            }
        });
    }
    // <DatePicker id="controlDate" ... onAction="#actionControlDate"
    public void actionControlDate() {
        printMessage("ActionControlDate");
        datePicControl.setOnAction((EventHandler) event -> {
            ctrlDate = datePicControl.getValue();
            printDate("Ctrl");
            //filterData.setControlDate(fid.setDates(locDate));
            printMessage("Select LocalDate: "+ctrlDate);
        });
    }
    //<DatePicker id="startDate" ... onAction="#actionStartDate"
    public void actionStartDate() {
        printMessage("ActionStartDate");
        datePicStart.setOnAction((EventHandler) event -> {
            stDate = datePicStart.getValue();
            printDate("Range");
            //filterData.setStartDate(fid.setDates(locDate));
            printMessage("Select StartDate: "+stDate);
        });
    }
    //<DatePicker id="endDate" ... onAction="#actionEndDate"
    public void actionEndDate() {
        printMessage("ActionEndDaate");
        datePicEnd.setOnAction((EventHandler) event -> {
            enDate = datePicEnd.getValue();
            printDate("Range");
            //filterData.setEndDate(fid.setDates(locDate));
            printMessage("Select EndDate: "+enDate);
        });
    }
    //<CheckBox fx:id="cbFlagNumber" ... onAction="#actionSpecificNumber"
    public void actionSpecificNumber() {
        BooleanProperty bNumber = cbFlagNumber.selectedProperty();
        bNumber.addListener((ov, oldValue, newValue) -> {
            if(newValue != null && newValue && !oldValue)
            {
                printMessage("Your Selection: Specific Numbers");
                bNum = newValue;
                //filterData.setSpecificNumbers(newValue);
            }else if(oldValue==true && newValue == false){
                printMessage("You deselect Specific Numbers");
                //filterData.setSpecificNumbers(newValue);
                bNum = newValue;
            }
        });
    }
    // <TextField id="dirId" fx:id="tfNumber" onAction="#actionTextNumber">
    public void actionTextNumber() {
        tfNumber.setPromptText("Enter your number here!");

    }
    //<Button fx:id="btnAdd" mnemonicParsing="false" onAction="#actionAddNumber"
    public void actionAddNumber() {
        printMessage("Click on Add Button");
        btnAdd.setOnAction((ActionEvent ae)->{
            if(tfNumber.getText() != null && !tfNumber.getText().isEmpty()) {
                printMessage("Your number is " + tfNumber.getText());
                number = tfNumber.getText();
                if (number != null && !number.isEmpty()) {
                    numbers.add(number);
                }
            }
            numbersToMessage();
        });
    }
    // <Button fx:id="btnClear" mnemonicParsing="false" onAction="#actionClear"
    public void actionClear() {
        printMessage("Clear out from set numbers");
        btnClear.setOnAction((ActionEvent ae)->{
            numbers.clear();
            tfNumber.clear();
            numbersToMessage();
        });
    }
    // <CheckBox fx:id="cbFlagExternal" mnemonicParsing="false" onAction="#actionOnlyExtern"
    public void actionOnlyExtern() {
        BooleanProperty bExtProp = cbFlagExternal.selectedProperty();
        bExtProp.addListener((ov, oldValue, newValue) -> {
            if(newValue != null && newValue && !oldValue)
            {
                printMessage("Your Selection: OnlyExternNumbers");
                bExt = newValue;
                //filterData.setOnlyExternal(newValue);
            }else if(oldValue==true && newValue == false){
                printMessage("You deselect OnlyExternNumbers");
                bExt = newValue;
                //filterData.setOnlyExternal(newValue);
            }
        });
    }
    //<CheckBox fx:id="cbFlagRoute" mnemonicParsing="false" onAction="#actionPartRoute"
    public void actionPartRoute() {
        BooleanProperty bRouteProp = cbFlagRoute.selectedProperty();
        bRouteProp.addListener((ov, oldValue, newValue) -> {
            if(newValue != null && newValue && !oldValue) {
                printMessage("Your Selection: Particular Route");
                bRou = newValue;
                filterData.setRouBool(newValue);
            }else if(oldValue==true && newValue == false){
                printMessage("You deselect Particular Route");
                bRou = newValue;
                filterData.setRouBool(newValue);
            }
        });
    }
    // <ComboBox id="cssRouteCombo" fx:id="routeCombo" cache="true" editable="true" onAction="#actionRouteCombo"
    public void actionRouteCombo() {
        printRoute("action event routeCombo");
        routeCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue!=null && newValue==null){
                route = oldValue;
                printRoute("selct route = "+route);
            }else if(oldValue!=null && newValue!= null) {
                route = newValue;
                printRoute("selct route = "+route);
            }
        });
    }

    //<ComboBox id="cssLimCombo" fx:id="limCombo" editable="true" onAction="#actionLimCombo"
    public void actionLimCombo() {
        printLim("action event limCombo");
        limCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue!=null && newValue==null){
                lim = oldValue;
                printLim("selct Lim = "+lim);
            }else if(oldValue!=null && newValue!= null) {
                lim = newValue;
                printLim("selct Lim = "+lim);
            }
        });
    }
    //<CheckBox fx:id="cbFlagLim" mnemonicParsing="false" onAction="#actionPartLim"
    public void actionPartLim() {
        BooleanProperty bLimProp = cbFlagLim.selectedProperty();
        bLimProp.addListener((ov, oldValue, newValue) -> {
            if(newValue != null && newValue && !oldValue) {
                printMessage("Your Selection: Particular Lim");
                bLim = newValue;
                filterData.setRouBool(newValue);
            }else if(oldValue==true && newValue == false){
                printMessage("You deselect Particular Lim");
                bLim = newValue;
                filterData.setRouBool(newValue);
            }
        });
    }
    //<CheckBox fx:id="cbFlagNonZero" mnemonicParsing="false" onAction="#actionNonZero"
    public void actionNonZero() {
        BooleanProperty bNonZeroProp = cbFlagNonZero.selectedProperty();
        String msg ="";
        bNonZeroProp.addListener(((ov, oldValue, newValue) -> {
            if(newValue != null && newValue && !oldValue) {
                printMessage("Your Selection: NonZero lengtch calls");
                bNonZ = newValue;
                if(bNonZ){printZero("true");}
            }else if(oldValue==true && newValue == false){
                printMessage("You deselect: NonZero lengtch calls");
                bNonZ = newValue;
                if(!bNonZ){printZero("false");}
            }
        }));
    }
    //<Button id="btnSubmit" fx:id="btnSubmit" ... onAction="#actionSubmit"
    public void actionSubmit() {
        printMessage("Data transfer to the main subject FilterData");
        btnSubmit.setOnAction((ActionEvent ae)->{
            filterData.setAllDate(bAll);
            filterData.setOneDate(bCtrl);
            filterData.setRangeDates(bRng);
            if(bCtrl) {
                filterData.setControlDate(ctrlDate);
            }
            if(bRng) {
                filterData.setStartDate(stDate);
                filterData.setEndDate(enDate);
            }
            filterData.setSpecificNumbers(bNum);
            if(bNum) {
                filterData.setNumbers(numbers);
            }
            filterData.setOnlyExternal(bExt);
            filterData.setRouBool(bRou);
            if(bRou) {
                filterData.setRou(route);
            }
            filterData.setLimBool(bLim);
            if(bLim) {
                filterData.setLim(lim);
            }
            filterData.setOnlyNonZero(bNonZ);
        });
    }
    //<Button id="btnClearAll" fx:id="btnClearAll" ... onAction="#actionClearAll"
    public void actionClearAll() {
        printMessage("Clean up all the data and start over");
        btnClearAll.setOnAction((ActionEvent ae)->{
            bAll = false;
            cbFlagAll.setSelected(false);
            cbFlagAll.setDisable(false);
            bCtrl =false;
            cbFlagOne.setSelected(false);
            cbFlagOne.setDisable(false);
            bRng = false;
            cbFlagRange.setSelected(false);
            cbFlagRange.setDisable(false);
            ctrlDate = null;
            stDate = null;
            enDate = null;
            datePicControl.setDisable(false);
            datePicStart.setDisable(false);
            datePicEnd.setDisable(false);
            printDate("Cleaned all");
            cbFlagNumber.setSelected(false);
            tfNumber.clear();
            number = null;
            numbers.clear();
            printNumber("Cleaned all");
            bExt = false;
            cbFlagExternal.setSelected(false);
            bRou = false;
            cbFlagRoute.setSelected(false);
            printRoute("Cleaned all");
            bLim = false;
            cbFlagLim.setSelected(false);
            printLim("Cleaned all");
            bNonZ =false;
            cbFlagNonZero.setSelected(false);
            route = null;
            lim = null;
            printZero("Cleaned all");
            if(filterData != null){
                filterData = null;
                filterData =new FilterData();
            }
        });
    }

    public void printMessage(String message)
    {
        // Set the Text of the Label
        selectionMsg.setText(message);
    }

    public void printDate(String message){
        String msg = "";
        if(message.equals("All")){
            msg = "bAll = "+bAll+";";
        }else if(message.equals("Ctrl")){
            msg = "bCtrl = "+bCtrl+"; "+"ControlDate = "+ctrlDate+";";
        }else  if(message.equals("Range")){
            msg = "bRange = "+bRng+"; "+"StartDate = "+stDate+"; "+"EndDate = "+enDate+";";
        }else if(message.equals("Cleaned all")){
            msg = message;
        }
        dateMsg.setText(msg);
    }

    public void printNumber(String message){
        numberMsg.setText(message);
    }

    public void printRoute(String message){
        String msg;
        if(bRou){
            msg = "bRou = true; ";
        }else{
            msg = "bRou = false; ";
        }
        if(message.equals("Cleaned all")){msg ="";}
        routeMsg.setText(msg + message);
    }

    public void printLim(String message){
        String msg;
        if(bLim){
            msg = "bLim = true; ";
        }else{
            msg = "bLim = false; ";
        }
        if(message.equals("Cleaned all")){msg ="";}
        limMsg.setText(msg + message);
    }

    public void printZero(String str){
        String msg = "bNonZ = ";
        if(str.equals("true")){
            nonzeroMsg.setText(msg + str);
        }else if(str.equals("false")){
            nonzeroMsg.setText(msg + str);
        }else if(str.equals("Cleaned all")){
            nonzeroMsg.setText(str);
        }
    }

    private void numbersToMessage(){
        String msgS = "The set numbers contains the elements: {";
        String msg = "";
        String msgE = "}";
        Iterator it = numbers.iterator();
        while (it.hasNext()){
            String elem = (String) it.next();
            if(!msg.isEmpty()) {
                msg = msg + ", " + elem;
            }else msg = elem;
        }
        printNumber(msgS+msg+msgE);
    }


    public void initialize(URL location, ResourceBundle resources) {
        routeCombo.getItems().removeAll(routeCombo.getItems());
        routeCombo.getItems().addAll("011","012","066","088","099","025");
        routeCombo.getSelectionModel().select("011");

        limCombo.getItems().removeAll(limCombo.getItems());
        limCombo.getItems().addAll("001","002","003");
        limCombo.getSelectionModel().select("001");
    }
}
