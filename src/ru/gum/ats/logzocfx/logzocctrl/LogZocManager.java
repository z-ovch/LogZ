package ru.gum.ats.logzocfx.logzocctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.gum.ats.logzocfx.modellogzoc.CallObject;
import ru.gum.ats.logzocfx.modellogzoc.FilterData;
import ru.gum.ats.logzocfx.modellogzoc.ParsingCallString;
import ru.gum.ats.logzocfx.start.MainApp;
import ru.gum.ats.logzocfx.tool.Filtering;
import ru.gum.ats.logzocfx.tool.ParseDates;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by chupiraov on 27.04.2016.
 */
public class LogZocManager {

    private static final String PROJECTS_WORK_FILE_LOG_ZOC = "\\Projects\\Work_File\\LogZoc\\";
    @FXML
    private AnchorPane logZocManager;

    @FXML
    private MenuBar mbLogZocId;

    @FXML
    private TextArea txtAreaId;

    @FXML
    private MenuItem miParseDatesId;

    @FXML
    private MenuItem miOpenBaseId;

    @FXML
    private MenuItem miOpenDotId;
    @FXML
    private MenuItem miSaveId;
    @FXML
    private MenuItem miSaveAsId;
    @FXML
    private MenuItem miCloseId;
    @FXML
    private MenuItem miPrintId;
    @FXML
    private MenuItem miExitId;
    @FXML
    private Menu mActionId;
    @FXML
    private MenuItem miSetFilterId;
    @FXML
    private MenuItem miFiltrationId;

    @FXML
    private void initialize(){
        initLoader();
    }

    private String path;
    private final  Desktop desktop;
    private boolean bSave;
    private FilterCtrl filterCtrl;
    private FilterData filterData;
    private Stage filterCtrlStage;
    private Parent fxmlFilter;
    private FXMLLoader fxmlLoader = new FXMLLoader();

