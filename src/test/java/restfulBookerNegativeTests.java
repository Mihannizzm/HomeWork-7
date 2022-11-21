import org.junit.Test;
import testSteps.TestSteps;


public class restfulBookerNegativeTests {

    @Test
    public void getTokenWithInvalidCredential() {
        String getTokenJsonBody =  "{\n" +
                "    \"username\" : \"adminAdmin\",\n" +
                "    \"password\" : \"password123456\"\n" +
                "}";
        TestSteps.getToken(getTokenJsonBody);
    }

    @Test
    public void createBookingWithInvalidRequestBody() {
        String bookingCreateJsonBody = "{\n" +
                "    \"firstname\" : \"Аркадий\",\n" +
                "    \"lastname\" : \"Паровозов\",\n" +
                "    \"totalprice\" : 15000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-01-01\",\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"mini bar\"\n" +
                "}";
        TestSteps.bookingCreate(bookingCreateJsonBody);
    }

    @Test
    public void getBookingWithInvalidBookingId() {
        Integer bookingId = 1;
        TestSteps.getBooking(bookingId);
    }

    @Test
    public void deleteBookingWithInvalidBookingId() {
        String getTokenJsonBody =  "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        String authToken = TestSteps.getToken(getTokenJsonBody);

        Integer bookingId = 1;
        TestSteps.bookingDelete(authToken, bookingId);
    }

}
