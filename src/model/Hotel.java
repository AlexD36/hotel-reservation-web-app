package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Entity class representing a Hotel with detailed property information
 */
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Hotel name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Address is required")
    @Column(nullable = false)
    private String address;

    @NotBlank(message = "City is required")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "Country is required")
    @Column(nullable = false)
    private String country;

    @Min(value = 1, message = "Star rating must be at least 1")
    @Max(value = 5, message = "Star rating must not exceed 5")
    @Column(name = "star_rating", nullable = false)
    private int starRating;

    @NotBlank(message = "Description is required")
    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(name = "hotel_amenities", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "amenity")
    private List<String> amenities = new ArrayList<>();

    @Min(value = 1, message = "Total rooms must be positive")
    @Column(name = "total_rooms", nullable = false)
    private int totalRooms;

    @ElementCollection
    @CollectionTable(name = "hotel_room_types", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "room_type")
    private List<String> roomTypes = new ArrayList<>();

    @NotNull(message = "Price per night is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Column(name = "price_per_night", nullable = false)
    private BigDecimal pricePerNight;

    @NotBlank(message = "Currency is required")
    @Column(nullable = false)
    private String currency;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0")
    @DecimalMax(value = "5.0", message = "Rating must not exceed 5")
    private Double rating;

    @Min(value = 0, message = "Number of reviews cannot be negative")
    @Column(name = "number_of_reviews")
    private int numberOfReviews;

    @ElementCollection
    @CollectionTable(name = "hotel_images", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    @Column(name = "cancellation_policy", columnDefinition = "TEXT")
    private String cancellationPolicy;

    private String contact;

    private String website;

    // New fields for latitude and longitude
    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    // Default constructor
    public Hotel() {}

    // Constructor with all fields
    public Hotel(String name, String address, String city, String country, int starRating,
                String description, List<String> amenities, int totalRooms, List<String> roomTypes,
                BigDecimal pricePerNight, String currency, Double rating, int numberOfReviews,
                List<String> images, String cancellationPolicy, String contact, String website,
                Double latitude, Double longitude) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.starRating = starRating;
        this.description = description;
        this.amenities = new ArrayList<>(amenities);
        this.totalRooms = totalRooms;
        this.roomTypes = new ArrayList<>(roomTypes);
        this.pricePerNight = pricePerNight;
        this.currency = currency;
        this.rating = rating;
        this.numberOfReviews = numberOfReviews;
        this.images = new ArrayList<>(images);
        this.cancellationPolicy = cancellationPolicy;
        this.contact = contact;
        this.website = website;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAmenities() {
        return new ArrayList<>(amenities);
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = new ArrayList<>(amenities);
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public List<String> getRoomTypes() {
        return new ArrayList<>(roomTypes);
    }

    public void setRoomTypes(List<String> roomTypes) {
        this.roomTypes = new ArrayList<>(roomTypes);
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public List<String> getImages() {
        return new ArrayList<>(images);
    }

    public void setImages(List<String> images) {
        this.images = new ArrayList<>(images);
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(id, hotel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", starRating=" + starRating +
                ", totalRooms=" + totalRooms +
                ", pricePerNight=" + pricePerNight +
                ", currency='" + currency + '\'' +
                ", rating=" + rating +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
} 