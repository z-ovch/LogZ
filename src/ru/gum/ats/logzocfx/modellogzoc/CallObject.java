package ru.gum.ats.logzocfx.modellogzoc;

import ru.gum.ats.logzocfx.tool.AddNull;
import ru.gum.ats.logzocfx.tool.AddingChar;
import ru.gum.ats.logzocfx.tool.Arrow;
import ru.gum.ats.logzocfx.tool.ReplacCh;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by chupiraov on 17.02.2016.
 */
public class CallObject {
    private Integer callid;
    private String date;
    private Date dateSQL;
    private String start;
    private Time startSQL;
    private String stop;
    private Time stopSQL;
    private String duration;
    private Time duratSQL;
    private String access1;
    private String access2;
    private String dialledN;
    private String callingN;
    private String inRou;
    private String inLim;
    private String outRou;
    private String outLim;
    private Integer iDurSec = 0;
    private Integer iCurHour = 0;
    static final String HOO = "<-+";
    private String arrow;
    private boolean bInternal;
    private boolean bIncom;
    private boolean bOutgoing;
    static final String INSERT1 = "insert into logzoc (callid, date, dstart, dstop, duration, access1, access2, ";
    static final String INSERT2 = "dialledN, callingN, inRou, inLim, outRou, outLim, iDurSec, iCurHour)";
    static final String VALUE = INSERT1 + INSERT2 + "\n" + "values ";

    public CallObject() {
    }

    CallObject(ResultSet rs) throws SQLException {
        setCallid(rs.getInt(1));
        setDateSQL(rs.getDate(2));
        setStartSQL(rs.getTime(3));
        setStopSQL(rs.getTime(4));
        setDuratSQL(rs.getTime(5));
        setAccess1(rs.getString(6));
        setAccess2(rs.getString(7));
        setDialledN(rs.getString(8));
        setCallingN(rs.getString(9));
        setInRou(rs.getString(10));
        setInLim(rs.getString(11));
        setOutRou(rs.getString(12));
        setOutLim(rs.getString(13));
        setiDurSec(rs.getInt(14));
    }

    public Integer getCallid() {
        return callid;
    }
    public Date getDateSQL(){return dateSQL;}

    public void setCallid(Integer id) {
        this.callid = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date,int y, int m,int d) {
        this.date = date;
        LocalDate ld = LocalDate.of(y,m,d);
        this.dateSQL = Date.valueOf(ld);
    }
    public void setDateSQL(Date dateSQL){
        this.dateSQL = dateSQL;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start,int h,int m,int s) {
        this.start = start;
        LocalTime lt = LocalTime.of(h,m,s);
        this.startSQL = Time.valueOf(lt);
    }

    public void setStartSQL(Time startSQL){
        this.startSQL = startSQL;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop,int h,int m,int s) {
        this.stop = stop;
        LocalTime lt = LocalTime.of(h,m,s);
        this.stopSQL = Time.valueOf(lt);
    }

    public void setStopSQL(Time stopSQL){
        this.stopSQL = stopSQL;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration,int h, int m, int s) {
        this.duration = duration;
        LocalTime lt = LocalTime.of(h,m,s);
        this.duratSQL = Time.valueOf(lt);
    }

    public void setDuratSQL(Time duratSQL){
        this.duratSQL = duratSQL;
    }

    public String getAccess1() {
        return access1;
    }

    public void setAccess1(String access1) {
        this.access1 = access1;
    }

    public String getAccess2() {
        return access2;
    }

    public void setAccess2(String access2) {
        this.access2 = access2;
    }

    public String getDialledN() {
        return dialledN;
    }

    public void setDialledN(String dialledN) {
        this.dialledN = dialledN;
    }

    public String getCallingN() {
        return callingN;
    }

    public void setCallingN(String callingN) {
        this.callingN = callingN;
    }

    public String getInRou() {
        return inRou;
    }

    public void setInRou(String inRou) {
        this.inRou = inRou;
    }

    public String getInLim() {
        return inLim;
    }

    public void setInLim(String inLim) {
        this.inLim = inLim;
    }

    public String getOutRou() {
        return outRou;
    }

    public void setOutRou(String outRou) {
        this.outRou = outRou;
    }

    public String getOutLim() {
        return outLim;
    }

    public void setOutLim(String outLim) {
        this.outLim = outLim;
    }

    public Integer getdDurSec() {
        return iDurSec;
    }

