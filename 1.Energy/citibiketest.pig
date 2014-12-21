records = LOAD '/Users/lsphate/Desktop/BD/201408.csv' USING PigStorage(',') AS 
	(tripduration:int, starttime:chararray, stoptime:chararray,
	start_station_id:int, start_station_name:chararray,
	start_station_latitude:double, start_station_longitude:double,
	end_station_id:int, end_station_name:chararray,
	end_station_latitude:double, end_station_longitude:double,
	bikeid:int, usertype:int, birth_year:int, gender:int);

station_pairs = GROUP records BY (start_station_id, end_station_id);

location_info = FOREACH station_pairs GENERATE
	group,
	AVG(records.start_station_latitude),
	AVG(records.start_station_longitude),
	AVG(records.end_station_latitude),
	AVG(records.end_station_longitude),
	COUNT(records);

rad_diff = FOREACH location_info GENERATE
	group, 0, 0,
	$3-$1, $4-$2,
	$5;

manhattan_distance = FOREACH rad_diff GENERATE
	group,
	-- sphere_radius = SIN($1)*SIN($3)+COS($1)*COS($3)*COS($2-$4)
	6371*ACOS(SIN($1)*SIN($3)+COS($1)*COS($3)*COS($2-$4))*3.1415/180,
	$5;

total_distance = FOREACH manhattan_distance GENERATE group, $1*$2 as sum;

grouped = GROUP total_distance ALL;
	
saving = FOREACH grouped GENERATE SUM(total_distance.sum) as result;

STORE saving INTO '/Users/lsphate/Desktop/BD/output/201408';