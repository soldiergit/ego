var checkAllItemCode = 0;
var EGOCart = {
	load : function(){ // 加载购物车数据
		
	},
	itemNumChange : function(){
		//＋
		$(".increment").click(function(){
			var _thisInput = $(this).siblings("input");
			_thisInput.val(eval(_thisInput.val()) + 1);
			/*
			* url : /cart/update/num/153153486789528/3.html
			* 返回值："OK"
			* */
			$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".html",function(data){
				EGOCart.refreshTotalPrice();
			});
		});
		//-
		$(".decrement").click(function(){
			var _thisInput = $(this).siblings("input");
			if(eval(_thisInput.val()) == 1){
				return ;
			}
			_thisInput.val(eval(_thisInput.val()) - 1);
			/*
			 * url : /cart/update/num/153153486789528/3.html
			 * 返回值："OK"
			 * */
			$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".html",function(data){
				EGOCart.refreshTotalPrice();
			});
		});
		$(".quantity-form .quantity-text").rnumber(1);//限制只能输入数字
		$(".quantity-form .quantity-text").change(function(){
			var _thisInput = $(this);
			/*
			 * url : /cart/update/num/153153486789528/3.html
			 * 返回值："OK"
			 * */
			$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".html",function(data){
				EGOCart.refreshTotalPrice();
			});
		});
		//全选
		$(".checkAllItem").click(function () {
			if (checkAllItemCode === 0) {
				$(".checkbox").prop("checked", true);
				checkAllItemCode = 1;
			} else {
				$(".checkbox").prop("checked", false);
				checkAllItemCode = 0;
			}

		});
		//删除选中
		$("#remove-batch").click(function () {
			var checkItemValue = [];
			$('input[name=checkItem]:checked').each(function () {
				checkItemValue.push($(this).val());
			});
			if (checkItemValue == false) {
				alert("请选择需要删除的商品！");
				return false;
			}
			else {
				$.post("/cart/delete/batch/"+checkItemValue.join(",") + ".html",function(data){
					EGOCart.refreshTotalPrice();
				});
			}
		});
	},
	refreshTotalPrice : function(){ //重新计算总价
		var total = 0;
		$(".quantity-form .quantity-text").each(function(i,e){
			var _this = $(e);
			total += (eval(_this.attr("itemPrice")) * 10000 * eval(_this.val())) / 10000;
		});
		$(".totalSkuPrice").html(new Number(total/100).toFixed(2)).priceFormat({ //价格格式化插件
			 prefix: '￥',
			 thousandsSeparator: ',',
			 centsLimit: 2
		});
	}
};

$(function(){
	EGOCart.load();
	EGOCart.itemNumChange();
});