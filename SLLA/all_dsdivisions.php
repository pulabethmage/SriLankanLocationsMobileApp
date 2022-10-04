<?php
  require_once('db.php');

  $dis_id = $_GET['dis_id'];

$sql = "SELECT * FROM pub_ds_division_tbl WHERE dis_Code = '$dis_id' ORDER BY ds_Code ASC";


  $result = mysqli_query($db,$sql);
  $data = array();
  
  while($row = mysqli_fetch_assoc($result)) {
      $data[] = $row;
  }
  
  echo mysqli_error($db);
  echo json_encode($data);
?>