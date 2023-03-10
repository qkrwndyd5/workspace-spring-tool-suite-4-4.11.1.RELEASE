
import * as View from "./view.js";
//import {render as Render} from "./view.js";
//import {render} from "./view.js";
import * as Service from "./service.js"
import * as URL from "./request-url.js";

/*********request-url.js******** */
const GUEST_LIST_URL = "guest/guest_list_json.jsp";
const GUEST_DETALE_URL = "guest/guest_detail_json.jsp";
const GUEST_WRITE_ACTION_URL = "guest/guest_write_action_json.jsp";
const GUEST_MODIFY_FORM_URL = "guest/guest_modify_form_json.jsp";
const GUEST_MODIFY_ACTION_URL = "guest/guest_modify_action_json.jsp";
const GUEST_REMOVE_ACTION_URL = "guest/guest_remove_action_json.jsp";
/******************************* */
/*
메뉴객체이벤트등록
*/
const menuGuestHome = document.querySelector('#menu_guest_home');
const menuGuestList = document.querySelector('#menu_guest_list');
const menuGuestWriteFome = document.querySelector('#menu_guest_write_form');

menuGuestHome.addEventListener(`click`,function(e){
	View.render("#guest-main-template",{},"#content");
	e.preventDefault();
	/*
	let templateHtml = document.querySelector('#guest-main-template');
	document.querySelector('#content').innerHTML = templateHtml.innerHTML;
	*/			
	
});

menuGuestList.addEventListener(`click`,function(e){
	let jsonResult = Service.guestService('GET',URL.GUEST_LIST_URL,'');
	
	View.render("#guest-list-template",jsonResult,"#content");
	e.preventDefault();
});

menuGuestWriteFome.addEventListener(`click`,function(e){
	View.render("#guest-write-form-template",{},"#content");
	e.preventDefault();
});

/*
초기로딩시에 home anchor click event trigger
*/
//menuGuestHome.click();
document.addEventListener(`click`,function(e) {
	/*
	Element속성
	*/
	console.log("Event객체:"+e);
	console.log("Event Target 객체:"+e.target);
	console.log("Event Target 객체 id:"+e.target.id);
	console.log("Event Target 객체 id:"+e.target.getAttribute("id"));
	console.log("Event Target 객체 className:"+e.target.className);
	console.log("Event Target 객체 classList:"+e.target.classList);
	console.log("Event Target 객체 classList.contains('guest_item_a'):"+e.target.classList.contains('guest_item_a'));
	/***********************guest_detail***********************/
	if(e.target.classList.contains('guest_item_a')) {
		let params = 'guest_no='+e.target.getAttribute("guest_no");
		let jsonResult = Service.guestService('GET',URL.GUEST_DETALE_URL,params);
		View.render("#guest-detail-template",jsonResult,"#content");
	}
	/***********************guest_delete_action***********************/
	if(e.target.id == 'btn_guest_remove_action') {
		let params = 'guest_no='+e.target.getAttribute("guest_no");
		let jsonResult = Service.guestService('POST',URL.GUEST_REMOVE_ACTION_URL,params);
		if(jsonResult.code == 1) {
			menuGuestList.click();
		}else {
			alert(jsonResult.msg);
		}
	}
	
	
		
	e.preventDefault();		
	
});

