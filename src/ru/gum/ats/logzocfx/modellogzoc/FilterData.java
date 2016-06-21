package ru.gum.ats.logzocfx.modellogzoc;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

/**
 * Created by chupiraov on 11.04.2016.
 */
public class FilterData {
    private static boolean allDate;
    private static boolean ctrlDate;
    private static boolean rangeDates;
    private static Date controlDate;
    private static Date startDate;
    private static Date endDate;
    private static boolean specificNumbers;
    private static Set<String> numbers;

    private static boolean onlyExternal;

    private static boolean rouBool;
    private static String rou;
    private static boolean limBool;
    private static String lim;
    private static boolean onlyNonZero;

    public FilterData(){
        //
    }

    public static boolean isAllDate() {
        return allDate;
    }

    public static void setAllDate(boolean allDate) {
        FilterData.allDate = allDate;
    }

    public static boolean isOneDate() {
        return ctrlDate;
    }

    public static void setOneDate(boolean oneDate) {
        FilterData.ctrlDate = oneDate;
    }

    public static boolean isRangeDates() {
        return rangeDates;
    }

    public static void setRangeDates(boolean rangeDates) {
        FilterData.rangeDates = rangeDates;
    }

    public static Date getControlDate() {
        return controlDate;
    }

    public static void setControlDate(LocalDate ldCtrl) {
        FilterData.controlDate = Date.valueOf(ldCtrl);
    }

    public static Date getStartDate() {
        return startDate;
    }

    public static void setStartDate(LocalDate ldStart) {
        FilterData.startDate = Date.valueOf(ldStart);
    }

    public static Date getEndDate() {
        return endDate;
    }

    public static void setEndDate(LocalDate ldEnd) {
        FilterData.endDate = Date.valueOf(ldEnd);
    }

    public static boolean isSpecificNumbers() {
        return specificNumbers;
    }

    public static void setSpecificNumbers(boolean specificNumbers) {
        FilterData.specificNumbers = specificNumbers;
    }

    public static Set<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(Set<String> numbers) {
        FilterData.numbers = numbers;
    }

    public static boolean isOnlyExternal() {
        return onlyExternal;
    }

    public static void setOnlyExternal(boolean onlyExternal) {
        FilterData.onlyExternal = onlyExternal;
    }

    public static boolean isRouBool() {
        return rouBool;
    }

    public static void setRouBool(boolean rouBool) {
        FilterData.rouBool = rouBool;
    }

    public static String getRou() {
        return rou;
    }

    public static void setRou(String rou) {
        FilterData.rou = rou;
    }

    public static boolean isLimBool() {
        return limBool;
    }

    public static void setLimBool(boolean limBool) {
        FilterData.limBool = limBool;
    }

    public static String getLim() {
        return lim;
    }

    public static void setLim(String lim) {
        FilterData.lim = lim;
    }

    public static boolean isOnlyNonZero() {
        return onlyNonZero;
    }

    public static void setOnlyNonZero(boolean onlyNonZero) {
        FilterData.onlyNonZero = onlyNonZero;
    }
}

