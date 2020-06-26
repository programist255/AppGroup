<?php

include 'conn.php';
//$respone = array();

$First_Name = $_POST['First_Name'];
$Name = $_POST['Name'];
$Login = $_POST['Login'];
$Password = $_POST['Password'];

$sql = "INSERT INTO users (Id, First_Name, Name, Login, Password) VALUES (NULL, '$First_Name', '$Name', '$Login', '$Password')";

$result = mysqli_query($conn,$sql);
//echo json_encode($result);
if($result){
	$dados = mysqli_num_rows($result);
	if($dados == 1){
            echo '1';            //ret = 1,
        }else{
            echo '0';
        }
	
}else{
	echo "0";
}

// if($result){
// 	$respone['error'] = false;
// 	$respone['message'] = "Invalid Request successfully";
// }else{
// 	$respone['error'] = true;
// 	$respone['message'] = "Invalid Request";
// }

// echo json_encode($respone);

	?>