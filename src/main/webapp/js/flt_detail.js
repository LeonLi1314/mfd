$(function(){
   document.documentElement.style.fontSize=document.documentElement.clientWidth/18+'px';
   var str=window.location.search.substring(1);
   var base=eval('('+decodeURIComponent(str)+')');
   var bFlag=true; 
   //点击关注按钮事件 
   function followClickEvent()
   {
		if(bFlag)
		{
			bFlag=false;
			var fltId=$(this).attr('data-fltId');
			var arrdep=$(this).attr('data-arrdep');
			if($('.follow').attr('data-follow')=='true')
			{
				var contractId=$(this).attr('data-conid');
				var data={fltId:fltId,arrdep:arrdep,contractId:contractId};
				ajaxInvoke('sbc/unfollowFlight.do',data,unFollowSuccess);

			}else
			{
				var data={fltId:fltId,arrdep:arrdep};
				ajaxInvoke('sbc/followFlight.do',data,followSuccess);
			}
		}    		
	}
   //ajax请求详细信息成功函数
   function flightDetailSuccess(details)
   {
		if(base.arrdep=='A')
		{
			createDetail(base,details,{first:'行李转盘',second:'转盘开放时间',third:'出租车等待时长',labelStr:''});
		}else if(base.arrdep=='D')
		{
			createDetail(base,details,{first:'值机柜台',second:'安检等待状态',third:'登机口',labelStr:''});
		}
		$('.follow').off('click',followClickEvent).on('click',followClickEvent);
		var contW=$('.share-flight-cont').width();
		var boxW=$('.share-flight').width();
		var maxWidth=contW-boxW;
		rollor();
		function rollor()
		{
			if(contW>boxW)
			{
				$('.share-flight-cont').stop().animate(
				{
					'marginLeft':-maxWidth
				},{complete:function(){
					$('.share-flight-cont').css({'marginLeft':0});
					setTimeout(function(){
						rollor();
					},1000);
				},duration:5000,easing:'linear'});
			}
		}
   }
   var data={fltId:base.fltId,arrdep:base.arrdep}
   ajaxInvoke('/mfd/flt/getDetailsByFltIdCond.do',data,flightDetailSuccess);
   //创建详细信息的列表
   function createDetail(base,details,json)
   {
   		var bltCnt='';
   		var bltotGatdisp='';
   		var taxiSecurity='';
   		var arrdep=base.arrdep;
   		var guideStr='';
   		if(arrdep=='A')
   		{
   			bltCnt=details['bltDisp'];
   			bltotGatdisp=details['firstBltOt'];
   			taxiSecurity=details['taxiWait'];
   			guideStr='交通出行引导';
   		}else if(arrdep=='D')
   		{
   			bltCnt=details['cntDisp'];
   			bltotGatdisp=details['securityState'];
   			taxiSecurity=details['gatDisp'];
   			guideStr='登机引导';
   		}
   		var str='';
   		if(details.follow)
   		{
   			str='取消关注';
   		}else
   		{
   			str='关注';
   		}
   		var oLi=$('<li class="cur detail-in">'+
						'<div class="title clearfix">'+
							'<i class="logo fl"></i>'+
							'<p class="name fl">'+base['airlineNameCn']+base['fltNo']+'</p>'+
						'</div>'+
						'<div class="sub-title clearfix">'+
							'<div class="share fl">'+details['relFltDesc']+':</div>'+
			'<div class="share-flight fl clearfix">'+
				'<div class="share-flight-cont fl">'+details['relFltNos']+'</div>'+
			'</div>'+
						'</div>'+
						'<div class="detail">'+
							'<div class="text clearfix">'+
								'<p class="fl">'+details['startTimeName']+'</p>'+
								'<p class="fr">'+details['destTimeName']+'</p>'+
							'</div>'+
							'<div class="time clearfix">'+
								'<p class="fl">'+details['startTime']+'</p>'+
								'<p class="status fl">'+base['stateCn']+'</p>'+
								'<p class="fr">'+details['destTime']+'</p>'+
							'</div>'+
							'<div class="plan clearfix">'+
								'<p class="fl">计划 '+base['startSdt']+'</p>'+
								'<p class="fr">计划 '+base['destSdt']+'</p>'+
							'</div>'+
							'<div class="weather clearfix">'+
								'<p class="fl clearfix">'+
									'<i class="cloud fl"></i>'+
									'<i class="fl text">-4℃</i>'+
								'</p>'+
								'<p class="fl clearfix">'+
									'<i class="sun fl"></i>'+
									'<i class="fl text">12℃</i>	'+		
								'</p>'+
							'</div>'+
							'<div class="addr clearfix">'+
								'<p class="fl">'+base['startAirportCn']+'</p>'+
								'<p class="fr">'+base['destAirportCn']+'</p>'+
							'</div>'+
						'</div>'+
						'<div class="open-cont show">'+
							'<ul class="clearfix">'+
								'<li>'+
									'<p class="time">'+bltCnt+'</p>'+
									'<p class="text">'+json.first+'</p>'+
								'</li>'+
								'<li>'+
									'<p class="time security">'+bltotGatdisp+'</p>'+
									'<p class="text">'+json.second+'</p>'+
								'</li>'+
								'<li>'+
									'<p class="time">'+taxiSecurity+json.labelStr+'</p>'+
									'<p class="text">'+json.third+'</p>'+
								'</li>'+
							'</ul>'+
							'<div class="traffic">'+
							guideStr+
							'</div>'+
							'<div class="follow" data-conid='+details.contractId+' data-follow='+details.follow+' data-fltId='+base['fltId']+' data-arrdep='+base['arrdep']+'>'+
								str+
							'</div>'+
						'</div>'+	
					'</li>');
			oLi.appendTo($('.box .list ul'));
			if(details['relFltDesc']=='')
			{
				oLi.find('.sub-title').hide();
			}
			oLi.find('.logo').css({
				'background':'url(images/'+base['iata']+'.gif) no-repeat',
				'backgroundSize':'cover'
			});
			oLi.find('.status').css('color',base['stateColor']);
			oLi.find('.security').css('color',details['securityColor']);
   }
   //关注
   function followSuccess(data)
   {
   		if(data.success)
   		{
   			bFlag=true;
    		$('.follow').html('取消关注');
    		$('.follow').attr({'data-follow':'true','data-conid':data.msg});
   		}
   }
   //取消关注
   function unFollowSuccess(data)
   {
   		if(data.success)
   		{
   			bFlag=true;
    		$('.follow').html('关注');
    		$('.follow').attr({'data-follow':'false','data-conid':data.msg});
   		}
   }
   //后退
	$('.back').on('click',function(){
		window.history.back();
	});
	//获取实时消息
	clearInterval(timer);
	messageSign();
	timer=setInterval(messageSign,10000);
});