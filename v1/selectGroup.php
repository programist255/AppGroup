<?php

include 'conn.php';
$IdUser = $_POST['Id'];//
//echo $IdUser."</br>";


$stmt = $conn->prepare("SELECT Table_Group_Name FROM user_in_group WHERE Id_User = '$IdUser'");
$stmt->execute();
$stmt->bind_result($Table_Group_Name);
$products = array();
while($stmt->fetch()){
	$temp = array();
	$temp['Table_Group_Name'] = $Table_Group_Name; 

	array_push($products, $temp);
	}


$response = array();
foreach ($products as $key => $ar){
	
	$sat = $ar['Table_Group_Name'];
	// echo "</br>" . $sat."</br>";
	$src = "SELECT Photo, NameGroup FROM prog_group WHERE table_user_group = '$sat'";
	$result = mysqli_query($conn, $src);
    $row = mysqli_fetch_assoc($result);
    $response[] = $row;
}
header('Content-Type: application/json');
echo json_encode(array("prog_group"=>$response));


	?>