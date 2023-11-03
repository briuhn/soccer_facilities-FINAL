package soccer.facility.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import soccer.facility.entity.SoccerFacility;

public interface SoccerFacilityDao extends JpaRepository<SoccerFacility, Long> {

}
