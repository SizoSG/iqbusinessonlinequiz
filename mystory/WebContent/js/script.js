var app = angular.module('myApp', []);

app.controller('appCtrl', function ($scope, $http, $window, StoreService) {


$scope.showPage = function (viewName) {
//        alert('hellox');
        $scope.view = viewName;
    };


    $scope.view = 'login';


    var admin = "admin";

    $scope.login = function () {

        $scope.user = this.user;
        if ($scope.user.password) {

            $http({
                method: "post",
                url: "http://localhost:8080/mystory/rest/quiz/login/",
                data: $scope.user,
                headers: {'Content-Type': 'application/json'}
            }).then(function (response)
            {
                $scope.user = response.data;
                //debugger;
                if ($scope.user.name) {
                    StoreService.set('user', $scope.user);
                    $scope.user.islogin = true;
                    if ($scope.user.usertype == admin) {
                        
                        $scope.view = "admindashboard";
                    } else {
                        
                    }
                }
                //$scope.message = "Login failed"
            });
        } else {
            $scope.message = "username or password is empty failed";
        }
    };



    $scope.register = function () {
    	 var idnumber = $('#idn').val();
   	  // the anatomy of an RSA ID Number : http://warwickchapman.com/the-anatomy-of-an-rsa-id-number
   	  // structure: (YYMMDD GSSS CAZ)

   	 // var idnumber = $('#idn').val(),
   	    invalid = 0;

   	  // display debugging
   	//  var debug = $('#debug');

   	  // check that value submitted is a number
   	  if (isNaN(idnumber)) {
   	     alert('Value supplied is not a valid number.<br />');
   	    invalid++;
   	    return;
   	  }

   	  // check length of 13 digits
   	  if (idnumber.length !== 13) {
   	    alert('Number supplied does not have 13 digits.<br />');
   	    invalid++;
   	    return;
   	    
   	  }

   	  // check that YYMMDD group is a valid date
   	  var yy = idnumber.substring(0, 2),
   	    mm = idnumber.substring(2, 4),
   	    dd = idnumber.substring(4, 6);

   	  var dob = new Date(yy, (mm - 1), dd);

   	  // check values - add one to month because Date() uses 0-11 for months
   	  if (!(((dob.getFullYear() + '').substring(2, 4) == yy) && (dob.getMonth() == mm - 1) && (dob.getDate() == dd))) {
   	    alert('Date in first 6 digits is invalid.<br />');
   	    invalid++;
   	    return;
   	  }

   	  // evaluate GSSS group for gender and sequence 
   	  var gender = parseInt(idnumber.substring(6, 10), 10) > 5000 ? "M" : "F";

   	  // ensure third to last digit is a 1 or a 0
   	  if (idnumber.substring(10, 11) > 1) {
   	    alert('Third to last digit can only be a 0 or 1 but is a ' + idnumber.substring(10, 11) + '.<br />');
   	    invalid++;
   	    return;
   	  } else {
   	    // determine citizenship from third to last digit (C)
   	    var saffer = parseInt(idnumber.substring(10, 11), 10) === 0 ? "C" : "F";
   	  }

   	  // ensure second to last digit is a 8 or a 9
   	  if (idnumber.substring(11, 12) < 8) {
   	    alert('Second to last digit can only be a 8 or 9 but is a ' + idnumber.substring(11, 12) + '.<br />');
   	    invalid++;
   	    return;
   	  }

   	  // calculate check bit (Z) using the Luhn algorithm
   	  var ncheck = 0,
   	    beven = false;

   	  for (var c = idnumber.length - 1; c >= 0; c--) {
   	    var cdigit = idnumber.charAt(c),
   	      ndigit = parseInt(cdigit, 10);

   	    if (beven) {
   	      if ((ndigit *= 2) > 9) ndigit -= 9;
   	    }

   	    ncheck += ndigit;
   	    beven = !beven;
   	  }

   	  if ((ncheck % 10) !== 0) {
   	    debug.append('Checkbit is incorrect.<br />');
   	    invalid++;
   	    return;
   	  }

   	  // if one or more checks fail, display details
   	  if (invalid > 0) {
   	    debug.css('display', 'block');
   	  }

   	 // return (ncheck % 10) === 0;
   	
    	
        $http({
            method: "post",
            url: "http://localhost:8080/mystory/rest/quiz/register/",
            data: $scope.user,
            headers: {'Content-Type': 'application/json'}
        }).then(function (response)
        {
            $scope.number = response.data;
            //debugger;
            if ($scope.number === 1) {
                //$scope.message1 = "User Added Succefully";
                $window.location.href = 'welcome.html';
            } else {
                $scope.message1 = "User Not Added Successfully";
            }
        });
    };



 $scope.viewUsers = function () {
       // $scope.questions.employeenumber = $scope.user.employeenumber;
        $scope.userlist = [];
        $http({
            method: "get",
            url: "http://localhost:8080/mystory/rest/quiz/viewUsers/",
            data: $scope.questions,
            headers: {'Content-Type': 'application/json'}
        }).then(function (response)
        {
             $scope.userlist = response.data;
            //debugger;
                $scope.view = "users";
        });
    };
   
});


app.service('StoreService', function () {

    var store = {};

    this.set = function (type, newObj) {
        store[type] = newObj;
    };

    this.get = function (type) {
        return store[type];
    };
});


app.factory('myService', function () {
    var savedData = {}
    function set(data) {
        savedData = data;
    }
    function get() {
        return savedData;
    }

    return {
        set: set,
        get: get
    }

});