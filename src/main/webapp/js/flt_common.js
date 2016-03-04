var oLoad=$('<div class="loading"><i></i></div>');
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

function trim(str)
{
	var reg=/\s/g;
	return str.replace(reg,'');
}

function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}