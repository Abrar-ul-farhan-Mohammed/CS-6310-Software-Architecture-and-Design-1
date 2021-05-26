public class DemographicGroup {

    private int numDemos;
    private String[] demoShortName;
    private String demoLongName[];
    private int demoAccounts[];
    private int demoCurrentSpending[];
    private int demoPreviousSpending[];
    private int demoTotalSpending[];
    private String demoWatchingHistory[];
    private static final int LIMIT_DEMOS = 10;
    private static int watchViewingCost;
    private static int selectDemo;
    private static int watchViewerCount;
    private static int demoIndex;
    String[] tokens = TestCaseReader.getTokens();

    public static int getLimitDemos() {
        return LIMIT_DEMOS;
    }

    public static int getWatchViewingCost() {
        return watchViewingCost;
    }


    public DemographicGroup() {
        numDemos = 0;
        demoShortName = new String[LIMIT_DEMOS];
        demoLongName = new String[LIMIT_DEMOS];
        demoAccounts = new int[LIMIT_DEMOS];
        demoCurrentSpending = new int[LIMIT_DEMOS];
        demoPreviousSpending = new int[LIMIT_DEMOS];
        demoTotalSpending = new int[LIMIT_DEMOS];
        demoWatchingHistory = new String[LIMIT_DEMOS];
        for (int index = 0; index < LIMIT_DEMOS; index++) {
            demoWatchingHistory[index] = "#";
        }
    }

    public int demographic() {
        if (tokens[0].equals("create_demo")) {System.out.println("create_demo_acknowledged");}
            if (numDemos >= LIMIT_DEMOS)
                demoShortName[numDemos] = tokens[1];
            demoLongName[numDemos] = tokens[2];
            demoAccounts[numDemos] = Integer.parseInt(tokens[3]);
            numDemos++;
            return numDemos;
        }

    public int iddemo() {
        // Identify the demo group & the number of viewers affected-demoindex changed to 0 from -1
        watchViewerCount = 0;
        demoIndex = 0;
        for (selectDemo = 0; selectDemo < numDemos; selectDemo++) {
            if (demoShortName[selectDemo].equals(Event.getWatchDemoGroup())) {
                watchViewerCount = demoAccounts[selectDemo] * Event.getWatchPercentage() / 100;
                demoIndex = selectDemo;
            }
        }
        return demoIndex;
    }

    public void vcost() {
        int watchViewingCost = 0;
        if (Offer.getWatchType() != null) {
            if (Offer.getWatchType().equals("movie")) {
                // For movies: identify the increased percentage of new customers and subscription fee
                if (watchViewerCount > TestCaseReader.getWatchGroupStreams()[demoIndex][StreamingService.getStreamIndex()]) {
                    watchViewingCost = (watchViewerCount - TestCaseReader.getWatchGroupStreams()[demoIndex][StreamingService.getStreamIndex()]) * StreamingService.getWatchSubscriptionFee();
                    TestCaseReader.getWatchGroupStreams()[demoIndex][StreamingService.getStreamIndex()] = watchViewerCount;
                }
            }
        }
        if (Offer.getWatchType() != null) {
            if (Offer.getWatchType().equals("ppv")) {
                // For Pay-Per-Views: identify the demo viewers affected and event price
                watchViewingCost = watchViewerCount * Offer.getWatchPayPerViewPrice();
            }
        }
    }
    public void funds(){
        // Adjust funds for watching events
        demoCurrentSpending[demoIndex] = demoCurrentSpending[demoIndex] + watchViewingCost;
        // Update current, previous and total dollar amounts
        for (selectDemo = 0; selectDemo < numDemos; selectDemo++) {
            demoTotalSpending[selectDemo] = demoTotalSpending[selectDemo] + demoCurrentSpending[selectDemo];
            demoPreviousSpending[selectDemo] = demoCurrentSpending[selectDemo];
            demoCurrentSpending[selectDemo] = 0;
        }
    }

    public void disdemo() {
        if (tokens[0].equals("display_demo")) {System.out.println("display_demo_acknowledged");
            //selectDemo = -1;
            for (int findItem = 0; findItem < numDemos; findItem++) {
                if (demoShortName[findItem].equals(tokens[1])) {
                    selectDemo = findItem;
                }
            }
            if (selectDemo < 0)//-----select demo array out of bounds error
                System.out.println("demo," + demoShortName[selectDemo] + "," + demoLongName[selectDemo] );
            System.out.println("size," + demoAccounts[selectDemo]);
            System.out.println("current_period," + demoCurrentSpending[selectDemo]);
            System.out.println("previous_period," + demoPreviousSpending[selectDemo]);
            System.out.println("total," + demoTotalSpending[selectDemo]);
        }
    }
}