    private void initLoader(){
        fxmlLoader.setLocation(getClass().getResource("../viewfxml/filter.fxml"));
        try {
            fxmlFilter = fxmlLoader.load();
            filterCtrl = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    {
        desktop = Desktop.getDesktop();
    }
    BufferedReader inputStream = null;
    PrintWriter outputStream = null;
    java.util.List<String> arrayList = null;

    //id="miClose" fx:id="miCloseId" ______onAction="#actionClose" text="Close"
    public void actionClose(ActionEvent actionEvent) {
        miCloseId.setOnAction((ActionEvent ae)->{
            txtAreaId.clear();
        });
    }

    //id="miOpen" fx:id="miOpenId" ______onAction="#actionOpen" text="Open"
    public void actionOpenBase() {
        File fdir = new File(PROJECTS_WORK_FILE_LOG_ZOC);
        FileChooser fileCh = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileCh.getExtensionFilters().add(extFilter);
        fileCh.setTitle("Open TXT files");
        miOpenBaseId.setOnAction((ActionEvent ae) -> {
            File openfile = fileCh.showOpenDialog(MainApp.getPrimaryStage());
            if (null != openfile) {
                    //openFile(openfile);
                try {
                    txtAreaId.setText(readFileBase(openfile));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //id="miParseDates" fx:id="miParseDatesId" onAction="#actionParseDates" text="Parse_Dates"
    public void actionParseDates() {
        FileChooser fileCh = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileCh.getExtensionFilters().add(extFilter);
        fileCh.setTitle("Open TXT files");
        miParseDatesId.setOnAction((ActionEvent ae)->{
            File openfile = fileCh.showOpenDialog(MainApp.getPrimaryStage());
            path = openfile.getParent();
            if(null != openfile){
                String strTuTxtArr = parsingFileDates(openfile, null);
                printTxtArea(strTuTxtArr);
            }
        });
    }
    //method is called from actionParseDates().
    // Method returns String & method fills stream outputStream
    private String parsingFileDates(File fileIn, File fileOut) {
        StringBuilder stringBuffer = new StringBuilder();
        String nameFile = null;
        inputStream = null;
        outputStream = null;

        try {

            inputStream = new BufferedReader(new FileReader(fileIn));
            Integer r =0;
            String linetext;
            ParseDates parseDates = new ParseDates();
            while ((linetext = inputStream.readLine()) != null) {
                //stringBuffer.append(text);
                r++;
                CallObject callOb = new CallObject();
                ParsingCallString parsCallStr = new ParsingCallString(r,linetext,callOb);
                parseDates.setParseDates(callOb);
                if(parseDates.isMyDate()){
                    nameFile = path +"\\"+parseDates.getMyName();
                    writeFile(nameFile);
                    outputStream.println(linetext);
                    stringBuffer.append("Operations with the newly created file: "+nameFile+"\n");
                }else if(!parseDates.isMyDate()& !parseDates.isFileClose()){
                    outputStream.println(linetext);
                }else if(parseDates.isFileClose() & !parseDates.isMyDate()){
                    outputStream.close();
                    nameFile = path +"\\"+parseDates.getMyName();
                    writeFile(nameFile);
                    outputStream.println(linetext);
                    stringBuffer.append("With the next file operation: "+nameFile+"\n");
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stringBuffer.toString();
    }//end parsingFileDates(File fileIn, File fileOut)

    //<MenuItem id="miOpenDot" fx:id="miOpenDotId" ...onAction="#actionOpenDot" text="OpenDot" />
    public void actionOpenDot() {
        FileChooser fileCh = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileCh.getExtensionFilters().add(extFilter);
        fileCh.setTitle("Open TXT files");
        miOpenDotId.setOnAction((ActionEvent ae)->{
            File openfile = fileCh.showOpenDialog(MainApp.getPrimaryStage());
            path = openfile.getParent();
            if(null != openfile){
                String content = processingFileDot(openfile);
                txtAreaId.setText(content);
            }
        });
    }

    //Method is called from  actionOpenBase()
    // Method returns String
    private String readFileBase(File file) throws IOException {
        StringBuilder stringBuffer = new StringBuilder();
        inputStream = null;

        try {

            inputStream = new BufferedReader(new FileReader(file));
            Integer r =0;
            String linetext;
            while ((linetext = inputStream.readLine()) != null) {
                //stringBuffer.append(text);
                r++;
                //stringBuffer.append(linetext+"\n");
                stringBuffer.append(linetext+"*"+r + "\n");
            }
            inputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stringBuffer.toString();
    }//end readFileBase(File file)

    private void writeFile(String filename){
        try {
            File fileout = new File(filename);
            outputStream = new PrintWriter(new FileWriter(fileout));
            boolean exists = fileout.createNewFile();
            if(exists){
                System.out.println("Operations with existing file "+fileout);
            }else {
                System.out.println("Operations with the newly created file"+fileout);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            System.out.println("Exception I/O for file_out");
        }
    }
    //Method is called from  actionOpenDot()
    // Method returns String
    private String processingFileDot(File fileIn){
        StringBuilder stringBuffer = new StringBuilder();
        String nameFile = null;
        inputStream = null;
        //outputStream = null;

        try {

            inputStream = new BufferedReader(new FileReader(fileIn));
            Integer r =0;
            String linetext;

            while ((linetext = inputStream.readLine()) != null) {
                //stringBuffer.append(text);
                r++;
                CallObject callOb = new CallObject();
                ParsingCallString parsCallStr = new ParsingCallString(r,linetext,callOb);
                String strDot = callOb.tuDotString();
                stringBuffer.append(strDot+"\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stringBuffer.toString();
    }//end processingFileDot(File fileIn)

    //id="miSave" fx:id="miSaveId" ________onAction="#actionSave" text="Save"
    public void actionSave(ActionEvent actionEvent) {

    }

    //id="miSaveAs" fx:id="miSaveAsId" _______onAction="#actionSaveAs" text="Save As"
    public void actionSaveAs() {
        File fdir = new File(PROJECTS_WORK_FILE_LOG_ZOC);
        FileChooser fileCh = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilter;
        extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileCh.setTitle("Save TXT Files As");
        //fileCh.setInitialDirectory(fdir);
        miSaveAsId.setOnAction((ActionEvent ae)->{
            final File fileSave = fileCh.showSaveDialog(MainApp.getPrimaryStage());
            String nameFile = fileSave.getAbsolutePath();

            if(null != fileSave){
                arrListToFile(nameFile);
                //saveFile(strTxt,fileSave);
            }
        });
    }

    public void arrListToFile(String namefile){
        try {
            outputStream = new PrintWriter(new FileWriter(namefile));
            if(!arrayList.isEmpty()){
                Iterator<String> it = arrayList.iterator();
                while (it.hasNext()) {
                    String el = (String) it.next();
                    outputStream.println(el);
                }
                outputStream.close();
            }else {
                System.out.println("File "+namefile+ "is empty!");
                outputStream.close();
            }
        }catch (IOException ex){
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //id="miPrint" fx:id="miPrintId" _______onAction="#actionPrint" text="Print"
    public void actionPrint(ActionEvent actionEvent) {
    }
    //id="miExit" fx:id="miExitId" _______onAction="#actionExit" text="Exit"
    public void actionExit(ActionEvent actionEvent) {
    }
    //id="mAction" fx:id="mActionId" _______onAction="#actionAction" text="Action"
    public void actionAction(ActionEvent actionEvent) {
    }

    //id="miSetFiltre" fx:id="miSetFilterId" _____onAction="#actionSetFiltre" text="Set Filter"
    public void actionSetFiltre() {
        miSetFilterId.setOnAction((ActionEvent ae)->{
            if(filterCtrlStage == null){
                filterCtrlStage = new Stage();
                filterCtrlStage.setTitle("The filter settings for the selection of telephone calls.");
                filterCtrlStage.setScene(new Scene(fxmlFilter));
                filterCtrlStage.setMinWidth(600);
                filterCtrlStage.setMinHeight(420);
                filterCtrlStage.initModality(Modality.WINDOW_MODAL);
                filterCtrlStage.initOwner(MainApp.getPrimaryStage());
            }
            filterCtrlStage.showAndWait();
        });
    }
    //id="miFiltration" fx:id="miFiltrationId" _______onAction="#actionFiltration" text="Filtration"
    public void actionFiltration() {
        arrayList = new ArrayList<String>();
        FileChooser fileCh = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileCh.getExtensionFilters().add(extFilter);
        fileCh.setTitle("Open TXT files");
        miFiltrationId.setOnAction((ActionEvent ae)->{
            File openfile = fileCh.showOpenDialog(MainApp.getPrimaryStage());
            filterData = filterCtrl.getFilterData();
            String calls = readFileFiltration(openfile, filterData);
            txtAreaId.setText(calls);
        });
    }

    private String readFileFiltration(File openfile, FilterData filtData) {
        StringBuilder stringBuffer = new StringBuilder();
        Filtering filtering;
        inputStream = null;

        try {

            inputStream = new BufferedReader(new FileReader(openfile));
            Integer r =0;
            String linetext;
            while ((linetext = inputStream.readLine()) != null) {
                //stringBuffer.append(text);
                r++;
                CallObject callOb = new CallObject();
                ParsingCallString parsCallStr = new ParsingCallString(r,linetext,callOb);
                if(parsCallStr.getGreen()) {
                    //stringBuffer.append(linetext+"\n");
                    //stringBuffer.append(callOb.toString()+"\n");
                    String strCallOb = callOb.tuDotString();
                    if(!filtData.equals(null)) {
                        filtering = new Filtering(callOb, filtData);

                        if (filtering.itsMyCall()) {
                            stringBuffer.append(strCallOb + "\n");
                            arrayList.add(strCallOb);
                        }
                    }else {
                        stringBuffer.append(strCallOb + "\n");
                        arrayList.add(strCallOb);
                    }
                }
            }
            inputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stringBuffer.toString();
    }



    private void openFile(File file){
        EventQueue.invokeLater(()->{
            try {
               desktop.open(file);
            }catch (IOException ex){
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public static void printString(Object s){
        //System.out.println(n.toString());
        try{
            System.out.println(new String(s.toString().getBytes("windows-1251"), "windows-1252"));
        }catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }
    //method for printing in the field txtArea
    public void printTxtArea(String str){
        txtAreaId.setText(str + "\n");
    }
}
