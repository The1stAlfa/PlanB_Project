/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys;

/**
 *
 * @author AI-Saac
 */
public enum Month {
    JANUARY ("01",31,"JAN"),
    FEBRUARY ("02",28,"FEB"),
    MARCH ("03",31, "MAR"),
    APRIL ("04",30, "APR"),
    MAY ("05",31, "MAY"),
    JUNE ("06",30, "JUN"),
    JULY ("07",31,"JUL"),
    AUGUST ("08",31,"AUG"),
    SEPTEMBER ("09",30,"SEP"),
    OCTUBER ("10",31,"OCT"),
    NOVEMBER ("11",30,"NOV"),
    DECEMBER ("12",31,"DEC");
    
    private final String value;
    private int days;
    private final String abbreviation;

    private Month(String value, int days, String abbreviation){
        this.value = value;
        this.days = days;
        this.abbreviation = abbreviation;
    }
    
    public String getValue(){
        return this.value;
    }
    
    public int getDays(){
        return this.days;
    }
            
    public String getAbbreviation(){
        return this.abbreviation;
}
}
