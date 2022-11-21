package testSteps;
import api.bookerApi;
import io.restassured.response.Response;
import org.junit.Assert;

public class TestSteps {

    public static String getToken(String jsonBody) {
        Response response = bookerApi.auth(jsonBody);
        Assert.assertEquals(200, response.statusCode());
        String authToken = response.jsonPath().get("token");
        String reason = response.jsonPath().get("reason");
        if (authToken != null) {
            System.out.println("Получили токен авторизации: " + authToken + "\n");
        } else if (reason != null ) {
            System.out.println("Получили отказ в авторизации с причиной: " + reason  + "\n");
        }
        return authToken;
    }

    public static Integer bookingCreate (String jsonBody) {
        Response response = bookerApi.bookingCreate(jsonBody);
        Integer bookingId = 0;
        if (response.statusCode() == 200) {
            bookingId = response.jsonPath().get("bookingid");
            System.out.println("Создали бронирование: " + bookingId + "\n");
        } else if (response.statusCode() == 400) {
            System.out.println("Невозможно создать бронирование. Невалидный запрос" + "\n");
        }
        return bookingId;
    }

    public static void getBooking (Integer bookingId) {
        Response response = bookerApi.getBooking(bookingId);
        if (response.statusCode() == 200) {
            System.out.println("Получили информацию о бронировании: " + bookingId + "\n");
        } else if (response.statusCode() == 404) {
            System.out.println("Не найдено бронирование: " + bookingId + "\n");
        }
    }

    public static void bookingUpdate (String authToken, Integer bookingId, String jsonBody) {
        Response response = bookerApi.bookingUpdate(authToken, bookingId, jsonBody);
        if (response.statusCode() == 200) {
            System.out.println("Обновили бронирование: " + bookingId + "\n");
        } else if (response.statusCode() == 400) {
            System.out.println("Невозможно обновить бронирование. Невалидный запрос" + "\n");
        }
    }

    public static void partialBookingUpdate (String authToken, Integer bookingId, String jsonBody) {
        Response response = bookerApi.partialBookingUpdate(authToken, bookingId, jsonBody);
        if (response.statusCode() == 200) {
            System.out.println("Частично обновили бронирование: " + bookingId + "\n");
        } else if (response.statusCode() == 400) {
            System.out.println("Невозможно обновить бронирование. Невалидный запрос" + "\n");
        }
    }

    public static void bookingDelete (String authToken, Integer bookingId) {
        Response response = bookerApi.bookingDelete(authToken,bookingId);
        if (response.statusCode() == 201) {
            System.out.println("Удалили бронирование: : " + bookingId + "\n");
        } else if (response.statusCode() == 405) {
            System.out.println("Невозможно удалить бронирование: " + bookingId + "\n");
        }
    }
}
