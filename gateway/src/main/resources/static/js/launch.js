var global = {
    mobileClient: false,
    savePermit: true,
    usd: 0,
    eur: 0
};

/**
 * Oauth2
 */

function requestOauthToken(username, password) {

	var success = false;

	$.ajax({
		url: 'uaa/oauth/token',
		datatype: 'json',
		type: 'post',
		headers: {'Authorization': 'Basic YnJvd3Nlcjo='},
		async: false,
		data: {
			scope: 'ui',
			username: username,
			password: password,
			grant_type: 'password'
		},
		success: function (data) {
			localStorage.setItem('token', data.access_token);
			success = true;
		},
		error: function () {
			removeOauthTokenFromStorage();
		}
	});

	return true;
}

function getOauthTokenFromStorage() {
	return localStorage.getItem('token');
}

function removeOauthTokenFromStorage() {
    return localStorage.removeItem('token');
}

/**
 * Current account
 */

function getCurrentAccount(username) {

	var token = getOauthTokenFromStorage();
	var account = null;

	//if (token) {
		$.ajax({
			url: 'account-service2/'+username,
			datatype: 'json',
			type: 'get',
			async: false,
			success: function (data) {
				console.log(data)
				account = data
				initAccount(account)
			},
			error: function () {
				removeOauthTokenFromStorage();
			}
		});
	//}

	return account;
}

$(window).load(function(){

	if(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
		FastClick.attach(document.body);
        global.mobileClient = true;
	}

    $.getJSON("https://api.exchangeratesapi.io/latest?base=RUB&symbols=EUR,USD", function( data ) {
        global.eur = 1 / data.rates.EUR;
        global.usd = 1 / data.rates.USD;
    });

	var account;

	if (account) {
		showGreetingPage(account);
	} else {
		showLoginForm();
	}
});

function showGreetingPage(account) {
    initAccount(account);
	var userAvatar = $("<img />").attr("src","images/userpic.jpg");
	$(userAvatar).load(function() {
		setTimeout(initGreetingPage, 500);
	});
}

function showLoginForm() {
	$("#loginpage").show();
	$("#frontloginform").focus();
	setTimeout(initialShaking, 700);
}