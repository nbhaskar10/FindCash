package sdkcash.findcash;

/**
 * Created by Abby on 10/16/2016.
 */
public class Current_Requests_Book {
    private String myID;
    private String moneyAmountHave;
    private String moneyAmountWant;
    private String airportFrom;
    private String airportTo;
    private String currenyHave;
    private String currenyWant;
    private String timeArriving;
    private String timeDeparting;
    private String meetingLocation;


    public Current_Requests_Book(){}


    public String getMyID() {
        return myID;
    }

    public String getMoneyAmountHave() {
        return moneyAmountHave;
    }

    public void setMoneyAmountHave(String moneyAmountHave) {
        this.moneyAmountHave = moneyAmountHave;
    }

    public String getMoneyAmountWant() {
        return moneyAmountWant;
    }

    public void setMoneyAmountWant(String moneyAmountWant) {
        this.moneyAmountWant = moneyAmountWant;
    }

    public Current_Requests_Book(String myID, String moneyAmountHave, String moneyAmountWant, String airportFrom, String airportTo, String currenyHave, String currenyWant, String timeArriving, String timeDeparting, String meetingLocation) {
        super();
        this.myID = myID;
        this.moneyAmountHave = moneyAmountHave;
        this.moneyAmountWant = moneyAmountWant;
        this.airportFrom = airportFrom;
        this.airportTo = airportTo;
        this.currenyHave = currenyHave;
        this.currenyWant = currenyWant;
        this.timeArriving = timeArriving;
        this.timeDeparting = timeDeparting;

        this.meetingLocation = meetingLocation;
    }

    public void setMyID (String myID) {
        this.myID =myID;
    }


    public String getAirportTo() {
        return airportTo;
    }

    public void setAirportTo(String airport) {
        this.airportTo = airport;
    }

    public String getAirportFrom() {
        return airportFrom;
    }

    public void setAirportFrom(String airport) {
        this.airportFrom = airport;
    }

    public String getCurrenyHave() {
        return currenyHave;
    }

    public void setCurrenyHave(String currenyHave) {
        this.currenyHave = currenyHave;
    }

    public String getCurrenyWant() {
        return currenyWant;
    }

    public void setCurrenyWant(String currenyWant) {
        this.currenyWant = currenyWant;
    }

    public String getTimeArriving() {
        return timeArriving;
    }

    public void setTimeArriving(String timeArriving) {
        this.timeArriving = timeArriving;
    }

    public String getTimeDeparting() {
        return timeDeparting;
    }

    public void setTimeDeparting(String timeDeparting) {
        this.timeDeparting = timeDeparting;
    }

    public String getMeetingLocation() {
        return meetingLocation;
    }

    public void setMeetingLocation(String meetingLocation) {
        this.meetingLocation = meetingLocation;
    }


}
