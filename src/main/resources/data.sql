-- Clean existing data (if needed when re-running)
DELETE FROM product_categories;
DELETE FROM cart_items;
DELETE FROM order_items;
DELETE FROM payments;
DELETE FROM orders;
DELETE FROM products;
DELETE FROM categories;
DELETE FROM customers;
DELETE FROM users;

-- Insert Users
INSERT INTO users (id, name, email, password) VALUES
(1, 'John Doe', 'john@example.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG'), -- password: password123
(2, 'Jane Smith', 'jane@example.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG'),
(3, 'Mike Johnson', 'mike@example.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG');

-- Insert Customers
INSERT INTO customers (id, phone, address, user_id) VALUES
(1, '(123) 456-7890', '123 Main St, Anytown, USA', 1),
(2, '(234) 567-8901', '456 Oak Ave, Somewhere, USA', 2),
(3, '(345) 678-9012', '789 Pine Dr, Nowhere, USA', 3);

-- Insert Categories
INSERT INTO categories (id, name, slug) VALUES
(1, 'Electronics', 'electronics'),
(2, 'Clothing', 'clothing'),
(3, 'Books', 'books'),
(4, 'Home & Garden', 'home-garden'),
(5, 'Sports', 'sports');

-- Insert Products
INSERT INTO products (id, name, slug, description, price) VALUES
(1, 'Smartphone X', 'smartphone-x', 'Latest smartphone with advanced features.', 899.99),
(2, 'Laptop Pro', 'laptop-pro', 'High-performance laptop for professionals.', 1299.99),
(3, 'Wireless Headphones', 'wireless-headphones', 'Noise-cancelling wireless headphones.', 149.99),
(4, 'Mens T-Shirt', 'mens-tshirt', 'Comfortable cotton t-shirt for men.', 24.99),
(5, 'Womens Jeans', 'womens-jeans', 'Stylish jeans for women.', 39.99),
(6, 'The Great Novel', 'great-novel', 'Bestselling fiction novel.', 19.99),
(7, 'Gardening Tools Set', 'gardening-tools', 'Complete set of gardening tools.', 59.99),
(8, 'Fitness Tracker', 'fitness-tracker', 'Track your daily activities and exercise.', 79.99),
(9, 'Coffee Maker', 'coffee-maker', 'Automatic coffee maker for home use.', 69.99),
(10, 'Yoga Mat', 'yoga-mat', 'High-quality yoga mat for all exercises.', 29.99);

-- Associate Products with Categories
INSERT INTO product_categories (product_id, category_id) VALUES
(1, 1), -- Smartphone belongs to Electronics
(2, 1), -- Laptop belongs to Electronics
(3, 1), -- Headphones belong to Electronics
(4, 2), -- T-Shirt belongs to Clothing
(5, 2), -- Jeans belong to Clothing
(6, 3), -- Novel belongs to Books
(7, 4), -- Gardening Tools belong to Home & Garden
(8, 1), -- Fitness Tracker belongs to Electronics
(8, 5), -- Fitness Tracker also belongs to Sports
(9, 4), -- Coffee Maker belongs to Home & Garden
(10, 5); -- Yoga Mat belongs to Sports

-- Insert Orders with different statuses
INSERT INTO orders (id, customer_id, created_at, status) VALUES
(1, 1, CURRENT_TIMESTAMP - 30, 'CONFIRMED'),
(2, 2, CURRENT_TIMESTAMP - 20, 'PENDING'),
(3, 1, CURRENT_TIMESTAMP - 10, 'CONFIRMED'),
(4, 3, CURRENT_TIMESTAMP - 5, 'CANCELLED');

-- Insert Order Items
INSERT INTO order_items (id, quantity, price, order_id, product_id) VALUES
(1, 1, 899.99, 1, 1),  -- Order 1: Smartphone
(2, 2, 149.99, 1, 3),  -- Order 1: Two Headphones
(3, 1, 1299.99, 2, 2), -- Order 2: Laptop
(4, 3, 24.99, 3, 4),   -- Order 3: Three T-Shirts
(5, 1, 69.99, 3, 9),   -- Order 3: Coffee Maker
(6, 1, 79.99, 4, 8);   -- Order 4: Fitness Tracker

-- Insert Payments
INSERT INTO payments (id, amount, payment_date, order_id, method, status) VALUES
(1, 119999, CURRENT_TIMESTAMP - 29, 1, 'CREDIT_CARD', 'PAID'),         -- Payment for Order 1
(2, 129999, CURRENT_TIMESTAMP - 19, 2, 'BANK_SLIP', 'WAITING'),        -- Payment for Order 2
(3, 14496, CURRENT_TIMESTAMP - 9, 3, 'CREDIT_CARD', 'PAID'),           -- Payment for Order 3
(4, 7999, CURRENT_TIMESTAMP - 4, 4, 'CREDIT_CARD', 'ERROR');           -- Payment for Order 4 (cancelled)

-- Insert Carts
INSERT INTO carts (id, uuid, customer_id, created_at) VALUES
(1, 'cart-uuid-1', 2, CURRENT_TIMESTAMP - 2),
(2, 'cart-uuid-2', 3, CURRENT_TIMESTAMP - 1);

-- Insert Cart Items
INSERT INTO cart_items (id, quantity, cart_id, product_id) VALUES
(1, 1, 1, 5),  -- Jane's cart: Jeans
(2, 1, 1, 6),  -- Jane's cart: Novel
(3, 2, 2, 10); -- Mike's cart: Two Yoga Mats