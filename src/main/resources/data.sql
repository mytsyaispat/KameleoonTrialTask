INSERT INTO users (id, username, password, email, date_of_creation)
-- password for users -> password
VALUES (1, 'user1', '$2a$13$t4xBSqPzIW9qac4y.vid7uTZG0dAbB0A01Y8GGp0iE.VKLhYnSYdS', 'user1@mail.ru', CURRENT_TIMESTAMP),
       (2, 'user2', '$2a$13$wUY9v68joSHOyQ80ZWgLHuSpmhgaBG0gnvipTfmJOWQurA6zgsEzi', 'user2@mail.ru', CURRENT_TIMESTAMP),
       (3, 'user3', '$2a$13$pn.5/lGvK4gaflJuxqS2puBHkvDRpQPzd/6VE5lfo2VKRBTAIr5t6', 'user3@mail.ru', CURRENT_TIMESTAMP);

--INSERT INTO quote (id, content, date, user_id, vote_sum_by_quote_id)
--VALUES (1, 'Content1', CURRENT_TIMESTAMP, 1, 1),
--       (2, 'Content2', CURRENT_TIMESTAMP, 1, 2),
--       (3, 'Content3', CURRENT_TIMESTAMP, 1, 3),
--       (4, 'Content4', CURRENT_TIMESTAMP, 2, 4),
--       (5, 'Content5', CURRENT_TIMESTAMP, 2, 5),
--       (6, 'Content6', CURRENT_TIMESTAMP, 2, 6),
--       (7, 'Content7', CURRENT_TIMESTAMP, 2, 7),
--       (8, 'Content8', CURRENT_TIMESTAMP, 2, 8),
--       (9, 'Content9', CURRENT_TIMESTAMP, 2, 9),
--       (10, 'Content10', CURRENT_TIMESTAMP, 2, 10),
--       (11, 'Content11', CURRENT_TIMESTAMP, 2, 11),
--       (12, 'Content12', CURRENT_TIMESTAMP, 3, 12),
--       (13, 'Content13', CURRENT_TIMESTAMP, 3, 13);
--INSERT INTO vote_sum_by_quote (id, result)
--VALUES (1, 0),
--       (2, 0),
--       (3, 0),
--       (4, 0),
--       (5, 0),
--       (6, 0),
--       (7, 0),
--       (8, 0),
--       (9, 0),
--       (10, 0),
--       (11, 0),
--       (12, 0),
--       (13, 0);
--COMMIT;