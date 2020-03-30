-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-03-2020 a las 00:55:20
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
-- Estructura de tabla para la tabla `abandoned_resident`
--

CREATE TABLE `abandoned_resident` (
  `id_abandoned_resident` int(20) NOT NULL,
  `id_animal` int(20) NOT NULL,
  `id_treatment` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `adopter`
--

CREATE TABLE `adopter` (
  `id_adopter` int(20) NOT NULL,
  `id_client` int(20) NOT NULL,
  `id_abandoned_resident` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `animal`
--

CREATE TABLE `animal` (
  `idAnimal` int(20) NOT NULL,
  `update_vaccines` tinyint(1) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `age` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  `breed` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `animal`
--

INSERT INTO `animal` (`idAnimal`, `update_vaccines`, `name`, `age`, `breed`) VALUES
(1, 0, 'Hachi', '11 meses', 'Perro Labrador'),
(2, 1, 'Hachi', '11 meses', 'Perro Labrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client`
--

CREATE TABLE `client` (
  `id_client` int(20) NOT NULL,
  `document` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `client`
--

INSERT INTO `client` (`id_client`, `document`) VALUES
(1, 12345);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `invoice`
--

CREATE TABLE `invoice` (
  `id_invoice` int(20) NOT NULL,
  `id_client` int(20) NOT NULL,
  `professional_card` int(20) NOT NULL,
  `id_pet` int(20) NOT NULL,
  `total_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medical_treatment`
--

CREATE TABLE `medical_treatment` (
  `id_medical_treatment` int(20) NOT NULL,
  `id_medicine` int(20) NOT NULL,
  `id_sacrifice` int(20) NOT NULL,
  `name_treatment` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicine`
--

CREATE TABLE `medicine` (
  `id_medicine` int(20) NOT NULL,
  `name_medicine` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  `quantity_in_warehouse` int(3) NOT NULL,
  `expiration_date` date NOT NULL,
  `manufacturer` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `person`
--

CREATE TABLE `person` (
  `document` int(20) NOT NULL,
  `name_surname` varchar(200) COLLATE utf8mb4_spanish_ci NOT NULL,
  `phone_number` int(15) NOT NULL,
  `email` varchar(200) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `address` varchar(200) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `person`
--

INSERT INTO `person` (`document`, `name_surname`, `phone_number`, `email`, `address`) VALUES
(1234, 'Carlos Paez', 321654, 'car@fd', 'Calle Falsam123'),
(12345, 'Renombrado', 321654, NULL, 'Malibú');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pet`
--

CREATE TABLE `pet` (
  `id_pet` int(20) NOT NULL,
  `id_animal` int(20) NOT NULL,
  `id_client` int(20) NOT NULL,
  `id_treatment` int(20) NOT NULL,
  `date_of_admission` date NOT NULL,
  `departure_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sacrifice`
--

CREATE TABLE `sacrifice` (
  `id_sacrifice` int(20) NOT NULL,
  `turn` int(3) NOT NULL,
  `place_of_realization` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `veterinarian`
--

CREATE TABLE `veterinarian` (
  `professional_card` int(20) NOT NULL,
  `document` int(20) NOT NULL,
  `medical_speciallity` varchar(200) COLLATE utf8mb4_spanish_ci NOT NULL,
  `work_shift` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `abandoned_resident`
--
ALTER TABLE `abandoned_resident`
  ADD PRIMARY KEY (`id_abandoned_resident`),
  ADD KEY `id_animal_resident_fk` (`id_animal`);

--
-- Indices de la tabla `adopter`
--
ALTER TABLE `adopter`
  ADD PRIMARY KEY (`id_adopter`),
  ADD KEY `id_client_adopter_fk` (`id_client`),
  ADD KEY `id_abandoned_resident_adopter_fk` (`id_abandoned_resident`);

--
-- Indices de la tabla `animal`
--
ALTER TABLE `animal`
  ADD PRIMARY KEY (`idAnimal`);

--
-- Indices de la tabla `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`),
  ADD KEY `document_client_fk` (`document`);

--
-- Indices de la tabla `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id_invoice`),
  ADD KEY `id_client_invoice` (`id_client`),
  ADD KEY `professional_card_invoice_fk` (`professional_card`),
  ADD KEY `id_pet_invoice_fk` (`id_pet`);

--
-- Indices de la tabla `medical_treatment`
--
ALTER TABLE `medical_treatment`
  ADD PRIMARY KEY (`id_medical_treatment`),
  ADD KEY `id_sacrifice_treatment_fk` (`id_sacrifice`),
  ADD KEY `id_medicine_treatment_fk` (`id_medicine`);

--
-- Indices de la tabla `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`id_medicine`);

--
-- Indices de la tabla `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`document`);

--
-- Indices de la tabla `pet`
--
ALTER TABLE `pet`
  ADD PRIMARY KEY (`id_pet`),
  ADD KEY `id_animal_pet_fk` (`id_animal`),
  ADD KEY `id_client_pet_fk` (`id_client`),
  ADD KEY `id_treatment_pet_fk` (`id_treatment`);

--
-- Indices de la tabla `sacrifice`
--
ALTER TABLE `sacrifice`
  ADD PRIMARY KEY (`id_sacrifice`);

--
-- Indices de la tabla `veterinarian`
--
ALTER TABLE `veterinarian`
  ADD PRIMARY KEY (`professional_card`),
  ADD KEY `document_veterinarian_fk` (`document`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `abandoned_resident`
--
ALTER TABLE `abandoned_resident`
  MODIFY `id_abandoned_resident` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `adopter`
--
ALTER TABLE `adopter`
  MODIFY `id_adopter` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `animal`
--
ALTER TABLE `animal`
  MODIFY `idAnimal` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `client`
--
ALTER TABLE `client`
  MODIFY `id_client` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id_invoice` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `medical_treatment`
--
ALTER TABLE `medical_treatment`
  MODIFY `id_medical_treatment` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `medicine`
--
ALTER TABLE `medicine`
  MODIFY `id_medicine` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pet`
--
ALTER TABLE `pet`
  MODIFY `id_pet` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sacrifice`
--
ALTER TABLE `sacrifice`
  MODIFY `id_sacrifice` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `veterinarian`
--
ALTER TABLE `veterinarian`
  MODIFY `professional_card` int(20) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `abandoned_resident`
--
ALTER TABLE `abandoned_resident`
  ADD CONSTRAINT `id_animal_resident_fk` FOREIGN KEY (`id_animal`) REFERENCES `animal` (`idAnimal`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `adopter`
--
ALTER TABLE `adopter`
  ADD CONSTRAINT `id_abandoned_resident_adopter_fk` FOREIGN KEY (`id_abandoned_resident`) REFERENCES `abandoned_resident` (`id_abandoned_resident`),
  ADD CONSTRAINT `id_client_adopter_fk` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

--
-- Filtros para la tabla `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `document_client_fk` FOREIGN KEY (`document`) REFERENCES `person` (`document`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `id_client_invoice` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_pet_invoice_fk` FOREIGN KEY (`id_pet`) REFERENCES `pet` (`id_pet`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `professional_card_invoice_fk` FOREIGN KEY (`professional_card`) REFERENCES `veterinarian` (`professional_card`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `medical_treatment`
--
ALTER TABLE `medical_treatment`
  ADD CONSTRAINT `id_medicine_treatment_fk` FOREIGN KEY (`id_medicine`) REFERENCES `medicine` (`id_medicine`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_sacrifice_treatment_fk` FOREIGN KEY (`id_sacrifice`) REFERENCES `sacrifice` (`id_sacrifice`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pet`
--
ALTER TABLE `pet`
  ADD CONSTRAINT `id_animal_pet_fk` FOREIGN KEY (`id_animal`) REFERENCES `animal` (`idAnimal`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_client_pet_fk` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_treatment_pet_fk` FOREIGN KEY (`id_treatment`) REFERENCES `medical_treatment` (`id_medical_treatment`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `veterinarian`
--
ALTER TABLE `veterinarian`
  ADD CONSTRAINT `document_veterinarian_fk` FOREIGN KEY (`document`) REFERENCES `person` (`document`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
