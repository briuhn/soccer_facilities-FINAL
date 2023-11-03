package soccer.facility.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.facility.entity.Booking;

@Data
@NoArgsConstructor
public class SoccerFacilityBooking {
	private Long bookingId;
	private String date;
	private String startTime;
	private String endTime;
	
	public SoccerFacilityBooking(Booking booking) {
		bookingId = booking.getBookingId();
		date = booking.getDate();
		startTime = booking.getStartTime();
		endTime = booking.getEndTime();
	}
}
