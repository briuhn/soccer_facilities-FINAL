package soccer.facility.entity; 

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class SoccerFacility {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long soccerFacilityId;
	private String soccerFacilityName;
	private String soccerFacilityAddress;
	private String soccerFacilityCity;
	private String soccerFacilityState;
	private String soccerFacilityZip;
	private String soccerFacilityPhone;
	

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "soccer_facility_player", joinColumns = @JoinColumn(name = "soccer_facility_id"), inverseJoinColumns = @JoinColumn(name = "player_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Player> players = new HashSet<>();
	
	
	@OneToMany(mappedBy = "soccerFacility", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Booking> bookings = new HashSet<>();
	
	

}
