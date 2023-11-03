package soccer.facility.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import soccer.facility.controller.model.SoccerFacilityBooking;
import soccer.facility.controller.model.SoccerFacilityData;
import soccer.facility.controller.model.SoccerFacilityPlayer;
import soccer.facility.dao.BookingDao;
import soccer.facility.dao.PlayerDao;
import soccer.facility.dao.SoccerFacilityDao;
import soccer.facility.entity.Booking;
import soccer.facility.entity.Player;
import soccer.facility.entity.SoccerFacility;

@Service
public class SoccerFacilityService {

	@Autowired
	private SoccerFacilityDao soccerFacilityDao;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private PlayerDao playerDao;

	// soccer facility data
	@Transactional(readOnly = false)
	public SoccerFacilityData saveSoccerFacility(SoccerFacilityData soccerFacilityData) {
		Long soccerFacilityId = soccerFacilityData.getSoccerFacilityId();
		SoccerFacility soccerFacility = findOrCreateSoccerFacility(soccerFacilityId);

		copySoccerFacilityFields(soccerFacility, soccerFacilityData);
		return new SoccerFacilityData(soccerFacilityDao.save(soccerFacility));
	}

	private void copySoccerFacilityFields(SoccerFacility soccerFacility, SoccerFacilityData soccerFacilityData) {
		soccerFacility.setSoccerFacilityId(soccerFacilityData.getSoccerFacilityId());
		soccerFacility.setSoccerFacilityName(soccerFacilityData.getSoccerFacilityName());
		soccerFacility.setSoccerFacilityAddress(soccerFacilityData.getSoccerFacilityAddress());
		soccerFacility.setSoccerFacilityCity(soccerFacilityData.getSoccerFacilityCity());
		soccerFacility.setSoccerFacilityState(soccerFacilityData.getSoccerFacilityState());
		soccerFacility.setSoccerFacilityZip(soccerFacilityData.getSoccerFacilityZip());
		soccerFacility.setSoccerFacilityPhone(soccerFacilityData.getSoccerFacilityPhone());

	}

	private SoccerFacility findOrCreateSoccerFacility(Long soccerFacilityId) {
		if (Objects.isNull(soccerFacilityId)) {
			return new SoccerFacility();
		} else {
			return findSoccerFacilityById(soccerFacilityId);
		}
	}

	private SoccerFacility findSoccerFacilityById(Long soccerFacilityId) {
		return soccerFacilityDao.findById(soccerFacilityId).orElseThrow(
				() -> new NoSuchElementException("Soccer facility with ID=" + soccerFacilityId + " was not found"));
	}
	// end of sfdata

	
	// start of booking
	@Transactional(readOnly = false)
	public SoccerFacilityBooking saveBooking(Long soccerFacilityId, SoccerFacilityBooking soccerFacilityBooking) {

		SoccerFacility soccerFacility = findSoccerFacilityById(soccerFacilityId);
		Long bookingId = soccerFacilityBooking.getBookingId();
		Booking booking = findOrCreateBooking(soccerFacilityId, bookingId);

		copyBookingFields(booking, soccerFacilityBooking);
		booking.setSoccerFacility(soccerFacility);
		soccerFacility.getBookings().add(booking);
		return new SoccerFacilityBooking(bookingDao.save(booking));

	}

	private void copyBookingFields(Booking booking, SoccerFacilityBooking soccerFacilityBooking) {
		booking.setBookingId(soccerFacilityBooking.getBookingId());
		booking.setDate(soccerFacilityBooking.getDate());
		booking.setStartTime(soccerFacilityBooking.getStartTime());
		booking.setEndTime(soccerFacilityBooking.getEndTime());

	}

	private Booking findOrCreateBooking(Long soccerFacilityId, Long playerId) {
		if (Objects.isNull(playerId)) {
			return new Booking();
		} else {
			return findBookingById(soccerFacilityId, playerId);
		}
	}

