-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-02-2020 a las 18:52:41
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mvc_review`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `name` varchar(120) NOT NULL,
  `phone_number` varchar(80) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `client`
--

INSERT INTO `client` (`id`, `name`, `phone_number`, `address`) VALUES
(1, 'andres', '311', 'calle falsa 123'),
(2, 'carlos', '315', 'N/A'),
(3, 'carlos', '315', 'N/A'),
(4, 'carlos', '315', 'N/A'),
(5, 'carlos', '315', 'N/A'),
(6, 'carlos', '315', 'N/A'),
(7, 'carlos', '315', 'N/A'),
(8, 'Otro', '311', 'Calle falsa 123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `invoice`
--

CREATE TABLE `invoice` (
  `id` int(11) NOT NULL,
  `invoice_date` datetime NOT NULL,
  `total_price` double(11,2) DEFAULT NULL,
  `client_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `invoice`
--

INSERT INTO `invoice` (`id`, `invoice_date`, `total_price`, `client_id`) VALUES
(2, '2020-02-21 10:23:50', 50000.00, 1),
(3, '2020-02-24 12:35:00', 5000.00, 1),
(4, '2020-02-24 13:35:00', 5000.00, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

CREATE TABLE `product` (
  `barcode` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `sell_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `product`
--

INSERT INTO `product` (`barcode`, `name`, `description`, `sell_price`) VALUES
(1001, 'Arroz', 'Arroz por Kilo', 50000),
(1002, 'Leche', 'Leche por litro', 5000),
(1003, 'Pollo', 'Pollo entero', 15000),
(1004, 'Gaseosa', 'Coca cola', 2550),
(1005, 'Agua', 'Agua por Galón', 1800);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product_detail`
--

CREATE TABLE `product_detail` (
  `invoice_id` int(11) NOT NULL,
  `product_barcode` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `product_detail`
--

INSERT INTO `product_detail` (`invoice_id`, `product_barcode`) VALUES
(2, 1001),
(2, 1003),
(2, 1005),
(3, 1001),
(3, 1002),
(3, 1003),
(3, 1004);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `client_client_id_fk` (`client_id`);

--
-- Indices de la tabla `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`barcode`);

--
-- Indices de la tabla `product_detail`
--
ALTER TABLE `product_detail`
  ADD PRIMARY KEY (`invoice_id`,`product_barcode`),
  ADD KEY `product_detail_product_barcode_id_fk` (`product_barcode`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `client_client_id_fk` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `product_detail`
--
ALTER TABLE `product_detail`
  ADD CONSTRAINT `product_detail_invoice_invoice_id_fk` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `product_detail_product_barcode_id_fk` FOREIGN KEY (`product_barcode`) REFERENCES `product` (`barcode`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
