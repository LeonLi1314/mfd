$(function(){
	document.documentElement.style.fontSize=document.documentElement.clientWidth/18+'px';
	//选项卡函数
	function tab(oParent)
	{
		var aBtn=oParent.find('.btn');
		var aCont=oParent.find('.cont');
		for(var i=0; i<aBtn.length; i++)
		{
			aBtn[i].addEventListener('touchstart',function(){
				aBtn.removeClass('active');
				$(this).addClass('active');
				aCont.removeClass('active');
				aCont.eq($(this).index()).addClass('active');
			},false);
		}
	}
	tab($('.box'));
	tab($('.city .list-cont'));
	tab($('.company .list-cont'));
	$('.open').on('touchstart',function(){
		$(this).hide();
		$('.open-cont').show();
		$('.box .list ul li.cur').css({'marginBottom':'1rem'});
	});
	$('.close').on('touchstart',function(){
		$('.open-cont').hide();
		$('.open').show();
	});

	//出发到达日期切换
	change($('.sub-nav'));
	//切换城市跳转
	$('.cut').on('touchstart',function(){
		$('.add-inp .start input').appendTo($('.add-inp .target'));
		$('.add-inp .target input').eq(0).appendTo($('.add-inp .start'));
	});
	//列表选择跳转函数
	function select(objStart,objTarget)
	{
		objStart.on('touchstart',function(){
			$('.company').hide();
			$('.city').hide();
			$('.box').show();
			$('footer').show();
			objTarget.val($(this).html());
			if($(this).attr('data-type')!=null)
			{
				objTarget.attr('data-type',$(this).attr('data-type'));
			}
			objTarget.attr('data-id',$(this).attr('data-id'));			
		});
	}
	//按航班号查询
	$('.flight .select').on('touchstart',function(){
		var arrdep=-1;
		if($('.flight .flight-date .sub-nav .active').html()=='出发日期')
		{
			arrdep=1;
		}else if($('.flight .flight-date .sub-nav .active').html()=='到达日期')
		{
			arrdep=0;
		}
		var time=$('.flight .flight-date .date .active').attr('data-time');
		var fltNo=$('.flight .flight-num .enter').val();
		/*console.log({"pageNo":1,
			"fltNoCond":{"fltNo":fltNo,
			"arrdep":arrdep,
			"queryDate":time}});*/
		var str='pageNo=1&fltNo='+fltNo+'&arrdep='+arrdep+'&queryDate='+time;
		window.open('list.html?'+str);
	});
	//按起降地查询
	$('.address .select').on('touchstart',function(){
		var time=$('.address .flight-date .date .active').attr('data-time');
		var airlineCode=$('.aircompany .enter').attr('data-id');
		var code=$('.selectable').attr('data-id');
		var type=$('.selectable').attr('data-type');
		var arrdep=-1;
		//console.log(typeof arrdep);
		if($('.selectable').parent('div').find('.title').html()=='出发地')
		{
			arrdep=1;
		}else if($('.selectable').parent('div').find('.title').html()=='目的地')
		{
			arrdep=0;
		}
		/*console.log({pageNo:1,
			arrdepPlaceCond:{code:code,
			arrdep:arrdep,
			type:type,
			airlineCode:airlineCode,
			queryDate:time}});*/
		var str='pageNo=1&code='+code+'&arrdep='+arrdep+'&type='+type+'&airlineCode='+
		'&queryDate='+time;
		window.open('list.html?'+str);
	});


	//条件搜索
	(function(){	
		$('.search input').keyup(function(){
			var json={"B":[{"code":"BJS","firstLetter":"BJ","fullPinyin":"BEIJING","nameCn":"北京","nameEn":"北京","type":0},
			{"code":"NAY","firstLetter":"BJNYJC","fullPinyin":"BEIJINGNANYUANJICHANG","nameCn":"北京南苑机场","nameEn":"北京南苑机场","type":1},
			{"code":"PEK","firstLetter":"BJSDGJJC","fullPinyin":"BEIJINGSHOUDUGUOJIJICHANG","nameCn":"北京首都国际机场","nameEn":"北京首都国际机场","type":1}],
			"S":[{"code":"SHA","firstLetter":"SH","fullPinyin":"SHANGHAI","nameCn":"上海","nameEn":"上海","type":0},
			{"code":"SHA","firstLetter":"SHHQGJJC","fullPinyin":"SHANGHAIHONGQIAOGUOJIJICHANG","nameCn":"上海虹桥国际机场","nameEn":"上海虹桥国际机场","type":1},
			{"code":"PVG","firstLetter":"SHPDGJJC","fullPinyin":"SHANGHAIPUDONGGUOJIJICHANG","nameCn":"上海浦东国际机场","nameEn":"上海浦东国际机场","type":1}],
			"G":[{"code":"CAN","firstLetter":"GZ","fullPinyin":"GUANGZHOU","nameCn":"广州","nameEn":"广州","type":0}],
			"H":[{"code":"HGH","firstLetter":"HZ","fullPinyin":"HANGZHOU","nameCn":"杭州","nameEn":"杭州","type":0}],
			"HOT":[{"code":"SHA","nameCn":"上海","nameEn":"Beijing","type":0},
			{"code":"CAN","nameCn":"广州","nameEn":"Guangzhou","type":0},
			{"code":"HGH","nameCn":"杭州","nameEn":"Hangzhou","type":0}]};
			$('.search-list').html('');
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
							if($(this).val()=='')
							{
								$('.search-list').html('');
						 		arrNew=[];
							}else{
								if(sName!='type')
								{
									if(jsonTmp[sName].indexOf($(this).val().toUpperCase())!=-1)
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
			//console.log(arrNew);
			for(var i=0; i<arrNew.length; i++)
			{
				var json=arrNew[i];
				var oLi=$('<li></li>');
				oLi.html(json['nameCn']);
				oLi.appendTo($('.search-list'));
			}
		})
	})();
	//城市列表
	(function(){
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
					var oP=$('<p class="sub-title">'+name+'</p>');
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
					var oP=$('<p class="sub-title">'+name+'</p>');
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
		function clickFnCity()
		{
			select($('.city .hot-name li'),$('.selectable'));
			select($('.city .sub-cont li'),$('.selectable'));
		}
		function clickFnCom()
		{
			select($('.company .hot-name li'),$('.aircom'));
			select($('.company .sub-cont li'),$('.aircom'));
		}
		//城市选择
		$('.selectable').on('touchstart',function(){
			$('.loading').show();
			$('.city').show();
			$('.box').hide();
			$('footer').hide();		
			$.ajax({
				url:'bas/domCities.do',
				dataType:'json',
				success:function(data)
				{
					$('.loading').hide();
					createCity(data,$('.city .domestic'));
					clickFnCity();
					show();
				}
			});
			$.ajax({
				url:'bas/intCities.do',
				dataType:'json',
				success:function(data)
				{
					createCity(data,$('.city .international'));
					clickFnCity();
					show();
				}
			});
		});
		$('.aircom').on('touchstart',function(){
			$('.loading').show();
			$('.company').show();
			$('.box').hide();
			$('footer').hide();
			$.ajax({
				url:'bas/domAirlines.do',
				dataType:'json',
				success:function(data)
				{
					$('.loading').hide();
					createCom(data,$('.company .domestic'));
					clickFnCom();
					show();
				}
			});
			$.ajax({
				url:'bas/intAirlines.do',
				dataType:'json',
				success:function(data)
				{
					createCom(data,$('.company .international'));
					clickFnCom();
					show();
				}
			});
		});
	})();
	//字母列表点击
	function show()
	{
		$('.sub-title').off("touchstart",_show).
			on('touchstart',_show);		
	}
	function _show()
	{
		var oCont=$(this).eq(0).parent().find('.sub-cont');
		if(oCont.hasClass('active'))
		{
			oCont.removeClass('active');
		}else
		{
			oCont.addClass('active');
		}
	}	
	//航班日期
	$.ajax({
		url:'bas/currentDateTime.do',
		success:function(data)
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
	});
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
			aLi[i].addEventListener('touchstart',function(){
				aLi.removeClass('active');
				$(this).addClass('active');
			},false);
		}
	}
});