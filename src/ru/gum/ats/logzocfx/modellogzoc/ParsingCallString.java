package ru.gum.ats.logzocfx.modellogzoc;

/**
 * Created by chupiraov on 16.02.2016.
 */
public class ParsingCallString {
    private String line;
    private Integer countR = 0;
    private boolean isGreen = false;
    private String date;
    private String start;
    private Integer seconds;
    private String stop;
    private String duration;
    private Integer dDurSec;
    private String flag;
    private String kod;

    private String dialledN;
    private String callingN;
    private String inRou;

    private String inR,outR;
    private String inLim;

    private String outRou;
    private String outLim;

    private Integer iCurHour = 0;

    private Boolean bIncom;
    private Boolean bOutgoing;
    private Boolean bInternal;

    public ParsingCallString(int r, String linePars, CallObject callOb) {

        this.line = linePars;
        int len = line.length();
        if (len >= 108) {
            this.countR = r;
            callOb.setCallid(countR);
        }
        date = line.substring(0, 6);
        String dateGG = date.substring(0, 2);
        String dateMM = date.substring(2, 4);
        String dateDD = date.substring(4, 6);
        date = "20" + dateGG + "-" + dateMM + "-" + dateDD;
        int iYY =parseToDigit("20"+dateGG);
        int iMM =parseToDigit(dateMM);
        int iDD =parseToDigit(dateDD);
        if(iMM>=1 && iMM<13){
            callOb.setDate(date,iYY,iMM,iDD);
        }else System.out.println("Error iMM: "+line);
        //-------------------------------------
        start = line.substring(7, 13);
        String startHH = start.substring(0, 2);
        String startMM = start.substring(2, 4);
        String startSS = start.substring(4, 6);
        start = startHH + ":" + startMM + ":" + startSS;
        int sH = parseToDigit(startHH);
        int sM =parseToDigit(startMM);
        int sS = parseToDigit(startSS);
        callOb.setStart(start,sH,sM,sS);
        //-------------------------------------
        seconds = sH * 3600 + sM * 60 + sS;
        //-------------------------------------
        stop = line.substring(16, 22);
        String stopHH = stop.substring(0, 2);
        String stopMM = stop.substring(2, 4);
        String stopSS = stop.substring(4, 6);
        stop = stopHH + ":" + stopMM + ":" + stopSS;
        int eH = parseToDigit(stopHH);
        int eM =parseToDigit(stopMM);
        int eS = parseToDigit(stopSS);
        callOb.setStop(stop,eH,eM,eS);
        //--------------------------------------
        int pHH = parseToDigit(stopHH);
        iCurHour = pHH + 1;
        callOb.setiCurHour(iCurHour);
        //-------------------------------------
        duration = line.substring(25, 31);
        String durH = duration.substring(0, 2);
        String durMM = duration.substring(1, 3);
        String durSS = duration.substring(3, 5);
        int hd = parseToDigit("0"+durH);
        int md = parseToDigit(durMM);
        int sd = parseToDigit(durSS);
        duration = durH + ":" + durMM + ":" + durSS;
        callOb.setDuration(duration,hd,md,sd);
        //-------------------------------------
        int dH = parseToDigit(durH);
        int dMM = parseToDigit(durMM);
        int dSS = parseToDigit(durSS);
        dDurSec = dH * 3600 + dMM * 60 + dSS;
        callOb.setiDurSec(dDurSec);
        //-------------------------------------
        flag = line.substring(31, 33);
        callOb.setAccess1(flag);
        //-------------------------------------
        kod = line.substring(36, 39);
        callOb.setAccess2(kod);
        //-------------------------------------
        dialledN = line.substring(45, 65);
        String diallN = dialledN.trim();
        callOb.setDialledN(diallN);
        //-------------------------------------
        callingN = line.substring(66, 76);
        String callN = callingN.trim();
        callOb.setCallingN(callN);
        //-------------------------------------
        if (len == 108) {
            bInternal = true;
            bIncom = false;
            bOutgoing = false;
            isGreen = true;
            callOb.setInLim("");
            callOb.setInRou("");
            callOb.setOutLim("");
            callOb.setOutRou("");
            callOb.setbInternal(bInternal);
            callOb.setbIncom(bIncom);
            callOb.setbOutgoing(bOutgoing);
        } else if (len == 118) {
            inRou = line.substring(109, 112);
            callOb.setInRou(inRou);
            //-------------------------------------
            inLim = line.substring(112, 118);
            callOb.setInLim(inLim);
            //-------------------------------------
            callOb.setOutRou("");
            callOb.setOutLim("");
            bInternal = false;
            bIncom = true;
            bOutgoing = false;
            isGreen = true;
            callOb.setbInternal(bInternal);
            callOb.setbIncom(bIncom);
            callOb.setbOutgoing(bOutgoing);
        } else if (len == 128) {
            inRou = line.substring(109, 112);
            inR = inRou.substring(0, 1);
            //-------------------------------------
            if (inR != "0") {
                bIncom = false;
                callOb.setInRou("");
                callOb.setInLim("");
            } else if(inR == "0"){
                bIncom = true;
                callOb.setInRou(inRou);
                inLim = line.substring(112, 118);
                callOb.setInLim(inLim);
            }
            outRou = line.substring(119, 122);
            outR = outRou.substring(0, 1);
            callOb.setOutRou(outRou);
            //-------------------------------------
            outLim = line.substring(122, 128);
            callOb.setOutLim(outLim);
            //-------------------------------------
            bInternal = false;
            bOutgoing = true;
            isGreen = true;
            callOb.setbInternal(bInternal);
            callOb.setbOutgoing(bOutgoing);
            callOb.setbIncom(false);
        } else {
            bInternal = false;
            bIncom = false;
            bOutgoing = false;
            isGreen = false;
            callOb.setbInternal(bInternal);
            callOb.setbIncom(bIncom);
            callOb.setbOutgoing(bOutgoing);
        }
    }//End

    public Boolean getbIncom() {
        return bIncom;
    }

    public Boolean getbOutgoing() {
        return bOutgoing;
    }

    public Boolean getbInternal() {
        return bInternal;
    }

    public boolean getGreen() {
        return isGreen;
    }

    public int parseToDigit(String string){
        int digits =0;
        try {
            digits = Integer.parseInt(string.trim());

        }catch (NumberFormatException nfe){
            System.out.println("Error line: "+this.line);
        }
        return digits;
    }
}
