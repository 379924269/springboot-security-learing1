-- --------------------------------------------------------
-- 主机:                           192.168.0.202
-- 服务器版本:                        5.5.56-MariaDB - MariaDB Server
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 emm 的数据库结构
USE `emm`;

-- 导出  函数 emm.getChildList 结构
DROP FUNCTION IF EXISTS `getChildList`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `getChildList`(
	`rootId` INT

) RETURNS text CHARSET utf8
    COMMENT '获取所有子部门'
BEGIN 
DECLARE sChildList VARCHAR(10000); 
DECLARE sChildTemp VARCHAR(10000); 
SET sChildTemp =cast(rootId as CHAR); 
WHILE sChildTemp is not null DO 
IF (sChildList is not null) THEN 
SET sChildList = concat(sChildList,',',sChildTemp); 
ELSE 
SET sChildList = concat(sChildTemp); 
END IF; 
SELECT group_concat(id) INTO sChildTemp FROM organization where FIND_IN_SET(parent_id,sChildTemp)>0; 
END WHILE; 
RETURN sChildList; 
END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
