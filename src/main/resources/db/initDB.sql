DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    email            VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    registered       TIMESTAMP           DEFAULT now() NOT NULL,
    enabled          BOOL                DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id          INTEGER NOT NULL,
    role             VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name            VARCHAR                             NOT NULL,
    enabled         BOOL                DEFAULT TRUE    NOT NULL
);
-- CREATE UNIQUE INDEX restaurants_unique_name_idx ON restaurants (name);

CREATE TABLE dishes
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id   INTEGER                             NOT NULL,
    date_time       TIMESTAMP           DEFAULT now()   NOT NULL,
    name            VARCHAR                             NOT NULL,
    price           REAL                                NOT NULL,
    enabled         BOOL                DEFAULT TRUE    NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
-- CREATE UNIQUE INDEX dishes_unique_restaurant_name_idx ON dishes (restaurant_id, name);

CREATE TABLE votes
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    date_time       TIMESTAMP           DEFAULT now() NOT NULL,
    user_id         INTEGER                           NOT NULL,
    restaurant_id   INTEGER                           NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE UNIQUE INDEX votes_unique_user_date_time_idx ON votes(user_id, date_time);