--
--

-- https://m.blog.naver.com/flyproject/222340250144
select *
from V$OPTION
where parameter = 'Spatial'
;

select *
from mdsys.sdo_cs_srs
-- where cs_name like '%WGS%'
--    or cs_name like '%Kakao%'
order by cs_name
;