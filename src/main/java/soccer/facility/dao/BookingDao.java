package soccer.facility.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import soccer.facility.entity.Booking;

public interface BookingDao extends JpaRepository<Booking, Long> {

}
