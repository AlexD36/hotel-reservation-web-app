package service;

import model.Hotel;
import repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling hotel business logic
 */
@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Optional<Hotel> getHotel(Long id) {
        return hotelRepository.findById(id);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    /**
     * Find hotels within a certain radius from a given latitude and longitude.
     * @param latitude - The latitude of the center point.
     * @param longitude - The longitude of the center point.
     * @param radius - The radius in kilometers.
     * @return List of hotels within the specified radius.
     */
    public List<Hotel> findHotelsWithinRadius(double latitude, double longitude, double radius) {
        return hotelRepository.findHotelsWithinRadius(latitude, longitude, radius);
    }

    // Additional methods can be added as needed
}