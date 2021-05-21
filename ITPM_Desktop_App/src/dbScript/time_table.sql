-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2021 at 07:22 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `time_table`
--

-- --------------------------------------------------------

--
-- Table structure for table `consecutive_sessions`
--

CREATE TABLE `consecutive_sessions` (
  `id` int(11) NOT NULL,
  `sessionCode` varchar(30) NOT NULL,
  `session_lec` int(11) NOT NULL,
  `session_tute` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consecutive_sessions`
--

INSERT INTO `consecutive_sessions` (`id`, `sessionCode`, `session_lec`, `session_tute`) VALUES
(24, 'CS101', 14, 15);

-- --------------------------------------------------------

--
-- Table structure for table `lecturer`
--

CREATE TABLE `lecturer` (
  `lec_id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `faculty` varchar(50) NOT NULL,
  `department` varchar(50) NOT NULL,
  `emp_id` int(6) NOT NULL,
  `level` int(11) NOT NULL,
  `rank` varchar(50) NOT NULL,
  `campus` varchar(50) NOT NULL,
  `building` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lecturer`
--

INSERT INTO `lecturer` (`lec_id`, `name`, `faculty`, `department`, `emp_id`, `level`, `rank`, `campus`, `building`) VALUES
(11, 'Prof.Dias', 'Engineering', 'Mechanical', 123001, 2, '2.123001', 'Malabe', 'Malabe Main building'),
(14, 'Prof Pieris', 'Engineering', 'Electrical', 230156, 4, '4.230156', 'Kandy', 'Kandy building 1'),
(15, 'Prof De Silva', 'Business', 'Logistics', 562103, 1, '1.562103', 'Metro Campus', 'Metro Main building'),
(32, 'Sankalpa', 'Computing', 'CSSE', 123401, 2, '2.123401', 'Metro Campus', 'Metro Main building'),
(33, 'Prof Kapila Perera', 'Computing', 'CSSE', 123654, 4, '4.123654', 'Malabe', 'Malabe BM');

-- --------------------------------------------------------

--
-- Table structure for table `lecturer_activedays`
--

CREATE TABLE `lecturer_activedays` (
  `id` int(11) NOT NULL,
  `emp_id` int(6) NOT NULL,
  `monday` int(11) NOT NULL,
  `tuesday` int(11) NOT NULL,
  `wednesday` int(11) NOT NULL,
  `thursday` int(11) NOT NULL,
  `friday` int(11) NOT NULL,
  `saturday` int(11) NOT NULL,
  `sunday` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lecturer_activedays`
--

INSERT INTO `lecturer_activedays` (`id`, `emp_id`, `monday`, `tuesday`, `wednesday`, `thursday`, `friday`, `saturday`, `sunday`) VALUES
(2, 123001, 0, 0, 1, 0, 1, 0, 1),
(5, 230156, 2, 2, 0, 0, 0, 0, 0),
(6, 562103, 8, 8, 0, 8, 0, 0, 0),
(23, 123401, 8, 0, 0, 0, 0, 0, 0),
(24, 123654, 3, 0, 0, 3, 0, 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `location_id` int(11) NOT NULL,
  `building_name` varchar(100) NOT NULL,
  `room_name` varchar(100) NOT NULL,
  `room_type` varchar(100) NOT NULL,
  `capacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`location_id`, `building_name`, `room_name`, `room_type`, `capacity`) VALUES
(4, 'A503', 'LAB 03', 'lab', 60),
(5, 'C 601', 'LEC 01', 'lecturehall', 100),
(6, 'C 106', 'LEC 05', 'lecturehall', 150);

-- --------------------------------------------------------

--
-- Table structure for table `managesessionroom`
--

CREATE TABLE `managesessionroom` (
  `id` int(11) NOT NULL,
  `session_id` int(11) NOT NULL,
  `room_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `managesessionroom`
--

INSERT INTO `managesessionroom` (`id`, `session_id`, `room_name`) VALUES
(21, 5, 'LEC 01'),
(22, 7, 'LEC 05'),
(23, 12, 'LAB 03'),
(24, 6, 'LEC 05'),
(25, 8, 'LEC 05'),
(26, 10, 'LEC 01'),
(27, 11, 'LEC 05'),
(28, 15, 'LAB 03'),
(29, 14, 'LAB 03');

-- --------------------------------------------------------

--
-- Table structure for table `nonoverlap_sessions`
--

CREATE TABLE `nonoverlap_sessions` (
  `id` int(11) NOT NULL,
  `sessionCode` varchar(30) NOT NULL,
  `nonoverlapSessionID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `notavailabletime`
--

CREATE TABLE `notavailabletime` (
  `id` int(11) NOT NULL,
  `duration` int(10) NOT NULL,
  `session_ID` int(15) NOT NULL,
  `first_lecturer` varchar(20) NOT NULL,
  `second_lecturer` varchar(20) NOT NULL,
  `group_ID` varchar(15) NOT NULL,
  `day` varchar(10) NOT NULL,
  `time` varchar(50) NOT NULL,
  `endTime` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notavailabletime`
--

INSERT INTO `notavailabletime` (`id`, `duration`, `session_ID`, `first_lecturer`, `second_lecturer`, `group_ID`, `day`, `time`, `endTime`) VALUES
(23, 1, 6, 'Prof.Dias', 'Prof Pieris', 'Y1.S1.IT.2', 'Monday', '09:30:00', '10:30:00'),
(24, 1, 5, 'Prof Pieris', 'Prof De Silva', 'Y1.S1.IT.2', 'Monday', '11:30:00', '12:30:00'),
(25, 3, 14, 'Prof De Silva', 'Prof Pieris', 'Y1.S1.IT.1', 'Wednesday', '09:30:00', '12:30:00'),
(26, 1, 11, 'Prof De Silva', 'Prof.Dias', 'Y1.S1.IT.2', 'Saturday', '14:30:00', '15:30:00'),
(27, 2, 8, 'Sankalpa', 'Prof De Silva', 'Y1.S1.IT.2.1', 'Thursday', '11:30:00', '13:30:00'),
(28, 1, 6, 'Prof.Dias', 'Prof Pieris', 'Y1.S1.IT.2', 'Monday', '10:30:00', '11:30:00'),
(29, 1, 6, 'Prof.Dias', 'Prof Pieris', 'Y1.S1.IT.2', 'Monday', '13:30:00', '14:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `notavailable_location`
--

CREATE TABLE `notavailable_location` (
  `id` int(11) NOT NULL,
  `session_ID` int(15) NOT NULL,
  `lRoom` varchar(15) NOT NULL,
  `lDay` varchar(15) NOT NULL,
  `startTime` varchar(10) NOT NULL,
  `endTime` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notavailable_location`
--

INSERT INTO `notavailable_location` (`id`, `session_ID`, `lRoom`, `lDay`, `startTime`, `endTime`) VALUES
(23, 6, 'LEC 05', 'Monday', '09:30:00', '11:30:00'),
(24, 11, 'LEC 05', 'Saturday', '10:30:00', '12:30:00'),
(25, 12, 'LAB 03', 'Friday', '09:30:00', '11:30:00'),
(26, 7, 'LEC 05', 'Wednesday', '13:30:00', '15:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `parallel_sessions`
--

CREATE TABLE `parallel_sessions` (
  `id` int(11) NOT NULL,
  `sessionCode` varchar(30) NOT NULL,
  `parallelSessionID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parallel_sessions`
--

INSERT INTO `parallel_sessions` (`id`, `sessionCode`, `parallelSessionID`) VALUES
(23, 'PS101', 8),
(24, 'PS101', 10);

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `id` int(11) NOT NULL,
  `first_lecturer` varchar(100) NOT NULL,
  `sec_lecturer` varchar(100) NOT NULL,
  `tag` varchar(30) NOT NULL,
  `group_id` varchar(50) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `no_of_students` int(3) NOT NULL,
  `day` varchar(50) NOT NULL,
  `duration` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `session`
--

INSERT INTO `session` (`id`, `first_lecturer`, `sec_lecturer`, `tag`, `group_id`, `subject`, `no_of_students`, `day`, `duration`) VALUES
(5, 'Prof Pieris', 'Prof De Silva', 'Tutorial', 'Y1.S1.IT.2', 'IT4563 - DSA', 10, 'Monday', 1),
(6, 'Prof.Dias', 'Prof Pieris', 'Tutorial', 'Y1.S1.IT.2', 'IT3012 - IWT', 100, 'Monday', 1),
(7, 'Prof Kapila Perera', 'Prof.Dias', 'Lecture', 'Y1.S2.SE.3', 'IT4521 - ICS', 50, 'Wednesday', 2),
(8, 'Sankalpa', 'Prof De Silva', 'Lab|Practical', 'Y1.S1.IT.2.1', 'IT3020 - MAD', 60, 'Thursday', 2),
(10, 'Prof De Silva', 'Prof Pieris', 'Lecture', 'Y3.S1.CSNE.2', 'IT3012 - IWT', 20, 'Thursday', 2),
(11, 'Prof De Silva', 'Prof.Dias', 'Tutorial', 'Y1.S1.IT.2', 'IT3020 - MAD', 10, 'Saturday', 1),
(12, 'Prof Kapila Perera', 'Prof Pieris', 'Lab|Practical', 'Y1.S1.IT.2.1', 'IT5020 - DS', 50, 'Friday', 1),
(14, 'Prof.Dias', 'Prof Pieris', 'Lecture', 'Y1.S1.IT.1', 'IT4563 - DSA', 100, 'Monday', 2),
(15, 'Prof.Dias', 'Prof Pieris', 'Tutorial', 'Y1.S1.IT.1', 'IT4563 - DSA', 100, 'Monday', 1);

-- --------------------------------------------------------

--
-- Table structure for table `student_group`
--

CREATE TABLE `student_group` (
  `student_group_id` int(11) NOT NULL,
  `year_and_semester` varchar(50) NOT NULL,
  `programme` varchar(50) NOT NULL,
  `group_no` int(10) NOT NULL,
  `sub_group_no` int(10) NOT NULL,
  `group_id` varchar(50) NOT NULL,
  `sub_group_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_group`
--

INSERT INTO `student_group` (`student_group_id`, `year_and_semester`, `programme`, `group_no`, `sub_group_no`, `group_id`, `sub_group_id`) VALUES
(1, '(Y1.S1) Year 1 Semester 1', 'IT', 1, 1, 'Y1.S1.IT.1', 'Y1.S1.IT.1.1'),
(2, '(Y2.S1) Year 2 Semester 1', 'IT', 2, 2, 'Y2.S1.IT.2', 'Y2.S1.IT.2.1'),
(3, '(Y1.S1) Year 1 Semester 1', 'SE', 1, 1, 'Y1.S1.SE.1', 'Y1.S1.SE.1.1'),
(4, '(Y1.S1) Year 1 Semester 1', 'IT', 1, 1, 'Y1.S1.IT.1', 'Y1.S1.IT.1.2');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `sub_id` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `semester` varchar(50) NOT NULL,
  `subject_code` varchar(10) NOT NULL,
  `subject_name` varchar(100) NOT NULL,
  `lecture_hours` int(11) NOT NULL,
  `tute_hours` int(11) NOT NULL,
  `lab_hours` int(11) NOT NULL,
  `evaluation_hours` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`sub_id`, `year`, `semester`, `subject_code`, `subject_name`, `lecture_hours`, `tute_hours`, `lab_hours`, `evaluation_hours`) VALUES
(1, 1, 'Second Semester', 'IT3201', 'Chemmis', 5, 2, 2, 2),
(2, 1, 'Second Semester', 'IT4563', 'DSA', 0, 0, 0, 0),
(7, 3, 'First Semester', 'IT3012', 'IWT', 6, 1, 2, 0),
(9, 2, 'Second Semester', 'IT3020', 'MAD', 2, 2, 0, 0),
(10, 3, 'First Semester', 'IT5020', 'DS', 5, 2, 0, 0),
(11, 4, 'Second Semester', 'IT4521', 'ICS', 2, 2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE `tag` (
  `tag_id` int(11) NOT NULL,
  `tag_name` varchar(30) NOT NULL,
  `tag_code` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`tag_id`, `tag_name`, `tag_code`) VALUES
(1, 'Lecture', 'T1001');

-- --------------------------------------------------------

--
-- Table structure for table `timeslot`
--

CREATE TABLE `timeslot` (
  `id` int(5) NOT NULL,
  `start_time` varchar(10) NOT NULL,
  `end_time` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `timeslot`
--

INSERT INTO `timeslot` (`id`, `start_time`, `end_time`) VALUES
(17, '08:30:00', '09:30:00'),
(38, '09:30:00', '10:30:00'),
(39, '10:30:00', '11:30:00'),
(40, '11:30:00', '12:30:00'),
(41, '12:30:00', '13:30:00'),
(42, '13:30:00', '14:30:00'),
(43, '14:30:00', '15:30:00'),
(44, '15:30:00', '16:30:00'),
(45, '16:30:00', '17:30:00'),
(46, '18:30:00', '18:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `timeslot_session`
--

CREATE TABLE `timeslot_session` (
  `id` int(5) NOT NULL,
  `session` int(15) NOT NULL,
  `timeslot` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `workingdays`
--

CREATE TABLE `workingdays` (
  `id` int(5) NOT NULL,
  `workingDays` varchar(60) NOT NULL,
  `workingTimePerDay` int(5) NOT NULL,
  `minutes` int(5) NOT NULL,
  `noOfWorkingDays` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `workingdays`
--

INSERT INTO `workingdays` (`id`, `workingDays`, `workingTimePerDay`, `minutes`, `noOfWorkingDays`) VALUES
(24, 'Monday Tuesday Wednesday Thursday Friday', 4, 30, 5),
(25, 'Monday Tuesday Wednesday Thursday Friday', 9, 0, 5),
(26, 'Monday Wednesday', 6, 45, 2),
(27, 'Monday Tuesday Wednesday Thursday Saturday', 8, 45, 5),
(28, 'Monday Tuesday Wednesday Thursday Friday', 9, 0, 5),
(29, 'Monday Tuesday Wednesday Thursday Saturday', 6, 10, 5),
(36, 'Monday Tuesday Thursday Friday Sunday', 8, 9, 5),
(38, ' Sunday', 7, 6, 5),
(39, ' Sunday', 8, 0, 5),
(42, 'Monday Tuesday Wednesday Thursday Friday', 8, 0, 5),
(44, 'Monday Tuesday Wednesday Thursday Friday', 7, 30, 5),
(46, 'Monday Tuesday Wednesday Thursday Friday', 9, 0, 5),
(48, 'Monday Tuesday Wednesday Thursday Friday', 7, 30, 5),
(50, ' Saturday Sunday', 3, 30, 2),
(51, 'Monday Wednesday Thursday Friday Saturday', 5, 0, 5),
(52, 'Monday Tuesday Wednesday Thursday Saturday', 5, 30, 5),
(53, 'Monday Tuesday Thursday Friday Saturday', 8, 0, 5),
(54, 'Monday Tuesday Wednesday Thursday Friday', 9, 0, 5),
(55, 'Monday Tuesday Wednesday Thursday Friday', 8, 30, 5),
(56, 'Monday Tuesday Thursday Friday Sunday', 5, 30, 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `consecutive_sessions`
--
ALTER TABLE `consecutive_sessions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lecturer`
--
ALTER TABLE `lecturer`
  ADD PRIMARY KEY (`lec_id`),
  ADD UNIQUE KEY `emp_id` (`emp_id`);

--
-- Indexes for table `lecturer_activedays`
--
ALTER TABLE `lecturer_activedays`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `emp_id` (`emp_id`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`location_id`);

--
-- Indexes for table `managesessionroom`
--
ALTER TABLE `managesessionroom`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nonoverlap_sessions`
--
ALTER TABLE `nonoverlap_sessions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notavailabletime`
--
ALTER TABLE `notavailabletime`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notavailable_location`
--
ALTER TABLE `notavailable_location`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `parallel_sessions`
--
ALTER TABLE `parallel_sessions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_group`
--
ALTER TABLE `student_group`
  ADD PRIMARY KEY (`student_group_id`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`sub_id`),
  ADD UNIQUE KEY `subject_code` (`subject_code`);

--
-- Indexes for table `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`tag_id`);

--
-- Indexes for table `timeslot`
--
ALTER TABLE `timeslot`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `timeslot_session`
--
ALTER TABLE `timeslot_session`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `workingdays`
--
ALTER TABLE `workingdays`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consecutive_sessions`
--
ALTER TABLE `consecutive_sessions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `lecturer`
--
ALTER TABLE `lecturer`
  MODIFY `lec_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `lecturer_activedays`
--
ALTER TABLE `lecturer_activedays`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `location_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `managesessionroom`
--
ALTER TABLE `managesessionroom`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `nonoverlap_sessions`
--
ALTER TABLE `nonoverlap_sessions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `notavailabletime`
--
ALTER TABLE `notavailabletime`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `notavailable_location`
--
ALTER TABLE `notavailable_location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `parallel_sessions`
--
ALTER TABLE `parallel_sessions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `session`
--
ALTER TABLE `session`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `student_group`
--
ALTER TABLE `student_group`
  MODIFY `student_group_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `sub_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tag`
--
ALTER TABLE `tag`
  MODIFY `tag_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `timeslot`
--
ALTER TABLE `timeslot`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `timeslot_session`
--
ALTER TABLE `timeslot_session`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `workingdays`
--
ALTER TABLE `workingdays`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
