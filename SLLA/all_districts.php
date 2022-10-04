<?php
  require_once('db.php');

  $pro_id = $_GET['pro_id'];

$sql = "SELECT * FROM pub_district_tbl WHERE pro_Code = '$pro_id' ORDER BY dis_Code ASC";


  $result = mysqli_query($db,$sql);
  $data = array();
  
  while($row = mysqli_fetch_assoc($result)) {
      $data[] = $row;
  }
  
  echo mysqli_error($db);
  echo json_encode($data);
?>