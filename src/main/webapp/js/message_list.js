$(function(){
	document.documentElement.style.fontSize=document.documentElement.clientWidth/18+'px';
	var pageAllNo=1;
	var dataAll={pageNo:pageAllNo};
	var pageUnReadNo=1;
	var dataUnRead={pageNo:pageUnReadNo};
	var totalCount=0;
	var unReadCount=0;
	var bFlagAjax=true;
	tab($('.box'));
	ajaxInvoke('/mfd/msg/getWechatMsgs.do',dataAll,flightListAllSuccess);
	var bFlagFirst=true;
	$('.box .nav ul li').eq(1).on('click',function(){
		if(bFlagFirst)
		{
			bFlagFirst=false;
			ajaxInvoke('/mfd/msg/getUnreadWechatMsgs.do',dataUnRead,flightListURSuccess);
		}
	});
	function flightListAllSuccess(data)
	{
		$('.box .nav').show();
		totalCount=data.totalCount;
		$('.box .nav ul li').eq(0).html('全部 '+totalCount+'条');
		$('.box .nav ul li').eq(1).html('未读 '+localStorage.totalCount+'条');
		if(data.rst)
		{
    		if(data.totalPage>=pageAllNo)
    		{
    			bFlagAjax=true;
    			$('.loading').hide();
	    		createList(data.rst,$('.all'));
	    		gotoDetail();
    		}else
    		{
    			$('.loading').hide();
				$('.msg').show();
				$('.msg').html('没有更多的通知');
    		}
		}else
		{
			$('.loading').hide();
			$('.msg').show();
			$('.msg').html('没有更多的通知');
		}
	}
	function flightListURSuccess(data)
	{
		unReadCount=data.totalCount;
		$('.box .nav ul li').eq(1).html('未读 '+unReadCount+'条');
		if(data.rst)
		{
    		if(data.totalPage>=pageUnReadNo)
    		{
    			bFlagAjax=true;
    			$('.loading').hide();
	    		createList(data.rst,$('.unread'));
	    		gotoDetail();
    		}
		}else
		{
			$('.loading').hide();
			$('.msg').show();
			$('.msg').html('没有更多的通知');
		}
	}
	function createList(arr,oParent)
	{
		for(var i=0; i<arr.length; i++)
		{
			var json=arr[i];
			var oLi=$('<li data-detail='+encodeURIComponent(JSON.stringify(json))+'>'+
					'<div class="title">'+json.msgTitle+'</div>'+
					'<div class="time">'+json.msgTime+'</div>'+
					'<div class="content">'+json.msgContent+
					'</div><a></a></li>');
			oLi.appendTo(oParent.find('ul'));
			if(json.readFlag=='N')
			{
				var oSign=$('<div class="sign"></div>');
				oSign.appendTo(oLi);
			}
		}
	}
	function gotoDetailClickEvent()
	{
		var str=$(this).attr('data-detail');
		$(this).find('a').attr('href','message_detail.html?'+str);
	}
	function gotoDetail()
	{
		$('.message-list ul li').off('click',gotoDetailClickEvent).on('click',gotoDetailClickEvent);
	}
	
	$(document).scroll(function(){
		var scrollTop=$(document).scrollTop();
		var oBoxTop=27*parseInt(document.documentElement.style.fontSize);
		if($('.all').hasClass('active'))
		{
			bFlag=true;
			var oMsgList=$('.all');
		}else if($('.unread').hasClass('active'))
		{
			bFlag=false;
			var oMsgList=$('.unread');
		}
		var top=oMsgList.outerHeight()-oBoxTop;
		if(scrollTop+1>=top)
		{
			$('.msg').show();
			if(bFlag)
			{
				if(bFlagAjax)
				{
					bFlagAjax=false;
					pageAllNo++;
					console.log(pageAllNo);
			   		var dataAll={pageNo:pageAllNo};
					ajaxInvoke('/mfd/msg/getWechatMsgs.do',dataAll,flightListAllSuccess);
				}		
				
			}else
			{
				if(bFlagAjax)
				{
					bFlagAjax=false;
					pageUnReadNo++;
					console.log(pageUnReadNo);
			   		var dataUnRead={pageNo:pageUnReadNo};
					ajaxInvoke('/mfd/msg/getUnreadWechatMsgs.do',dataUnRead,flightListURSuccess);
				}
			}
		}else
		{
			$('.loading').hide();
			$('.msg').hide();
		}
	});

	//后退
   $('.back').on('click',function(){
      $(this).attr('href','./flt_query.html')
   });
});
