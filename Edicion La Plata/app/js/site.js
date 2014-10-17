$(function() {
	$("#accordion").accordion({
		header : "h3"
	});

	$('#tabs').tabs();

	$('#dialog_link, ul#icons li').hover(function() {
		$(this).addClass('ui-state-hover');
	}, function() {
		$(this).removeClass('ui-state-hover');
	});

});