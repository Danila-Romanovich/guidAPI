-- Добавление записей в таблицу Styles
INSERT INTO Styles (name, description)
VALUES
    ('Конструктивизм', 'Description 1'),
    ('Неоклассицизм', 'Description 2'),
    ('Уникальные объекты', 'Description 3'),
    ('Достопримечательности', 'Description 3');

-- Добавление записей в таблицу Districts
INSERT INTO Districts (name, description)
VALUES
    ('Центральный', ''),
    ('Ленинский', ''),
    ('Рудничный', ''),
    ('Заводский', '');

-- Добавление записей в таблицу Objects
INSERT INTO Objects (name, description, address, districtId, pos1, pos2, pathToPhoto, pathToAudio, styleId)
VALUES
    ('Областная клиническая больница скорой медицинской помощи им. М.А. Подгорбунского', 'Description 1', 'ул. Николая Островского, 22, Кемерово, Кемеровская обл., 650000', 1, 55.3707923, 86.0363043, 'img/objects/1', 'audio/objects/1', 1),
    ('Центральный Универмаг', 'Description 2', 'Кирова ул., 37, Кемерово, Кемеровская обл., 650000', 1, 55.356476, 86.0724036, 'img/objects/2', 'audio/objects/2', 1),
    ('Кинотеатр "Москва"', 'Description 3', 'ул. Дзержинского, 2, Кемерово, Кемеровская обл., 650000', 1, 55.3544268, 86.0733053, 'img/objects/3', 'audio/objects/3', 1);