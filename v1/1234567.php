<?php
 
	if($_SERVER['REQUEST_METHOD']=='GET'){
		$id = '53';
		$conn = mysqli_connect("localhost","root","Mysql19.09misha","android");
		$sql = "select * from images where id = '$id'";

		
		$r = mysqli_query($conn,$sql);
		
		$result = mysqli_fetch_array($r);
		
		header('content-type: image/jpeg');
	
		echo base64_decode($result['image']);
		
		mysqli_close($conn);
		
	
		
	}else{
		echo "Error";
	}