package soccer.facility.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import soccer.facility.controller.model.SoccerFacilityBooking;
import soccer.facility.controller.model.SoccerFacilityData;
import soccer.facility.controller.model.SoccerFacilityPlayer;
import soccer.facility.service.SoccerFacilityService;

@RestController
@RequestMapping("/soccer_facility")
@Slf4j
public class FacilityController {
	
	@Autowired
	private SoccerFacilityService soccerFacilityService;
	
	//create soccer facility
	@PostMapping("/soccer_facility")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SoccerFacilityData saveSoccerFacility(@RequestBody SoccerFacilityData soccerFacilityData) {
		log.info("Creating soccer_facility {}", soccerFacilityData);
		return soccerFacilityService.saveSoccerFacility(soccerFacilityData);
	}
	
	//update soccer facility
	@PutMapping("/soccer_facility/{soccerFacilityId}")
	public SoccerFacilityData updateSoccerFacilityData(@PathVariable Long soccerFacilityId, @RequestBody SoccerFacilityData soccerFacilityData) {
		soccerFacilityData.setSoccerFacilityId(soccerFacilityId);
		log.info("Updating soccer facility{}", soccerFacilityData);
		return soccerFacilityService.saveSoccerFacility(soccerFacilityData);
	}
	
	
	//retrieve
	@GetMapping
	public List<SoccerFacilityData> retrieveAllSoccerFacilities(){
		log.info("Showing all soccer facilities ");
		return soccerFacilityService.retrieveAllSoccerFacilities();
	}
	
	//creating player in soccer facility
	@PostMapping("/{soccerFacilityId}/player")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SoccerFacilityPlayer savePlayer(@PathVariable Long soccerFacilityId, @RequestBody SoccerFacilityPlayer soccerFacilityPlayer) {
		log.info("Creating soccer_facility_player {}", soccerFacilityPlayer);
		return soccerFacilityService.savePlayer(soccerFacilityId, soccerFacilityPlayer);
	}
	
//	//updating player ---------------------
	@PutMapping("/{soccerFacilityId}/player/{playerId}")
	public SoccerFacilityPlayer updateSoccerFacilityPlayer(@PathVariable Long playerId, @RequestBody SoccerFacilityPlayer soccerFacilityPlayer, @PathVariable Long soccerFacilityId) {
		log.info("Updating soccer_facility_player {}", soccerFacilityPlayer);
		soccerFacilityPlayer.setPlayerId(playerId);
		return soccerFacilityService.savePlayer(soccerFacilityId, soccerFacilityPlayer);
	}
	
	//retrieving soccer facility with ID 
	@GetMapping("/{soccerFacilityId}")
	public SoccerFacilityData retreiveSoccerFacilityById(@PathVariable Long soccerFacilityId) {
		log.info("retrieving soccer facility with ID = {}", soccerFacilityId);
		return soccerFacilityService.retrieveSoccerFacilityById(soccerFacilityId);
	}
	
	@DeleteMapping("/{soccerFacilityId}")
	public Map<String, String> deleteSoccerFacilityById(@PathVariable Long soccerFacilityId){
		log.info("Deleting soccer facility with id = " + soccerFacilityId);
		soccerFacilityService.deleteSoccerFacilityById(soccerFacilityId);
		
		return Map.of("message", "Soccer facility with ID= " + soccerFacilityId + " was deleted successfully");
	}
	
	//Create booking in soccer facility ID 
	@PostMapping("/{soccerFacilityId}/booking")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SoccerFacilityBooking saveBooking(@PathVariable Long soccerFacilityId, @RequestBody SoccerFacilityBooking soccerFacilityBooking) {
		log.info("Creating booking for soccer facility ID = {}", soccerFacilityId, soccerFacilityBooking);
		return soccerFacilityService.saveBooking(soccerFacilityId, soccerFacilityBooking);
	}
	
	//update booking in soccer facility  -----------------
	@PutMapping("/{soccerFacilityId}/booking/{bookingId}")
	public SoccerFacilityBooking updateBooking(@PathVariable Long soccerFacilityId,@RequestBody SoccerFacilityBooking soccerFacilityBooking, @PathVariable Long bookingId) {
		log.info("Updating booking ID {} for soccer facility ID {}",soccerFacilityId, soccerFacilityBooking);
		soccerFacilityBooking.setBookingId(bookingId);
		return soccerFacilityService.saveBooking(soccerFacilityId, soccerFacilityBooking);
	}
	//----------------
	
	//retrieve players
	@GetMapping("/{soccerFacilityId}/player")
	public List<SoccerFacilityPlayer> retrieveAllSoccerPlayers(){
		log.info("Showing all players ");
		return soccerFacilityService.retrieveAllSoccerPlayers();
	}
	
	//retrieve bookings
	@GetMapping("/{soccerFacilityId}/booking")
	public List<SoccerFacilityBooking> retrieveAllBookings(){
		log.info("Showing all bookings ");
		return soccerFacilityService.retrieveAllBookings();
	}
	

}
