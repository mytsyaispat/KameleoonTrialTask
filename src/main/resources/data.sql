INSERT INTO users (id, username, password, email, date_of_creation)
-- password for users -> password
VALUES (1, 'user1', '$2a$13$t4xBSqPzIW9qac4y.vid7uTZG0dAbB0A01Y8GGp0iE.VKLhYnSYdS', 'user1@mail.ru', CURRENT_TIMESTAMP),
       (2, 'user2', '$2a$13$wUY9v68joSHOyQ80ZWgLHuSpmhgaBG0gnvipTfmJOWQurA6zgsEzi', 'user2@mail.ru', CURRENT_TIMESTAMP),
       (3, 'user3', '$2a$13$pn.5/lGvK4gaflJuxqS2puBHkvDRpQPzd/6VE5lfo2VKRBTAIr5t6', 'user3@mail.ru', CURRENT_TIMESTAMP);
INSERT INTO quote (id, content, date, user_id)
VALUES (1, 'Content1', CURRENT_TIMESTAMP, 1),
       (2, 'Content2', CURRENT_TIMESTAMP, 1),
       (3, 'Content3', CURRENT_TIMESTAMP, 2),
       (4, 'Content4', CURRENT_TIMESTAMP, 2),
       (5, 'Content5', CURRENT_TIMESTAMP, 2),
       (6, 'Content6', CURRENT_TIMESTAMP, 3),
       (7, 'Content7', CURRENT_TIMESTAMP, 3);