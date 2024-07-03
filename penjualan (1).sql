-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 03, 2024 at 02:27 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penjualan`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_akun`
--

CREATE TABLE `tb_akun` (
  `id_akun` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `no_tlp` int(30) NOT NULL,
  `agama` varchar(30) NOT NULL,
  `alamat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_akun`
--

INSERT INTO `tb_akun` (`id_akun`, `username`, `password`, `jenis_kelamin`, `email`, `no_tlp`, `agama`, `alamat`) VALUES
(1, 'yuda531', 'yuda531', 'laki-laki', 'yuda@gmail.com', 815224982, 'Islam', 'Bandung'),
(2, 'agung', 'agung', 'laki-laki', 'agung@gmail.com', 81354532, 'Islam', 'Bandung'),
(4, 'Chintya', '987654', 'Perempuan', 'tya@gmail.com', 3216515, 'Budha', 'Bali');

-- --------------------------------------------------------

--
-- Table structure for table `tb_barang`
--

CREATE TABLE `tb_barang` (
  `id_barang` int(11) NOT NULL,
  `kd_barang` varchar(11) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `harga_beli` int(11) NOT NULL,
  `harga_jual` int(11) NOT NULL,
  `tgl_masuk` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_barang`
--

INSERT INTO `tb_barang` (`id_barang`, `kd_barang`, `nama_barang`, `jumlah_barang`, `harga_beli`, `harga_jual`, `tgl_masuk`) VALUES
(1, 'B0001', 'Rexus Daxa Asteria AX1 V2', 143, 339000, 360000, '2024-06-19'),
(2, 'B0002', 'Fantech Zeus X5s', 145, 150000, 175000, '2024-06-19'),
(3, 'B0003', 'Toolkit 15 in 1', 136, 40000, 60000, '2024-06-19'),
(4, 'B0004', 'DBE GM190', 144, 180000, 199000, '2024-06-19'),
(5, 'B0005', 'Jakemy Toolkit', 145, 45000, 50000, '2024-06-19');

-- --------------------------------------------------------

--
-- Table structure for table `tb_hitung_jual`
--

CREATE TABLE `tb_hitung_jual` (
  `id_hitung` int(11) NOT NULL,
  `kd_barang` varchar(11) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `harga_satuan` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `jumlah_jual` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `tb_hitung_jual`
--
DELIMITER $$
CREATE TRIGGER `tr_batal` AFTER DELETE ON `tb_hitung_jual` FOR EACH ROW BEGIN
UPDATE tb_barang set jumlah_barang=jumlah_barang+old.jumlah_jual
WHERE kd_barang=old.kd_barang;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tr_jual` AFTER INSERT ON `tb_hitung_jual` FOR EACH ROW BEGIN
 UPDATE tb_barang 
    SET jumlah_barang = jumlah_barang - new.jumlah_jual
WHERE kd_barang=new.kd_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_penjualan`
--

CREATE TABLE `tb_penjualan` (
  `id_penjualan` int(11) NOT NULL,
  `no_faktur` varchar(11) NOT NULL,
  `kd_barang` varchar(11) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `hsatuan` int(11) NOT NULL,
  `jumlah_jual` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `kembalian` int(11) NOT NULL,
  `tgl_penjualan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_penjualan`
--

INSERT INTO `tb_penjualan` (`id_penjualan`, `no_faktur`, `kd_barang`, `nama_barang`, `hsatuan`, `jumlah_jual`, `harga`, `bayar`, `kembalian`, `tgl_penjualan`) VALUES
(1, 'F0001', 'B0004', 'DBE GM190', 199000, 1, 199000, 200000, 1000, '2024-07-02'),
(2, 'F0002', 'B0003', 'Toolkit 15 in 1', 60000, 1, 60000, 500000, 439999, '2024-07-02'),
(3, 'F0002', 'B0001', 'Rexus Daxa Asteria AX1 V2', 360000, 143, 1, 500000, 439999, '2024-07-02'),
(4, 'F0003', 'B0001', 'Rexus Daxa Asteria AX1 V2', 360000, 1, 360000, 600000, 41000, '2024-07-02'),
(5, 'F0003', 'B0004', 'DBE GM190', 199000, 1, 199000, 600000, 41000, '2024-07-02'),
(6, 'F0004', 'B0001', 'Rexus Daxa Asteria AX1 V2', 360000, 1, 360000, 1000000, 62000, '2024-07-03'),
(7, 'F0004', 'B0003', 'Toolkit 15 in 1', 60000, 3, 180000, 1000000, 62000, '2024-07-03'),
(8, 'F0004', 'B0004', 'DBE GM190', 199000, 2, 398000, 1000000, 62000, '2024-07-03'),
(9, 'F0005', 'B0001', 'Rexus Daxa Asteria AX1 V2', 360000, 1, 360000, 600000, 60000, '2024-07-03'),
(10, 'F0005', 'B0003', 'Toolkit 15 in 1', 60000, 3, 180000, 600000, 60000, '2024-07-03'),
(11, 'F0006', 'B0002', 'Fantech Zeus X5s', 175000, 2, 350000, 550000, 1000, '2024-07-03'),
(12, 'F0006', 'B0004', 'DBE GM190', 199000, 1, 199000, 550000, 1000, '2024-07-03'),
(13, 'F0007', 'B0001', 'Rexus Daxa Asteria AX1 V2', 360000, 2, 720000, 1700000, 80000, '2024-07-03'),
(14, 'F0007', 'B0001', 'Rexus Daxa Asteria AX1 V2', 360000, 2, 720000, 1700000, 80000, '2024-07-03'),
(15, 'F0007', 'B0003', 'Toolkit 15 in 1', 60000, 3, 180000, 1700000, 80000, '2024-07-03'),
(16, 'F0008', 'B0005', 'Jakemy Toolkit', 50000, 5, 250000, 300000, 50000, '2024-07-03');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_akun`
--
ALTER TABLE `tb_akun`
  ADD PRIMARY KEY (`id_akun`);

--
-- Indexes for table `tb_barang`
--
ALTER TABLE `tb_barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `tb_hitung_jual`
--
ALTER TABLE `tb_hitung_jual`
  ADD PRIMARY KEY (`id_hitung`);

--
-- Indexes for table `tb_penjualan`
--
ALTER TABLE `tb_penjualan`
  ADD PRIMARY KEY (`id_penjualan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_akun`
--
ALTER TABLE `tb_akun`
  MODIFY `id_akun` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tb_barang`
--
ALTER TABLE `tb_barang`
  MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tb_hitung_jual`
--
ALTER TABLE `tb_hitung_jual`
  MODIFY `id_hitung` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_penjualan`
--
ALTER TABLE `tb_penjualan`
  MODIFY `id_penjualan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
