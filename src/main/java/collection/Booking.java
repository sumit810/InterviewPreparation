package collection;

public class Booking {

    public String bookingId;
    public String userId;
    public Long amount;

    public Booking(String bookingId, String userId, Long amount){
        this.bookingId = bookingId;
        this.userId = userId;
        this.amount = amount;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