	private Booking findBookingById(Long soccerFacilityId, Long bookingId) {
		Booking booking = bookingDao.findById(bookingId)
				.orElseThrow(() -> new NoSuchElementException("No booking found"));
		if (booking.getSoccerFacility().getSoccerFacilityId() != soccerFacilityId) {
			throw new IllegalArgumentException("Booking with id" + bookingId + " does not exist");
		}
		return booking;

	}
	// end of booking

	
	@Transactional(readOnly = true)
	public List<SoccerFacilityData> retrieveAllSoccerFacilities() {
		List<SoccerFacility> soccerFacilities = soccerFacilityDao.findAll();
		List<SoccerFacilityData> result = new LinkedList<>();

		for (SoccerFacility soccerFacility : soccerFacilities) {
			SoccerFacilityData soccerFacilityData = new SoccerFacilityData(soccerFacility);

			soccerFacilityData.getBookings().clear();
			soccerFacilityData.getPlayers().clear();

			result.add(soccerFacilityData);
		}
		return result;
	}
	
	//start of players
	

	@Transactional(readOnly = false)
	public SoccerFacilityPlayer savePlayer(Long soccerFacilityId, SoccerFacilityPlayer soccerFacilityPlayer) {

		SoccerFacility soccerFacility = findSoccerFacilityById(soccerFacilityId);
		Long playerId = soccerFacilityPlayer.getPlayerId();

		Player player = findOrCreatePlayer(soccerFacilityId, playerId);

		copyPlayerFields(player, soccerFacilityPlayer);
		player.getSoccerFacilities().add(soccerFacility);
		soccerFacility.getPlayers().add(player);

		return new SoccerFacilityPlayer(playerDao.save(player));
	}

	private void copyPlayerFields(Player player, SoccerFacilityPlayer soccerFacilityPlayer) {
		
		player.setPlayerId(soccerFacilityPlayer.getPlayerId());
		player.setPlayerFirstName(soccerFacilityPlayer.getPlayerFirstName());
		player.setPlayerLastName(soccerFacilityPlayer.getPlayerLastName());
		player.setPlayerEmail(soccerFacilityPlayer.getPlayerEmail());
		
		
	}

	private Player findOrCreatePlayer(Long soccerFacilityId, Long playerId) {
		if (Objects.isNull(playerId)) {
			return new Player();
		} else {
			return findPlayerById(soccerFacilityId, playerId);
		}
	}

	private Player findPlayerById(Long soccerFacilityId, Long playerId) {
		Player player = playerDao.findById(playerId).orElseThrow(() -> new NoSuchElementException("No Player found"));
		boolean sfd = false;
		for (SoccerFacility soccerFacility : player.getSoccerFacilities()) {
			if(soccerFacility.getSoccerFacilityId() == soccerFacilityId) {
				sfd = true;
				break;
			}
		}
		if(!sfd) {
			throw new IllegalArgumentException("Player with id" + playerId + " is not available");
		}
		return player;
	}
	
	//end of players
	
	//retrieve
	@Transactional(readOnly = true)
	public SoccerFacilityData retrieveSoccerFacilityById(Long soccerFacilityId) {
		SoccerFacility soccerFacility = findSoccerFacilityById(soccerFacilityId);
		return new SoccerFacilityData(soccerFacility);
	}
	
	//delete
	@Transactional(readOnly = false)
	public void deleteSoccerFacilityById(Long soccerFacilityId) {
		SoccerFacility soccerFacilty = findSoccerFacilityById(soccerFacilityId);
		soccerFacilityDao.delete(soccerFacilty);
	}

	
	//retrieve players
	@Transactional(readOnly = true)
	public List<SoccerFacilityPlayer> retrieveAllSoccerPlayers() {
		List<Player> players = playerDao.findAll();
		List<SoccerFacilityPlayer> result = new LinkedList<>();
		
		for (Player player : players) {
			SoccerFacilityPlayer soccerFacilityPlayer = new SoccerFacilityPlayer(player);
			
			result.add(soccerFacilityPlayer);
		}
		return result;
	}

	
	//retrieve bookings
	public List<SoccerFacilityBooking> retrieveAllBookings() {
		List<Booking> bookings = bookingDao.findAll();
		List<SoccerFacilityBooking> result = new LinkedList<>();
		
		for (Booking booking : bookings) {
			SoccerFacilityBooking soccerFacilityBooking = new SoccerFacilityBooking(booking);
			
			result.add(soccerFacilityBooking);
		}
		return result;
	}
	
	
	

}
