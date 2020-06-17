-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 17-06-2020 a las 10:30:00
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `sistemareparto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `movil` double DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idCliente`, `nombre`, `direccion`, `movil`, `mail`) VALUES
(1, 'Pamela', 'Calle La Perla', 654212304, 'Pamela@mail.es'),
(2, 'Juan', 'Barrio nuevo', 600234231, 'Juancliente@prueba1.es'),
(3, 'Jaime', 'Calle la palma', 600777731, 'Jaime@prueba.es'),
(4, 'Teresa', 'Calle sin nombre n13', 654732312, 'Teresa@mail.es'),
(5, 'Carlos Rodriguez', 'Calle Salamanca n17', 654321043, 'Carlosr@mail.es'),
(6, 'Luis Daniel Perez G?mez', 'Calle frontera n?2', 678567432, ''),
(7, 'Jorge Molina', 'Calle Crucifijo', 567434343, 'Jorgemolina@gmail.com'),
(8, 'Numero8', 'calle las ventas', 456454545, 'rojo@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE IF NOT EXISTS `pedido` (
  `idPedido` int(11) NOT NULL AUTO_INCREMENT,
  `total` decimal(10,2) DEFAULT NULL,
  `Cliente_idCliente` int(11) NOT NULL,
  `Repartidor_idRepartidor` int(11) NOT NULL,
  PRIMARY KEY (`idPedido`),
  UNIQUE KEY `idPedido` (`idPedido`),
  KEY `fk_Pedido_Cliente_idx` (`Cliente_idCliente`),
  KEY `fk_Pedido_Repartidor1_idx` (`Repartidor_idRepartidor`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`idPedido`, `total`, `Cliente_idCliente`, `Repartidor_idRepartidor`) VALUES
(1, '43.50', 2, 2),
(2, '53.00', 2, 4),
(3, '114.00', 3, 1),
(6, '39.50', 4, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido_has_producto`
--

CREATE TABLE IF NOT EXISTS `pedido_has_producto` (
  `Pedido_idPedido` int(11) NOT NULL,
  `Producto_idProducto` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`Pedido_idPedido`,`Producto_idProducto`),
  KEY `fk_Pedido_has_Producto_Producto1_idx` (`Producto_idProducto`),
  KEY `fk_Pedido_has_Producto_Pedido1_idx` (`Pedido_idPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pedido_has_producto`
--

INSERT INTO `pedido_has_producto` (`Pedido_idPedido`, `Producto_idProducto`, `cantidad`, `subtotal`) VALUES
(1, 1, 3, '43.50'),
(2, 1, 1, '14.50'),
(2, 3, 2, '19.00'),
(2, 6, 3, '19.50'),
(3, 1, 3, '43.50'),
(3, 3, 4, '38.00'),
(3, 6, 5, '32.50'),
(6, 7, 3, '22.50'),
(6, 13, 2, '17.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE IF NOT EXISTS `producto` (
  `idProducto` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` int(10) unsigned NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idProducto`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idProducto`, `codigo`, `nombre`, `descripcion`, `precio`) VALUES
(1, 1, 'Rabo de toro', 'Especialidad de la casa', '14.50'),
(3, 1, 'Flamenquin de pollo', 'Flamenquin de pollo y jamon con patatas y ensalada', '8.50'),
(6, 10, 'Salmorejo', 'Mu rico', '6.50'),
(7, 10, 'Ensaladilla Rusa', 'Patata cocida con mahonesa', '7.50'),
(8, 10, 'Salpicon de marisco', 'Cocktail de marisco servido fr?o', '7.50'),
(9, 2, 'Ensalada mediterranea', 'Ensalada tipica del mediterraneo', '6.50'),
(10, 2, 'Ensalada Cesar', 'Ensalada con salsa cesar', '9.00'),
(11, 4, 'Bacalo con pisto', 'Lomo entero de bacalo rebozado y frito sobre una base de patatas fritas y cubierto con pisto ', '10.50'),
(12, 4, 'Calamares fritos', 'Rodajas de calamar fritas', '8.50'),
(13, 4, 'Gulas con langostinos', 'Gulas con ajito y langostinos, riquisimo', '8.50');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `repartidor`
--

CREATE TABLE IF NOT EXISTS `repartidor` (
  `idRepartidor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `turno` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRepartidor`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `repartidor`
--

INSERT INTO `repartidor` (`idRepartidor`, `nombre`, `turno`) VALUES
(1, 'Luis', 'Mañanas'),
(2, 'Daniel', 'Tardes'),
(3, 'Prueba', 'Ninguno'),
(4, 'Arturo', 'Jefe'),
(5, 'Pepe', 'Ma?anas');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`Cliente_idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`Repartidor_idRepartidor`) REFERENCES `repartidor` (`idRepartidor`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedido_has_producto`
--
ALTER TABLE `pedido_has_producto`
  ADD CONSTRAINT `pedido_has_producto_ibfk_1` FOREIGN KEY (`Pedido_idPedido`) REFERENCES `pedido` (`idPedido`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pedido_has_producto_ibfk_2` FOREIGN KEY (`Producto_idProducto`) REFERENCES `producto` (`idProducto`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
