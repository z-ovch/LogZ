package ru.gum.ats.logzocfx.tool;

import ru.gum.ats.logzocfx.modellogzoc.CallObject;
import ru.gum.ats.logzocfx.modellogzoc.FilterData;
import java.sql.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by chupiraov on 25.05.2016.
 */
public class Filtering {
    private boolean level1;
    private boolean level2;
    private boolean level3;
    private boolean level4;
    private boolean level5;
    private boolean level6;
    private boolean resultFiltration;

    public Filtering(CallObject call, FilterData fdata){
        //////level1////////
        Date dateCall = call.getDateSQL();
        if(fdata.isAllDate()){
            level1 = true;
        }else if(fdata.isOneDate()){
            Date dateOne = fdata.getControlDate();
            if(dateCall.equals(dateOne)){
                level1 = true;
            }else {level1 = false;}
        }else if(fdata.isRangeDates()){
            Date start = fdata.getStartDate();
            Date end = fdata.getEndDate();
            boolean after = dateCall.after(start);
            boolean befor = dateCall.before(end);
            boolean eq = dateCall.equals(start)| dateCall.equals(end);
            if((after & befor)| eq ){
                level1=true;
            }else level1 = false;
        }else {
            level1 = false;
            resultFiltration =false;
            System.out.println("filtering date is not set");
        }
        ///////level2///////
        if(fdata.isSpecificNumbers()){
            Set<String> numbers = fdata.getNumbers();
            String dialledN = call.getDialledN();
            String callingN = call.getCallingN();
            if(!numbers.isEmpty()){
                Iterator it = numbers.iterator();
                while (it.hasNext()){
                    String element = (String)it.next();
                    if(element.equals(dialledN)|element.equals(callingN)){
                        level2 = true;
                        break;
                    }else level2 = false;
                }
            }else {
                level2= false;
                resultFiltration = false;
                System.out.println("Set numbers is Empty!");
            }
        }else level2 = true;
        //////level3///only external//////
        if(fdata.isOnlyExternal()){
            boolean bIn = call.isIncom();
            boolean bOut = call.isOutgoing();
            if(bIn | bOut){level3 = true;}
            else {level3 = false;}
        }else level3 = true;
        ///////level4//route selection//////
        if(fdata.isRouBool()){
            String rouIn = call.getInRou();
            String rouOut = call.getOutRou();
            String rouCtrl = fdata.getRou();
            if(rouCtrl.equals(rouIn) | rouCtrl.equals(rouOut)){
                level4 = true;
            }else level4 = false;
        }else level4 = true;
        ////////level5//lim selection//////
        if(fdata.isLimBool()){
            String limIn = call.getInLim();
            String limOut = call.getOutLim();
            String limCtrl = fdata.getLim();
            if(limCtrl.equals(limIn)|limCtrl.equals(limOut)){
                level5 = true;
            }else level5 = false;
        }else level5 = true;
        ////////level6//onlyNonZero////
        if(fdata.isOnlyNonZero()){
            Integer iDuratSec = call.getdDurSec();
            if(iDuratSec>0){
                level6 = true;
            }else level6 = false;
        }else level6 = true;
        resultFiltration = level1&level2&level3&level4&level5&level6;
    }
    public boolean itsMyCall(){
        return resultFiltration;
    }
}
