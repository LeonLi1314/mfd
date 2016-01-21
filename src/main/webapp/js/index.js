$(function(){
	//alert(1);
	//localStorage.removeItem('hisCache');
	document.documentElement.style.fontSize=document.documentElement.clientWidth/18+'px';
	tab($('.box'));
	tab($('.city .list-cont'));
	tab($('.company .list-cont'));
	//初始化的东西
	$('.selectable').val('广州');
	$('.aircom').val('');
	localStorage.removeItem('companyDomCache');
	localStorage.removeItem('companyIntCache');
	localStorage.removeItem('cityIntCache');
	localStorage.removeItem('cityDomCache');
	/*function openClickEvent(){
		$(this).hide();
		$('.open-cont').show();
		$('.box .list ul li.cur').css({'marginBottom':'1rem'});
	}
	function closeClickEvent(){
		$('.open-cont').hide();
		$('.open').show();
	}
	$('.open').on('click',openClickEvent);
	$('.close').on('click',closeClickEvent);*/
	//出发到达日期切换
	change($('.sub-nav'));
	//切换城市按钮
	function cut()
	{
		$('.add-inp .start input').appendTo($('.add-inp .target'));
		$('.add-inp .target input').eq(0).appendTo($('.add-inp .start'));
		$('.cut').on('transitionend',function(){
			$(this).css({
				'transition':'none',
				'transform':'rotate(0)'
			});
		});
		$(this).css({
				'transition':'.5s all ease',
				'transform':'rotate(360deg)'
			});
	}
	$('.cut').off('click',cut).on('click',cut);
	//航班日期
	function flightDate(data)
	{
		for(var i=-1; i<2; i++)
		{
			if(i==0)
			{
				var json=formatsTime(data,'active');
				json.week='今天';
				createDate(json);
			}
			else
			{
				var json=formatsTime(data+i*86400000,'');
				createDate(json);
			}
		}
		var aDate=$('.box .change');
		for(var i=0; i<aDate.length; i++)
		{
			change(aDate.eq(i));
		}
	}
	//ajaxInit('bas/currentDateTime.do?',flightDate);
	ajaxInvoke('bas/currentDateTime.do',null,flightDate);
	function formatsTime(time,sClass)
	{
		var weekArr=["星期日", 
	    "星期一", 
	    "星期二", 
	    "星期三", 
	    "星期四", 
	    "星期五", 
	    "星期六"]; 
    	var monArr=['一月','二月','三月','四月','五月','六月','七月','八月',
    	'九月','十月','十一月','十二月',];
		var oDate=new Date();
		oDate.setTime(time);
		var mon=oDate.getMonth();
		var day=oDate.getDate();
		var week=oDate.getDay();
		return {mon:monArr[mon],day:day,week:weekArr[week],sClass:sClass,time:time};
	}	
	function createDate(json)
	{
		var oLi=$('<li data-time='+json.time+' class='+json.sClass+'><p>'+json.week+'</p>'+
						'<p>'+json.day+'</p>'+
						'<p>'+json.mon+'</p></li>');
		oLi.appendTo($('.box .flight-date .date ul'));
	}
	//日期切换
	function change(oParent)
	{
		var aLi=oParent.find('li');
		for(var i=0; i<aLi.length; i++)
		{
			aLi.eq(i).on('click',function(){
				aLi.removeClass('active');
				$(this).addClass('active');
			});
		}
	}
	//航班号正则校验
	regular();
	function regular(){
		var bRes=true;
		$('.flight-num .enter').on('blur',function(){
			var reg=/^(\w{2})?[0-9]{3,4}([a-zA-Z]{1})?$/g;
			var bFlag=reg.test($(this).val());
			if(!bFlag){
				alert('您输入的航班号有误');
				$(this).val('');
				$(this).focus();
			}
			bRes=bFlag;
		});
		return bRes;
	}
	//历史缓存
	var hisCacheArr=[];
	if(localStorage.hisCache)
	{
		$('.history').show();
		hisCacheArr=localStorage.hisCache.replace(/^\[|\"|\]$/g,'').split(',');
		var hisCount=hisCacheArr.length;
		var endCount=0;
		if(hisCount==1)
		{
			endCount=2;
		}else if(hisCount==2)
		{
			endCount=3;
		}else{
			endCount=4;
		}
		for(var i=hisCount-1; i>hisCount-endCount; i--)
		{
			var oLi=$('<li>'+hisCacheArr[i]+'</li>');
			oLi.appendTo($('.history .his-list ul'));
		}
	}else{
		$('.history').hide();
	}
	$('.history .his-list ul li').on('click',function(){
		$('.flight-num .enter').val($(this).html());
	});
	//--------------------------------------------------------------------------
	//点击北京弹出框
	$('.currCity').on('click',function(){
		//var str='';
		var str=$(this).parent().find('p').html();
		if(str=='出发地')
		{
			//str='目的地';
			alert('您目前在北京请选择目的地');
		}else
		{
			alert('您的目的地是北京请选择出发地');
		}
	});
	//按航班号查询函数
	function flightSelectEvent()
	{
		var arrdep='';
		if($('.flight .flight-date .sub-nav .active').html()=='出发日期')
		{
			arrdep='D';
		}else if($('.flight .flight-date .sub-nav .active').html()=='到达日期')
		{
			arrdep='A';
		}
		var time=$('.flight .flight-date .date .active').attr('data-time');
		var fltNo=$('.flight .flight-num .enter').val();
		//var str='pageNo=1&fltNo='+fltNo+'&arrdep='+arrdep+'&queryDate='+time;
		var json={pageNo:1,cond:{fltNo:fltNo,arrdep:arrdep,queryDate:time}};
		console.log('航班号时间'+time);
		/*var json={};
		json.pageNo=1;
		json.cond={};
		json.cond.fltNo=fltNo;
		json.cond.arrdep=arrdep;
		json.cond.queryDate=time;*/
		//console.log(json);
		if(regular() && $('.flight-num .enter').val()!='')
		{
			$(this).attr('href','list.html?'+encodeURIComponent(JSON.stringify(json)));
			localStorage.removeItem('listFltCache');
			var str=$('.flight-num .enter').val();
			if(localStorage.hisCache)
			{
				//localStorage.hisCache=localStorage.hisCache+'='+$('.flight-num .enter').val();
				if(hisCacheArr.indexOf(str)==-1)
				{
					hisCacheArr.push(str);
					localStorage.hisCache=JSON.stringify(hisCacheArr);
				}
			}else{
				//localStorage.hisCache=$('.flight-num .enter').val();
				hisCacheArr.push(str);
				localStorage.hisCache=JSON.stringify(hisCacheArr);
			}
		}else{
			alert('请输入正确的航班号');
		}
	}
	//按航班号查询
	$('.flight .select').on('click',flightSelectEvent);
	//按起降地查询函数
	function addSelectEvent()
	{
		var time=$('.address .flight-date .date .active').attr('data-time');
		var airlineCode=$('.aircompany .enter').attr('data-id');
		var code=$('.selectable').attr('data-id');
		var type=$('.selectable').attr('data-type');
		var arrdep='';
		console.log('起降地'+time);
		if($('.selectable').parent('div').find('.title').html()=='出发地')
		{
			arrdep='A';
		}else if($('.selectable').parent('div').find('.title').html()=='目的地')
		{
			arrdep='D';
		}
		/*var str='pageNo=1&code='+code+'&arrdep='+arrdep+'&type='+type+'&airlineCode='+airlineCode+
		'&queryDate='+time;*/
		var json={pageNo:1,cond:{code:code,arrdep:arrdep,type:type,airlineCode:airlineCode,queryDate:time}};
		$(this).attr('href','list.html?'+encodeURIComponent(JSON.stringify(json)));
		localStorage.removeItem('listAddCache');
	}
	//--------------------------------------------------------------------
	//搜索公共函数
	function searchComm(oparent,json)
	{
		var arrNew=[];
		for(var name in json)
		{
			if(name!='HOT')
			{
				var arr=json[name];
				for(var i=0; i<arr.length; i++)
				{
					var jsonTmp=arr[i];						
					for(var sName in jsonTmp)
					{
						if(oparent.find('.search input').val()==' ')
						{	
							oparent.find('.search-list').html(' ');
					 		oparent.find('.list-cont').show();
					 		arrNew=[];
						}else{
							if(sName!='type')
							{
								if(jsonTmp[sName].indexOf(oparent.find('.search input').val().toUpperCase())!=-1)
								{
									if(arrNew.indexOf(jsonTmp)==-1)
									{
										arrNew.push(jsonTmp);
									}
								}
							}
						}
					}
				}
			}
		}
		return arrNew;
	}
	//城市搜索
	$('.city .search input').keyup(function()
	{
		$('.city .search-list').html(' ');
		if($('.city .search input').val()=='')
		{
			$('.city .list-cont').show();	
		}else{
			$('.city .list-cont').hide();
			if($('.city .list-cont .cont.domestic').hasClass('active'))
			{
				var arrNew=searchComm($('.city'),cityDomDate);
			}else
			{
				var arrNew=searchComm($('.city'),cityIntDate);
			}
			if(arrNew.length==0)
			{
				var oLi=$('<li>没有查到相关信息</li>');
				oLi.appendTo($('.city .search-list'));
			}
			for(var i=0; i<arrNew.length; i++)
			{
				var json=arrNew[i];
				var oLi=$('<li data-type='+json['type']+' data-id='+json['code']+'></li>');
				oLi.html(json['nameCn']);
				oLi.appendTo($('.city .search-list'));
			}
		}
		clickChoice($('.city .search-list li'),$('.selectable'));
	});
	//航空公司搜索
	$('.company .search input').keyup(function(){
		$('.company .search-list').html(' ');
		if($('.company .search input').val()=='')
		{
			$('.company .list-cont').show();
		}
		else
		{
			$('.company .list-cont').hide();			
			if($('.company .list-cont .cont.domestic').hasClass('active'))
			{
				var arrNew=searchComm($('.company'),companyDomDate);
			}else
			{
				var arrNew=searchComm($('.company'),companyIntDate);
			}
			if(arrNew.length==0)
			{
				var oLi=$('<li>没有查到相关信息</li>');
				oLi.appendTo($('.company .search-list'));
			}
			for(var i=0; i<arrNew.length; i++)
			{
				var json=arrNew[i];
				var oLi=$('<li data-id='+json['airlineCode']+'></li>');
				oLi.html(json['nameCn']);
				oLi.appendTo($('.company .search-list'));
			}
			clickChoice($('.company .search-list li'),$('.aircom'));
		}
	});
	//列表选择跳转函数
	function clickChoice(objStart,objTarget)
	{
		objStart.off('click').on('click',function(){
			objStart.off('click');
			$('.company').hide();
			$('.city').hide();
			$('.box').show();
			$('footer').show();
			$('.search-list').html('');
			$('.list-cont').show();
			objTarget.val($(this).html());
			if($(this).attr('data-type')!=null)
			{
				objTarget.attr('data-type',$(this).attr('data-type'));
			}
			objTarget.attr('data-id',$(this).attr('data-id'));	
		});
	}
	function clickFnCity()
	{
		clickChoice($('.city .hot-name li'),$('.selectable'));
		clickChoice($('.city .sub-cont li'),$('.selectable'));		
	}
	function clickFnCom()
	{
		clickChoice($('.company .hot-name li'),$('.aircom'));
		clickChoice($('.company .sub-cont li'),$('.aircom'));		
	}
	//字母列表点击
	function show()
	{
		$('.sub-title').off("click",_show).
			on('click',_show);		
	}
	function _show()
	{
		var oCont=$(this).eq(0).parent().parent().find('.sub-cont');
		
		if(oCont.hasClass('active'))
		{
			oCont.removeClass('active');
		}else
		{
			$('.sub-cont').removeClass('active');
			oCont.addClass('active');
		}
		
		//oCont.addClass('active');
	}
	//创建城市列表
	function createCity(json,oParent)
	{
		oParent.find('.hot-name ul').html('');
		oParent.find(' .letter .letter-name ul').html('');
		for(var name in json)
		{
			if(name=='HOT')
			{
				var arr=json[name];
				for(var i=0; i<arr.length; i++)
				{
					var jsonTmp=arr[i];
					var oLi=$('<li data-type='+jsonTmp['type']+' data-id='+jsonTmp['code']+'>'+jsonTmp['nameCn']+'</li>');
					oLi.appendTo(oParent.find('.hot-name ul'));
				}
			}else
			{
				var oLi=$('<li></li>');
				var oP=$('<div class="title-box"><p class="sub-title">'+name+'</p></div>');
				oP.appendTo(oLi);
				var arr=json[name];
				var str='';
				for(var i=0; i<arr.length; i++)
				{
					var jsonTmp=arr[i];
					str+='<li data-type='+jsonTmp['type']+' data-id='+jsonTmp['code']+'>'+jsonTmp['nameCn']+'</li>';
				}
				var oDiv=$('<div class="sub-cont">'+
										'<ul>'+str+
											
										'</ul>'+
									'</div>');
				oDiv.appendTo(oLi);
				oLi.appendTo(oParent.find(' .letter .letter-name>ul'));
			}
		}
	}
	//创建航空公司列表
	function createCom(json,oParent)
	{
		oParent.find('.hot-name ul').html('');
		oParent.find(' .letter .letter-name ul').html('');
		for(var name in json)
		{
			if(name=='HOT')
			{
				var arr=json[name];
				for(var i=0; i<arr.length; i++)
				{
					var jsonTmp=arr[i];
					var oLi=$('<li data-id='+jsonTmp['airlineCode']+'>'+jsonTmp['nameCn']+'</li>');
					oLi.appendTo(oParent.find('.hot-name ul'));
				}
			}else
			{
				var oLi=$('<li></li>');
				var oP=$('<div class="title-box"><p class="sub-title">'+name+'</p></div>');
				oP.appendTo(oLi);
				var arr=json[name];
				var str='';
				for(var i=0; i<arr.length; i++)
				{
					var jsonTmp=arr[i];
					str+='<li data-id='+jsonTmp['airlineCode']+'>'+jsonTmp['nameCn']+'</li>';
				}
				var oDiv=$('<div class="sub-cont">'+
										'<ul>'+str+
											
										'</ul>'+
									'</div>');
				oDiv.appendTo(oLi);
				oLi.appendTo(oParent.find(' .letter .letter-name>ul'));
			}
		}
	}
	//城市选择
	var cityDomDate={};
	var cityIntDate={};
	function domCities(data)
	{
		$('.city').show();
		createCity(data,$('.city .domestic'));
		cityDomDate=data;
		localStorage.cityDomCache=JSON.stringify(data);
		clickFnCity();	
		show();
	}
	function intCities(data)
	{
		createCity(data,$('.city .international'));
		cityIntDate=data;
		localStorage.cityIntCache=JSON.stringify(data);
		clickFnCity();
		show();
	}
	function selectableClickEvent()
	{
		$('.city').show();
		$('.box').hide();
		$('footer').hide();
		$('.search input').val('');
		if(localStorage.cityDomCache)
		{
			$('.loading').hide();
			$('.city').show();
			cityDomDate=JSON.parse(localStorage.cityDomCache);
			createCity(cityDomDate,$('.city .domestic'));
			clickFnCity();	
			show();
		}else{
			$('.loading').show();
			$('.city').hide();
			//ajaxInit('bas/domCities.do?a=a',domCities);
			ajaxInvoke('bas/domCities.do',null,domCities);
		}
		if(localStorage.cityIntCache)
		{
			cityIntDate=JSON.parse(localStorage.cityIntCache);
			createCity(cityIntDate,$('.city .international'));
			clickFnCity();
			show();
		}else{
			//ajaxInit('bas/intCities.do?a=a',intCities);
			ajaxInvoke('bas/intCities.do',null,intCities);
		}
	}
	$('.selectable').on('click',selectableClickEvent);
	//航空公司选择
	var companyDomDate={};
	var companyIntDate={};
	function domAirlines(data)
	{
		$('.company').show();
		createCom(data,$('.company .domestic'));
		clickFnCom();
		show();
		companyDomDate=data;
		localStorage.companyDomCache=JSON.stringify(data);
	}
	function intAirlines(data)
	{
		createCom(data,$('.company .international'));
		clickFnCom();
		show();
		companyIntDate=data;
		localStorage.companyIntCache=JSON.stringify(data);
	}
	function aircomClickEvent()
	{
		$('.company').show();
		$('.box').hide();
		$('footer').hide();
		$('.search input').val('');
		if(localStorage.companyDomCache)
		{
			$('.loading').hide();
			$('.company').show();
			companyDomDate=JSON.parse(localStorage.companyDomCache);
			createCom(companyDomDate,$('.company .domestic'));
			clickFnCom();
			show();	
		}else{
			$('.loading').show();
			$('.company').hide();
			//ajaxInit('bas/domAirlines.do?a=a',domAirlines);
			ajaxInvoke('bas/domAirlines.do',null,domAirlines);
		}
		if(localStorage.companyIntCache)
		{
			companyIntDate=JSON.parse(localStorage.companyIntCache);
			createCom(companyIntDate,$('.company .international'));
			clickFnCom();
			show();
		}else{
			//ajaxInit('bas/intAirlines.do?a=a',intAirlines);
			ajaxInvoke('bas/intAirlines.do',null,intAirlines);
		}
	}
	$('.aircom').on('click',aircomClickEvent);
	//按起降地查询
	$('.address .select').on('click',addSelectEvent);
	//-------------------------------------------------------------

	//吸顶条
	var oDiv1=$('.box .nav');
	var top=oDiv1.offset().top;
	var oDiv2=$('<div></div>');
	oDiv2.css({'display':'none','height':oDiv1.outerHeight()});
	oDiv2.insertAfter(oDiv1);	
	$(document).scroll(function (){
		var scrollTop=$(document).scrollTop()+parseInt(document.documentElement.style.fontSize)*3.5;
		if (scrollTop > top && oDiv1.find('li').eq(2).hasClass('active'))
		{
			oDiv1.css('position', 'fixed');
			oDiv2.show();
		}
		else
		{
			oDiv1.css({
				'position':'',
				'marginTop':'1rem'
			});
			oDiv2.hide();
		}
	});
	setInterval(function(){
		if (!oDiv1.find('li').eq(2).hasClass('active'))
		{
			oDiv1.css({
				'position':'',
				'marginTop':'1rem'
			});
			oDiv2.hide();
		}
	},200);
	//后退
	$('.back').on('click',function(){
		$('.box').show();
		$('.city').hide();
		$('.company').hide();
		$('.loading').hide();
	});
	//----------------------------------------------------------------
	
	//我的关注
	//取消关注点击事件
	function cancelClickEvent()
	{
		var bFlag=window.confirm('您确定取消关注吗？');
		if(bFlag)
		{
			var contractId=$(this).attr('data-conId');
			var arrdep=$(this).attr('data-arrdep');
			var fltId=$(this).attr('data-fltId');
			var _this=$(this);
			_this.parents('li').remove();
			/*ajaxInit('sbc/unfollowFlight.do?'+'fltId='+fltId+'&arrdep='+arrdep+'&contractId='+contractId,
				function(data){
					unFollow(data,_this);
				});*/
			var data={fltId:fltId,arrdep:arrdep,contractId:contractId};
			ajaxInvoke('sbc/unfollowFlight.do',data,function(data){
					unFollow(data,_this);
				});

		}
    }
    //ajax请求成功后调用的函数
	function myFollow(data)
    {
		if(data)
		{
    		followList(data.rst);
    		//console.log(data);
    		$('.cancel').off('click',cancelClickEvent).on('click',cancelClickEvent);
		    gotoDetail();
		}else{
			$('.box .list').html('您没有关注任何航班信息');
		} 		
    }
	//取消关注
	function unFollow(data,obj)
	{
		if(data.success)
		{
			obj.html('关注');
		}
	}
	//ajaxInit('/mfd/flt/getFollowedFlights.do?',myFollow);
	var followData={sourceCode:'PEK-WECHAT-SHAKE',subscribeId:1,subscribeModule:'FLIGHT',
subscribeEvent:'DYNAMICS'};
	ajaxInvoke('/mfd/flt/getFollowedFlights.do',followData,myFollow);
    function followList(arr)
    {
    	for(var i=0; i<arr.length; i++)
    	{
    		var json=arr[i];
    		var oLi=$('<li data-detail='+encodeURIComponent(JSON.stringify(json))+' data-arrdep='+json['arrdep']+' data-fltId='+json['fltId']+'><div class="title clearfix">'+
						'<i class="logo fl"></i>'+
						'<p class="name fl">'+json['airlineNameCn']+json['fltNo']+'</p>'+
						'<p class="cancel fr" data-arrdep='+json['arrdep']+' data-fltId='+json['fltId']+' data-conId='+json['contractId']+'>取消关注</p>'+
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
					'<a></a></div></li>');
    		oLi.appendTo($('.box .list .cont-ul'));
    		oLi.find('.logo').css({
				'background':'url(images/'+json['iata']+'.gif) no-repeat',
				'backgroundSize':'cover'
			}); 
			oLi.find('.status').css('color',json['stateColor']);   		
    	}
    }
	//跳转详情页面
	function gotoDetail()
	{
		$('.list ul li a').on('click',function(){
			var str=$(this).parents('li').attr('data-detail');
			var arrdep=$(this).parents('li').attr('data-arrdep');
			if(arrdep=='A')
			{
				$(this).attr('href','detail_in.html?'+str);
			}else if(arrdep=='D')
			{
				$(this).attr('href','detail_out.html?'+str);
			}					
		});
	}
});