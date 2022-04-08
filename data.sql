/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.26 : Database - detection_reminder
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`detection_reminder` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `detection_reminder`;

/*Table structure for table `data` */

DROP TABLE IF EXISTS `data`;

CREATE TABLE `data` (
  `name` text,
  `phone` text,
  `tail` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `data` */

insert  into `data`(`name`,`phone`,`tail`) values ('冯迪','15801448268',5),('张丁祎','15932752003',3),('邓思佳','13230275926',7),('刘小溪','18135820720',9),('谭浩','18547125546',5),('林靖涵','18524958888',9),('刘思萌','15144122787',0),('李卓然','18182798499',7),('陈溢阳','13585425321',9),('陈乐','15151799630',1),('韩幽竹','18761186036',5),('朱嘉俊','15558670925',3),('章韦雯','18968022056',7),('黄羽','13855946250',2),('谢宇','15656653985',7),('张岩','19525259569',8),('丁于桐','13909693641',0),('俞程','13159064025',0),('李铖','13779911976',6),('侯晓晨','15589236383',8),('汪芒许飞','18653844828',9),('曹洪桤','13210772602',4),('陈浩','13156967731',9),('闵德硕','15269735358',0),('陈勇','13869784911',8),('郭云鹏','18615167080',3),('唐浩然','17854427801',6),('马宇杰','15563272497',2),('徐宇翔','16653072086',6),('杨洪鹏','18854590096',7),('刘洋','13280126221',0),('李倩','15550991811',8),('仲子辰','15063803288',7),('王家乐','13687676909',9),('马玉洁','19806365331',0),('李金哲','18353254599',7),('郭潇飞','15136371936',6),('牛梦兰','19588938681',7),('张庭凯','19510186536',0),('陈崇铭','19510186885',1),('王瑞婷','18239093656',3),('冯飞扬','13545590211',5),('黄益坚','19907118294',7),('方静','13203335663',4),('侯玉祺','15889766016',1),('许悦','17876429241',2),('朱济来','19510186598',3),('陈辉舞','17320355163',6),('吴曈','13368090095',8),('冯心怡','15353870838',2),('刘宪顺','19510186808',2),('张安琪','13753505537',4);

/*Table structure for table `exclude` */

DROP TABLE IF EXISTS `exclude`;

CREATE TABLE `exclude` (
  `name` text,
  `phone` text,
  `tail` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `exclude` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
