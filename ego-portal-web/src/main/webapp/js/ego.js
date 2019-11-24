var TT = TAOTAO = {
	checkLogin : function(){
		var _ticket = $.cookie("SSO_TOKEN");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://localhost:8083/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					// var html = username + "，欢迎来到淘淘！<a href=\"http://www.taotao.com/user/logout.html\" class=\"link-logout\">[退出]</a>";
					var html = username + "，欢迎来到淘淘！<a href=\"javascript:TT.logout()\" class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	},

	logout: function () {
		//	获取本地保存的SSO_TOKEN
		var _ticket = $.cookie("SSO_TOKEN");
		if(!_ticket){
			return ;
		}

		$.ajax({
			url : "http://localhost:8083/user/logout/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					location.href = 'http://localhost:8081';
				}
			}
		});
	}
};

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});