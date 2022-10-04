<?php
  require_once('db.php');

$sql = "SELECT * FROM pub_province_tbl ORDER BY `pub_province_tbl`.`pro_Code` ASC";


  $result = mysqli_query($db,$sql);
  $data = array();
  
  while($row = mysqli_fetch_assoc($result)) {
      $data[] = $row;
  }
  
  echo mysqli_error($db);
  echo json_encode($data);
?>