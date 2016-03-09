$(function(){
	var shakeDataAll={beaconId:2};
	ajaxInvoke('/mfd/flt/getByShakeCond.do',shakeDataAll,function(data){
		createList(data);
		gotoDetail();
	});
	//创建列表
	function createList(arr)
	{
		for(var i=0; i<arr.length; i++)
		{
			var json=arr[i];
			var oLi=$('<li data-detail='+encodeURIComponent(JSON.stringify(json))+' data-arrdep='+json['arrdep']+' data-fltId='+json['fltId']+'><div class="title clearfix">'+
							'<i class="logo fl"></i>'+
							'<p class="name fl">'+json['airlineNameCn']+json['fltNo']+'</p>'+
						'</div>'+
						'<div class="detail">'+
							'<div class="time clearfix">'+
								'<p class="fl">'+json['startSdt']+'</p>'+
								'<p class="fr">'+json['destSdt']+'</p>'+
							'</div>'+
							'<div class="addr clearfix">'+
								'<p class="fl">'+json['startAirportCn']+'</p>'+
								'<p class="fr">'+json['destAirportCn']+'</p>'+
							'</div>'+
							'<p class="status">'+json['stateCn']+'</p>'+
						'</div><a></a></li>');
			oLi.appendTo($('.box .list>ul'));
			oLi.find('.logo').css({
				'background':'url(images/'+json['iata']+'.gif) no-repeat',
				'backgroundSize':'cover'
			});
			oLi.find('.status').css('color',json['stateColor']);
		}
	}
	//跳转详情页面
	function gotoDetailClickEvent()
	{
		var str=$(this).attr('data-detail');
		var arrdep=$(this).attr('data-arrdep');
		if(arrdep=='A')
		{
			$(this).find('a').attr('href','arr_detail.html?'+str);
		}else if(arrdep=='D')
		{
			$(this).find('a').attr('href','dep_detail.html?'+str);
		}					
	}
	function gotoDetail()
	{
		$('.list ul li').off('click',gotoDetailClickEvent).on('click',gotoDetailClickEvent);
	}
	//后退
	function backFn()
	{
		window.history.back();
	}
	$('.back').on('click',backFn);
});