    public void setdDurSec(String dDurSec) {
        this.iDurSec = Integer.parseInt(dDurSec);
    }

    public void setiDurSec(Integer iDurSec) {
        this.iDurSec = iDurSec;
    }

    public Integer getiCurHour() {
        return iCurHour;
    }

    public void setiCurHour(Integer iCurHour) {
        this.iCurHour = iCurHour;
    }

    public void setbInternal(boolean bInternal) {
        this.bInternal = bInternal;
    }

    public void setbIncom(boolean bIncom) {
        this.bIncom = bIncom;
    }

    public void setbOutgoing(boolean bOutgoing) {
        this.bOutgoing = bOutgoing;
    }

    public boolean isInternal() {
        return this.bInternal;
    }

    public boolean isIncom() {
        return this.bIncom;
    }

    public boolean isOutgoing() {
        return this.bOutgoing;
    }

    public Integer getDurSec() {
        return this.iDurSec;
    }

    @Override
    public String toString() {
        return callid +
                "; " + dateSQL +
                "; " + startSQL +
                "; " + stopSQL +
                "; " + duratSQL +
                "; " + '\'' + access1 + '\'' +
                "; " + '\'' + access2 + '\'' +
                "; " + '\'' + dialledN + '\'' +
                "; " + '\'' + callingN + '\'' +
                "; " + '\'' + inRou + '\'' +
                "; " + '\'' + inLim + '\'' +
                "; " + '\'' + outRou + '\'' +
                "; " + '\'' + outLim + '\'' +
                "; " + iDurSec +
                "; " + iCurHour;
    }

    public String toTableBD(){
        return "("+ callid +
                ", " + dateSQL +
                ", " + startSQL +
                ", " + stopSQL +
                ", " + duratSQL +
                ", " + '\'' + access1 + '\'' +
                ", " + '\'' + access2 + '\'' +
                ", " + '\'' + dialledN + '\'' +
                ", " + '\'' + callingN + '\'' +
                ", " + '\'' + inRou + '\'' +
                ", " + '\'' + inLim + '\'' +
                ", " + '\'' + outRou + '\'' +
                ", " + '\'' + outLim + '\'' +
                ", " + iDurSec +
                ", " + iCurHour +
                ")";
    }

    public String tuDotString(){
        AddingChar strAddDot;
        ReplacCh strReplaceCh;
        Arrow arrowFun;
        AddNull addNullFun;
        strAddDot = (k, ch, ln)->{
            int  dc, len;
            String st;
            len = ln.length();
            dc = k - len;
            if (dc > 0) {return ln.replace(' ', ch);}
            else return ln;

        };

        strReplaceCh = (n, str)->{
            String dots = "....................";
            String st,res;
            Integer len,dc;
            st = str.trim();
            len = st.length();
            dc = n - len;
            res = dots.substring(0, dc);
            if(dc == n){
                return res;
            }else return res+st;
        };

        arrowFun = (b1, b2, b3) -> {
            if((b1 == true)&(b2 == false)&(b3 ==false)){arrow = "(--)";}
            if((b1 == false)&(b2 == true)&(b3 ==false)){arrow = "<---";}
            if((b1 == false)&(b2 == false)&(b3 ==true)){arrow = "--->";}
            if((b1 == false)&(b2 == true)&(b3 ==true)){arrow = "<-->";}
            return arrow;
        };

        addNullFun = (id, size)->{
            String strnull = "000000";
            String strnumb = String.valueOf(id);
            Integer len = strnumb.length();
            Integer dc = size - len;
            return strnull.substring(0,dc)+id;
        };
        return addNullFun.addNull(callid,6)+ " "
                + dateSQL+ " "
                + startSQL+ " "
                + stopSQL+ " "
                + duration+" "
                + strReplaceCh.replacCh(2,access1)+" "
                + strReplaceCh.replacCh(3,access2)+" "
                + strReplaceCh.replacCh(20,dialledN)+" "
                + HOO+" "
                + strReplaceCh.replacCh(10,callingN)+" "
                + arrowFun.getArrow(bInternal,bIncom,bOutgoing)+" "
                + strReplaceCh.replacCh(3, inRou)+" "
                + strReplaceCh.replacCh(6, inLim)+" "
                + strReplaceCh.replacCh(3, outRou)+" "
                + strReplaceCh.replacCh(6, outLim)+" "
                + addNullFun.addNull(iDurSec,5)+" "
                + addNullFun.addNull(iCurHour,2);
    }
}
