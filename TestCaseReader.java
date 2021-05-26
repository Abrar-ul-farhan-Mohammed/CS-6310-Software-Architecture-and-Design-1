import java.util.Scanner;

public class TestCaseReader {
    private static String[] tokens;
    private static int watchGroupStreams[][];
    private int monthTimeStamp;
    private int yearTimeStamp;

    //getters and setters
    public static String[] getTokens() {
        return tokens;
    }

    public static int[][] getWatchGroupStreams() {
        return watchGroupStreams;
    }
    public static void setWatchGroupStreams(int[][] watchGroupStreams) {
        TestCaseReader.watchGroupStreams = watchGroupStreams;
    }

    //constructor
    public TestCaseReader() {
        watchGroupStreams = new int[DemographicGroup.getLimitDemos()][StreamingService.getLimitStreams()];
        monthTimeStamp = 10;
        yearTimeStamp = 2020;
    }

    public void processInstructions(Boolean verboseMode) {
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        //String[] tokens;
        final String DELIMITER = ",";

        while (true) {
            try {
                // Determine the next command and echo it to the monitor for testing purposes
                wholeInputLine = commandLineInput.nextLine();
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("> " + wholeInputLine);


                if (tokens[0].equals("create_demo")) {
                    DemographicGroup dg = new DemographicGroup();
                    dg.demographic();
                    continue;
                }
                else if (tokens[0].equals("create_studio")) {
                    Studio st = new Studio();
                    st.studiometh();
                    continue;
                }
                else if (tokens[0].equals("create_event")) {
                    Event ev = new Event();
                    ev.crevent();
                    continue;
                }
                else if (tokens[0].equals("create_stream")) {
                    StreamingService ss = new StreamingService();
                    ss.streammeth();
                    continue;
                }

                else if (tokens[0].equals("offer_movie") || tokens[0].equals("offer_ppv")) {
                    Offer of = new Offer();
                    of.offermeth();
                    continue;
                }

                Event ev2 = new Event();
                StreamingService ss2 = new StreamingService();
                Studio st2 = new Studio();
                ev2.license();
                ss2.sslicense();
                st2.sslicense();

                if (tokens[0].equals("watch_event")) {
                    Event ev3 = new Event();
                    ev2.wtevent();
                }

                DemographicGroup dg2 = new DemographicGroup();
                dg2.iddemo();

                StreamingService ss3 = new StreamingService();
                ss3.iddemo();

                Offer ofr = new Offer();
                ofr.offerpr();

                dg2.vcost();
                dg2.funds();
                ss3.strfunds();
                st2.stfund();
                //ofr.removermvppv();
                //watchGroupStreams = new int[DemographicGroup.getLimitDemos()][StreamingService.getLimitStreams()];

                if (tokens[0].equals("display_demo")){
                    DemographicGroup dg3 = new DemographicGroup();
                    dg3.disdemo();
                }
                else if(tokens[0].equals("display_events")) {
                    ev2.disevent();
                }
                else if (tokens[0].equals("display_stream")){
                    StreamingService ss4 = new StreamingService();
                    ss4.disstream();
                }
                else if (tokens[0].equals("display_studio")){
                    st2.disstudio();
                }
                else if (tokens[0].equals("display_offers")){
                    ofr.disoffer();
                }

                if (tokens[0].equals("next_month")) {
                    if (verboseMode) { System.out.println("next_month_acknowledged"); }
                    if (monthTimeStamp == 12) { yearTimeStamp++; }
                    monthTimeStamp = (monthTimeStamp % 12) + 1;

                } else if (tokens[0].equals("display_time")) {
                    if (verboseMode) { System.out.println("display_time_acknowledged"); }
                    System.out.println("time," + monthTimeStamp + "," + yearTimeStamp);

                } else if (tokens[0].equals("stop")) {
                    break;
                } else {
                    if (verboseMode) { System.out.println("command_" + tokens[0] + "_NOT_acknowledged"); }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        if (verboseMode) { System.out.println("stop_acknowledged"); }
        commandLineInput.close();
    }

}

