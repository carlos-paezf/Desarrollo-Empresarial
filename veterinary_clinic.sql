-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-03-2020 a las 23:21:03
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `veterinary_clinic`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `address`
--

CREATE TABLE `address` (
  `id_address` int(10) NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_spanish_ci NOT NULL,
  `relation` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `address`
--

INSERT INTO `address` (`id_address`, `name`, `relation`) VALUES
(1, 'Colombia', NULL),
(2, 'Boyacá', 1),
(3, 'Tunja', 2),
(4, 'Barrio Las Nieves', 3),
(5, 'Calle Falsa 123', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `animal`
--

CREATE TABLE `animal` (
  `id_animal` int(10) NOT NULL,
  `update_vaccines` tinyint(1) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `age` varchar(20) COLLATE utf8mb4_spanish_ci NOT NULL,
  `species` int(11) NOT NULL,
  `breed` int(11) NOT NULL,
  `id_client_owner` int(11) DEFAULT NULL,
  `id_treatment` int(11) NOT NULL,
  `admission_date` int(11) NOT NULL,
  `departure_date` int(11) DEFAULT NULL,
  `found_in` int(11) DEFAULT NULL,
  `animalType` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `invoice`
--

CREATE TABLE `invoice` (
  `id_invoice` int(10) NOT NULL,
  `id_person` int(10) NOT NULL,
  `id_animal` int(10) NOT NULL,
  `expedition_date` date NOT NULL,
  `id_medical_treatment` int(10) NOT NULL,
  `total_price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medical_treatment`
--

CREATE TABLE `medical_treatment` (
  `id_medical_treatment` int(10) NOT NULL,
  `id_medicine` int(10) DEFAULT NULL,
  `name_treatment` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `turn_of_sacrifice` int(3) DEFAULT NULL,
  `place_realization` int(10) DEFAULT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicine`
--

CREATE TABLE `medicine` (
  `id_medicine` int(10) NOT NULL,
  `barcode` int(20) NOT NULL,
  `name_medicine` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `quantity_in_warehouse` int(10) NOT NULL,
  `expiration_date` date NOT NULL,
  `manufacturer` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `person`
--

CREATE TABLE `person` (
  `id_person` int(10) NOT NULL,
  `document` int(15) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `surname` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `phone_number` varchar(11) COLLATE utf8mb4_spanish_ci NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `id_address` int(10) NOT NULL,
  `professional_card` int(10) DEFAULT NULL,
  `medical_speciality` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `work_shift` int(1) DEFAULT NULL,
  `arrival_turn` int(3) DEFAULT NULL,
  `id_rescued_pet` int(10) DEFAULT NULL,
  `personType` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `person`
--

INSERT INTO `person` (`id_person`, `document`, `name`, `surname`, `phone_number`, `email`, `id_address`, `professional_card`, `medical_speciality`, `work_shift`, `arrival_turn`, `id_rescued_pet`, `personType`) VALUES
(1, 12345, 'David', 'Ferrer', '123456', 'david@ferrer', 5, 11111, 'Cirujano', 0, NULL, NULL, 0),
(2, 54321, 'Carlos', 'Paez', '654321', NULL, 5, NULL, NULL, NULL, 1, NULL, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id_address`),
  ADD KEY `relation_address` (`relation`);

--
-- Indices de la tabla `animal`
--
ALTER TABLE `animal`
  ADD PRIMARY KEY (`id_animal`),
  ADD KEY `person_animal_fk` (`id_client_owner`),
  ADD KEY `address_animal_fk` (`found_in`),
  ADD KEY `treatment_animal_fk` (`id_treatment`);

--
-- Indices de la tabla `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id_invoice`),
  ADD KEY `person_invoice_fk` (`id_person`),
  ADD KEY `animal_invoice_fk` (`id_animal`),
  ADD KEY `treatment_invoice_fk` (`id_medical_treatment`);

--
-- Indices de la tabla `medical_treatment`
--
ALTER TABLE `medical_treatment`
  ADD PRIMARY KEY (`id_medical_treatment`),
  ADD KEY `medicine_treatment_fk` (`id_medicine`),
  ADD KEY `address_treatment_fk` (`place_realization`);

--
-- Indices de la tabla `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`id_medicine`);

--
-- Indices de la tabla `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id_person`),
  ADD KEY `address_person_fk` (`id_address`) USING BTREE,
  ADD KEY `rescued_person_fk` (`id_rescued_pet`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `address`
--
ALTER TABLE `address`
  MODIFY `id_address` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `animal`
--
ALTER TABLE `animal`
  MODIFY `id_animal` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id_invoice` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `medical_treatment`
--
ALTER TABLE `medical_treatment`
  MODIFY `id_medical_treatment` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `medicine`
--
ALTER TABLE `medicine`
  MODIFY `id_medicine` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `person`
--
ALTER TABLE `person`
  MODIFY `id_person` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `relation_address` FOREIGN KEY (`relation`) REFERENCES `address` (`id_address`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `animal`
--
ALTER TABLE `animal`
  ADD CONSTRAINT `address_animal_fk` FOREIGN KEY (`found_in`) REFERENCES `address` (`id_address`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `person_animal_fk` FOREIGN KEY (`id_client_owner`) REFERENCES `person` (`id_person`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `treatment_animal_fk` FOREIGN KEY (`id_treatment`) REFERENCES `medical_treatment` (`id_medical_treatment`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `animal_invoice_fk` FOREIGN KEY (`id_animal`) REFERENCES `animal` (`id_animal`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `person_invoice_fk` FOREIGN KEY (`id_person`) REFERENCES `person` (`id_person`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `treatment_invoice_fk` FOREIGN KEY (`id_medical_treatment`) REFERENCES `medical_treatment` (`id_medical_treatment`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `medical_treatment`
--
ALTER TABLE `medical_treatment`
  ADD CONSTRAINT `address_treatment_fk` FOREIGN KEY (`place_realization`) REFERENCES `address` (`id_address`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `medicine_treatment_fk` FOREIGN KEY (`id_medicine`) REFERENCES `medicine` (`id_medicine`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `address_person_fk` FOREIGN KEY (`id_address`) REFERENCES `address` (`id_address`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rescued_person_fk` FOREIGN KEY (`id_rescued_pet`) REFERENCES `animal` (`id_animal`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
