package controller;

import model.Hotel;
import service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for handling hotel-related HTTP requests
 */
@RestController
@RequestMapping("/api/hotels")
@Api(value = "Hotel Management System", tags = {"Hotels"})
public class HotelController {
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    /**
     * Creates a new hotel
     * @param hotel - The hotel data from the request body
     * @return ResponseEntity with the created hotel and HTTP status
     */
    @PostMapping
    @ApiOperation(value = "Create a new hotel", response = Hotel.class)
    public ResponseEntity<Hotel> createHotel(@Valid @RequestBody Hotel hotel) {
        Hotel createdHotel = hotelService.createHotel(hotel);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    /**
     * Retrieves a hotel by its ID
     * @param id - The hotel ID
     * @return ResponseEntity with the hotel or a not found status
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve a hotel by its ID", response = Hotel.class)
    public ResponseEntity<Hotel> getHotel(@PathVariable Long id) {
        return hotelService.getHotel(id)
                .map(hotel -> new ResponseEntity<>(hotel, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Retrieves all hotels
     * @return ResponseEntity with a list of hotels
     */
    @GetMapping
    @ApiOperation(value = "Retrieve all hotels", response = List.class)
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    /**
     * Updates an existing hotel
     * @param id - The hotel ID
     * @param hotel - The updated hotel data
     * @return ResponseEntity with the updated hotel or a not found status
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing hotel", response = Hotel.class)
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @Valid @RequestBody Hotel hotel) {
        hotel.setId(id); // Ensure the ID is set for the update
        return hotelService.getHotel(id)
                .map(existingHotel -> {
                    Hotel updatedHotel = hotelService.updateHotel(hotel);
                    return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Deletes a hotel by its ID
     * @param id - The hotel ID
     * @return ResponseEntity with no content or a not found status
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a hotel by its ID")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        try {
            if (hotelService.getHotel(id).isPresent()) {
                hotelService.deleteHotel(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Log the exception (optional)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Search for hotels within a certain radius from a given latitude and longitude.
     * @param latitude - The latitude of the center point.
     * @param longitude - The longitude of the center point.
     * @param radius - The radius in kilometers.
     * @return ResponseEntity with a list of hotels within the specified radius.
     */
    @GetMapping("/search")
    @ApiOperation(value = "Search hotels by radius", response = List.class)
    public ResponseEntity<List<Hotel>> searchHotelsByRadius(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius) {
        List<Hotel> hotels = hotelService.findHotelsWithinRadius(latitude, longitude, radius);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }
} 