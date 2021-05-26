public class Offer {

    private int numOffers;
    private String offerStream[];
    private String offerType[];
    private String offerEventName[];
    private int offerEventYear[];
    private int offerEventPrice[];
    private static final int LIMIT_OFFERS = Event.getLimitEvents() * StreamingService.getLimitStreams();
    private static int selectOffer;
    private static int watchPayPerViewPrice;
    private static int offerIndex;
    private static String watchType;
    String[] tokens = TestCaseReader.getTokens();

    public static int getSelectOffer() {
        return selectOffer;
    }

    public static int getWatchPayPerViewPrice() {
        return watchPayPerViewPrice;
    }

    public static int getOfferIndex() {
        return offerIndex;
    }

    public static String getWatchType() {
        return watchType;
    }

    public Offer() {
        numOffers = 0;
        offerStream = new String[LIMIT_OFFERS];
        offerType = new String[LIMIT_OFFERS];
        offerEventName = new String[LIMIT_OFFERS];
        offerEventYear = new int[LIMIT_OFFERS];
        offerEventPrice = new int[LIMIT_OFFERS];
    }

    public void offermeth() {
        numOffers = 0;
        if (tokens[0].equals("offer_movie") || tokens[0].equals("offer_ppv")) {
            System.out.println(tokens[0] + "_acknowledged");
            if (numOffers >= LIMIT_OFFERS)
                offerType[numOffers] = tokens[0].substring(6);
            offerStream[numOffers] = tokens[1];
            offerEventName[numOffers] = tokens[2];
            offerEventYear[numOffers] = Integer.parseInt(tokens[3]);
            if (offerType[numOffers].equals("ppv")) {
                offerEventPrice[numOffers] = Integer.parseInt(tokens[4]);
            }
            numOffers++;
        }
    }

public void offerpr() {
            // Identify the event selected & the Pay-Per-View price
            // For all events: determine event type
            String watchType = "";
            int watchPayPerViewPrice = 0;
            int offerIndex = -1;
            for (selectOffer = 0; selectOffer < numOffers; selectOffer++) {
                if (offerStream[selectOffer].equals(Event.getWatchStream()) && offerEventName[selectOffer].equals(Event.getWatchEventName()) && offerEventYear[selectOffer] == Event.getWatchEventYear()) {
                    watchType = offerType[selectOffer];
                    watchPayPerViewPrice = offerEventPrice[selectOffer];
                    offerIndex = selectOffer;
                }
            }
        }

 public void removermvppv(){
        // Remove all movie and Pay-Per-View offerings
        numOffers = 0;
        offerStream = new String[LIMIT_OFFERS];
        offerType = new String[LIMIT_OFFERS];
        offerEventName = new String[LIMIT_OFFERS];
        offerEventYear = new int[LIMIT_OFFERS];
        offerEventPrice = new int[LIMIT_OFFERS];
    }
    public void disoffer() {
        if (tokens[0].equals("display_offers")) {
                System.out.println("display_offers_acknowledged");
                for (selectOffer = 0; selectOffer < numOffers; selectOffer++) {
                    System.out.print(offerStream[selectOffer] + "," + offerType[selectOffer] + "," + offerEventName[selectOffer] + "," + offerEventYear[selectOffer]);
                    if (offerType[selectOffer].equals("ppv")) {
                        System.out.print("," + offerEventPrice[selectOffer]);
                    }
                    System.out.println();
                }
            }
        }
}

