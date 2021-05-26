public class Event {

    private int numEvents;
    private String eventType[];
    private String eventFullName[];
    private String eventStudioOwner[];
    private int eventYear[];
    private int eventDuration[];
    private int eventLicenseFee[];
    private static final int LIMIT_EVENTS = 20;
    private static int payLicenseFee;
    private static String payStudio;
    private static String watchDemoGroup;
    private static int watchPercentage;
    private static String watchStream;
    private static String watchEventName;
    private static int watchEventYear;
    private int selectEvent;
    String[] tokens = TestCaseReader.getTokens();

    public static int getLimitEvents() {
        return LIMIT_EVENTS;
    }

    public static int getPayLicenseFee() {
        return payLicenseFee;
    }

    public static String getPayStudio() {
        return payStudio;
    }

    public static String getWatchDemoGroup() {
        return watchDemoGroup;
    }

    public static int getWatchPercentage() {
        return watchPercentage;
    }

    public static String getWatchStream() {
        return watchStream;
    }

    public static String getWatchEventName() {
        return watchEventName;
    }

    public static int getWatchEventYear() {
        return watchEventYear;
    }


    public Event() {
        numEvents = 0;
        payLicenseFee = 0;
        payStudio = "";
        eventType = new String[LIMIT_EVENTS];
        eventFullName = new String[LIMIT_EVENTS];
        eventStudioOwner = new String[LIMIT_EVENTS];
        eventYear = new int[LIMIT_EVENTS];
        eventDuration = new int[LIMIT_EVENTS];
        eventLicenseFee = new int[LIMIT_EVENTS];
    }

    public int crevent() {
        if (tokens[0].equals("create_event")) {System.out.println("create_event_acknowledged");
            if (numEvents >= LIMIT_EVENTS)
                eventType[numEvents] = tokens[1];
            eventFullName[numEvents] = tokens[2];
            eventYear[numEvents] = Integer.parseInt(tokens[3]);
            eventDuration[numEvents] = Integer.parseInt(tokens[4]);
            eventStudioOwner[numEvents] = tokens[5];
            eventLicenseFee[numEvents] = Integer.parseInt(tokens[6]);
            numEvents++;
        }
        return numEvents;
    }

    public void license() {
        // The streaming service must license the event from the studio
        for (selectEvent = 0; selectEvent < numEvents; selectEvent++) {
            if (eventFullName[selectEvent].equals(tokens[2]) && eventYear[selectEvent] == Integer.parseInt(tokens[3])) {
                payStudio = eventStudioOwner[selectEvent];
                payLicenseFee = eventLicenseFee[selectEvent];
            }
        }
    }
        public void wtevent () {
            if (tokens[0].equals("watch_event")) {
                System.out.println("watch_event_acknowledged");
                String watchDemoGroup = tokens[1];
                int watchPercentage = Integer.parseInt(tokens[2]);
                String watchStream = tokens[3];
                String watchEventName = tokens[4];
                int watchEventYear = Integer.parseInt(tokens[5]);
            }
        }

        public void disevent () {
            if (tokens[0].equals("display_events")) {System.out.println("display_events_acknowledged");

                for (selectEvent = 0; selectEvent < numEvents; selectEvent++) {
                     System.out.println(eventType[selectEvent] + "," + eventFullName[selectEvent] + "," + eventYear[selectEvent] + "," + eventDuration[selectEvent] + "," + eventStudioOwner[selectEvent] + "," + eventLicenseFee[selectEvent]);
                }

            }

    }
}
