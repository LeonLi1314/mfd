$(function(){
	document.documentElement.style.fontSize=document.documentElement.clientWidth/18+'px';
	var str=window.location.search.substring(1);
    var pageNo=1;
    var bFlag=true;
    if(str)
    {
    	var data=JSON.parse(decodeURIComponent(str));
    }

    //页面初始化执行的函数
    getFlightInfor();
    //获取航班信息函数
    function getFlightInfor()
    {
	    if(str.indexOf('fltNo')!=-1)
	    {
	    	data.pageNo=pageNo;
		    ajaxInvoke('/mfd/flt/getByFltNoCond.do',data,flightNumSuccess);
		    localStorage.urlStr='fltNo';
	    }else if(str.indexOf('airlineCode')!=-1)
	    {
	    	data.pageNo=pageNo;
		    ajaxInvoke('/mfd/flt/getByPlaceCond.do',data,flightAddSuccess);
		    localStorage.urlStr='airlineCode';
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
	//按航班号查询请求成功后的函数
	function flightNumSuccess(data)
	{
		//console.log(data);
		if(data)
		{
			if(data.rst.length>0)
			{
	    		if(data.totalPage>=pageNo)
	    		{
	    			bFlag=true;
	    			$('.loading').hide();
		    		createList(data.rst);
		    		gotoDetail();
	    		}
			}else
			{
				$('.loading').hide();
				$('.msg').show();
				$('.msg').html('没有查到更多的航班信息');
			}
		}
	}
	//按起降地查询请求成功后的函数
	function flightAddSuccess(data)
	{
		if(data)
		{
			if(data.rst.length>0)
			{
	    		if(data.totalPage>=pageNo)
	    		{
	    			bFlag=true;
	    			createList(data.rst);
		    		$('.loading').hide();
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
	var oList=$('.list');
	var oBox=$('.box');
	//页面滚动
	$(document).scroll(function(){
		var scrollTop=$(document).scrollTop();
		var oBoxTop=27*parseInt(document.documentElement.style.fontSize);
		var top=oList.outerHeight()-oBoxTop;
		if(scrollTop>=top)
		{
			if(bFlag)
			{
				bFlag=false;
				$('.msg').show();
				pageNo++;
				console.log(pageNo);
			   	getFlightInfor();
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
	//获取实时消息
	clearInterval(timer);
	messageSign();
	timer=setInterval(messageSign,10000);

});