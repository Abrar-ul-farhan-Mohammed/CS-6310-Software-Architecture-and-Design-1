public class StreamingService {

    private int numStreams;
    private String streamShortName[];
    private String streamLongName[];
    private int streamSubscription[];
    private int streamCurrentRevenue[];
    private int streamPreviousRevenue[];
    private int streamTotalRevenue[];
    private int streamLicensing[];
    private static final int LIMIT_STREAMS = 10;
    private static int watchSubscriptionFee;
    private static int streamIndex;
    private static int selectStream;
    String[] tokens = TestCaseReader.getTokens();

    public static int getLimitStreams() {
        return LIMIT_STREAMS;
    }

    public static int getWatchSubscriptionFee() {
        return watchSubscriptionFee;
    }

    public static int getStreamIndex() {
        return streamIndex;
    }

    public StreamingService() {
        numStreams = 0;
        streamShortName = new String[LIMIT_STREAMS];
        streamLongName = new String[LIMIT_STREAMS];
        streamSubscription = new int[LIMIT_STREAMS];
        streamCurrentRevenue = new int[LIMIT_STREAMS];
        streamPreviousRevenue = new int[LIMIT_STREAMS];
        streamTotalRevenue = new int[LIMIT_STREAMS];
        streamLicensing = new int[LIMIT_STREAMS];
    }

    public int streammeth() {

        if (tokens[0].equals("create_stream")) {System.out.println("create_stream_acknowledged");
            if (numStreams >= LIMIT_STREAMS)
                streamShortName[numStreams] = tokens[1];
            streamLongName[numStreams] = tokens[2];
            streamSubscription[numStreams] = Integer.parseInt(tokens[3]);
            numStreams++;
        }
        return numStreams;
    }

    public void sslicense() {
        // The streaming service must license the event from the studio

        for (selectStream = 0; selectStream < numStreams; selectStream++) {
            if (streamShortName[selectStream].equals(tokens[1])) {
                streamLicensing[selectStream] = streamLicensing[selectStream] + Event.getPayLicenseFee();
            }
        }
    }
    public int iddemo() {
        // Identify the streaming service & the subscription fee
        int watchSubscriptionFee = 0;
        int streamIndex = -1;
        for (selectStream = 0; selectStream < numStreams; selectStream++) {
            if (streamShortName[selectStream].equals(Event.getWatchStream())) {
                watchSubscriptionFee = streamSubscription[selectStream];
                streamIndex = selectStream;
            }
        }
        return streamIndex;
    }


    public void strfunds(){
        streamCurrentRevenue[streamIndex] = streamCurrentRevenue[streamIndex] + DemographicGroup.getWatchViewingCost();
        for (selectStream = 0; selectStream < numStreams; selectStream++) {
            streamTotalRevenue[selectStream] = streamTotalRevenue[selectStream] + streamCurrentRevenue[selectStream];
            streamPreviousRevenue[selectStream] = streamCurrentRevenue[selectStream];
            streamCurrentRevenue[selectStream] = 0;
        }
    }

    public void disstream(){ //selectstream changed to 0 from -1
        if (tokens[0].equals("display_stream")) {System.out.println("display_stream_acknowledged");
        selectStream = -1;
            for (int findItem = 0; findItem < numStreams; findItem++) {
                if (streamShortName[findItem].equals(tokens[1])) {
                    selectStream = findItem;
                }
            }
            if (selectStream < 0)
            System.out.println("stream," + streamShortName[selectStream] + "," + streamLongName[selectStream]);
            System.out.println("subscription," + streamSubscription[selectStream]);
            System.out.println("current_period," + streamCurrentRevenue[selectStream]);
            System.out.println("previous_period," + streamPreviousRevenue[selectStream]);
            System.out.println("total," + streamTotalRevenue[selectStream]);
            System.out.println("licensing," + streamLicensing[selectStream]);

        }
    }
}
