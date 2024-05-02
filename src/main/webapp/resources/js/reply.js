/**
*
*/

console.log("Reply Module......");

const replyService=(function(){

   function add(reply,callback, error){
       console.log("add reply............");

       $.ajax({
           type: 'post',
           url: '/replies/new',
           data: JSON.stringify(reply),
           contentType: "application/json; charset=UTF-8",
           success: function(result, status, xhr){
               if(callback) {
                   callback(result);
                 }
           },
           error: function(xhr, status, er){
                if (error){
                   error(er);
                }
           }
       })
   }	/* end of add*/
   
   
   
   /* $.getJSON(url, callback 함수).fail */
   
   function getList(param, callback, error){
     const bno = param.bno;
     const page = param.page || 1; /*param.page == true이면(=값이들어있으면) param.page 아니면 (=null이나 0이면) 1*/

        /*$.getJSON(url,callback함수).fail*/
     $.getJSON("/replies/pages/" + bno + "/" + page,
             function(data){
               if(callback){
                 //callback(data); // 댓글의 목록만 가져오는경우
                 callback(data.replyCnt,data.list); //댓글의 갯수와 목록을 가져옴
               }
            }
     ).fail(function(xhr, status, err){
       if(error){error(err) }
       }); 
     }/*end of getList*/
   	
   	
   	function remove(rno, callback, error){
   	
   	
   	$.ajax({
   	type:'delete',
   	url: '/replies/'+rno,
   	success: function(deleteResult, status, xhr){
   	if(callback){
   		callback(deleteResult);
   		}
   	}, /* success end*/
   	error: function(xhr, status, er){
   	if(error){
   		error(er)
   		}
   		}
   		});
   		}
   		
   	function update(reply, callback, error){
   	console.log("....................update...RNO:"+reply.rno);
   	
   	$.ajax({
   		type: 'put',
   		url:'/replies/'+reply.rno,
   		data: JSON.stringify(reply),
   		contentType:"application/json; charset=utf-8",
   		success:function(result, status, xhr){
   		if(callback){
   			callback(result);
   			}
   		},
   		error: function(xhr, status, er){
   			if(error){
   				error(er);
   				}
   				}
   				});
   				}
   				
   	function get(rno, callback, error){
   	$.getJSON("/replies/"+rno, function(result){
   		if(callback){callback(result);}
   		}
   		).fail(function(xhr, status, err){
   		if(error){error();}
   		});
   		}
   		
   function displayTime(timeValue){
     const today = new Date();
     const gap = today.getTime() - timeValue;
     const dateObj = new Date(timeValue);

     if(gap < (1000 * 60 * 60 * 24)) {
         const hh = dateObj.getHours().toString().padStart(2,'0'); /* javascript에서 기본적으로 제공해주는 메서드*/
         const mm = dateObj.getMinutes().toString().padStart(2,'0');
         const ss = dateObj.getSeconds().toString().padStart(2,'0');
         return hh + ":" + mm + ":" + ss;
     }else{
         const yy = dateObj.getFullYear().toString().padStart(2,'0');
         const MM = (dateObj.getMonth() + 1).toString().padStart(2,'0');
         const dd = dateObj.getDate().toString().padStart(2,'0');
         return yy + "-" + MM + "-" + dd;
     }
 }
   	
   	
   return {add : add,
   			getList:getList,
   			remove: remove,
   			update:update,
   			get:get,
   			displayTime:displayTime};

})();