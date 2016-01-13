$(function(){
	var oList=$('.list');
	var oBox=$('.box');
	oBox.css({
		'height':'24.5rem',
		'overflow':'hidden',
		'position':'absolute',
		'left':0,
		'top':'2.5rem',
		'width':'16rem'
	});
	oList.css({
		'position':'absolute',
		'left':0,
		'top':0
	});
	var minTop=oList[0].scrollHeight-$(oBox).innerHeight();
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
					}else if(y<-minTop)
					{
						y=-minTop;
					}
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
	$('.list ul li').on('touchstart',function(){
		window.open('detail_in.html');
	});
});