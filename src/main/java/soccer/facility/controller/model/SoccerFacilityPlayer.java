package soccer.facility.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.facility.entity.Player;

@Data
@NoArgsConstructor
public class SoccerFacilityPlayer {
	private Long playerId;
	private String playerFirstName;
	private String playerLastName;
	private String playerEmail;
	
	public SoccerFacilityPlayer(Player player) {
		playerId = player.getPlayerId();
		playerFirstName = player.getPlayerFirstName();
		playerLastName = player.getPlayerLastName();
		playerEmail = player.getPlayerEmail();
	}
	
}
