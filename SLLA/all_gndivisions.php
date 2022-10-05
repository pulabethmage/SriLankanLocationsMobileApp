<?php
  require_once('db.php');

  $ds_id = $_GET['ds_id'];

$sql = "SELECT * FROM pub_gn_division_tbl WHERE ds_Code = '$ds_id' ORDER BY Gnd_name ASC";


  $result = mysqli_query($db,$sql);
  $data = array();
  
  while($row = mysqli_fetch_assoc($result)) {
      $data[] = $row;
  }
  
  echo mysqli_error($db);
  echo json_encode($data);
?>