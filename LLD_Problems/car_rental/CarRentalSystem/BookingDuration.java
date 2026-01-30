package CarRentalSystem;
import java.time.*;

public class BookingDuration {
    private LocalDateTime startDateTime, enDateTime;
    private Duration duration;
    public BookingDuration(LocalDateTime startDateTime, LocalDateTime enDateTime) {
        if(enDateTime.isBefore(startDateTime)) {
            throw new IllegalArgumentException("End date time cannot be before start date time");
        }
        this.startDateTime = startDateTime;
        this.enDateTime = enDateTime;
        this.duration = Duration.between(startDateTime, enDateTime);
    }
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
    public LocalDateTime getEnDateTime() {
        return enDateTime;
    }
    public long toHours() {
        return (long) Math.ceil(duration.toHours());
    }
    public long toDays() {
        return (long) Math.ceil(duration.toDays());
    }
    public boolean overlaps(BookingDuration other) {
        if(other.getEnDateTime().isBefore(startDateTime) || other.getStartDateTime().isAfter(enDateTime)) {
            return false;
        }
        return true;
    }
}
