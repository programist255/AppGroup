<?php

include 'conn.php';

$Login = $_POST['Login'];
$Password = $_POST['Password'];

$response = array();
$res = array("us" => "");
$src = "SELECT Id, Name FROM users WHERE Login = '$Login' AND Password = '$Password'";
	$result = mysqli_query($conn, $src);
    $row = mysqli_fetch_assoc($result);
    $response['0'] = $row['Id'];
    $rows = mysqli_num_rows($result);
    
    if($rows){$res = '1';}
    $response['1'] = $res;
    $response['2'] = $row['Name'];

    echo json_encode($response);

// if($result){
// 	$dados = mysqli_num_rows($result);
// 	if($dados == 1){
//             echo '1';            //ret = 1,
//         }else{
//             echo '0';
//         }
	
// }else{
// 	echo "0";
// }

	?>