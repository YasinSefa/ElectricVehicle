<?php

//establish connection

$db = pg_connect("host=127.0.0.1 port=5432 dbname=ChargeAllDatabase user=ChargeAllAdmin password=1234");

if($db)
{
    //create variables to receive data from android

    $email;
    $password;

    //initialize variables from android (adduser method)

      if(isset($_POST['email']))
    {
        $email=$_POST['email'];
    }

      if(isset($_POST['password']))
    {
        $password=md5($_POST['password']);
    }


    if((isset($name) || isset($email)) && isset($password) ){

         if(isset($email)){

            $check =pg_query("select * from login.users where email='$email' and password='$password'");

            if(pg_num_rows($check)==1)
        
            { 

            $status="ok";
            $result_code=1;
            echo json_encode(array("status"=>$status,"result_code"=>$result_code));

           }else{

            $status="ok";
            $result_code=0;
            echo json_encode(array("status"=>$status,"result_code"=>$result_code));
  
           }

        }

    }else 
    
    
    {

    echo json_encode("Tum alanlar dolu degil"); 

    }



}
?>