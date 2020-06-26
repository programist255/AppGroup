<?php  

include 'conn.php';
	$NameGroup = $_POST['NameGroup'];

	$response = array();

	// $NameGroup = str_replace(" ", "_", $nameGroup);
	$src = "SELECT task_group FROM prog_group WHERE NameGroup = '$NameGroup'";
	$result = mysqli_query($conn, $src);
    $row = $result->fetch_assoc();
	$TaskGroup = $row['task_group'];
	// echo $TaskGroup;
	
	$sqlct = "SELECT Title, Texts FROM $TaskGroup";
    $result1 = mysqli_query($conn,$sqlct);
    while($row = mysqli_fetch_assoc($result1)){
    		$response[] = $row;
    	}
    echo json_encode(array("task_group"=>$response));
?>