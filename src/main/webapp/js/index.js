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
	//我关注的展开，收缩
	$('.open')[0].addEventListener('touchstart',function(){
		$(this).hide();
		$('.open-cont').show();
		$('.box .list ul li.cur').css({'marginBottom':'1rem'});
	});
	$('.close')[0].addEventListener('touchstart',function(){
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
			objTarget.attr('data-id',$(this).attr('data-id'));			
		});
	}
	//按航班号查询
	$('.flight .select').on('touchstart',function(){
		 alert('航班号：'+$('.flight .flight-num .enter').val()
		 	+'----航班日期：'
		 	+$('.flight .flight-date .sub-nav .active').html()+'===='
			+$('.flight .flight-date .date .active p').eq(2).html()
			+$('.flight .flight-date .date .active p').eq(1).html());
		// window.open('list.html');
	});
	//按起降地查询
	$('.address .select').on('touchstart',function(){
		alert('出发地：'+$('.add-inp .start .enter').attr('data-id')+
			'---到达地：'
			+$('.add-inp .target .enter').attr('data-id')+'---航空公司：'
			+$('.aircompany .enter').attr('data-id')+'---日期：'
			+$('.address .flight-date .date .active p').eq(2).html()
			+$('.address .flight-date .date .active p').eq(1).html());
	});


	//条件搜索
	(function(){
		var arr=[['上海','pik','shanghai','sh'],
		['上海虹桥机场','pik','shanghaihongqiaojichang','shhqjc'],
		['上海浦东机场','pik','shanghaipudongjichang','shpdjc'],
		['北京','pik','beijing','bj'],
		['北京首都机场','pik','beijingshoudujichang','bjsdjc'],
		['北京南苑机场','pik','beijingnanyuanjichang','bjnyjc'],
		['石家庄正定机场','pin','shijiazhuangzhengdingjichang','sjzzdjc']];
		$('.search input').keyup(function(){
			$('.search-list').html('');
			var arrNew=[];
			for(var i=0; i<arr.length; i++)
			{
				var arrTemp=arr[i];
				for(var j=0; j<arrTemp.length; j++)
				{
					if($(this).val()=='')
					{
						$('.search-list').html('');
						 arrNew=[];
					}
					else {
						if(arrTemp[j].indexOf($(this).val())!=-1)
						{
							if(arrNew.indexOf(arrTemp)==-1)
							{
								arrNew.push(arrTemp);
							}
						}
					}
				}
			}
			for(var i=0; i<arrNew.length; i++)
			{
				var oLi=$('<li></li>');
				oLi.html(arrNew[i][0]);
				oLi.appendTo($('.search-list'));
			}
			select($('.city .search-list li'),$('.selectable'));
			select($('.company .search-list li'),$('.aircom'));
		});
	})();
	//城市列表
	(function(){
		/*var jsonCityD={"B":[{"cityCode":"BJS","firstLetter":"BJ","fullPinyin":"BEIJING","nameCn":"北京","nameEn":"Beijing"},
		{"cityCode":"BJS","firstLetter":"BJ","fullPinyin":"BEIJING","nameCn":"北京2","nameEn":"Beijing"}],
		"S":[{"cityCode":"SHA","firstLetter":"SH","fullPinyin":"SHANGHAI","nameCn":"上海","nameEn":"Shanghai"}],
		"G":[{"cityCode":"CAN","firstLetter":"GZ","fullPinyin":"GUANGZHOU","nameCn":"广州","nameEn":"Guangzhou"}],
		"H":[{"cityCode":"HGH","firstLetter":"HZ","fullPinyin":"HANGZHOU","nameCn":"杭州","nameEn":"Hangzhou"}],
		"HOT":[{"cityCode":"SHA","nameCn":"上海","nameEn":"Beijing"},
		{"cityCode":"CAN","nameCn":"广州","nameEn":"Guangzhou"},
		{"cityCode":"HGH","nameCn":"杭州","nameEn":"Hangzhou"}]};
		var jsonCityI={"C":[{"airlineCode":"BR","firstLetter":"CRHKGS","fullPinyin":"CHANGRONGHANGKONGGONGSI","nameCn":"长荣航空公司","nameEn":"EVA Airways Corporation"}],
		"F":[{"airlineCode":"AY","firstLetter":"FLHKGS","fullPinyin":"FENLANHANGKONGGONGSI","nameCn":"芬兰航空公司","nameEn":"FINNAIR"}],
		"H":[{"airlineCode":"HU","firstLetter":"HNHKGS","fullPinyin":"HAINANHANGKONGGONGSI","nameCn":"海南航空公司","nameEn":"Hainan Airlines-HU"}],
		"Z":[{"airlineCode":"MU","firstLetter":"ZGDFHKGS","fullPinyin":"ZHONGGUODONGFANGHANGKONGGONGSI","nameCn":"中国东方航空公司","nameEn":"China Eastern Airlines"},
		{"airlineCode":"CA","firstLetter":"ZGGJHKGS","fullPinyin":"ZHONGGUOGUOJIHANGKONGGONGSI","nameCn":"国际航空公司\r\n","nameEn":"Air China"},
		{"airlineCode":"CZ","firstLetter":"ZGNFHKGS","fullPinyin":"ZHONGGUONANFANGHANGKONGGONGSI","nameCn":"南方航空公司","nameEn":"Chine Southern Airlines"}],
		"HOT":[{"airlineCode":"CA","nameCn":"国际航空公司","nameEn":"Air China"},
		{"airlineCode":"AA","nameCn":"美国航空公司","nameEn":"American Airlines"},
		{"airlineCode":"AY","nameCn":"芬兰航空公司","nameEn":"FINNAIR"}],
		"M":[{"airlineCode":"AA","firstLetter":"MGHKGS","fullPinyin":"MEIGUOHANGKONGGONGSI","nameCn":"美国航空公司\r\n","nameEn":"American Airlines"}]};
		var jsonComD={"H":[{"airlineCode":"HU","firstLetter":"HNHKGS","fullPinyin":"HAINANHANGKONGGONGSI","nameCn":"海南航空公司","nameEn":"Hainan Airlines-HU"}],
		"Z":[{"airlineCode":"MU","firstLetter":"ZGDFHKGS","fullPinyin":"ZHONGGUODONGFANGHANGKONGGONGSI","nameCn":"中国东方航空公司","nameEn":"China Eastern Airlines"},
		{"airlineCode":"CA","firstLetter":"ZGGJHKGS","fullPinyin":"ZHONGGUOGUOJIHANGKONGGONGSI","nameCn":"中国国际航空公司\r\n","nameEn":"Air China"},
		{"airlineCode":"CZ","firstLetter":"ZGNFHKGS","fullPinyin":"ZHONGGUONANFANGHANGKONGGONGSI","nameCn":"中国南方航空公司","nameEn":"Chine Southern Airlines"}],
		"HOT":[{"airlineCode":"CA","nameCn":"中国国际航空公司","nameEn":"Air China"},
		{"airlineCode":"CZ","nameCn":"中国南方航空公司","nameEn":"China Southern Airlines"},
		{"airlineCode":"MU","nameCn":"中国东方航空公司","nameEn":"China Eastern Airlines"},
		{"airlineCode":"HU","nameCn":"海南航空公司","nameEn":"Hainan Airlines-HU"}]};
		var jsonComI={"C":[{"airlineCode":"BR","firstLetter":"CRHKGS","fullPinyin":"CHANGRONGHANGKONGGONGSI","nameCn":"长荣航空公司","nameEn":"EVA Airways Corporation"}],
		"F":[{"airlineCode":"AY","firstLetter":"FLHKGS","fullPinyin":"FENLANHANGKONGGONGSI","nameCn":"芬兰航空公司","nameEn":"FINNAIR"}],
		"H":[{"airlineCode":"HU","firstLetter":"HNHKGS","fullPinyin":"HAINANHANGKONGGONGSI","nameCn":"海南航空公司","nameEn":"Hainan Airlines-HU"}],
		"Z":[{"airlineCode":"MU","firstLetter":"ZGDFHKGS","fullPinyin":"ZHONGGUODONGFANGHANGKONGGONGSI","nameCn":"中国东方航空公司","nameEn":"China Eastern Airlines"},
		{"airlineCode":"CA","firstLetter":"ZGGJHKGS","fullPinyin":"ZHONGGUOGUOJIHANGKONGGONGSI","nameCn":"中国国际航空公司\r\n","nameEn":"Air China"},
		{"airlineCode":"CZ","firstLetter":"ZGNFHKGS","fullPinyin":"ZHONGGUONANFANGHANGKONGGONGSI","nameCn":"中国南方航空公司","nameEn":"Chine Southern Airlines"}],
		"HOT":[{"airlineCode":"CA","nameCn":"中国国际航空公司","nameEn":"Air China"},
		{"airlineCode":"AA","nameCn":"美国航空公司","nameEn":"American Airlines"},
		{"airlineCode":"AY","nameCn":"芬兰航空公司","nameEn":"FINNAIR"}],
		"M":[{"airlineCode":"AA","firstLetter":"MGHKGS","fullPinyin":"MEIGUOHANGKONGGONGSI","nameCn":"美国航空公司\r\n","nameEn":"American Airlines"}]}*/

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
						var oLi=$('<li data-id='+jsonTmp['cityCode']+'>'+jsonTmp['nameCn']+'</li>');
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
						str+='<li data-id='+jsonTmp['cityCode']+'>'+jsonTmp['nameCn']+'</li>';
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
		//城市航空公司选择
		$('.selectable')[0].addEventListener('touchstart',function(){
			$('.city').show();
			$('.box').hide();
			$('footer').hide();
			//createCity(jsonCityD,$('.city .domestic'));
			//createCity(jsonCityI,$('.city .international'));
			select($('.city .hot-name li'),$('.selectable'));
			select($('.city .sub-cont li'),$('.selectable'));
			show();
			$.ajax({
				url:'http://localhost:8080/mfd/bas/domAirlines.do',
				dataType:'json',
				success:function(data)
				{
					createCity(data,$('.city .domestic'));
				}
			});
			$.ajax({
				url:'http://localhost:8080/mfd/bas/intAirlines.do',
				dataType:'json',
				success:function(data)
				{
					createCity(data,$('.city .international'));
				}
			});
		});
		$('.aircom')[0].addEventListener('touchstart',function(){
			$('.company').show();
			$('.box').hide();
			$('footer').hide();
			createCom(jsonComD,$('.company .domestic'));
			createCom(jsonComI,$('.company .international'));
			select($('.company .hot-name li'),$('.aircom'));
			select($('.company .sub-cont li'),$('.aircom'));
			show();
			/*$.ajax({
				url:'http://172.169.40.136:8080/mfd/bas/domAirlines.do',
				dataType:'jsonp',
				success:function()
				{
					create(json,$('.city .domestic'));
				}
			});*/
		});
	})();
	//字母列表点击
	function show()
	{
		$('.list .list-cont .cont .letter .letter-name .sub-title').on('touchstart',
			function()
			{
				var oCont=$(this).parent().find('.sub-cont');
				if(oCont.hasClass('active'))
				{
					oCont.removeClass('active');
				}else
				{
					oCont.addClass('active');
				}
			}
		);
	}
	//航班日期
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
		return {mon:monArr[mon],day:day,week:weekArr[week],sClass:sClass};
	}
	for(var i=-1; i<2; i++)
	{
		if(i==0)
		{
			var json=formatsTime(1454228890956,'active');
			json.week='今天';
			createDate(json);
		}
		else
		{
			var json=formatsTime(1454228890956+i*86400000,'');
			createDate(json);
		}
	}
	function createDate(json)
	{
		var oLi=$('<li class='+json.sClass+'><p>'+json.week+'</p>'+
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
			});
		}
	}
	var aDate=$('.box .change');
	for(var i=0; i<aDate.length; i++)
	{
		change(aDate.eq(i));
	}
});