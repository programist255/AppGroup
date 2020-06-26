<?php

require_once('conn.php');
	$NameGroup = $_POST['NameGroup'];
	// $Photo = $_POST['Photo'];
	$Title = $_POST['Title'];
	$Texts = $_POST['Texts'];

	// echo $Photo;

// 	$target = "images/";
// if(!file_exists($target)){
// 	mkdir($target, 0777,true);
// }

// $target = $target ."/".rand()  . "_" . time() . ".jpeg";

// if(file_put_contents($target, base64_decode($Photo))) {
// 	echo json_encode([
// 		"Message" => "OK"]);
// }else{
// 	echo json_encode([
// 		"Message" => "ERROR"]);
// }
 // $actualpath = "http://192.168.0.101/Android/v1/$target";
	$src = "SELECT task_group FROM prog_group WHERE NameGroup = '$NameGroup'";
	$result = mysqli_query($conn, $src);
    $row = $result->fetch_assoc();
	$TaskGroup = $row['task_group'];
	$sqlct = "INSERT INTO $TaskGroup (Id, Title, Texts) VALUES (NULL, '$Title', '$Texts')";
    $result3 = mysqli_query($conn,$sqlct);
    
?>