INSERT INTO users (username, email, password, first_name, last_name, phone_number, roles) VALUES
('john_doe', 'john@example.com', 'password123', 'John', 'Doe', '1234567890', 'ROLE_USER'),
('jane_doe', 'jane@example.com', 'password456', 'Jane', 'Doe', '0987654321', 'ROLE_USER');

INSERT INTO hotels (name, address, city, country, star_rating, description, total_rooms, price_per_night, currency, latitude, longitude) VALUES
('Hotel California', '123 Sunset Blvd', 'Los Angeles', 'USA', 5, 'A lovely place', 100, 199.99, 'USD', 34.0522, -118.2437),
('Grand Hotel', '456 Ocean Drive', 'Miami', 'USA', 4, 'Luxury by the beach', 200, 299.99, 'USD', 25.7617, -80.1918); 