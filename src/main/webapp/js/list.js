$(function(){
    var str=window.location.search;
    //pageNo=1&code=HGH&arrdep=1&type=0&airlineCode=&queryDate=1452684775440
    /*var json={};
    var arr=str.split('&');
    for(var i=0; i<arr.length; i++)
    {
    	var arrTmp=arr[i].split('=');
    	json[arrTmp[0]]=arrTmp[1];
    }
    console.log(json);*/
    if(str.indexOf('fltNo')!=-1)
    {
	    $.ajax({
	    	url:'/mfd/flt/getByFltNoCond.do'+str,
	    	success:function(data)
	    	{
	    		createList(data.rst);
	    		$('.list ul li').on('click',function(){
	    			var str=$(this).attr('data-detail');
	    			var arrdep=$(this).attr('data-arrdep');
	    			if(arrdep=='A')
	    			{
	    				window.open('detail_in.html?'+str);
	    			}else if(arrdep=='D')
	    			{
	    				window.open('detail_out.html?'+str);
	    			}
					
				});
	    	}
	    });
    }else
    {
    	$.ajax({
    		url:'/mfd/flt/getByPlaceCond.do'+str,
	    	success:function(data)
	    	{
	    		createList(data.rst);
	    		$('.list ul li').on('click',function(){
	    			var str=$(this).attr('data-detail');
					var arrdep=$(this).attr('data-arrdep');
	    			if(arrdep=='A')
	    			{
	    				window.open('detail_in.html?'+str);
	    			}else if(arrdep=='D')
	    			{
	    				window.open('detail_out.html?'+str);
	    			}
				});
	    	}
	    });
    }
	//创建列表
	function createList(arr)
	{
		for(var i=0; i<arr.length; i++)
		{
			var json=arr[i];
			var sClass=statusCheck(json['stateCn']);
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
							'<p class="status '+sClass+'">'+json['stateCn']+'</p>'+
						'</div></li>');
			oLi.appendTo($('.box .list>ul'));
			oLi.find('.logo').css({
				'background':'url(images/'+json['iata']+'.png) no-repeat',
				'backgroundSize':'cover'
			});
		}
	}
	//判断状态
	function statusCheck(status)
	{
		var sClass='';
		switch(status){
			case '已起飞':
				sClass='green';
				break;
			case '延误':
				sClass='red';
				break;
			case '未开始值机':
				sClass='yellow';
				break;
			case '开始登机':
				sClass='green';
				break;
			defaule:
				sClass='';
		}
		return sClass
	}
	var oList=$('.list');
	var oBox=$('.box');
	oBox.css({
		'height':'24.5rem',
		'overflow':'hidden',
		'position':'relative'
	});
	oList.css({
		'position':'absolute',
		'left':0,
		'top':'2.5rem',
	});
	var minTop=oList.height()-$(oBox).innerHeight();
	drag(oList[0]);
	function drag(oDiv){
		var y=0;
		oDiv.addEventListener('touchstart',function(ev){
			var disY=ev.targetTouches[0].pageY-y;
			var id=ev.targetTouches[0].identifier;
			
			function fnMove(ev){
				if(ev.targetTouches[0].identifier==id){
					y=ev.targetTouches[0].pageY-disY;
					if(y>0)
					{
						y=0;
					}else if(y<-Math.abs(minTop))
					{
						y=-Math.abs(minTop);
					}
					console.log(y);
					oDiv.style.WebkitTransform='translate(0,'+y+'px)';
					ev.preventDefault();	
				}
			}
			function fnEnd(ev){
				if(ev.changedTouches[0].identifier==id){
					document.removeEventListener('touchmove',fnMove,false);
					document.removeEventListener('touchend',fnEnd,false);		
				}
			}			
			document.addEventListener('touchmove',fnMove,false);			
			document.addEventListener('touchend',fnEnd,false);
		},false);
	}
	
});