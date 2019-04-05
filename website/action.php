<?php

        if (isset($_POST['submit'])){
            $id = $_POST['id'];
        }
            $apiurl="http://ec2-18-223-239-89.us-east-2.compute.amazonaws.com:8080/checkin/log-checkin/" . $id;

            $response = file_get_contents($apiurl);
            echo $response;

                //$id=$_POST['submit'];
                //add api url

?>