<?php
  $dbuser = "root";
  $dbname = "sl_locationsdb";
  $password = "";
  $db = mysqli_connect("localhost",$dbuser,$password,$dbname);
	// Change character set to utf8
	mysqli_set_charset($db,"utf8");
?>