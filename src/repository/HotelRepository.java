package repository;

import model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Hotel entity
 * Extends JpaRepository to provide CRUD operations
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Optional<Hotel> findByUsername(String username);
    Optional<Hotel> findByEmail(String email);

    /**
     * Find hotels within a certain radius from a given latitude and longitude.
     * @param latitude - The latitude of the center point.
     * @param longitude - The longitude of the center point.
     * @param radius - The radius in kilometers.
     * @return List of hotels within the specified radius.
     */
    @Query(value = "SELECT * FROM hotels h WHERE " +
                   "6371 * acos(cos(radians(:latitude)) * cos(radians(h.latitude)) * " +
                   "cos(radians(h.longitude) - radians(:longitude)) + " +
                   "sin(radians(:latitude)) * sin(radians(h.latitude))) < :radius", 
                   nativeQuery = true)
    List<Hotel> findHotelsWithinRadius(double latitude, double longitude, double radius);
} 