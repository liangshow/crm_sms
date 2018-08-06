app.controller("userController",function($scope,userService){
	
	//注册用户
	$scope.reg=function(){
		//比较两次密码是否一致
		//alert($scope.password+"两次密码"+$scope.entity.password);
		if($scope.entity.username==null||$scope.entity.username==""){
			alert("用户名不能为空");
			return;
		}
		if($scope.entity.password==null||$scope.entity.password==""){
			alert("密码不能为空");
			return;
		}
		if($scope.entity.mobile==null||$scope.entity.mobile==""){
			alert("手机不能为空");
			return;
		}
		if ($scope.password!=$scope.entity.password) {
			alert("两次输入的密码不一致");
			$scope.password="";
			return;
		}
		//调用service服务
		userService.add($scope.entity,$scope.smscode).success(
			function(response){
				alert(response.message);
			}	
		);
	}
	
	//发送短信验证码
	  $scope.sendCode=function(){
		  //alert("获取短信验证码");
		  
		  if(!$scope.entity.mobile.match(/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/)){
			  alert("请填写正确的手机号码");
			  return;
		  }
		  userService.sendCode($scope.entity.mobile).success(
				  function(response){
					  //alert(response.message);
					  alert("发送短信成功，请等待");
				  }
		  );
	  }
});