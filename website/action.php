<?php

        if (isset($_POST['submit'])){
            $id = $_POST['id'];
            echo $id;
        }





            $apiurl="http://ec2-18-223-239-89.us-east-2.compute.amazonaws.com:8080/checkin/log-checkin/" . $id;
            $ch = curl_init( $apiurl );
            curl_setopt( $ch, CURLOPT_POST, 1);
            curl_setopt( $ch, CURLOPT_POSTFIELDS, $id);
            curl_setopt( $ch, CURLOPT_FOLLOWLOCATION, 1);
            curl_setopt( $ch, CURLOPT_HEADER, 0);
            curl_setopt( $ch, CURLOPT_RETURNTRANSFER, 1);



            $response = curl_exec( $ch );







            echo $response;

                //$id=$_POST['submit'];
                //add api url

?>