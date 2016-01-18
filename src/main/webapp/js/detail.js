$(function(){
   var str=window.location.search.substring(1);
   //console.log(decodeURIComponent(str));
   var base=eval('('+decodeURIComponent(str)+')')
   console.log(base);
   //var search=
   $.ajax({
    	url:'/mfd/flt/getDetailsByFltIdCond.do?'+'fltId='+base.fltId+'&arrdep='+base.arrdep,
    	success:function(details)
    	{
    		//createDetail(base,details);
    		if(base.arrdep=='A')
    		{
    			createDetailIn(base,details);
    		}else if(base.arrdep=='D')
    		{
    			createDetailOut(base,details);
    		}
    	}
    });
   function createDetailIn(base,details)
   {
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
								'<p class="take-off status fl">已起飞</p>'+
								'<p class="fr">'+details['destTime']+'</p>'+
							'</div>'+
							'<div class="plan clearfix">'+
								'<p class="fl">计划 '+details['startSdt']+'</p>'+
								'<p class="fr">预计 '+details['destSdt']+'</p>'+
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
									'<p class="time">'+details['bltDisp']+'</p>'+
									'<p class="text">行李转盘</p>'+
								'</li>'+
								'<li>'+
									'<p class="time">'+details['firstBltOt']+'<i>分钟</i></p>'+
									'<p class="text">转盘第一开放时间</p>'+
								'</li>'+
								'<li>'+
									'<p class="time">32 <i>分钟</i></p>'+
									'<p class="text">出租车等待时长</p>'+
								'</li>'+
							'</ul>'+
							'<div class="traffic">'+
							'交通出行引导'+
							'</div>'+
							'<div class="attention">'+
								'取消关注'+
							'</div>'+
						'</div>'+	
					'</li>');
			oLi.appendTo($('.box .list ul'));
   }
   function createDetailOut(base,details)
   {
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
								'<p class="take-off status fl">已起飞</p>'+
								'<p class="fr">'+details['destTime']+'</p>'+
							'</div>'+
							'<div class="plan clearfix">'+
								'<p class="fl">计划 '+details['startSdt']+'</p>'+
								'<p class="fr">预计 '+details['destSdt']+'</p>'+
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
									'<p class="time">'+details['cntDisp']+'</p>'+
									'<p class="text">值机柜台</p>'+
								'</li>'+
								'<li>'+
									'<p class="time">'+details['securityDur']+'<i>分钟</i></p>'+
									'<p class="text">安检等待时长</p>'+
								'</li>'+
								'<li>'+
									'<p class="time">'+details['gatDisp']+'<i>分钟</i></p>'+
									'<p class="text">登机口</p>'+
								'</li>'+
							'</ul>'+
							'<div class="traffic">'+
							'交通出行引导'+
							'</div>'+
							'<div class="attention">'+
								'取消关注'+
							'</div>'+
						'</div>'+	
					'</li>');
			oLi.appendTo($('.box .list ul'));
   }
});