-- Создание таблицы для модели Styles
CREATE TABLE IF NOT EXISTS Styles (
                                      styleId SERIAL PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL,
    description TEXT
    );

-- Создание таблицы для модели Districts
CREATE TABLE IF NOT EXISTS Districts (
                                         districtId SERIAL PRIMARY KEY,
                                         name VARCHAR(255) NOT NULL,
    description TEXT
    );

-- Создание таблицы для модели Objects
CREATE TABLE IF NOT EXISTS Objects (
                                       objectId SERIAL PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
    description TEXT,
    address VARCHAR(255),
    districtId INT REFERENCES Districts(districtId) NOT NULL,
    pos1 DOUBLE PRECISION,
    pos2 DOUBLE PRECISION,
    pathToPhoto VARCHAR(255),
    pathToAudio VARCHAR(255),
    styleId INT REFERENCES Styles(styleId) NOT NULL
    );