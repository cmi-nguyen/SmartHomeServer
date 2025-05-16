-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2025 at 02:46 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smarthome`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL,
  `account_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `pin` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`account_id`, `account_name`, `email`, `password`, `phone`, `pin`, `status`, `type`) VALUES
(1, 'nguyen4', 'test4@gmail.com', '123456', '12345678', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `account_seq`
--

CREATE TABLE `account_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account_seq`
--

INSERT INTO `account_seq` (`next_val`) VALUES
(51);

-- --------------------------------------------------------

--
-- Table structure for table `logentries`
--

CREATE TABLE `logentries` (
  `log_id` int(11) NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logentries`
--

INSERT INTO `logentries` (`log_id`, `action`, `date`, `user`) VALUES
(352, 'Turn On LED1', '5/16/2025, 12:44:58 AM', 'nguyen4'),
(353, 'Turn Off LED2', '5/16/2025, 12:44:59 AM', 'nguyen4'),
(354, 'Close Door', '5/16/2025, 12:44:59 AM', 'nguyen4'),
(355, 'Turn On LED1', '5/16/2025, 12:48:11 AM', 'nguyen4'),
(356, 'Turn Off LED2', '5/16/2025, 12:48:11 AM', 'nguyen4'),
(357, 'Close Door', '5/16/2025, 12:48:11 AM', 'nguyen4'),
(402, 'Test', '12/4/2024, 9:44:53 AM', 'Nguyn'),
(403, 'LED0 1', '5/16/2025, 4:26:26 AM', 'nguyen4'),
(404, 'DOOR 1', '5/16/2025, 4:26:35 AM', 'nguyen4'),
(405, 'DOOR 0', '5/16/2025, 4:27:14 AM', 'nguyen4'),
(406, 'LED1 1', '5/16/2025, 4:27:50 AM', 'nguyen4'),
(407, 'LED0 0', '5/16/2025, 4:27:56 AM', 'nguyen4'),
(408, 'DOOR 1', '5/16/2025, 4:30:56 AM', 'nguyen4'),
(409, 'LED0 1', '5/16/2025, 4:45:40 AM', 'nguyen4'),
(410, 'LED1 1', '5/16/2025, 4:45:42 AM', 'nguyen4'),
(411, 'DOOR 0', '5/16/2025, 4:45:45 AM', 'nguyen4'),
(412, 'DOOR 1', '5/16/2025, 4:46:14 AM', 'nguyen4'),
(413, 'LED1 0', '5/16/2025, 4:46:16 AM', 'nguyen4'),
(414, 'LED0 0', '5/16/2025, 4:46:17 AM', 'nguyen4');

-- --------------------------------------------------------

--
-- Table structure for table `logentries_seq`
--

CREATE TABLE `logentries_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logentries_seq`
--

INSERT INTO `logentries_seq` (`next_val`) VALUES
(501);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `logentries`
--
ALTER TABLE `logentries`
  ADD PRIMARY KEY (`log_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
