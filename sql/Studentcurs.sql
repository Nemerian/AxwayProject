--
-- Database: `dbtest`
--
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cnp` int(13) NOT NULL,
  `nume` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `curs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cod` varchar(15) NOT NULL,
  `titlu` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `student_curs` (
  `ids` int(11) NOT NULL,
  `idc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD KEY `nume` (`nume`);

--
-- Indexes for table `curs`
--
ALTER TABLE `curs`
  ADD KEY `titlu` (`titlu`);

--
-- Indexes for table `student_curs`
--
ALTER TABLE `student_curs`
  ADD KEY `ids` (`ids`),
  ADD KEY `idc` (`idc`);

