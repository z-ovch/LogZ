package ru.gum.ats.logzocfx.tool;

import ru.gum.ats.logzocfx.modellogzoc.CallObject;

import java.sql.Date;

/**
 * Created by chupiraov on 18.05.2016.
 */
public class ParseDates {
    private Date oldDate;
    private String myDate;
    private Date newDate;
    private boolean bMyDate, bFileClose;

    public ParseDates(){
    }

    public void setParseDates(CallObject callOb){
        newDate = callOb.getDateSQL();
        if (oldDate==null){
            oldDate = newDate; //Ustanavlivaem staruyu datu dlja sravnenija
            myDate = callOb.getDate();//Zapolnjaem datu dlja imeni file
            bMyDate = true; //Flag ust
        }else if(oldDate.equals(newDate)){
            bMyDate = false;
            bFileClose= false;
        }else if(!oldDate.equals(newDate)){
            bMyDate = false;
            oldDate = newDate;
            myDate = callOb.getDate();
            bFileClose = true;
        }
    }

    public boolean isMyDate(){
        return bMyDate;
    }

    public boolean isFileClose(){return bFileClose;}

    public String getMyName(){
        return "logzoc_"+myDate.replace(':','_')+".txt";
    }
}
