$(function(){
	document.documentElement.style.fontSize=document.documentElement.clientWidth/18+'px';
	//导航切换
	tab($('.box'));
	//城市切换
	tab($('.city .list-cont'));
	//航空公司切换
	tab($('.company .list-cont'));
	//初始化的东西
	$('.citySelect').val('广州');
	$('.aircom').val('');
	$('.fltNo').val('');
	if(localStorage.urlStr=='fltNo')
	{
		$('.box .nav li').removeClass('active');
		$('.box .cont').removeClass('active');
		$('.box .nav li').eq(0).addClass('active');
		$('.box .cont').eq(0).addClass('active');
	}else if(localStorage.urlStr=='airlineCode')
	{
		$('.box .nav li').removeClass('active');
		$('.box .cont').removeClass('active');
		$('.box .nav li').eq(1).addClass('active');
		$('.box .cont').eq(1).addClass('active');
	}else if(localStorage.urlStr=='follow')
	{
		$('.box .nav li').removeClass('active');
		$('.box .cont').removeClass('active');
		$('.box .nav li').eq(2).addClass('active');
		$('.box .cont').eq(2).addClass('active');	
	}
	//----------按航班号查询-----------------按航班号查询-------------按航班号查-----------
	//格式化时间
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
	//创建日期DOM
	function createDate(json)
	{
		var oLi=$('<li data-time='+json.time+' class='+json.sClass+'><p>'+json.week+'</p>'+
						'<p>'+json.day+'</p>'+
						'<p>'+json.mon+'</p></li>');
		oLi.appendTo($('.box .flight-date .date ul'));
	}
	//日期切换函数
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
	//航班日期
	function flightDateSuccess(data)
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
	ajaxInvoke('bas/currentDateTime.do',null,flightDateSuccess);
	//出发到达日期导航切换
	change($('.sub-nav'));
	//航班号正则校验
	//regular();
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
		var json={pageNo:1,cond:{fltNo:fltNo,arrdep:arrdep,queryDate:time}};
		if(regular() && $('.flight-num .enter').val()!='')
		{
			$(this).attr('href','flt_list.html?'+encodeURIComponent(JSON.stringify(json)));
			var str=$('.flight-num .enter').val();
			if(localStorage.hisCache)
			{
				if(hisCacheArr.indexOf(str)==-1)
				{
					hisCacheArr.push(str);
					localStorage.hisCache=JSON.stringify(hisCacheArr);
				}
			}else{
				hisCacheArr.push(str);
				localStorage.hisCache=JSON.stringify(hisCacheArr);
			}
		}else{
			alert('请输入正确的航班号');
		}
	}
	//按航班号查询
	$('.flight .select').on('click',flightSelectEvent);

	//--------------按起降地查询----------------------------按起降地查询-------------------
	//点击北京弹出框
	$('.currCity').on('click',function(){
		var str='';
		var val=$(this).parent().find('p').html();
		if(val=='出发地')
		{
			str='目的地';
		}else
		{
			str='出发地';
		}
		$('.box .boxTips').show();
		var pStr=$('.box .boxTips .boxTips-cont p').html();
		$('.box .boxTips .boxTips-cont p').html('您现在位于北京，请选择'+str);
		$('.box .address .boxTips .boxTips-cont .ensure').on('click',function(){
			$('.box .boxTips').hide();
		});
	});
	//切换城市按钮效果
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
	//城市选择-----------------------------------------------
	$('.citySelect').on('click',citySelectClickEvent);
	function citySelectClickEvent()
	{
		$('.city').show();
		$('.box').hide();
		$('footer').hide();
		$('.search input').val('');
		if(localStorage.domCityDataCache)
		{
			$('.loading').hide();
			$('.city').show();
			domCityDataCache=JSON.parse(localStorage.domCityDataCache);
			createCityList(domCityDataCache,$('.city .domestic'));
			clickChoice($('.city .hot-name li'),$('.citySelect'));
			clickChoice($('.city .sub-cont li'),$('.citySelect'));		
			$('.sub-title').off("click",_show).
			on('click',_show);
		}else{
			$('.loading').show();
			$('.city').hide();
			ajaxInvoke('bas/domCities.do',null,domCitiesSuccess);
		}
		if(localStorage.intCityDataCache)
		{
			intCityDataCache=JSON.parse(localStorage.intCityDataCache);
			createCityList(intCityDataCache,$('.city .international'));
			clickChoice($('.city .hot-name li'),$('.citySelect'));
			clickChoice($('.city .sub-cont li'),$('.citySelect'));	
			$('.sub-title').off("click",_show).
			on('click',_show);
		}else{
			ajaxInvoke('bas/intCities.do',null,intCitiesSuccess);
		}
	}
	//创建城市列表
	function createCityList(json,oParent)
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
	var domCityDataCache={};
	var intCityDataCache={};
	function domCitiesSuccess(data)
	{
		$('.city').show();
		createCityList(data,$('.city .domestic'));
		domCityDataCache=data;
		localStorage.domCityDataCache=JSON.stringify(data);
		clickChoice($('.city .hot-name li'),$('.citySelect'));
		clickChoice($('.city .sub-cont li'),$('.citySelect'));		
		$('.sub-title').off("click",_show).
			on('click',_show);
	}
	function intCitiesSuccess(data)
	{
		createCityList(data,$('.city .international'));
		intCityDataCache=data;
		localStorage.intCityDataCache=JSON.stringify(data);
		clickChoice($('.city .hot-name li'),$('.citySelect'));
		clickChoice($('.city .sub-cont li'),$('.citySelect'));	
		$('.sub-title').off("click",_show).
			on('click',_show);
	}
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
					var str=oparent.find('.search input').val();			
					for(var sName in jsonTmp)
					{
						if(trim(str)==' ')
						{	
							oparent.find('.search-list').html(' ');
					 		oparent.find('.list-cont').show();
					 		arrNew=[];
						}else{
							if(sName!='type')
							{
								if(jsonTmp[sName].indexOf(trim(str).toUpperCase())!=-1)
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
	function citySearch()
	{
		$('.city .search-list').html(' ');
		if($('.city .search input').val()=='')
		{
			$('.city .list-cont').show();	
		}else{
			$('.city .list-cont').hide();
			if($('.city .list-cont .cont.domestic').hasClass('active'))
			{
				var arrNew=searchComm($('.city'),domCityDataCache);
			}else
			{
				var arrNew=searchComm($('.city'),intCityDataCache);
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
		clickChoice($('.city .search-list li'),$('.citySelect'));
	}
	//城市搜索
	$('.city .search input').on('input',citySearch);
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
	//字母列表点击展开收缩
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
	}

	//创建航空公司列表--------------------------------------------
	function createComList(json,oParent)
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
	//创建航空公司列表
	function createComList(json,oParent)
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
	//航空公司选择
	$('.aircom').on('click',aircomClickEvent);
	var domCompanyDataCache={};
	var intCompanyDataCache={};
	function domAirlinesSuccess(data)
	{
		$('.company').show();
		createComList(data,$('.company .domestic'));
		clickChoice($('.company .hot-name li'),$('.aircom'));
		clickChoice($('.company .sub-cont li'),$('.aircom'));
		$('.sub-title').off("click",_show).
			on('click',_show);
		domCompanyDataCache=data;
		localStorage.companyDomCache=JSON.stringify(data);
	}
	function intAirlinesSuccess(data)
	{
		createComList(data,$('.company .international'));
		clickChoice($('.company .hot-name li'),$('.aircom'));
		clickChoice($('.company .sub-cont li'),$('.aircom'));
		$('.sub-title').off("click",_show).
			on('click',_show);
		intCompanyDataCache=data;
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
			domCompanyDataCache=JSON.parse(localStorage.companyDomCache);
			createComList(domCompanyDataCache,$('.company .domestic'));
			clickChoice($('.company .hot-name li'),$('.aircom'));
			clickChoice($('.company .sub-cont li'),$('.aircom'));
			$('.sub-title').off("click",_show).
			on('click',_show);
		}else{
			$('.loading').show();
			$('.company').hide();
			ajaxInvoke('bas/domAirlines.do',null,domAirlinesSuccess);
		}
		if(localStorage.companyIntCache)
		{
			intCompanyDataCache=JSON.parse(localStorage.companyIntCache);
			createComList(intCompanyDataCache,$('.company .international'));
			clickChoice($('.company .hot-name li'),$('.aircom'));
			clickChoice($('.company .sub-cont li'),$('.aircom'));
			$('.sub-title').off("click",_show).
			on('click',_show);
		}else{
			ajaxInvoke('bas/intAirlines.do',null,intAirlinesSuccess);
		}
	}
	//航空公司搜索
	function companySearch(){
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
				var arrNew=searchComm($('.company'),domCompanyDataCache);
			}else
			{
				var arrNew=searchComm($('.company'),intCompanyDataCache);
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
	}
	$('.company .search input').on('input',companySearch);
	//按起降地查询函数
	function addSelectEvent()
	{
		var time=$('.address .flight-date .date .active').attr('data-time');
		var airlineCode=$('.aircompany .enter').attr('data-id');
		var code=$('.citySelect').attr('data-id');
		var type=$('.citySelect').attr('data-type');
		var arrdep='';
		if($('.citySelect').parent('div').find('.title').html()=='出发地')
		{
			arrdep='A';
		}else if($('.citySelect').parent('div').find('.title').html()=='目的地')
		{
			arrdep='D';
		}
		var json={pageNo:1,cond:{code:code,arrdep:arrdep,type:type,airlineCode:airlineCode,queryDate:time}};
		$(this).attr('href','flt_list.html?'+encodeURIComponent(JSON.stringify(json)));
	}
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
	
	//----------------------我的关注--------------------我的关注---------
	//取消关注点击事件
	function cancelClickEvent()
	{
		//var bFlag=window.confirm('您确定取消关注吗？');
		var _this=$(this);
		$('.box .boxTips').show();
		$('.box .list .boxTips .ensure').on('click',function()
		{
			$('.box .boxTips').hide();
			var contractId=_this.attr('data-conId');
			var arrdep=_this.attr('data-arrdep');
			var fltId=_this.attr('data-fltId');
			_this.parents('li').remove();
			var data={fltId:fltId,arrdep:arrdep,contractId:contractId};
			ajaxInvoke('sbc/unfollowFlight.do',data,function(data){
					unFollow(data,_this);
				});
		});
		$('.box .boxTips .cancel').on('click',function(){
			$('.box .boxTips').hide();
		});
    }
    //ajax请求成功后调用的函数
	function myFollowSuccess(data)
    {
		if(data)
		{
    		createFollowList(data.rst);
    		$('.cont-ul .cancel').off('click',cancelClickEvent).on('click',cancelClickEvent);
		    gotoDetail();
		    localStorage.urlStr='follow';
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
	var followData={sourceCode:'PEK-WECHAT-SHAKE',subscribeId:1,subscribeModule:'FLIGHT',
subscribeEvent:'DYNAMICS'};
	ajaxInvoke('/mfd/flt/getFollowedFlights.do',followData,myFollowSuccess);
    function createFollowList(arr)
    {
    	for(var i=0; i<arr.length; i++)
    	{
    		var json=arr[i];
    		console.log(json);
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
				$(this).attr('href','arr_detail.html?'+str);
			}else if(arrdep=='D')
			{
				$(this).attr('href','dep_detail.html?'+str);
			}						
		});
	}
});