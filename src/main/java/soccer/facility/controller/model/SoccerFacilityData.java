package soccer.facility.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.facility.entity.Booking;
import soccer.facility.entity.Player;
import soccer.facility.entity.SoccerFacility;

@Data
@NoArgsConstructor
public class SoccerFacilityData {
	
	private Long soccerFacilityId;
	private String soccerFacilityName;
	private String soccerFacilityAddress;
	private String soccerFacilityCity;
	private String soccerFacilityState;
	private String soccerFacilityZip;
	private String soccerFacilityPhone;
	private Set<SoccerFacilityPlayer> players = new HashSet<>();
	private Set<SoccerFacilityBooking> bookings = new HashSet<>();
	
	public SoccerFacilityData(SoccerFacility soccerFacility) {
		soccerFacilityId = soccerFacility.getSoccerFacilityId();
		soccerFacilityName = soccerFacility.getSoccerFacilityName();
		soccerFacilityAddress = soccerFacility.getSoccerFacilityAddress();
		soccerFacilityCity = soccerFacility.getSoccerFacilityCity();
		soccerFacilityState = soccerFacility.getSoccerFacilityState();
		soccerFacilityZip = soccerFacility.getSoccerFacilityZip();
		soccerFacilityPhone = soccerFacility.getSoccerFacilityPhone();
		
		for(Player player : soccerFacility.getPlayers()) {
			players.add(new SoccerFacilityPlayer(player));
		}
		
		for(Booking booking : soccerFacility.getBookings()) {
			bookings.add(new SoccerFacilityBooking(booking));
		}
		
	}



}
