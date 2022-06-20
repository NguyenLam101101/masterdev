DELIMITER ;;
CREATE DEFINER=`lamnv155`@`%` PROCEDURE `class_max_average`()
begin
    select d.id_class, avg(d.mark) as avg_mark
    from detail_class d
    inner join class c
    on d.id_class = c.id
	group by c.id
    order by avg_mark desc
    limit 10;
end ;;

DELIMITER ;;
CREATE DEFINER=`lamnv155`@`%` PROCEDURE `student_max_average`()
begin
    select d.id_student, s.`name`, avg(d.mark) as avg_mark
    from detail_class d
	inner join student s
    on d.id_student = s.id
    group by d.id_student
    order by avg_mark desc
    limit 10;
end ;;

DELIMITER ;;
CREATE DEFINER=`lamnv155`@`%` PROCEDURE `student_pass_all`()
begin
select s.id, s.`name`
from student s
where s.id not in
(select d.id_student
from detail_class d
where d.mark < 4);
end ;;

DELIMITER ;;
CREATE DEFINER=`lamnv155`@`%` PROCEDURE `subject_max_average`()
begin
    select t.id_subject, s.`name`, avg(d.mark) as avg_mark
    from detail_class d
    inner join class c
    on d.id_class = c.id
	inner join teacher t
    on c.id_teacher = t.id
    inner join subject s
    on t.id_subject = s.id
    group by s.id
    order by avg_mark desc
    limit 10;
end ;;

DELIMITER ;;
CREATE DEFINER=`lamnv155`@`%` PROCEDURE `subject_max_fail`()
begin
    select tb1.id_subject, tb1.`name` , num_fail/total rate_fail
    from (select count(mark) num_fail, t.id_subject, s.`name`
	from detail_class d
	inner join class c
    on d.id_class = c.id
    inner join teacher t
    on c.id_teacher = t.id
    inner join `subject` s
    on t.id_subject = s.id    
    where mark < 4
    group by s.id) tb1
    inner join
    (select count(d.id_student) total, t.id_subject
    from detail_class d
    inner join class c
    on d.id_class = c.id
    inner join teacher t
    on c.id_teacher = t.id
    inner join `subject` s
    on t.id_subject = s.id    
    group by s.id) tb2
    on tb1.id_subject = tb2.id_subject
    order by rate_fail desc
    limit 10;
end ;;

DELIMITER ;;
CREATE DEFINER=`lamnv155`@`%` PROCEDURE `summary`()
begin
    declare total int;
    set total = (
    select count(mark)
    from detail_class);
    
    select
    (select count(mark)/total 
    from detail_class
    where mark < 4) as weak,
    
    (select count(mark)/total 
    from detail_class
    where mark >=4 and mark <6) as `medium`,
    
    (select count(mark)/total 
    from detail_class
    where mark >=6 and mark <8) as pretty,
    
    (select count(mark)/total 
    from detail_class
    where mark >=8 and mark <9) as good,
    
    (select count(mark)/total as excellent
    from detail_class
    where mark >= 9) as excellent;
end ;;

DELIMITER ;;
CREATE DEFINER=`lamnv155`@`%` PROCEDURE `total_average_mark`()
begin
  select avg(mark) total_avg_mark
  from detail_class;
end ;;

