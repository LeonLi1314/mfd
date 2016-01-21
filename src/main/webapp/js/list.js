$(function(){
	document.documentElement.style.fontSize=document.documentElement.clientWidth/18+'px';
	var str=window.location.search.substring(1);
    var pageNo=1;
    var data=JSON.parse(decodeURIComponent(str));
    //页面初始化执行的函数
    if(str.indexOf('fltNo')!=-1)
    {
    	if(localStorage.listFltCache)
    	{
    		$('.loading').hide();
    		var data=JSON.parse(localStorage.listFltCache);
    		createList(data);
    		gotoDetail();
    	}
    	else
    	{
    		 ajaxNum(1);
    	}
    }else if(str.indexOf('airlineCode')!=-1)
    {
    	if(localStorage.listAddCache)
    	{
    		var data=JSON.parse(localStorage.listAddCache);
			createList(data);
    		$('.loading').hide();
    		gotoDetail();
    	}
    	else
    	{
    		ajaxAddress(1);
    	}
    }
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
			$(this).find('a').attr('href','detail_in.html?'+str);
		}else if(arrdep=='D')
		{
			$(this).find('a').attr('href','detail_out.html?'+str);
		}					
	}
	function gotoDetail()
	{
		$('.list ul li').off('click',gotoDetailClickEvent).on('click',gotoDetailClickEvent);
	}
	//按航班号查询请求成功后的函数
	function queryFlightNum(data)
	{
		if(data)
		{
			if(data.rst.length>0)
			{
	    		if(data.totalPage>=pageNo)
	    		{
	    			$('.loading').hide();
		    		createList(data.rst);
		    		gotoDetail();
		    		localStorage.listFltCache=JSON.stringify(data.rst);
	    		}
			}else
			{
				$('.loading').hide();
				$('.msg').show();
				$('.msg').html('没有查到更多的航班信息');
			}
		}
	}
	function ajaxNum(pageNo)
	{
	    data.pageNo=pageNo;
	    ajaxInvoke('/mfd/flt/getByFltNoCond.do',data,queryFlightNum);
	}
	//按起降地查询请求成功后的函数
	function queryFlightAdd(data)
	{
		if(data)
		{
			if(data.rst.length>0)
			{
	    		if(data.totalPage>=pageNo)
	    		{
	    			createList(data.rst);
		    		$('.loading').hide();
		    		localStorage.listAddCache=JSON.stringify(data.rst);
		    		gotoDetail();
	    		}
			}else
			{
				$('.loading').hide();
				$('.msg').show();
				$('.msg').html('没有查到更多的航班信息');
			}
		}else
		{		
			$('.loading').hide();
			$('.msg').show();
			$('.msg').html('没有查到更多的航班信息');
		}
		
	}
	function ajaxAddress(pageNo)
	{
	    data.pageNo=pageNo;
	    ajaxInvoke('/mfd/flt/getByPlaceCond.do',data,queryFlightAdd);
	}
	var oList=$('.list');
	var oBox=$('.box');
	//页面滚动
	$(document).scroll(function(){
		var scrollTop=$(document).scrollTop();
		var oBoxTop=27*parseInt(document.documentElement.style.fontSize);
		var top=oList.outerHeight()-oBoxTop;
		if(scrollTop>=top)
		{
			$('.msg').show();
			pageNo++;
		    if(str.indexOf('fltNo')!=-1)
		    {
			    ajaxNum(pageNo);
		    }else
		    {
		    	ajaxAddress(pageNo);
		    }
		}else
		{
			$('.loading').hide();
			$('.msg').hide();
		}
	});
	//后退
	function backFn()
	{
		window.history.back();
	}
	$('.back').on('click',backFn);	
});