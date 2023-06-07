<?php

//establish connection

$db = pg_connect("host=127.0.0.1 port=5432 dbname=ChargeAllDatabase user=ChargeAllAdmin password=1234");

if($db)
{
    //create variables to receive data from android

    $name;
    $email;
    $password;
    $phonenumber;

    //initialize variables from android (adduser method)

    if(isset($_POST['name']))
    {
        $name=$_POST['name'];
    }
    
    if(isset($_POST['phonenumber']))
    {
        $phonenumber=$_POST['phonenumber'];
    }
    
    if(isset($_POST['email']))
    {
        $email=$_POST['email'];
    }

      if(isset($_POST['password']))
    {
        $password=md5($_POST['password']);
    }


    if(isset($name) && isset($email) && isset($password) && isset($phonenumber) ){

        //check for data duplications

    $check =pg_query("select * from login.users where email='$email'");
    if(pg_num_rows($check)>0)
    {
        //send response to android that the user exists
        $status="ok";
        $result_code=0;
        echo json_encode(array("status"=>$status,"result_code"=>$result_code));
    }
    else
    {
        //inser data
        $query="insert into login.users(name,email,password,phonenumber) values('$name','$email','$password','$phonenumber')";
         //execute the query
         if($result=pg_query($query)){
          //send response to android that the user has been registered
          $status="ok";
          $result_code=1;
          echo json_encode(array("status"=>$status,"result_code"=>$result_code));
         }
    }
 }else{
    echo json_encode("Tum alanlar dolu degil");
 }


}
?>

