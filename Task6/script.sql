DELIMITER ;;
CREATE DEFINER=`lamnv155`@`%` PROCEDURE `10_age_SG`()
begin
    select *
    from `user`
    where province = "TP Hồ Chí Minh"
    order by age desc
    limit 10;
end ;;


DELIMITER ;;
CREATE DEFINER=`lamnv155`@`%` PROCEDURE `find_by_user`(uname varchar(30))
begin
select user_name, full_name
from user
where user_name = uname;
end ;;

