//服务层
app.service("userService",function($http){
	
	//增加 
	this.add=function(entity,smscode){
		return  $http.post('/user/add/'+smscode,entity);
	}
	
	//发送验证短信
	this.sendCode=function(mobile){
		//alert("开始进入短信发送service服务");
		return $http.get("/user/sendCode/"+mobile);
	}
});