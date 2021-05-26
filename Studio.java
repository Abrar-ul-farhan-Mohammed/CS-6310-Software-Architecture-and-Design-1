public class Studio {

    private int numStudios;
    private String studioShortName[];
    private String studioLongName[];
    private int studioCurrentRevenue[];
    private int studioPreviousRevenue[];
    private int studioTotalRevenue[];
    private final int LIMIT_STUDIOS = 10;
    private int selectStudio;
    String[] tokens = TestCaseReader.getTokens();

    public Studio() {
        numStudios = 0;
        studioShortName = new String[LIMIT_STUDIOS];
        studioLongName = new String[LIMIT_STUDIOS];
        studioCurrentRevenue = new int[LIMIT_STUDIOS];
        studioPreviousRevenue = new int[LIMIT_STUDIOS];
        studioTotalRevenue = new int[LIMIT_STUDIOS];

    }

    public void studiometh() {
        if (tokens[0].equals("create_studio")) {
            System.out.println("create_studio_acknowledged");
            if (numStudios >= LIMIT_STUDIOS)
                studioShortName[numStudios] = tokens[1];
            studioLongName[numStudios] = tokens[2];
            numStudios++;
        }
    }

    public void sslicense() {
        // The streaming service must license the event from the studio
        for (selectStudio = 0; selectStudio < numStudios; selectStudio++) {
            if (studioShortName[selectStudio].equals(Event.getPayStudio())) {
                studioCurrentRevenue[selectStudio] = studioCurrentRevenue[selectStudio] + Event.getPayLicenseFee();
            }
        }
    }

    public void stfund(){
        for (selectStudio = 0; selectStudio < numStudios; selectStudio++) {
            studioTotalRevenue[selectStudio] = studioTotalRevenue[selectStudio] + studioCurrentRevenue[selectStudio];
            studioPreviousRevenue[selectStudio] = studioCurrentRevenue[selectStudio];
            studioCurrentRevenue[selectStudio] = 0;
        }
    }

    public void disstudio() { //selectstudio changed to 0 from -1
        if (tokens[0].equals("display_studio")) {
            System.out.println("display_studio_acknowledged");
            selectStudio = -1;
            for (int findItem = 0; findItem < numStudios; findItem++) {
                if (studioShortName[findItem].equals(tokens[1])) {
                    selectStudio = findItem;
                }
            }
            if (selectStudio < 0) {
                System.out.println("studio," + studioShortName[selectStudio] + "," + studioLongName[selectStudio]);
                System.out.println("current_period," + studioCurrentRevenue[selectStudio]);
                System.out.println("previous_period," + studioPreviousRevenue[selectStudio]);
                System.out.println("total," + studioTotalRevenue[selectStudio]);
            }
        }
    }
}