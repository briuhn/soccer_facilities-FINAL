package soccer.facility.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import soccer.facility.entity.Player;

public interface PlayerDao extends JpaRepository<Player, Long> {

}
