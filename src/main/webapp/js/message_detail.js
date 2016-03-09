$(function(){
	document.documentElement.style.fontSize=document.documentElement.clientWidth/18+'px';
   var str=window.location.search.substring(1);
   var data=eval('('+decodeURIComponent(str)+')');
   createDetail();
   function createDetail()
   {
   	 	var oDiv=$('<div class="message-detail">'+
			'<div class="title">'+data.msgTitle+'</div>'+
			'<div class="time">'+data.msgTime+'</div>'+
			'<div class="content">'+
				'<p>'+data.msgContent+'</p>'+			
			'</div>'+
		'</div>');
		oDiv.appendTo($('.message-box'));
   }
   var oDel=$('.del');
   oDel.on('click',function(){
      console.log(data.msgId);
   		var dataDel=data.msgId;
   		ajaxInvoke('/mfd/msg/deleteWechatMsgById.do',dataDel,function(data){
   			//删除的函数
   		});
   });
   if(data.readFlag=='N')
   {
       read();
   }
   function read()
   {
   		var dataRead=data.msgId;
         $.ajax({
            url:'/mfd/msg/modifyWechatMsgReadFlag.do?openId=openId&currAirportCode=PEK',
            data:JSON.stringify(dataRead),
            type:'POST',
            contentType:'application/json',
            success:function(data)
            {
               localStorage.totalCount=parseInt(localStorage.totalCount)-1;
               console.log(localStorage.totalCount);
            }
         });
   }
   //后退
   $('.back').on('click',function(){
      console.log('back');
      window.history.back();
   });
});