insert into users (name, last_name, age, email, password) values
    ('admin', 'Adminov', 35, 'admin@admin.com', '$2a$12$uyAfz3v1JrEcHMfjFjn1WOmAlsAPAZDyjymQFXsODY3eU64MzkMHy');

insert into roles (role) values
    ('ADMIN'),
    ('USER');

insert into users_roles (user_id, role_id) values
    ('1', '1'),
    ('1', '2');