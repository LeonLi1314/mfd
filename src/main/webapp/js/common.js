var oLoad=$('<div class="loading"><i></i></div>');
function ajaxInit(url,successFn)
{
	url=url+'&openId=openId&currAirportCode=PEK&subscribeId=1&subscriberId=openId&sourceCode=PEK-WECHAT-SHAKE&subscribeModule=FLIGHT&subscribeEvent=DYNAMICS'
	$.ajax({
		beforeSend:function()
		{
			oLoad.appendTo($('.wrap'));
			if($('.msg').css('display')=='block')
			{
				$('.loading').hide();
			}else
			{
				$('.loading').show();
			}
		},
		url:url,
		success:successFn,
		complete:function(){
			$('.loading').hide();
		}
	});
}

function ajaxInvoke(url,data,successFn)
{
	$.ajax({
		beforeSend:function()
		{
			oLoad.appendTo($('.wrap'));
			if($('.msg').css('display')=='block')
			{
				$('.loading').hide();
			}else
			{
				$('.loading').show();
			}
		},
		url:url + '?openId=openId&currAirportCode=PEK',
		data:JSON.stringify(data),
		type:'POST',
		contentType:'application/json',
		success:successFn,
		complete:function(){
			$('.loading').hide();
		}
	});
}
//选项卡函数
function tab(oParent)
{
	var aBtn=oParent.find('.btn');
	var aCont=oParent.find('.cont');
	for(var i=0; i<aBtn.length; i++)
	{
		aBtn[i].addEventListener('click',function(){
			aBtn.removeClass('active');
			$(this).addClass('active');
			aCont.removeClass('active');
			aCont.eq($(this).index()).addClass('active');
		},false);
	}
}

