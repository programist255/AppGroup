<?php  
include 'conn.php';
$GroupName = $_POST['Text'];

$response = array();

$src = "SELECT Photo, NameGroup FROM prog_group WHERE NameGroup = '$GroupName'";
	$result = mysqli_query($conn, $src);
    $row = mysqli_fetch_assoc($result);
    $response[] = $row;

header('Content-Type: application/json');
echo json_encode(array("prog_group"=>$response));


?>