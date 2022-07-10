-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 23, 2022 at 03:16 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `asetku`
--

-- --------------------------------------------------------

--
-- Table structure for table `assets`
--

CREATE TABLE `assets` (
  `id` int(11) NOT NULL,
  `kode_aset` varchar(60) NOT NULL,
  `category_id` int(11) NOT NULL,
  `nama_aset` varchar(30) NOT NULL,
  `nominal_aset` bigint(20) NOT NULL,
  `keterangan` varchar(30) NOT NULL,
  `current_branch_id` int(11) NOT NULL,
  `current_divisi_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `assets`
--

INSERT INTO `assets` (`id`, `kode_aset`, `category_id`, `nama_aset`, `nominal_aset`, `keterangan`, `current_branch_id`, `current_divisi_id`) VALUES
(1, 'd167337f-e3b6-472e-8b69-74a6463583da', 4, 'Laptop Asus', 7500000, '8 GB RAM', 5, 1),
(2, 'a51ef929-ade1-4d2d-98e5-527c0d76320c', 1, 'Pulpen Faster', 10000, 'pupen', 5, 2),
(3, '225C8596', 4, 'iPhone 13', 13000000, 'Handphone', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `id` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `branch`
--

INSERT INTO `branch` (`id`, `nama`) VALUES
(1, 'Jakarta Barat'),
(2, 'Jakarta Utara'),
(3, 'Jakarta Selatan'),
(4, 'Jakarta Utara'),
(5, 'Jakarta Pusat');

-- --------------------------------------------------------

--
-- Table structure for table `branch_divisi`
--

CREATE TABLE `branch_divisi` (
  `branch_id` int(11) NOT NULL,
  `divisi_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `category_assets`
--

CREATE TABLE `category_assets` (
  `id` int(11) NOT NULL,
  `nama` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category_assets`
--

INSERT INTO `category_assets` (`id`, `nama`) VALUES
(1, 'Alat Tulis dan Perlengkapan Kantor'),
(2, 'Barang Cetakan dan Alat Tulis'),
(3, 'Transportasi'),
(4, 'Inventaris dan Perlengkapan Kerja');

-- --------------------------------------------------------

--
-- Table structure for table `divisi`
--

CREATE TABLE `divisi` (
  `id` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `budget` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `divisi`
--

INSERT INTO `divisi` (`id`, `nama`, `budget`) VALUES
(1, 'Finance', '0'),
(2, 'Accountant', '0'),
(3, 'Human Resources', '0'),
(4, 'Tax', '0'),
(5, 'Sales', '0'),
(6, 'Public Relationship', '0');

-- --------------------------------------------------------

--
-- Table structure for table `history_assets`
--

CREATE TABLE `history_assets` (
  `id` int(11) NOT NULL,
  `assets_id` int(11) NOT NULL,
  `old_branch_id` int(11) NOT NULL,
  `old_divisi_id` int(11) NOT NULL,
  `new_branch_id` int(11) NOT NULL,
  `new_divisi_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `nama`) VALUES
(1, 'Administrator'),
(2, 'Accounting'),
(3, 'Head Dept');

-- --------------------------------------------------------

--
-- Table structure for table `submission_assets`
--

CREATE TABLE `submission_assets` (
  `id` int(11) NOT NULL,
  `assets_id` int(11) NOT NULL,
  `applicant_branch_id` int(11) NOT NULL,
  `target_branch_id` int(11) NOT NULL,
  `status` enum('menunggu','setuju','batal') DEFAULT 'menunggu'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nip` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `branch_id` int(11) NOT NULL,
  `divisi_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `nip`, `password`, `nama`, `branch_id`, `divisi_id`, `role_id`) VALUES
(1, '43502010', 'user', 'Muhammad Quraisy', 5, 3, 1),
(2, '43502017', 'admin', 'Muhammad Bayhaqi', 5, 2, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assets`
--
ALTER TABLE `assets`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kode_aset` (`kode_aset`),
  ADD KEY `fk_branch_assets` (`current_branch_id`),
  ADD KEY `fk_divisi_assets` (`current_divisi_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `branch_divisi`
--
ALTER TABLE `branch_divisi`
  ADD KEY `fk_branch_divisi` (`branch_id`),
  ADD KEY `fk_divisi_branch` (`divisi_id`);

--
-- Indexes for table `category_assets`
--
ALTER TABLE `category_assets`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `divisi`
--
ALTER TABLE `divisi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `history_assets`
--
ALTER TABLE `history_assets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_history_assets` (`assets_id`),
  ADD KEY `fk_old_branch_assets` (`old_branch_id`),
  ADD KEY `fk_old_divisi_assets` (`old_divisi_id`),
  ADD KEY `fk_new_branch_assets` (`new_branch_id`),
  ADD KEY `fk_new_divisi_assets` (`new_divisi_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `submission_assets`
--
ALTER TABLE `submission_assets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_submission_assets` (`assets_id`),
  ADD KEY `fk_applicant_branch_submission` (`applicant_branch_id`),
  ADD KEY `fk_target_branch_submission` (`target_branch_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nip` (`nip`),
  ADD KEY `fk_divisi_user` (`divisi_id`),
  ADD KEY `fk_branch_user` (`branch_id`),
  ADD KEY `fk_role_user` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assets`
--
ALTER TABLE `assets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `branch`
--
ALTER TABLE `branch`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `category_assets`
--
ALTER TABLE `category_assets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `divisi`
--
ALTER TABLE `divisi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `history_assets`
--
ALTER TABLE `history_assets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `submission_assets`
--
ALTER TABLE `submission_assets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assets`
--
ALTER TABLE `assets`
  ADD CONSTRAINT `assets_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category_assets` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_branch_assets` FOREIGN KEY (`current_branch_id`) REFERENCES `branch` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_divisi_assets` FOREIGN KEY (`current_divisi_id`) REFERENCES `divisi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `branch_divisi`
--
ALTER TABLE `branch_divisi`
  ADD CONSTRAINT `fk_branch_divisi` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_divisi_branch` FOREIGN KEY (`divisi_id`) REFERENCES `divisi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `history_assets`
--
ALTER TABLE `history_assets`
  ADD CONSTRAINT `fk_history_assets` FOREIGN KEY (`assets_id`) REFERENCES `assets` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_new_branch_assets` FOREIGN KEY (`new_branch_id`) REFERENCES `branch` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_new_divisi_assets` FOREIGN KEY (`new_divisi_id`) REFERENCES `divisi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_old_branch_assets` FOREIGN KEY (`old_branch_id`) REFERENCES `branch` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_old_divisi_assets` FOREIGN KEY (`old_divisi_id`) REFERENCES `divisi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `submission_assets`
--
ALTER TABLE `submission_assets`
  ADD CONSTRAINT `fk_applicant_branch_submission` FOREIGN KEY (`applicant_branch_id`) REFERENCES `branch` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_submission_assets` FOREIGN KEY (`assets_id`) REFERENCES `assets` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_target_branch_submission` FOREIGN KEY (`target_branch_id`) REFERENCES `branch` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_branch_user` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_divisi_user` FOREIGN KEY (`divisi_id`) REFERENCES `divisi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_role_user` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
