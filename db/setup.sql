create table users
(
    id           int          not null
        primary key,
    access_token varchar(255) not null,
    email        varchar(255) not null,
    username     varchar(255) not null
);

INSERT INTO users (id, username, email, access_token)  VALUES (1, "Applifting", "info@applifting.cz", "93f39e2f-80de-4033-99ee-249d92736a25");
INSERT INTO users (id, username, email, access_token)  VALUES (2, "Batman", "batman@example.com", "dcb20f8a-5657-4f1b-9f7f-ce65739b359e");