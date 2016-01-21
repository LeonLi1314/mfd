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
				/*ajaxInit('sbc/unfollowFlight.do?'+'fltId='+fltId+'&arrdep='+arrdep+'&contractId='+contractId,function(data){
					follow(data,'关注','false');
				});*/
				ajaxInvoke('sbc/unfollowFlight.do',data,function(data){
					follow(data,'关注','false');
				});

			}else
			{
				/*ajaxInit('sbc/followFlight.do?'+'fltId='+fltId+'&arrdep='+arrdep,function(data){
					follow(data,'取消关注','true');
				})*/
				var data={fltId:fltId,arrdep:arrdep};
				ajaxInvoke('sbc/followFlight.do',data,function(data){
					follow(data,'取消关注','true');
				});
			}
		}    		
	}
   //ajax请求成功函数
   function flightDetail(details)
   {
		if(base.arrdep=='A')
		{
			createDetail(base,details,{first:'行李转盘',second:'转盘开放时间',third:'出租车等待时长'});
		}else if(base.arrdep=='D')
		{
			createDetail(base,details,{first:'值机柜台',second:'安检等待时长',third:'登机口'});
		}
		$('.follow').off('click',followClickEvent).on('click',followClickEvent);
   }
   var data={fltId:base.fltId,arrdep:base.arrdep}
   ajaxInvoke('/mfd/flt/getDetailsByFltIdCond.do',data,flightDetail);
   function createDetail(base,details,json)
   {
   		console.log(details);
   		var bltCnt='';
   		var bltotGatdisp='';
   		var taxiSecurity='';
   		var arrdep=base.arrdep;
   		if(arrdep='A')
   		{
   			bltCnt=details['bltDisp'];
   			bltotGatdisp=details['firstBltOt'];
   			taxiSecurity=details['taxiWait'];
   		}else
   		{
   			bltCnt=details['cntDisp'];
   			bltotGatdisp=details['gatDisp'];
   			taxiSecurity=details['securityDur'];
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
						'<div class="sub-title">'+
							'<p>'+details['relFltDesc']+'：'+details['relFltNos']+'</p>'+
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
									'<i class="fl text">-4/12℃</i>'+
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
									'<p class="time">'+bltotGatdisp+'</p>'+
									'<p class="text">'+json.second+'</p>'+
								'</li>'+
								'<li>'+
									'<p class="time">'+taxiSecurity+' <i>分钟</i></p>'+
									'<p class="text">'+json.third+'</p>'+
								'</li>'+
							'</ul>'+
							'<div class="traffic">'+
							'交通出行引导'+
							'</div>'+
							'<div class="follow" data-conid='+details.contractId+' data-follow='+details.follow+' data-fltId='+base['fltId']+' data-arrdep='+base['arrdep']+'>'+
								str+
							'</div>'+
						'</div>'+	
					'</li>');
			oLi.appendTo($('.box .list ul'));
			if(details['relFltDesc']==undefined)
			{
				oLi.find('.sub-title').hide();
			}
			oLi.find('.logo').css({
				'background':'url(images/'+base['iata']+'.gif) no-repeat',
				'backgroundSize':'cover'
			});
			oLi.find('.status').css('color',base['stateColor']);
   }
   function follow(data,str,sFlag)
   {
   		if(data.success)
   		{
   			bFlag=true;
    		$('.follow').html(str);
    		$('.follow').attr({'data-follow':sFlag,'data-conid':data.msg});
   		}
   }
   //后退
	$('.back').on('click',function(){
		window.history.back();
	});
});