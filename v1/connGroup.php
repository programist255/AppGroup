<?php
	
include 'conn.php';
	$Id = $_POST['Id'];
	$Password = $_POST['Password'];
	$Group_Name = $_POST['NameGroup'];

	$IsUser = 0;
	$First_Name;
	$Name = '';
	$TableGroup = '';


	$sql = "SELECT table_user_group FROM prog_group WHERE NameGroup = '$Group_Name' AND PasswordGroup = '$Password'";
	$result = mysqli_query($conn, $sql);

	$row = mysqli_fetch_assoc($result);
	$response = array();

	if(isset($row['table_user_group'])){
		$TableGroup = $row['table_user_group'];

		$selID = "SELECT First_Name, Name FROM users WHERE Id = '$Id'";
		$result1 = mysqli_query($conn, $selID);
		$rowsUser = mysqli_fetch_assoc($result1);

		$First_Name = $rowsUser['First_Name'];
		$Name = $rowsUser['Name'];

		$user_in_group = "INSERT INTO user_in_group (Id, Id_User, Table_Group_Name) VALUES (NULL, '$Id', '$TableGroup')";

		$result3 = mysqli_query($conn,$user_in_group);

		$sqlcr = "INSERT INTO $TableGroup (Id, Id_user, FirstName, Name) VALUES (NULL, '$Id', '$First_Name', '$Name')";

		$result3 = mysqli_query($conn,$sqlcr);
		$dados = mysqli_num_rows($result);
		$response['0'] = $dados;
		echo json_encode($response);
}else{
$dados = 0;
$response['0'] = $dados;
echo json_encode($response);}
// header('Content-Type: application/json');
  ?>