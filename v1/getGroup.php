<?php  
include 'conn.php';
$Id = $_POST['Id'];
$Group_Name = $_POST['NameGroup'];

$sql = "SELECT table_user_group FROM prog_group WHERE NameGroup = '$Group_Name'";
$result = mysqli_query($conn, $sql);

$row = mysqli_fetch_assoc($result);
$response = array();

if(isset($row['table_user_group'])){
	$Group_Names = $row['table_user_group'];
	// echo $Group_Names;
	$sql1 = "SELECT Id FROM $Group_Names WHERE Id_user = '$Id'";
	$result1 = mysqli_query($conn, $sql1);

	$row = mysqli_fetch_assoc($result1);
    // $row = mysqli_fetch_array($result1);
	// $ImageGroup = $rows['Photo'];
    // echo $row;

	// $row = mysqli_fetch_assoc($result1);
	// echo $row;

	//$row1 = mysqli_fetch_assoc($result1);

	if($row != 0){
		$dados = 1;
		$response['0'] = $dados;
		echo json_encode($response);
	}else{
		$dados = 0;
		$response['0'] = $dados;
		echo json_encode($response);
	}
}else{
	$dados = 0;
	$response['0'] = $dados;
	echo json_encode($response);
}



?>