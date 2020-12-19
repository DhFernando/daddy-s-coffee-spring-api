-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 19, 2020 at 11:13 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `daddy-coffiee`
--

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `Id` int(100) NOT NULL,
  `itemName` varchar(100) NOT NULL,
  `price` int(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `itemCreator` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`Id`, `itemName`, `price`, `description`, `itemCreator`) VALUES
(1, 'TestItem', 100, 'This is the test description', 'testuser'),
(3, 'TestItem', 100, 'This is the test description', 'testuser'),
(4, 'testItems', 45, 'description here', 'testuser'),
(5, 'testItem', 45, 'description here', 'testuser');

-- --------------------------------------------------------

--
-- Table structure for table `ordereditems`
--

CREATE TABLE `ordereditems` (
  `Id` int(255) NOT NULL,
  `orderId` int(255) NOT NULL,
  `itemId` int(255) NOT NULL,
  `quantity` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ordereditems`
--

INSERT INTO `ordereditems` (`Id`, `orderId`, `itemId`, `quantity`) VALUES
(1, 13, 1, 5),
(2, 12, 2, 2),
(3, 10, 3, 2),
(4, 13, 4, 2),
(5, 9, 83, 9),
(6, 10, 86, 5),
(7, 10, 83, 9);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderId` int(255) NOT NULL,
  `contactNumber` int(10) NOT NULL,
  `deliveryAddress` varchar(255) NOT NULL,
  `orderOwner` varchar(100) NOT NULL,
  `ownerNIC` varchar(20) NOT NULL,
  `orderedDate` datetime NOT NULL,
  `orderCompletedDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderId`, `contactNumber`, `deliveryAddress`, `orderOwner`, `ownerNIC`, `orderedDate`, `orderCompletedDate`) VALUES
(1, 713797147, 'No 168/01 Galahitiyawa, Ganemulla', 'Dilshan Hasitha Fernando', '199625302594', '2020-12-14 11:12:29', '2020-12-14 15:19:58'),
(2, 713797147, 'No 168/01 Galahitiyawa, Ganemulla', 'Dilshan Hasitha Fernando', '199625302594', '2020-12-14 13:25:09', NULL),
(3, 713797147, 'No 168/01 Galahitiyawa, Ganemulla', 'Dilshan Hasitha Fernando', '199625302594', '2020-12-14 13:25:34', NULL),
(4, 713797147, 'No 168/01 Galahitiyawa, Ganemulla', 'Dilshan Hasitha Fernando', '199625302594', '2020-12-14 13:27:44', NULL),
(5, 713797147, 'No 168/01 Galahitiyawa, Ganemulla', 'Dilshan Hasitha Fernando', '199625302594', '2020-12-14 13:28:36', '2020-12-18 15:06:09'),
(6, 713797147, 'No 168/01 Galahitiyawa, Ganemulla', 'Dilshan Hasitha Fernando', '199625302594', '2020-12-14 13:31:17', NULL),
(7, 713797147, 'No 168/01 Galahitiyawa, Ganemulla', 'Dilshan Hasitha Fernando', '199625302594', '2020-12-14 13:32:51', NULL),
(8, 713797147, 'No 168/01 Galahitiyawa, Ganemulla', 'Dilshan Hasitha Fernando', '199625302594', '2020-12-14 13:33:50', NULL),
(9, 713797147, 'No 168/01 Galahitiyawa, Ganemulla', 'Dilshan Hasitha Fernando', '199625302594', '2020-12-14 13:35:23', NULL),
(10, 713797147, 'No 168/01 Galahitiyawa, Ganemulla', 'Dilshan Hasitha Fernando', '199625302594', '2020-12-19 08:25:18', '2020-12-19 08:42:39'),
(11, 713797147, 'No 168/01 Galahitiyawa, Ganemulla', 'Dilshan Hasitha Fernando', '199625302594', '2020-12-19 08:26:16', NULL),
(12, 713797147, 'No 168/01 Galahitiyawa, Ganemulla', 'Dilshan Hasitha Fernando', '199625302594', '2020-12-19 08:32:55', NULL),
(13, 713797147, 'No 168/01 Galahitiyawa, Ganemulla', 'Dilshan Hasitha Fernando', '199625302594', '2020-12-19 08:33:15', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `permissionLevel` varchar(255) NOT NULL,
  `imageUrl` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `permissionLevel`, `imageUrl`) VALUES
('testuser', '$2a$10$PoqDOiCkwqGDeeYqbuUWvOFyvmWrFUc/zKINfBeblTB9HQX.M2Zs2', 'admin', 'http://testdomain.com/pictures/12'),
('testUser2', '$2a$10$Gys8Jl0ecGttn8nGEWgBZuwLl1.BiGHJ1MeApW4nRB0WJHEXKPcBS', 'user', 'Some imageUrl here');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `ordereditems`
--
ALTER TABLE `ordereditems`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `Id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `ordereditems`
--
ALTER TABLE `ordereditems`
  MODIFY `Id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderId` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
