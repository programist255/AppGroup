<?php

$con = mysqli_connect("localhost","root","Mysql19.09misha","android");

$sql = "select Photo from prog_group";
	
	$res = mysqli_query($con,$sql);
	
	$result = array();
	
	while($row = mysqli_fetch_array($res)){
		array_push($result,array('url'=>$row['Photo']));
	}
	
	echo json_encode(array("result"=>$result));
	?>