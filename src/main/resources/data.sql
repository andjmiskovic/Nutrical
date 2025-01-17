-- PASSWORD = 'cascaded'
INSERT INTO NUTRITIONIST
VALUES ('e3661c31-d1a4-47ab-94b6-1c6500dccf24', TIMESTAMP WITH TIME ZONE '2023-03-29 23:31:00.283326+00',
        'apetrovic@outlook.com', TRUE, 'Ana', 'Petrovic', TIMESTAMP WITH TIME ZONE '2023-03-29 23:31:24.10518+00',
        '$2a$10$Qg.gpYTtZiVMJ6Fs9QbQA.BtCx4106oSj92X.A/Gv7iAEKQXAg.gy', 'NUTRITIONIST',
        'b687c6b59f651f54893c2589dca06ad1fc1f09c7be267a5e23525ccef672bb87');

INSERT INTO CLIENT_DATA (id, biological_status, activity_status, weight_kg, height_cm, birth, goal, protein, carbs, fat,
                         additional_information)
VALUES (1, 'MAN', 'SEDENTARY', 75.5, 180.0, DATE '1990-01-01', 2000, 20, 60, 20, ''),
       (2, 'WOMAN', 'MODERATELY_ACTIVE', 62.0, 165.0, DATE '1995-05-10', 2000, 20, 60, 20, '');

INSERT INTO CLIENT (email, name, surname, client_data_id)
VALUES ('client1@example.com', 'John', 'Doe', 1),
       ('client2@example.com', 'Jane', 'Smith', 2);

INSERT INTO MEAL(id, name)
VALUES (11928471, 'Meal 1'),
       (21192847, 'Meal 1');

INSERT INTO daily_plan(id, notes, user_email, training)
VALUES (1123421, '', 'client1@example.com', ''),
       (21244, '', 'client2@example.com', '');

INSERT INTO daily_plan_meals(daily_plan_id, meals_id)
VALUES (1123421, 11928471),
       (21244, 21192847);

INSERT INTO NUTRITIONIST_CLIENTS (nutritionist_id, clients_email)
VALUES ('e3661c31-d1a4-47ab-94b6-1c6500dccf24', 'client1@example.com'),
       ('e3661c31-d1a4-47ab-94b6-1c6500dccf24', 'client2@example.com');

INSERT INTO PLAN(id, client_email, nutritionist_id)
VALUES (1, 'client1@example.com', 'e3661c31-d1a4-47ab-94b6-1c6500dccf24'),
       (2, 'client2@example.com', 'e3661c31-d1a4-47ab-94b6-1c6500dccf24');

INSERT INTO plan_daily_plans(plan_id, daily_plans_id)
VALUES (1, 1123421),
       (2, 21244);