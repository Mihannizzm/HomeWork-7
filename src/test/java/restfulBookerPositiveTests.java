import org.junit.Test;
import testSteps.TestSteps;

public class restfulBookerPositiveTests {
    private final String getTokenJsonBody = "{\n" +
            "    \"username\" : \"admin\",\n" +
            "    \"password\" : \"password123\"\n" +
            "}";

    @Test
    public void getToken() {
        TestSteps.getToken(getTokenJsonBody);
    }

    @Test
    public void createAndGetBooking() {
        String bookingCreateJsonBody = "{\n" +
                "    \"firstname\" : \"Аркадий\",\n" +
                "    \"lastname\" : \"Паровозов\",\n" +
                "    \"totalprice\" : 15000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-01-01\",\n" +
                "        \"checkout\" : \"2023-01-10\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"mini bar\"\n" +
                "}";
        Integer bookingId = TestSteps.bookingCreate(bookingCreateJsonBody);

        TestSteps.getBooking(bookingId);
    }

    @Test
    public void createAndUpdateAndGetBooking() {
        String authToken = TestSteps.getToken(getTokenJsonBody);

        String bookingCreateJsonBody = "{\n" +
                "    \"firstname\" : \"Геннадий\",\n" +
                "    \"lastname\" : \"Онищенко\",\n" +
                "    \"totalprice\" : 270000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-05-01\",\n" +
                "        \"checkout\" : \"2023-06-21\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"mini bar\"\n" +
                "}";
        Integer bookingId = TestSteps.bookingCreate(bookingCreateJsonBody);

        String bookingUpdateJsonBody = "{\n" +
                "    \"firstname\" : \"Геннадий\",\n" +
                "    \"lastname\" : \"Онищенко\",\n" +
                "    \"totalprice\" : 300000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-05-08\",\n" +
                "        \"checkout\" : \"2023-07-28\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"mini bar\"\n" +
                "}";
        TestSteps.bookingUpdate(authToken, bookingId, bookingUpdateJsonBody);

        String bookingPartialUpdateJsonBody = "{\n" +
                "    \"depositpaid\" : false\n" +
                "}";
        TestSteps.partialBookingUpdate(authToken, bookingId, bookingPartialUpdateJsonBody);

        TestSteps.getBooking(bookingId);
    }

    @Test
    public void createAndDeleteBooking() {
        String authToken = TestSteps.getToken(getTokenJsonBody);

        String bookingCreateJsonBody = "{\n" +
                "    \"firstname\" : \"Виталий\",\n" +
                "    \"lastname\" : \"Милонов\",\n" +
                "    \"totalprice\" : 10000000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-01-01\",\n" +
                "        \"checkout\" : \"2023-01-10\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"mini bar\"\n" +
                "}";
        Integer bookingId = TestSteps.bookingCreate(bookingCreateJsonBody);

        TestSteps.bookingDelete(authToken, bookingId);

        TestSteps.getBooking(bookingId);
    }

}
