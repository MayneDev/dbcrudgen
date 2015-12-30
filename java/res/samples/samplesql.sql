CREATE DATABASE IF NOT EXISTS `where_there_is_no_doc`

CREATE TABLE IF NOT EXISTS `firstaids` (
  `ailment` varchar(128) NOT NULL,
  `ailment_information` text NOT NULL,
  `ailment_causes` text,
  `ailement_prevention` text,
  `ailment_signs` text,
  `ailment_symptoms` text,
  `ailment_cautions` text,
  `ailment_medication` text,
  `ailment_treatmeant` text,
  `ailment_treatmeant_precautions` text,
  `ailment_treatment_position` text NOT NULL,
  `ailment_short_notes` text,
`id_firstaid` int(11) NOT NULL
);